package com.liyao.neo4j.eneity;


public class Total {

  private long id;
  private String studentName;
  private long studentAge;
  private String teacherName;
  private String teacherTelephone;
  private String courseName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Total{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", studentAge=" + studentAge +
            ", teacherName='" + teacherName + '\'' +
            ", teacherTelephone='" + teacherTelephone + '\'' +
            ", courseName='" + courseName + '\'' +
            '}';
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public long getStudentAge() {
    return studentAge;
  }

  public void setStudentAge(long studentAge) {
    this.studentAge = studentAge;
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


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

}
