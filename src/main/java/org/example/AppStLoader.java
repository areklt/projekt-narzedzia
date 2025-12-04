package org.example;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import java.io.File;

public class AppStLoader {
    public static String getSciezka() {
        try {
            String envPath = System.getenv("SCIEZKA_ZAMOWIENIA");

            if (envPath != null && !envPath.isBlank()) {
                return envPath;
            }
            Configurations configs = new Configurations(); //wcz pli konf
            Configuration config = configs.properties(new File("src/main/resources/appsettings.properties"));
            return config.getString("zamowienia.sciezka");
        } catch (Exception e) {
            return null;
        }
    }
}
