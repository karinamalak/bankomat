package com.company.Bankomat;


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
    public static JLabel kursL = new JLabel("");
    private static Float kurs = 1f;
    private static String waluta = "PLN";


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
                try {
                    Bankomat.zweryfikujKlienta(pin); //wywoluje metode, ktora weryfikuje klienta
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    protected static void pokazWidokiPoZalogowaniu() throws IOException { // pokazuje rezte komponentow po zalogowaniu
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
        JLabel pusty2 = new JLabel("");


        ramka.add(pusty);
        ramka.add(pusty2);

        ramka.add(kwotaL);
        ramka.add(kwotaF);
        ramka.add(dodajSrodkiB);
        ramka.add(wyplacSrodkiB);
        ramka.add(stanKontaB);
        ramka.add(konsola);
        ramka.add(kursL);

        ButtonGroup radio = new ButtonGroup();

        JRadioButton pln = new JRadioButton("PLN");
pln.setSelected(true);
        ramka.add(pln);
        JRadioButton usd = new JRadioButton("USD");
        ramka.add(usd);
        JRadioButton euro = new JRadioButton("EURO");
        ramka.add(euro);
        radio.add(pln);
        radio.add(usd);
        radio.add(euro);

        if (!kwotaF.getText().isEmpty()) {
            kwota = (Double) kwotaF.getValue();
        }


        //waluta
        euro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (euro.isSelected()) {
                    try {
                        kurs = (HTMLparse.getElemntEuro());
                        waluta = "EURO";
                        kursL.setText("kurs: " +kurs);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        usd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usd.isSelected()) {
                    try {
                        kurs = (HTMLparse.getElemntUsd());
                        waluta = "USD";
                        kursL.setText("kurs: " +kurs);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        pln.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           kurs = 1f;
           waluta = "PLN";
                kursL.setText("");
            }
        });

        dodajSrodkiB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bankomat.dodajSrodki(pin, Double.valueOf(kwotaF.getText()),kurs,waluta);
            }

        });

        wyplacSrodkiB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bankomat.wyplacSrodki(pin, Double.valueOf(kwotaF.getText()),kurs,waluta);
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