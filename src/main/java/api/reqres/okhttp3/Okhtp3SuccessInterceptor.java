package api.reqres.okhttp3;

import api.apiassert.ApiAssert;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class Okhtp3SuccessInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        ApiAssert.assertThat(response).isResponseSuccessful();
        ApiAssert.assertThat(response).bodyNotNull();

        return response;
    }
}
