package com.liyao.neo4j.eneity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity("course")
public class Course {
  @Id
  @GeneratedValue
  private long id;
  @Property("courseName")
  private String courseName;
  @Property("courseClassroom")
  private String courseClassroom;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getCourseClassroom() {
    return courseClassroom;
  }

  public void setCourseClassroom(String courseClassroom) {
    this.courseClassroom = courseClassroom;
  }

}
