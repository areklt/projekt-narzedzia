package org.example;

public class ZamowienieValidatorTest {

    public static void validate(Zamowienie z) {
        if (z == null) {
            throw new IllegalArgumentException("Zamówienie jest null");
        }

        if (z.getKlient() == null ||
                isBlank(z.getKlient().getImie()) ||
                isBlank(z.getKlient().getNazwisko())) {

            throw new IllegalArgumentException("Imię i nazwisko nie mogą być puste");
        }

        if (z.getProdukty() == null ||
                z.getProdukty().isEmpty() ||
                z.getProdukty().size() > 9) {

            throw new IllegalArgumentException("Zamówienie musi zawierać od 1 do 9 produktów");
        }

        double sumaWGramach = 0;

        for (Produkty p : z.getProdukty()) {

            if (p.getIlosc() <= 0) {
                throw new IllegalArgumentException("Ilość produktu musi być dodatnia");
            }

            if (!isJednostkaOk(p.getJednostka())) {
                throw new IllegalArgumentException("Jednostka musi być: gram, kilogram lub tona");
            }

            sumaWGramach += naGramy(p.getIlosc(), p.getJednostka());
        }


        if (sumaWGramach > 2_000_000) {
            throw new IllegalArgumentException("Łączna ilość produktów nie może przekroczyć 2 ton");
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    private static boolean isJednostkaOk(String j) {
        return j != null && switch (j.toLowerCase()) {
            case "gram", "kilogram", "tona" -> true;
            default -> false;
        };
    }

    private static double naGramy(double ilosc, String jednostka) {
        return switch (jednostka.toLowerCase()) {
            case "gram" -> ilosc;
            case "kilogram" -> ilosc * 1000;
            case "tona" -> ilosc * 1_000_000;
            default -> 0;
        };
    }
}
