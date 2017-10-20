package bots.cont;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * Created by aer on 13.10.17.
 */
public class NewsParcer {

    public static String getLastNewsTitle(){
        String url = "http://cont.ws/";
        Document doc;
        String outText = " ";

        try {
            doc = Jsoup.connect(url).get();
            List<Element> elementList = doc.select(".post_prv h3 a");
            for (Element elem:elementList) {
                outText += elem.text();
                outText += "\n";
                outText += url + elem.attr("href");
                outText += "\n";
                outText += "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return outText;
    }

}
