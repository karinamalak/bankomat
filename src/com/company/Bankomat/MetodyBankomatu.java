package com.company.Bankomat;

//import org.jsoup.Jsoup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;



public abstract class MetodyBankomatu {

    public static JFrame ramka = new JFrame("Mój bankomat");
    public static JPanel controlPanel = new JPanel();
    private static Integer pin = 0; //nie moze byc null
    private static Double kwota = 0.0; //nie moze byc null
    public static JLabel konsola = new JLabel("");


    public static void pokazWidok() { //uzupelnianie ramki/widoku
        JLabel powitanie = new JLabel("Witaj, podaj pin");
        JFormattedTextField weryfikator = new JFormattedTextField();
        JButton przyciskZaloguj = new JButton("Zaloguj");
        ramka.setLayout(new GridLayout(1, 1, 5, 5));
        ramka.setMinimumSize(new Dimension(500, 50));
        ramka.add(powitanie);
        ramka.add(weryfikator);
        // JSONWriter
        ramka.add(przyciskZaloguj);
        ramka.pack();
        ramka.setVisible(true);


            przyciskZaloguj.addActionListener(new ActionListener() { //event do buttona
                @Override
                public void actionPerformed(ActionEvent e) {
                    pin = Integer.valueOf(weryfikator.getText()); //przypisuje do zmiennej pin wartosc z polatektowego
                    Bankomat.zweryfikujKlienta(pin); //wywoluje metode, ktora weryfikuje klienta
                }
            });
    }


        protected static void pokazWidokiPoZalogowaniu () { // pokazuje rezte komponentow po zalogowaniu
            ramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JLabel kwotaL = new JLabel("Kwota");
            ramka.add(kwotaL);
            JFormattedTextField kwotaF = new JFormattedTextField();
            JButton dodajSrodkiB = new JButton("Dodaj środki");
            JButton wyplacSrodkiB = new JButton("Wypłać środki");
            JButton stanKontaB = new JButton("Stan konta");
            ramka.setLayout(new GridLayout(3, 2, 5, 5));
            ramka.setMinimumSize(new Dimension(800, 150));
            JLabel pusty = new JLabel("");
            ramka.add(pusty);

            ramka.add(kwotaL);
            ramka.add(kwotaF);
            ramka.add(dodajSrodkiB);
            ramka.add(wyplacSrodkiB);
            ramka.add(stanKontaB);
            ramka.add(konsola);

            JLabel stanKonta = new JLabel("");
            ramka.add(stanKonta);

            if (!kwotaF.getText().isEmpty()) {
                kwota = (Double) kwotaF.getValue();
            }

            dodajSrodkiB.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Bankomat.dodajSrodki(pin, Double.valueOf(kwotaF.getText()));
                }

            });

            wyplacSrodkiB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bankomat.wyplacSrodki(pin, Double.valueOf(kwotaF.getText()));
                }
            });

            stanKontaB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bankomat.sprawdzStanKonta(pin);
                }
            });

            ramka.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {

                    try {
                        Pliki_odczyt2.zapis();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });

        }


    }