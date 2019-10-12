package ebay;

import com.google.gson.Gson;
import hibernate.HibernateHandler;

public class ebayHistorical implements ebayFunctions {

  public String process(String searchPhrase) {

    //Base Object
    eBayRecord ebayRecord;

    //Hibernate Connection
    HibernateHandler hibernateHandler = HibernateHandler.getInstance();

    if(hibernateHandler.containsRecord(searchPhrase.toLowerCase())) {
      ebayRecord = hibernateHandler.getRecord(searchPhrase.toLowerCase());

      //Gson our object for returning
      Gson gson = new Gson();
      String temp = gson.toJson(ebayRecord);

      System.out.println(temp);

      //Sense we already have it return it!
      return temp;
    }

    //Do Not have scrap it first!
    ebayScrap ebayScrap = new ebayScrap();
    return ebayScrap.process(searchPhrase);
  }
}
