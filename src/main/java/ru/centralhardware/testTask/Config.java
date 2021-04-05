package ru.centralhardware.testTask;

public class Config {

    private static final String DEFAULT_CAPTCHA_STRING_LENGTH = "5";

    public static final int CAPTCHA_STRING_LENGTH = Integer.parseInt(System.getProperty("CAPTCHA_STRING_LENGTH", DEFAULT_CAPTCHA_STRING_LENGTH));
    public static final boolean CAPTCHA_IS_USE_LETTERS = Boolean.parseBoolean(System.getProperty("CAPTCHA_IS_USE_LETTERS", "true"));
    public static final boolean CAPTCHA_IS_USE_NUMBERS = Boolean.parseBoolean(System.getProperty("CAPTCHA_IS_USE_NUMBERS", "true"));

}
