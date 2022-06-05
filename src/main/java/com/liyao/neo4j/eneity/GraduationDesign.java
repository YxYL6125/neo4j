package com.liyao.neo4j.eneity;


public class GraduationDesign {

  private long id;
  private String designReport;
  private String designPaper;
  private String subject;

  @Override
  public String toString() {
    return "GraduationDesign{" +
            "id=" + id +
            ", designReport='" + designReport + '\'' +
            ", designPaper='" + designPaper + '\'' +
            ", subject='" + subject + '\'' +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getDesignReport() {
    return designReport;
  }

  public void setDesignReport(String designReport) {
    this.designReport = designReport;
  }


  public String getDesignPaper() {
    return designPaper;
  }

  public void setDesignPaper(String designPaper) {
    this.designPaper = designPaper;
  }


  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

}
