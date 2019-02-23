package com.company.Bankomat;

import java.io.*;
import java.util.ArrayList;


public class Pliki_odczyt2 {

    //   public static void main(String[] args) {
    public static FileReader fr = null;
    public static String PATH = "C:\\Users\\Karina\\IdeaProjects\\Bankomat\\src\\com\\company\\Bankomat\\plik.txt";
    public static String linia = "";
    public static ArrayList<String> lista = new ArrayList<>();
    public static String[][] daneOdczytane;
    public static FileWriter fileWriter = null;
    public static BufferedWriter bufferedWriter = null;

    public static void odczyt() {
        //		OTWIERANIE PLIKU:
        try {
            fr = new FileReader(PATH);
            fileWriter = new FileWriter(PATH);
            bufferedWriter = new BufferedWriter(fileWriter);

        } catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr); //tablica dwoymiar o wymiarze 2 na nic


        int nrLinii = 0;
        // ODCZYT KOLEJNYCH LINII Z PLIKU:
        try {
            while ((linia = bfr.readLine()) != null) { //odczytuje z pliku linijka po linijce
                lista.add(linia); //wrzuca kolejne linijki do listy
            }

            // String[][]
            daneOdczytane = new String[lista.size()][];
            for (String s : lista) {
                String[] liniaDaneString = s.split(";");
                String[] liniaDouble = new String[liniaDaneString.length];
                // Pętla, która pobiera z tablicy String-ów po jednej liczbie,
                // konwertuje ją na liczbę double i zapisuje w tablicy typu double[]
                for (int j = 0; j < liniaDouble.length; j++) {
                    liniaDouble[j] = liniaDaneString[j];

                }
                // Dodajemy tablicę z serią danych do tablicy z wszystkimi danymi
                daneOdczytane[nrLinii] = liniaDouble;
                // kolejna linia...
                nrLinii++;
            }

            for (int i = 0; i < daneOdczytane.length; i++) {
                for (int j = 0; j < daneOdczytane[i].length; j++) {
                    System.out.println(daneOdczytane[i][j]);
                }

            }

        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU Z PLIKU!");
            System.exit(2);
        }

        try {
            fr.close(); //zamyka plik
        } catch (IOException e) {
            System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
            System.exit(3);
        }
    }

    public static void zapis() throws IOException {
        for (int i = 0; i < daneOdczytane.length; i++) {
            for (int j = 0; j < daneOdczytane[i].length; j++) {
                linia += daneOdczytane[i][j] + ";";
            }
            try {
                bufferedWriter.write(linia);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bufferedWriter.close();
            }

        }


    }

}

