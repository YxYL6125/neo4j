package com.liyao.neo4j.eneity;


public class CourierConnection {

  private long id;
  private long courierId;
  private long deliverymanId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCourierId() {
    return courierId;
  }

  public void setCourierId(long courierId) {
    this.courierId = courierId;
  }


  public long getDeliverymanId() {
    return deliverymanId;
  }

  public void setDeliverymanId(long delivermanId) {
    this.deliverymanId = delivermanId;
  }

}
