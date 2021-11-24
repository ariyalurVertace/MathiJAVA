package com.vertace.javapostgre.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
public class UserSession extends org.springframework.security.core.userdetails.User {

    Long tenant;
    Long orgId;
    String authToken;
    String userRole;
    Long userId;
    Map<String, Object> contextDetails = new HashMap<String, Object>();

    public UserSession(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserSession(
            String username,
            String password,
            Long orgId,
            String userRole,
            Long userId,
            Collection<? extends GrantedAuthority> authorities) {
        this(username, password, authorities);
        this.orgId = orgId;
        this.userId = userId;
        this.userRole = userRole;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
