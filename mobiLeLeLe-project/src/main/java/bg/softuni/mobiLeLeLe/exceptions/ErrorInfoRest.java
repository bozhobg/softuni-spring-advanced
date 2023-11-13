package bg.softuni.mobiLeLeLe.exceptions;

public class ErrorInfoRest {
    private final String url;
    private final String message;

    public ErrorInfoRest(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
