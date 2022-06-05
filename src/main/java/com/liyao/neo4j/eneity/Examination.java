package com.liyao.neo4j.eneity;


public class Examination {

  private long id;
  private long courseId;
  private String examinationClassroom;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }


  public String getExaminationClassroom() {
    return examinationClassroom;
  }

  public void setExaminationClassroom(String examinationClassroom) {
    this.examinationClassroom = examinationClassroom;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }

}
