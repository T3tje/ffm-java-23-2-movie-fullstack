package de.neuefische.backend.service;

import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
public class IdService {

    public String randomId(){
        return UUID.randomUUID().toString();
    }
}
