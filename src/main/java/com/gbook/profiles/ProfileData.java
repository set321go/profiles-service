package com.gbook.profiles;

import java.util.Map;

/**
 * All Data parts that expect to be rendered need to implement this interface.
 */
public interface ProfileData {
    /**
     * A map representation of the data to be rendered.
     * @return Map&lt;String, Object&gt; map
     */
    Map<? extends String,?> asMap();
}
