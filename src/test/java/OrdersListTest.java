import client.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrdersListTest {
    private OrdersApiClient ordersApiClient = new OrdersApiClient();
    private UserApiClient userApiClient = new UserApiClient();
    private String accessToken;

    @Before
    public void prepare() {
        User newUser = new User("testemail@testmail.ru", "testpassword", "Test User");

        Response createUserRawResponse = this.userApiClient.createUser(newUser);
        CreateUserResponse createUserResponse = createUserRawResponse.body().as(CreateUserResponse.class);
        this.accessToken = createUserResponse.accessToken;
    }

    @After
    public void cleanup() {
        if (this.accessToken != null) {
            this.userApiClient.deleteUser(this.accessToken);
        }
    }

    @Test
    @DisplayName("Get authorized user orders")
    public void getAuthorizedUserOrdersTest() {
        Response getOrdersResponse = ordersApiClient.getOrders(this.accessToken);
        GetOrdersResponse ordersResult = getOrdersResponse.body().as(GetOrdersResponse.class);

        Assert.assertEquals(ordersResult.success, true);
        Assert.assertNotNull(ordersResult.total);
        Assert.assertNotNull(ordersResult.totalToday);
        Assert.assertNotNull(ordersResult.orders);
    }

    @Test
    @DisplayName("Get not authorized user orders")
    public void getNotAuthorizedUserOrdersTest() {
        Response getOrdersResponse = ordersApiClient.getOrders();
        GetOrdersResponse ordersResult = getOrdersResponse.body().as(GetOrdersResponse.class);

        Assert.assertEquals(ordersResult.success, false);
        Assert.assertEquals(ordersResult.message, "You should be authorised");
    }
}
