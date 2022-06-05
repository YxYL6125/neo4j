package com.liyao.neo4j.eneity;


public class Score {

  private long id;
  private long courseId;
  private long studentId;
  private long courseScore;


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


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public long getCourseScore() {
    return courseScore;
  }

  public void setCourseScore(long courseScore) {
    this.courseScore = courseScore;
  }

}
