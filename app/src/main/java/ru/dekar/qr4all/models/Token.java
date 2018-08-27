package ru.dekar.qr4all.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "token")
public class Token {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String token;
    private Date expires;

    public Token(int id, String token, Date expires)
    {
        this.id = id;
        this.token = token;
        this.expires = expires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
