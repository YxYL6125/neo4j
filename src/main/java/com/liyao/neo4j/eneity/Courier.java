package com.liyao.neo4j.eneity;


public class Courier {

  private long id;
  private String goodsName;
  private String receiveAddress;
  private long buyerId;
  private String identityCode;
  private java.sql.Timestamp arriveTime;
  private java.sql.Timestamp takeTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


  public String getReceiveAddress() {
    return receiveAddress;
  }

  public void setReceiveAddress(String receiveAddress) {
    this.receiveAddress = receiveAddress;
  }


  public long getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(long buyerId) {
    this.buyerId = buyerId;
  }


  public String getIdentityCode() {
    return identityCode;
  }

  public void setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
  }


  public java.sql.Timestamp getArriveTime() {
    return arriveTime;
  }

  public void setArriveTime(java.sql.Timestamp arriveTime) {
    this.arriveTime = arriveTime;
  }


  public java.sql.Timestamp getTakeTime() {
    return takeTime;
  }

  public void setTakeTime(java.sql.Timestamp takeTime) {
    this.takeTime = takeTime;
  }

}
