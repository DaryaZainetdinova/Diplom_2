package client;

import io.qameta.allure.Step;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    private final String JSON = "application/json";

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    @Step("send POST request to \"{url}\"")
    protected Response doPostRequest(String url, Object body) {
        return given().config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(url);
    }

    @Step("send POST request to \"{url}\"")
    protected Response doPostRequest(String url, Object body, String accessToken) {
        return given().config(config)
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .post(url);
    }

    @Step("send DELITE request")
    protected Response doDeleteRequest(String url, String accessToken) {
        return given().config(config)
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .delete(url);
    }

    @Step("send PATCH request")
    protected Response doPatchRequest(String url, Object body, String accessToken) {
        return given().config(config)
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .body(body)
                .patch(url);
    }

    @Step("send PATCH request without authorization")
    protected Response doPatchRequest(String url, Object body) {
        return given().config(config)
                .header("Content-Type", JSON)
                .body(body)
                .patch(url);
    }

    @Step("send GET ")
    protected Response doGetRequest(String url) {
        return given().config(config)
                .header("Content-Type", JSON)
                .get(url);
    }

    @Step("send GET ")
    protected Response doGetRequest(String url, String accessToken) {
        return given().config(config)
                .header("Content-Type", JSON)
                .header("authorization", accessToken)
                .get(url);
    }
}
