package com.batsignals;


import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;



/**
 * Tarea programada para rotar la clave de encriptación periódicamente.
 */
@Singleton
public class KeyRotationScheduler {

    private final KeyService keyService;

    public KeyRotationScheduler(KeyService keyService) {
        this.keyService = keyService;
    }

    @Scheduled(fixedRate = "30s")
    void rotateKey() {
        System.out.println("Disparando rotación de clave programada...");
        keyService.generateNewKey();
    }
}
