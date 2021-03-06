package ru.centralhardware.testTask.Id;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UUIDGeneratorTest {

    private final UUIDGenerator uuidGenerator = new UUIDGenerator();

    private static final int UUID_4_LENGTH = 36;

    @Test
    public void generateID() {
        String uuid = uuidGenerator.generateID();
        assertEquals(36, uuid.length());
    }
}