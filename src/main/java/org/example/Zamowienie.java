package org.example;

import java.util.List;

public class Zamowienie {

    private Klient klient;
    private List<Produkty> produkty;

    public Klient getKlient() { return klient; }
    public void setKlient(Klient klient) { this.klient = klient; }

    public List<Produkty> getProdukty() { return produkty; }
    public void setProdukty(List<Produkty> produkty) { this.produkty = produkty; }
}
