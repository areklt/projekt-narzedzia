package org.example;

public class ZamowienieValidator {

    public boolean isValid(Zamowienie zamowienie) {

        if (zamowienie == null) {
            return false;
        }

        Klient klient = zamowienie.getKlient();

        if (klient == null
                || klient.getImie() == null || klient.getImie().trim().isEmpty()
                || klient.getNazwisko() == null || klient.getNazwisko().trim().isEmpty()) {
            return false;
        }

        if (zamowienie.getProdukty() == null
                || zamowienie.getProdukty().isEmpty()
                || zamowienie.getProdukty().size() > 9) {
            return false;
        }

        double totalWeightKg = 0;

        for (Produkty p : zamowienie.getProdukty()) {

            if (p == null) {
                return false;
            }

            String unit = p.getJednostka();
            double quantity = p.getIlosc();

            if (unit == null) {
                return false;
            }

            if (!unit.equals("gram") && !unit.equals("kilogram") && !unit.equals("tona")) {
                return false;
            }

            if (quantity <= 0) {
                return false;
            }

            switch (unit) {
                case "gram":
                    totalWeightKg += quantity / 1000.0;
                    break;
                case "kilogram":
                    totalWeightKg += quantity;
                    break;
                case "tona":
                    totalWeightKg += quantity * 1000;
                    break;
            }
        }

        return totalWeightKg <= 2000;
    }
}
