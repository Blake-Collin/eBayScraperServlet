package ebay;

import com.google.gson.Gson;
import hibernate.HibernateHandler;
import hibernate.HibernateUtils;
import java.util.ArrayList;

public class ebayScrap implements ebayFunctions {

  @Override
  public String process(String searchPhrase) {

    System.out.println("Scraping: \"" +searchPhrase + "\"");
    //Base Object
    eBayRecord ebayRecord = null;

    //Hibernate Connection
    HibernateHandler hibernateHandler = HibernateHandler.getInstance();

    if(hibernateHandler.containsRecord(searchPhrase.toLowerCase())) {
      System.out.println("Already exists eBayRecord");
      ebayRecord = hibernateHandler.getRecord(searchPhrase.toLowerCase());
    }
    else
    {
      System.out.println("Created new eBayRecord");
      ebayRecord = new eBayRecord(searchPhrase.toLowerCase());
    }

    if(ebayRecord == null)
    {
      System.out.println("Huston we have a problem!");
    }

    //Scrap
    System.out.println("Scraping Data");
    ebayAPICaller ebayAPICaller = new ebayAPICaller();
    ArrayList<Double> list = ebayAPICaller.getPriceInfo(searchPhrase);

    //Reset our current high and low
    ebayRecord.setCurrentHigh(0.0);
    ebayRecord.setCurrentLow(100000.0);

    //Add our new values together
    for (Double price : list) {

      double newPrice = (Math.round((price*100)))/100;
      if(newPrice == 0.0) {
        continue;
      }

      //Increase Count and Amount
      ebayRecord.setTotalCounted(ebayRecord.getTotalCounted() + 1);
      ebayRecord.setTotalAmount(ebayRecord.getTotalAmount() + newPrice);

      //Check if higher
      if(newPrice > ebayRecord.getHistoricalHigh()) {
        ebayRecord.setHistoricalHigh(newPrice);
      }

      //Check if Lower
      if(newPrice < ebayRecord.getHistoricalLow()) {
        ebayRecord.setHistoricalLow(newPrice);
      }

      //Check currents
      if(newPrice > ebayRecord.getCurrentHigh()) {
        ebayRecord.setCurrentHigh(newPrice);
      }

      if(newPrice < ebayRecord.getCurrentLow()) {
        ebayRecord.setCurrentLow(newPrice);
      }

    }

    System.out.println("Setting new Average");
    //Set our new average
    double newAverage = (Math.round((ebayRecord.getTotalAmount() / ebayRecord.getTotalCounted())*100))/100;
    ebayRecord.setAverage(newAverage);

    System.out.println("Updating or saving our new value");
    //Update or Save
    hibernateHandler.updateRecord(ebayRecord);

    //Json our object for returning
    System.out.println("Creating Json");
    Gson gson = new Gson();
    String temp = gson.toJson(ebayRecord);

    System.out.println(temp);


    System.out.println("Returning a JSON string");
    return temp;
  }
}
