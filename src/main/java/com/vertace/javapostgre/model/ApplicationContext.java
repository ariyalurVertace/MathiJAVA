package com.vertace.javapostgre.model;

public class ApplicationContext {
    public static final String X_PASSWORD = "X-PASSWORD";
    public static final String AUTHORIZATION = "Authorization";
    public static final String X_USER_ROLE = "UserRole";
    public static final String X_USER_ID = "X-USER-ID";
    private static final ThreadLocal<UserSession> currentTenantContext = new ThreadLocal<>();

    public static UserSession getCurrentTenantContext() {
        return currentTenantContext.get();
    }

    public static void setCurrentTenantContext(UserSession authenticationToken) {
        currentTenantContext.set(authenticationToken);
    }

    public static Long getTenant() {
        if (getCurrentTenantContext() != null)
            return getCurrentTenantContext().getTenant();
        return null;
    }

    public static void clear() {
        currentTenantContext.remove();
    }

}