package com.example.App.api.model;


import javax.persistence.*;

import java.util.Date;

@Entity(name = "Notice")
@Table(name= "Notice")
public class NoticeModel {

     @Id
     @GeneratedValue(strategy= GenerationType.AUTO)
     public Integer id;

     @Column(nullable = false, length = 100)
     public String title;
     @Column(nullable = false, length = 100)
     public String description;
     @Column(nullable = true, length = 100)
     public Date publicationDate;
     @Column(nullable = true, length = 100)
     public Date visualizationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getVisualizationDate() {
        return visualizationDate;
    }

    public void setVisualizationDate(Date visualizationDate) {
        this.visualizationDate = visualizationDate;
    }
}
