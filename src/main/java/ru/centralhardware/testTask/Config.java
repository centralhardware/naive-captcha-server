package ru.centralhardware.testTask;

public class Config {

    public static final Integer CAPTCHA_STRING_LENGTH = Integer.valueOf(System.getenv("CAPTCHA_STRING_LENGTH"));
    public static final boolean CAPTCHA_IS_USE_LETTERS = Boolean.parseBoolean(System.getenv("CAPTCHA_IS_USE_LETTERS"));
    public static final boolean CAPTCHA_IS_USE_NUMBERS = Boolean.parseBoolean(System.getenv("CAPTCHA_IS_USE_NUMBERS"));

    public static final String IMAGE_FOLDER_LOCATION = System.getenv("IMAGE_FOLDER_LOCATION");
}
