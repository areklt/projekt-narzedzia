package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String sciezkaDoZamowien = AppStLoader.getSciezka();

        if (sciezkaDoZamowien == null) {
            System.out.println("bład ścieżki katalogu");
            return;
        }
        File folder = new File(sciezkaDoZamowien);
        File[] pliki = folder.listFiles((   k, nazwa) -> nazwa.toLowerCase().endsWith(".json"));


        ObjectMapper mapper = new ObjectMapper();

        if (pliki == null || pliki.length == 0) {
            System.out.println("brak plików JSON");
            return;
        }

        for (File plik : pliki) {
            try {
                Zamowienie z = mapper.readValue(plik, Zamowienie.class);



                String numerZamowienia = generujNumer(z);

                System.out.println("     " + plik.getName());
                System.out.println("numer zamówienia: " + numerZamowienia);

            } catch (Exception e) {
                System.out.println("blad pliku: " + plik.getName());
            }
        }
    }

    public static String generujNumer(Zamowienie z) {
        StringBuilder sb = new StringBuilder();
        sb.append(z.getKlient().getImie())
                .append(z.getKlient().getEmail())
                .append(z.getKlient().getNazwisko());


        z.getProdukty().forEach(p ->
                sb.append(p.getKod())
                        .append(p.getIlosc())
                        .append(p.getJednostka())
        );

        return sb.toString();
    }
}
