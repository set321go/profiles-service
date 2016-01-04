package com.gbook.profiles.identity;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 14:49
 */
public interface IdentityLoader {
    Optional<Identity> find(String aToken);
}
