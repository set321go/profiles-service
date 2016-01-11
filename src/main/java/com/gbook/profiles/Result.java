package com.gbook.profiles;

import org.apache.commons.lang3.StringEscapeUtils;
import ratpack.handling.Context;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 13:30
 */
public class Result {
    private static final Result SUCCESS = new Result(true, false, "");
    private boolean isSuccess;
    private boolean isInputError;
    private String clientSafeCause;

    private Result(boolean aIsSuccess, boolean aIsInputError, String aClientSafeCause) {
        isSuccess = aIsSuccess;
        isInputError = aIsInputError;
        clientSafeCause = aClientSafeCause;
    }

    public void processResponse(Context ctx) {
        if (isSuccess) { ctx.next(); }
        else if (isInputError) { ctx.getResponse().status(400).send(clientSafeCause); }
        else { ctx.getResponse().status(500); }
    }

    public static Result withClientCause(String aCause) {
        return new Result(false, true, StringEscapeUtils.escapeHtml4(aCause));
    }

    public static Result withServerCause(String aCause) {
        return new Result(false, false, StringEscapeUtils.escapeHtml4(aCause));
    }

    public static Result success() {
        return SUCCESS;
    }
}
