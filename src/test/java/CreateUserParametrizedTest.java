import client.UserApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CreateUserParametrizedTest {
    private UserApiClient userApiClient = new UserApiClient();
    private User user;
    private final String email;
    private final String password;
    private final String name;
    private final String errorMessage;

    public CreateUserParametrizedTest(String email, String password, String name, String errorMessage) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.errorMessage = errorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] dataForTest() {
        return new Object[][]{
            {"", "yidjsvc", "fjlshdh", "Email, password and name are required fields"},
            {"hdjlj@khk.jk", "", "hkhdjg", "Email, password and name are required fields"},
            {"hjhjh@bj,Oi", "osjkbv8936", "", "Email, password and name are required fields"},
            {"", "", "", "Email, password and name are required fields"},
        };
    }

    @Test
    @DisplayName("test skip required field ")
    public void creareUserWithoutRequiredFieldTests() {
        this.user = new User();
        Response response = userApiClient.createUser(this.user);
        response.then().statusCode(403).body("message", equalTo("Email, password and name are required fields"));
    }
}
