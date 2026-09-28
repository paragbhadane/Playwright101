package config;

public class TestConfig {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String LOGIN_URL = BASE_URL + "/";

    //Single user
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    //Storage state file paths
    public static final String USER_STATE = "auth/user.json";
    public static final String EXPIRED_STATE = "auth/expired.json";
}