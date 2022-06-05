package com.liyao.neo4j.eneity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity("book")
public class Book {
  @Id
  @GeneratedValue
  private long id;
  @Property("bookName")
  private String bookName;
  @Property("bookAuthor")
  private String bookAuthor;
  @Property("publishYear")
  private String publishYear;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }


  public String getPublishYear() {
    return publishYear;
  }

  public void setPublishYear(String publishYear) {
    this.publishYear = publishYear;
  }

}
