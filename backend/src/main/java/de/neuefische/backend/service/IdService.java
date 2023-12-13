package de.neuefische.backend.service;

import java.util.UUID;

public class IdService {

    public String randomId(){
        return UUID.randomUUID().toString();
    }
}
