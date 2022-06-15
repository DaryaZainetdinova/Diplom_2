package client;

import models.User;
import io.restassured.response.Response;

public class UserApiClient extends BaseHttpClient {
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api";

    public Response createUser(User user) {
        return doPostRequest(baseUrl + "/auth/register", user);
    }

    public Response deleteUser(String acceesToken) {
        return doDeleteRequest(baseUrl + "/auth/user", acceesToken);
    }

    public Response loginUser(User user) {
        return doPostRequest(baseUrl + "/auth/login", user);
    }

    public Response changeUserInfo(User user, String accessToken) {
        return doPatchRequest(baseUrl + "/auth/user", user, accessToken);
    }

    public Response changeUserInfoWithoutAuthorization(User user) {
        return doPatchRequest(baseUrl + "/auth/user", user);
    }
}
