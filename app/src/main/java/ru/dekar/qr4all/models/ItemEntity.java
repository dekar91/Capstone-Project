package ru.dekar.qr4all.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "item")
public class ItemEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String details;
    private String imageUrl;
    private String codeUrl;
    private Date updatedAt;

    public ItemEntity(String name, String details, String imageUrl, String codeUrl, Date updatedAt) {
        this.name = name;
        this.details = details;
        this.imageUrl = imageUrl;
        this.codeUrl = codeUrl;

        if(null== updatedAt)
            this.updatedAt = new Date();
        else
            this.updatedAt = updatedAt;
    }

    @Ignore
    public ItemEntity(int id, String name, String details, String imageUrl, String codeUrl, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.imageUrl = imageUrl;
        this.codeUrl = codeUrl;

        if(null== updatedAt)
            this.updatedAt = new Date();
        else
            this.updatedAt = updatedAt;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return name;
    }
}
