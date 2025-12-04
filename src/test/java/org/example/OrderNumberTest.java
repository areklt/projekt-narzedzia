package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderNumberTest {

    @Test
    void numberShouldBeGeneratedCorrectly() {
        Klient k = new Klient();
        k.setImie("Jan");
        k.setNazwisko("Kowalski");
        k.setEmail("jan@wp.pl");

        Produkty p = new Produkty();
        p.setKod("A1");
        p.setIlosc(5);
        p.setJednostka("kilogram");

        Zamowienie z = new Zamowienie();
        z.setKlient(k);
        z.setProdukty(java.util.List.of(p));

        String nr = Main.generujNumer(z);

        assertEquals("Janjan@wp.plKowalskiA15.0kilogram", nr);
    }
}
