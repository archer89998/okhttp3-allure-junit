package api.reqres.retrofit2.service.users;

import api.reqres.okhttp3.Okhtp3SuccessInterceptor;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReqResServiceFactory {

    private ReqResServiceFactory() {}

    private static final String BASE_URL = "https://reqres.in";
    private static final OkHttpClient.Builder okHttpClientBulder
            = new OkHttpClient
            .Builder()
            .addInterceptor(new Okhtp3SuccessInterceptor())
            .addInterceptor(new AllureOkHttp3());

    private static final OkHttpClient okHttpClient = okHttpClientBulder.build();

    private static final Retrofit.Builder retrofitBuilder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .client(okHttpClient);

    private static final Retrofit retrofitClientForSuccessResponse = retrofitBuilder.build();

    public static <S> S createServiceForSuccessResponse(Class<S> serviceClass) {
        return retrofitClientForSuccessResponse.create(serviceClass);
    }

}
