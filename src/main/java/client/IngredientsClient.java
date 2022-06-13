package client;

import io.restassured.response.Response;

public class IngredientsClient extends BaseHttpClient {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api";

    public Response getIngredients () {
        return doGetRequest(baseUrl + "/ingredients");
    }
}
