package com.liyao.neo4j.eneity;

public class Teacher {

  private long id;
  private String teacherName;
  private String teacherTelephone;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public String getTeacherTelephone() {
    return teacherTelephone;
  }

  public void setTeacherTelephone(String teacherTelephone) {
    this.teacherTelephone = teacherTelephone;
  }

}
