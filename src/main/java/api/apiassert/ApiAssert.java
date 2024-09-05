package api.apiassert;


import okhttp3.Response;
import org.assertj.core.api.AbstractAssert;

public class ApiAssert extends AbstractAssert<ApiAssert, Response> {

    public ApiAssert(Response actualPojoResponse) {
        super(actualPojoResponse, ApiAssert.class);
    }

    public static ApiAssert assertThat(Response pojoRespons) {
        return new ApiAssert(pojoRespons);
    }

    public ApiAssert bodyNotNull() {
        if(actual.body() == null) {
            failWithMessage("Body should not be null");
        }
        return this;
    }

    public ApiAssert isResponseSuccessful() {
        if(!actual.isSuccessful()) {
            failWithMessage("Response should start from '2'");
        }
        return this;
    }

}
