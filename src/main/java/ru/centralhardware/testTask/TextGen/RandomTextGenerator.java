package ru.centralhardware.testTask.TextGen;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import ru.centralhardware.testTask.config.Config;

@Component
public class RandomTextGenerator implements TextGenerator {

    @Override
    public String generate(int length) {
        return RandomStringUtils.random(length, Config.CAPTCHA_IS_USE_LETTERS, Config.CAPTCHA_IS_USE_NUMBERS);
    }

}
