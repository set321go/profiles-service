package com.gbook.profiles.identity;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * This service will cache identities because it is assumed your identity loader will be contacting a separate system
 */
@Singleton
public class IdentityService {
    private final static Logger LOGGER = LoggerFactory.getLogger(IdentityService.class);

    private LoadingCache<String, Identity> identityCache;
    private IdentityLoader identityLoader;

    @Inject
    public IdentityService(IdentityLoader aIdentityLoader) {
        identityLoader = aIdentityLoader;
        identityCache = CacheBuilder.newBuilder()
                 .maximumSize(100)
                 .expireAfterWrite(5, TimeUnit.MINUTES)
                 .build(new CacheLoader<String, Identity>() {
                     @Override
                     public Identity load(String key) throws Exception {
                         Optional<Identity> identity = identityLoader.find(key);
                         if (identity.isPresent()) {
                             return identity.get();
                         }

                         throw new IdentityNotFound("No matching identity could be found.");
                     }
                 });
    }

    public Optional<Identity> isIdentifiable(String aIdentityToken) {
        if (StringUtils.isNotBlank(aIdentityToken)) {
            try {
                return Optional.of(identityCache.get(aIdentityToken));
            } catch (ExecutionException exception) {
                LOGGER.debug(exception.getMessage());
            }
        }

        return Optional.empty();
    }
}
