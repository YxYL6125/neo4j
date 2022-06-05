package com.liyao.neo4j.eneity;

public class Student {

  private long id;
  private String studentName;
  private long age;
  private String sex;
  private String speciality;
  private long cardNumber;
  private String college;
  private long dormitoryId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            ", speciality='" + speciality + '\'' +
            ", cardNumber=" + cardNumber +
            ", college='" + college + '\'' +
            ", dormitoryId=" + dormitoryId +
            '}';
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }


  public long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(long cardNumber) {
    this.cardNumber = cardNumber;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }


  public long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

}
