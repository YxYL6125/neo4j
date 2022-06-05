package com.liyao.neo4j.eneity;

public class GraduationConnection {

  private long id;
  private long studentId;
  private long graduationDesignTeacherId;
  private long graduationDesignId;


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


  public long getGraduationDesignTeacherId() {
    return graduationDesignTeacherId;
  }

  public void setGraduationDesignTeacherId(long graduationDesignTeacherId) {
    this.graduationDesignTeacherId = graduationDesignTeacherId;
  }


  public long getGraduationDesignId() {
    return graduationDesignId;
  }

  public void setGraduationDesignId(long graduationDesignId) {
    this.graduationDesignId = graduationDesignId;
  }

}
