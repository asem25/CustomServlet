package models;

public class ResponseMethod {
    private String req_type;
    private String message;

    public ResponseMethod() {
    }

    public ResponseMethod(String req_type, String message) {
        this.req_type = req_type;
        this.message = message;
    }

    public String getReq_type() {
        return req_type;
    }

    public void setReq_type(String req_type) {
        this.req_type = req_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
