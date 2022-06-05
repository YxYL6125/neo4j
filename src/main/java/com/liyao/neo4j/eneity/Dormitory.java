package com.liyao.neo4j.eneity;


public class Dormitory {

  private long id;
  private String bulidName;
  private String floor;
  private String room;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBulidName() {
    return bulidName;
  }

  public void setBulidName(String bulidName) {
    this.bulidName = bulidName;
  }


  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }


  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

}
