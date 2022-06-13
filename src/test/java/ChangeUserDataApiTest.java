import client.CreateUserResponse;
import client.UserApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ChangeUserDataApiTest {
    UserApiClient userApiClient = new UserApiClient();
    User user;
    String accessToken;

    @Before
    public void createUser() {
        this.user = new User("moydey@j9jkj.uj", "6666666", "Moisey89");
        Response response = userApiClient.createUser(this.user);
        CreateUserResponse res = response.body().as(CreateUserResponse.class);
        this.accessToken = res.accessToken;
    }

    @After
    public void cleanUp() {
        userApiClient.deleteUser(this.accessToken);
    }

    @Test
    @DisplayName("Change user name with auth")
    public void ChangeUserNameAuthTest() {
        User user = new User(this.user.getEmail(), this.user.getPassword(), "Moise7865");
        Response response = userApiClient.changeUserInfo(user, accessToken);
        response.then().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user email with auth")
    public void ChangeUserEmailAuthTest() {
        User user = new User("newemail@jkn.jj", this.user.getPassword(), this.user.getName());
        Response response = userApiClient.changeUserInfo(user, accessToken);
        response.then().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user password with auth")
    public void ChangeUserPasswordAuthTest() {
        User user = new User(this.user.getEmail(), "111111111", this.user.getName());
        Response response = userApiClient.changeUserInfo(user, accessToken);
        response.then().statusCode(200).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user Data without authorization")
    public void changeUserDataWithoutAuthorizationTest() {
        User user = new User("hkkkhd@nmm.op", "1178", "ivan");
        Response response = userApiClient.changeUserInfoWithoutAuthorization(user);
        response.then().statusCode(401).and().body("message", equalTo("You should be authorised"));
    }
}
