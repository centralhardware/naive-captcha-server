package ru.centralhardware.testTask;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class RandomTextGenerator implements TextGenerator {

    @Override
    public String generate(int length) {
        return RandomStringUtils.random(length, Config.CAPTCHA_IS_USE_LETTERS, Config.CAPTCHA_IS_USE_NUMBERS);
    }

}
