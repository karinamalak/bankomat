package com.company.Bankomat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;


public class HTMLparse {
public static Document doc;

    static {
        try {
            doc = Jsoup.connect("https://www.bankier.pl/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float getElemntUsd() throws IOException {

        String usd = doc.select("span.value").get(8).text();
        String usd2 = usd.replace(',','.');
        return Float.valueOf(usd2);
    }
    public static float getElemntEuro() throws IOException {
        Document doc = Jsoup.connect("https://www.bankier.pl/").get();

        String euro = doc.select("span.value").get(9).text();
        String euro2 = euro.replace(',','.');

        return Float.valueOf(euro2);
    }

}
