package ebay;


import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ebayAPICaller {

  public ArrayList<Double> getPriceInfo(String searchPhrase) {

    System.out.println("Beginning our Price Getter");
    //Varaibles
    ArrayList<Double> list = new ArrayList<>();
    String search = searchPhrase.replace(" ", "+");

    //Get our document of data from the URL
    System.out.println("Getting Data from URL");
    Document doc = null;
    try {
      doc = Jsoup.connect("https://www.ebay.com/sch/i.html?_nkw=" + search + "&_sacat=0&LH_TitleDesc=0&_ipg=200").get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(doc.title());

    System.out.println("Creating our list");
    //Create our list
    Elements prices = doc.select(".s-item__price");
    for (Element price : prices) {
      String newStr = price.toString().replaceAll("[^\\d.]+", "");
      list.add(Double.parseDouble(newStr));
    }

    System.out.println("Ouput List for debugging purposes");
    System.out.println(list);

    //Return
    System.out.println("Return the list");
    return list;
  }

}
