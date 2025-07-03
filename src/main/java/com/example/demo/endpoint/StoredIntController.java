package com.example.demo.endpoint;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public String getStoredInt() throws IOException {
        File file = new File(FILE_PATH);
        String value;

        if (file.exists()) {
            // Lire le contenu si le fichier existe
            value = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            System.out.println("Fichier trouvé avec valeur : " + value);
        } else {
            // Créer un nombre aléatoire et stocker dans le fichier
            int randomValue = new Random().nextInt(100) + 1;
            value = String.valueOf(randomValue);
            Files.write(Paths.get(FILE_PATH), value.getBytes());
            System.out.println("Fichier créé avec valeur : " + value);
        }

        return "{\"storedValue\": " + value + "}";
    }
}


