package com.liyao.neo4j.eneity;



public class HomeWork {

  private long id;
  private long courseId;
  private String homeworkName;
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


  public String getHomeworkName() {
    return homeworkName;
  }

  public void setHomeworkName(String homeworkName) {
    this.homeworkName = homeworkName;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }

}
