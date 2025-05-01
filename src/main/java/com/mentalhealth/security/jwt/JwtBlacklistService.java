package com.mentalhealth.security.jwt;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtBlacklistService {

    private final Set<String> blacklistedTokens = Collections.synchronizedSet(new HashSet<>());

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        boolean isBlacklisted = blacklistedTokens.contains(token);
        return blacklistedTokens.contains(token);
    }

    public void clearBlacklist() {
        blacklistedTokens.clear();
    }

}
