package api.reqres;

public class ReqresApiException extends RuntimeException {

    public ReqresApiException(String message, Throwable e, String... args) {
        super(String.format(message, args), e);
    }
}
