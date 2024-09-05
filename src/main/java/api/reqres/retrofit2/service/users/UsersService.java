package api.reqres.retrofit2.service.users;

import api.reqres.ReqresApiException;
import api.reqres.pojo.users.PostUserPojo;
import api.reqres.pojo.users.RootUsersPojo;
import io.qameta.allure.Step;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.IOException;

public interface UsersService {

    String USERS = "/api/users";
    String USERS_23 = "/api/users/23";

    @GET(USERS)
    Call<RootUsersPojo> getUsersOnPageCall(
            @Query("page") int page);

    @GET(USERS_23)
    Call<RootUsersPojo> getUsers23Call();

    @POST(USERS)
    Call<PostUserPojo> postUserCall(@Body PostUserPojo userData);

    @Step("POST '/api/users'")
    default Response<PostUserPojo> postUser(PostUserPojo userData) {
        Response<PostUserPojo> user;
        try {
            user = postUserCall(userData).execute();
        } catch (IOException e) {
            throw new ReqresApiException("Cannot POST user with body '%s'", e, userData.toString());
        }
        return user;
    }

    @Step("GET '/api/users?page={page}'")
    default Response<RootUsersPojo> getUsersOnPage(int page) {
        Response<RootUsersPojo> users;
        try {
            users = getUsersOnPageCall(page).execute();
        } catch (IOException e) {
            throw new ReqresApiException("Cannot GET users on %s page", e, String.valueOf(page));
        }
        return users;
    }


}
