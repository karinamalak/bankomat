package com.company.Bankomat;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class HTMLparse {


    public  static void getElelemntById() throws IOException {

        Document doc = Jsoup.connect("https://www.bankier.pl/").get();
       // Document doc1 = Jsoup.parse(doc);
      //  Element code = doc.getElementById("");
     //   String category = doc.select(" span.name > a").text();
        String category = doc.select("span.value").get(8).text();

      // String thirdParagraph = category.get(1).text();

        //foreach name jezeli USD to wez kolejny span.value
  //      #ui-accordion-1-header-8

        System.out.println(category);
        //Document doc = res.parse();

        //Element code = doc.getElementById("ui-accordion-1-header-8");
        //String codeString = code.attr("value");
       // System.out.println(codeString);
       // Element pauth = doc.getElementById("ID");
       // String pauthStr = pauth.attr("action");
       // System.out.println(pauthStr);


//        Document doc = Jsoup.parse( "UTF-8", "https://www.bankier.pl/");
//        Element content = doc.getElementById("ui-accordion-1-header-8");
//        Elements links = content.getElementsByTag("span");
//        for (Element link : links) {
//            String linkHref = link.attr("value");
//            System.out.println(linkHref);
//            String linkText = link.text();
//            System.out.println(linkText);
//        }



    }



}
