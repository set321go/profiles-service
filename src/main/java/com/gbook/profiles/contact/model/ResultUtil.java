package com.gbook.profiles.contact.model;

import com.gbook.profiles.model.Result;
import ratpack.handling.Context;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-23
 * Time: 14:59
 */
public class ResultUtil {

    public static void processResultList(Context aContext, List<Result> aResultList) {
        Result unsuccessfulResult = null;

        for (Result result : aResultList) {
            if (result.hasError()) {
                unsuccessfulResult = result;
            }
        }

        if (unsuccessfulResult == null) {
            Result.success().processResponse(aContext);
        } else {
            unsuccessfulResult.processResponse(aContext);
        }
    }
}
