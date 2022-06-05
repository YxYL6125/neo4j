package com.liyao.neo4j.eneity;


public class ElectricityCost {

  private long id;
  private long dormitoryId;
  private long electricityStart;
  private long electricityEnd;
  private double electricityPrice;
  private double electricityCost;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }


  public long getElectricityStart() {
    return electricityStart;
  }

  public void setElectricityStart(long electricityStart) {
    this.electricityStart = electricityStart;
  }


  public long getElectricityEnd() {
    return electricityEnd;
  }

  public void setElectricityEnd(long electricityEnd) {
    this.electricityEnd = electricityEnd;
  }


  public double getElectricityPrice() {
    return electricityPrice;
  }

  public void setElectricityPrice(double electricityPrice) {
    this.electricityPrice = electricityPrice;
  }


  public double getElectricityCost() {
    return electricityCost;
  }

  public void setElectricityCost(double electricityCost) {
    this.electricityCost = electricityCost;
  }

}
