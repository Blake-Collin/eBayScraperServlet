package ebay;

import javax.persistence.*;

@Entity
@Table(name = "historical")
public class eBayRecord {

  @Id
  @Column(name = "search_phrase")
  private String searchValue;

  @Column(name = "count")
  private int totalCounted;

  @Column(name = "total")
  private double totalAmount;

  @Column(name = "highest")
  private double historicalHigh;

  @Column(name = "lowest")
  private double historicalLow;

  @Column(name = "average")
  private double average;

  @Column(name ="currentlow")
  private double currentLow;

  @Column(name = "currenthigh")
  private double currentHigh;


  public eBayRecord() {
    this.searchValue = "";
    this.totalAmount = 0;
    this.totalAmount = 0.0;
    this.historicalHigh = 0.0;
    this.historicalLow = 100000.0;
    this.average = 0.0;
    this.currentLow = 100000.0;
    this.currentHigh = 0.0;
  }

  public eBayRecord(String searchValue) {
    this.searchValue = searchValue;
    this.totalAmount = 0;
    this.totalAmount = 0.0;
    this.historicalHigh = 0.0;
    this.historicalLow = 100000.0;
    this.average = 0.0;
    this.currentLow = 100000.0;
    this.currentHigh = 0.0;
  }

  // Getters
  public String getSearchValue() {
    return searchValue;
  }

  public int getTotalCounted() {
    return totalCounted;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public double getHistoricalHigh() {
    return historicalHigh;
  }

  public double getHistoricalLow() {
    return historicalLow;
  }

  public double getAverage() {
    return average;
  }

  public double getCurrentLow() {
    return currentLow;
  }

  public double getCurrentHigh() {
    return currentHigh;
  }

  // Setters

  public void setSearchValue(String searchValue) {
    this.searchValue = searchValue;
  }

  public void setTotalCounted(int totalCounted) {
    this.totalCounted = totalCounted;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setHistoricalHigh(double historicalHigh) {
    this.historicalHigh = historicalHigh;
  }

  public void setHistoricalLow(double historicalLow) {
    this.historicalLow = historicalLow;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public void setCurrentLow(double currentLow) {
    this.currentLow = currentLow;
  }

  public void setCurrentHigh(double currentHigh) {
    this.currentHigh = currentHigh;
  }
}
