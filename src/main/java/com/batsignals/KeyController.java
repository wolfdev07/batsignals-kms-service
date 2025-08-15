package com.batsignals;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.Collections;
import java.util.Map;


/**
 * Controlador REST que expone la clave de encriptación actual.
 * En un entorno de producción, este endpoint DEBE estar protegido.
 */
@Controller("/keys")
public class KeyController {
    
    private final KeyService keyService;

    public KeyController(KeyService keyService) {
        this.keyService =  keyService;
    }

    /**
     * Endpoint para obtener la clave actual en formato JSON.
     * @return Un mapa que contiene la clave en Base64.
     */

    @Get(uri = "/current", produces = MediaType.APPLICATION_JSON)
    public Map<String, String> getCurrentKey() {
        String currentKey = keyService.getCurrentKeyAsBase64();
        return Collections.singletonMap("key", currentKey);
    }
}
