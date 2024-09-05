package okhttp3.restApi.unknownService;

import api.reqres.pojo.users.PostUserPojo;
import api.reqres.pojo.users.RootUsersPojo;
import api.reqres.retrofit2.service.users.ReqResServiceFactory;
import api.reqres.retrofit2.service.users.UsersService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@Epic("Rest API suits")
@Feature("Suit for https://reqres.in '/users.*' service")
@DisplayName("Suit for https://reqres.in '/users.*' service")
class RequestResponseTest1 {

    @Test
    @Story("GET /api/users?page={page}")
    @DisplayName("In response body each 'Avatar' should contains 'Id'")
    @Tag("RestAPI")
    void eachAvatarContainsIdForUsersOnPageTest() {
        Response<RootUsersPojo> usersOnPage = ReqResServiceFactory
                .createServiceForSuccessResponse(UsersService.class)
                .getUsersOnPage(2);

        checkInEachUserAvatarContainsId(usersOnPage);
    }

    @Step("Check in each user 'avatar' contains 'id'")
    private void checkInEachUserAvatarContainsId(Response<RootUsersPojo> users) {
        assertThat(users.body().getData())
                .as("Users 'user.avatar', 'user.id' not null, empty and 'user.avatar' contains 'user.id'")
                .filteredOn(user ->
                        (user.getAvatar() != null && user.getAvatar().isEmpty()) &&
                                (user.getId() != null && user.getId().toString().isEmpty()) &&
                                (user.getAvatar().contains(user.getId().toString())))
                .isEmpty();
    }

    @Test
    @Story("POST /api/users")
    @DisplayName("In response body generated 'id' and 'createdAt'")
    @Tag("RestAPI")
    void postRequestGenerateNewUserWithUniqueIdAndCreatedAtTest() {
        Response<PostUserPojo> userPostResponse = ReqResServiceFactory
                .createServiceForSuccessResponse(UsersService.class)
                .postUser(PostUserPojo.builder().name("morpheus").job("leader").build());
        checkGenerateIdAndCreatedAt(userPostResponse);
    }

    @Step("Check post request generated user with unique 'id' and 'createdAt'")
    private void checkGenerateIdAndCreatedAt(Response<PostUserPojo> user) {
        assertThat(user.body())
                .as(String.format("User not generated field '%s'", user.body()))
                .hasNoNullFieldsOrProperties();
    }
}
