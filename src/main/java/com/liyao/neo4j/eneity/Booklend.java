package com.liyao.neo4j.eneity;

public class Booklend {

  private long id;
  private long bookId;
  private long studentId;
  private java.sql.Timestamp lendTime;
  private java.sql.Timestamp remainTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public java.sql.Timestamp getLendTime() {
    return lendTime;
  }

  public void setLendTime(java.sql.Timestamp lendTime) {
    this.lendTime = lendTime;
  }


  public java.sql.Timestamp getRemainTime() {
    return remainTime;
  }

  public void setRemainTime(java.sql.Timestamp remainTime) {
    this.remainTime = remainTime;
  }

}
