package com.gbook.profiles.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gbook.profiles.model.ProfileData;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 14:33
 */
public class Identity implements ProfileData {
    @JsonIgnore
    private UUID guid;

    public Identity(UUID aGuid) {
        guid = aGuid;
    }

    public UUID getGuid() {
        return guid;
    }

    @Override
    public Map<? extends String, ?> asMap() {
        return Maps.newHashMap();
    }
}
