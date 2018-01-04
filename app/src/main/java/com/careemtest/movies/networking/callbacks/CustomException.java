package com.careemtest.movies.networking.callbacks;

import retrofit2.Response;

/**
 * Created by wajahat.karim on 28/11/2017.
 */

public class CustomException extends Exception {

    private int mCode;
    private Response mResponseBody;
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public Response getResponseBody() {
        return mResponseBody;
    }

    public void setResponseBody(Response mResponseBody) {
        this.mResponseBody = mResponseBody;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    CustomException(int code, String message, Response responseBody) {
        super(message);
        mCode = code;
        mMessage = message;
        mResponseBody = responseBody;
    }

}
