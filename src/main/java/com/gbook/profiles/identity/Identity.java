package com.gbook.profiles.identity;

import com.gbook.profiles.ProfileData;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 14:33
 */
public class Identity implements ProfileData {
    @Override
    public Map<? extends String, ?> asMap() {
        return Maps.newHashMap();
    }
}
