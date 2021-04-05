package ru.centralhardware.testTask.ImageGen;

import java.io.File;

public interface CaptchaImageGenerator {

    File generate(String randomText);

}
