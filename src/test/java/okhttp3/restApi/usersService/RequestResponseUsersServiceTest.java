package okhttp3.restApi.usersService;

import com.google.gson.Gson;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.extern.slf4j.Slf4j;
import api.reqres.pojo.users.UserPojo;
import api.reqres.pojo.users.RootUserPojo;
import api.reqres.pojo.users.RootUsersPojo;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@Epic("Rest API suits")
@Feature("Suit for https://reqres.in '/users.*' service")
@DisplayName("Suit for https://reqres.in '/users.*' service")
class RequestResponseUsersServiceTest {
    OkHttpClient httpClient;

    @BeforeEach
    @Step("Before step")
    void setup() {
        //        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
//                .setLevel(HttpLoggingInterceptor.Level.BODY);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(5);
        httpClient = new OkHttpClient.Builder()
//                .addInterceptor(logging)
                .addInterceptor(new AllureOkHttp3())
//                .dispatcher(dispatcher)
                .build();
    }

    private static final String URL = "https://reqres.in";

    @Test
    @Story("/api/users/{page}")
    @DisplayName("In response body each 'Avatar' should contains 'Id'")
    @Tag("RestAPI")
    void eachAvatarContainsIdForUsersOnPageTest() {
        step("/api/users/2");
        step("/api/users?page=2");
        int a = 0;

    }

    @Step("Get")
    public void step(String path) {


        Request request = new Request.Builder()
                .get()
                .url(URL + path)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            UserPojo data;
            if("/api/users/2".contains(path)) {
                data = new Gson().fromJson(response.body().string(), RootUserPojo.class).getData();

            } else {
                data = new Gson().fromJson(response.body().string(), RootUsersPojo.class).getData().get(0);
            }
            assertThat(response.code()).as("Status code is 200").isEqualTo(200);
            assertThat(data).as("POJO checker")
                    .satisfies(s -> {
                        assertThat(s).as("POJO not null").isNotNull();
                        assertThat(s.getId()).as("POJO 'id' is equals").isNotNull().isEqualTo(2);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
