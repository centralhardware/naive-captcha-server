package ru.centralhardware.testTask.ImageGen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LinesAndDotsCaptchaGeneratorTest {

    private File image;

    @AfterEach
    void tearDown() {
        assertTrue(image.delete());
    }

    @Test
    void generate() {
        image = new LinesAndDotsCaptchaGenerator().generate("foo");
        assertTrue(image.getName().endsWith(".png"));
        assertTrue(image.length() > 0);
    }
}