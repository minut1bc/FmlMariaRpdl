package com.codminskeyboards.universekeyboard.subscriptionmenu.util;

/**
 * Represents the result of an in-app billing operation.
 * A result is composed of a response code (an integer) and possibly a
 * message (String). You can get those by calling
 * {@link #getResponse} and {@link #getMessage()}, respectively. You
 * can also inquire whether a result is a success or a failure by
 * calling {@link #isSuccess()} and {@link #isFailure()}.
 */
public class IabResult {
    private int mResponse;
    private String mMessage;

    IabResult(int response, String message) {
        mResponse = response;
        if (message == null || message.trim().length() == 0) {
            mMessage = IabHelper.getResponseDesc(response);
        }
        else {
            mMessage = message + " (response: " + IabHelper.getResponseDesc(response) + ")";
        }
    }
    public int getResponse() { return mResponse; }
    public String getMessage() { return mMessage; }

    private boolean isSuccess() {
        return mResponse == IabHelper.BILLING_RESPONSE_RESULT_OK;
    }

    private boolean isFailure() {
        return !isSuccess();
    }
    public String toString() { return "IabResult: " + getMessage(); }
}
