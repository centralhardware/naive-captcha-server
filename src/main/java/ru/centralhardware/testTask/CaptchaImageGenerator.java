package ru.centralhardware.testTask;

import java.io.File;

public interface CaptchaImageGenerator {

    File generate(String randomText);

}
