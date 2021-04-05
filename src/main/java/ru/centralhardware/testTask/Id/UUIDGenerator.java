package ru.centralhardware.testTask.Id;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IdGenerator {

    /**
     * @return UUID 4 version
     */
    @Override
    public String generateID() {
        return UUID.randomUUID().toString();
    }



}
