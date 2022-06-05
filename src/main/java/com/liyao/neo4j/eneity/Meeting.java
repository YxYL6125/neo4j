package com.liyao.neo4j.eneity;


public class Meeting {

  private long id;
  private String meetingName;
  private java.sql.Timestamp meetingTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMeetingName() {
    return meetingName;
  }

  public void setMeetingName(String meetingName) {
    this.meetingName = meetingName;
  }


  public java.sql.Timestamp getMeetingTime() {
    return meetingTime;
  }

  public void setMeetingTime(java.sql.Timestamp meetingTime) {
    this.meetingTime = meetingTime;
  }

}
