package ebay;

import java.util.HashMap;

public class ApplicationController {

  private String searchPhrase;
  private String operation;
  private HashMap<String, ebayFunctions> runs = createMap();

  private HashMap<String, ebayFunctions> createMap() {
    HashMap<String, ebayFunctions> map = new HashMap<String, ebayFunctions>();
    map.put("scrap", new ebayScrap());
    map.put("historical", new ebayHistorical());
    return map;
  }

  public ApplicationController(String searchPhrase, String operation) {
    this.searchPhrase = searchPhrase;
    this.operation = operation;
  }

  public String run() {
      ebayFunctions newFunction = this.runs.get(this.operation);
      return newFunction.process(this.searchPhrase);
  }

}
