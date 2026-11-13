package com.pearlbank.security.utils;

/**
 * @Project merchant_app_backend
 * @Date 13/11/2026
 * @LocalTime 10:18
 **/
public class SecurityMsgConstant {

    private SecurityMsgConstant() {}

    public static final String USERNAME_REQUIRED = "Username must not be null";
    public static final String PASSWORD_REQUIRED = "Password must not be null";
    public static final String REFRESH_TOKEN_REQUIRED = "Refresh token must not be null";

    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_AUTHORITIES = "authorities";

    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String INVALID_TOKEN = "Access token is missing, invalid or expired";
    public static final String INVALID_REFRESH_TOKEN = "Refresh token is invalid or expired";
    public static final String ACCESS_DENIED = "You do not have permission to perform this action";

    public static final String JWT_KEYS_REQUIRED = "JWT_PUBLIC_KEY_LOCATION and JWT_PRIVATE_KEY_LOCATION must be set outside the dev profile";
    public static final String JWT_KEY_UNREADABLE = "Could not read JWT key from %s";
    public static final String JWT_KEY_NOT_PKCS8 = "JWT private key in %s must be PKCS#8 (BEGIN PRIVATE KEY), create it with scripts/make-jwt-keys.sh";

}
