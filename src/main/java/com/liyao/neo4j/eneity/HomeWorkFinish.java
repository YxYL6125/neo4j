package com.liyao.neo4j.eneity;

public class HomeWorkFinish {

  private long id;
  private long studentId;
  private long homeworkId;
  private long homeworkStatus;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public long getHomeworkId() {
    return homeworkId;
  }

  public void setHomeworkId(long homeworkId) {
    this.homeworkId = homeworkId;
  }


  public long getHomeworkStatus() {
    return homeworkStatus;
  }

  public void setHomeworkStatus(long homeworkStatus) {
    this.homeworkStatus = homeworkStatus;
  }

}
