package com.liyao.neo4j.eneity;

public class MeetingConnection {

  private long id;
  private long meetingId;
  private long teacherId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(long meetingId) {
    this.meetingId = meetingId;
  }


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }

}
