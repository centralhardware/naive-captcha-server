package ru.centralhardware.testTask;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IdGenerator {

    @Override
    public String generateID() {
        return UUID.randomUUID().toString();
    }



}
