package com.company.Bankomat;


import java.io.IOException;

public class Bankomat {

    public static boolean confirm = false;
    public static Double staraKwota; //kwota wyjsciowa danego klienta
    public static int ityKlient;
    public static float kurs = 1f; //domyslny PLN
    public static String waluta = "";

    public static void main(String[] args) throws IOException {

        Pliki_odczyt2.odczyt();
        MetodyBankomatu.pokazWidok();
    }

    public static void zweryfikujKlienta(Integer pin) throws IOException {
        while (confirm == false) {
            for (int i = 0; i < Pliki_odczyt2.daneOdczytane.length; i++) {
                //   if(String.valueOf(pin).equals(Integer.parseInt(Pliki_odczyt2.daneOdczytane[i][2]))) {
                if (String.valueOf(pin).equals(Pliki_odczyt2.daneOdczytane[i][2])) {
                    //  System.out.println("ZGADZA SIE PIN");
                    staraKwota = Double.parseDouble(Pliki_odczyt2.daneOdczytane[i][1]);
                    MetodyBankomatu.pokazWidokiPoZalogowaniu();
                    ityKlient = i;

                    confirm = true;
                    break;
                }
            }
            if (!confirm) System.out.println("NIE ZGADZA SIE PIN"); //tutaj jescze raz czeka na wisanie jak??????????/
        }
    }

    public static void sprawdzStanKonta(Integer pin) {
        MetodyBankomatu.konsola.setText("Stan konta to " + staraKwota);
    }

    public static void dodajSrodki(Integer pin, Double amount,float kurs, String waluta) {

        staraKwota += amount*kurs;
        Pliki_odczyt2.daneOdczytane[ityKlient][1] = staraKwota.toString();

        MetodyBankomatu.konsola.setText("Dodajesz " + amount+" "+ waluta);
    }

    public static void wyplacSrodki(Integer pin, Double amount, float kurs, String waluta) {

        if (staraKwota >= amount) {
            staraKwota -= amount*kurs;
            Pliki_odczyt2.daneOdczytane[ityKlient][1] = staraKwota.toString();
            MetodyBankomatu.konsola.setText("Wypłacasz " + amount+" "+waluta);
        } else {
            MetodyBankomatu.konsola.setText("Niewystarczajace srodki");
        }
    }


}
