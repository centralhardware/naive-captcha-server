package ru.centralhardware.testTask.TextGen;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTextGeneratorTest {

    private static final int RANDOM_TEXT_LENGTH = 5;

    private final RandomTextGenerator textGenerator = new RandomTextGenerator();

    @Test
    public void generate() {
        String randomText = textGenerator.generate(RANDOM_TEXT_LENGTH);
        assertEquals(RANDOM_TEXT_LENGTH, randomText.length());
    }
}