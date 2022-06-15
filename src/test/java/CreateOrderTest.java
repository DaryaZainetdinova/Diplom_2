import client.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Ingredient;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CreateOrderTest {
    private IngredientsClient ingredientsClient = new IngredientsClient();
    private OrdersApiClient ordersApiClient = new OrdersApiClient();
    private UserApiClient userApiClient = new UserApiClient();
    private ArrayList<Ingredient> ingredients;
    private String accessToken;

    @Before
    public void prepare() {
        Response response = ingredientsClient.getIngredients();
        GetIngredientsResponse allIngredientsResponse = response.body().as(GetIngredientsResponse.class);
        this.ingredients = allIngredientsResponse.data;
    }

    @After
    public void cleanup() {
        if (this.accessToken != null) {
            this.userApiClient.deleteUser(this.accessToken);
        }
    }

    @Test
    @DisplayName("Create order for authorized user")
    public void createOrderForAuthorizedUserTest() {
        User newUser = new User("testemail@testmail.com", "testpassword", "Test User");

        Response createUserRawResponse = this.userApiClient.createUser(newUser);
        CreateUserResponse createUserResponse = createUserRawResponse.body().as(CreateUserResponse.class);
        this.accessToken = createUserResponse.accessToken;

        ArrayList<Ingredient> appliedIngredients = new ArrayList<>();
        appliedIngredients.add(this.ingredients.get(0));
        appliedIngredients.add(this.ingredients.get(1));

        Response createdOrderResponse = ordersApiClient.createOrder(appliedIngredients, this.accessToken);
        CreateOrderResponse orderResult = createdOrderResponse.body().as(CreateOrderResponse.class);

        Assert.assertEquals(orderResult.success, true);
        Assert.assertNotNull(orderResult.name);
        Assert.assertNotNull(orderResult.order.number);
    }

    @Test
    @DisplayName("Create order for not authorized user")
    public void createOrderForNotAuthorizedUserTest() {
        ArrayList<Ingredient> appliedIngredients = new ArrayList<>();
        appliedIngredients.add(this.ingredients.get(0));
        appliedIngredients.add(this.ingredients.get(1));

        Response createdOrderResponse = ordersApiClient.createOrder(appliedIngredients);
        CreateOrderResponse orderResult = createdOrderResponse.body().as(CreateOrderResponse.class);

        Assert.assertEquals(orderResult.success, true);
        Assert.assertNotNull(orderResult.name);
        Assert.assertNotNull(orderResult.order.number);
    }

    @Test
    @DisplayName("Create order without ingredients")
    public void createOrderWithoutIngredientsTest() {
        Response createdOrderResponse = ordersApiClient.createOrder(new ArrayList<>());
        CreateOrderResponse orderResult = createdOrderResponse.body().as(CreateOrderResponse.class);

        createdOrderResponse.then().statusCode(400);
        Assert.assertEquals(orderResult.success, false);
        Assert.assertEquals(orderResult.message, "Ingredient ids must be provided");
    }

    @Test
    @DisplayName("Create order with wrong ingredient")
    public void createOrderWithWrongIngredientTest() {
        Ingredient wrongIngredient = new Ingredient("some wrong id");
        ArrayList<Ingredient> appliedIngredients = new ArrayList<>();
        appliedIngredients.add(wrongIngredient);

        Response createdOrderResponse = ordersApiClient.createOrder(appliedIngredients);
        createdOrderResponse.then().statusCode(500);
    }

    @Test
    @DisplayName("Create order with two ingredients, with wrong item")
    public void createOrderWithOneWrongIngredientTest() {
        Ingredient wrongIngredient = new Ingredient("some wrong id");
        ArrayList<Ingredient> appliedIngredients = new ArrayList<>();
        appliedIngredients.add(this.ingredients.get(0));
        appliedIngredients.add(wrongIngredient);

        Response createdOrderResponse = ordersApiClient.createOrder(appliedIngredients);
        createdOrderResponse.then().statusCode(500);
    }
}
