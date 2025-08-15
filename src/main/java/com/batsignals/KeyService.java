package com.batsignals;

import io.micronaut.context.annotation.Context;
import jakarta.inject.Singleton;
import jakarta.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Servicio Singleton para gestionar la clave de encriptación AES.
 * La clave se mantiene en memoria y se rota periódicamente.
 * @Context se usa para asegurar que se inicialice al arrancar la app.
 */
@Singleton
@Context
public class KeyService {
   
    private volatile SecretKey currentKey;
    private final SecureRandom secureRandom =  new SecureRandom();

    @PostConstruct
    public void init() {
        System.out.println("Inicializando Keyservice y generando primera clave...");
        //generateNewKey();
    }

    public synchronized void generateNewKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");

            keyGen.init(256, secureRandom);
            this.currentKey = keyGen.generateKey();

            System.out.println("¡Nueva clave AES-256 generada exitosamente! Hora: " + java.time.LocalTime.now());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error fatal: El algortimo AES no está disponible.", e);
        }
    }

    public String getCurrentKeyAsBase64 () {
        return Base64.getEncoder().encodeToString(currentKey.getEncoded());
    }

}
