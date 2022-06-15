package client;

import io.restassured.response.Response;
import models.Ingredient;

import java.util.ArrayList;

public class OrdersApiClient extends BaseHttpClient {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api";

    public Response createOrder(ArrayList<Ingredient> ingredients) {
        ArrayList<String> ids = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            ids.add(ing._id);
        }

        CreateOrderRequest request = new CreateOrderRequest(ids);
        return doPostRequest(baseUrl + "/orders", request);
    }

    public Response createOrder(ArrayList<Ingredient> ingredients, String accessToken) {
        ArrayList<String> ids = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            ids.add(ing._id);
        }

        CreateOrderRequest request = new CreateOrderRequest(ids);
        return doPostRequest(baseUrl + "/orders", request, accessToken);
    }

    public Response getOrders() {
        return doGetRequest(baseUrl + "/orders");
    }

    public Response getOrders(String accessToken) {
        return doGetRequest(baseUrl + "/orders", accessToken);
    }
}
