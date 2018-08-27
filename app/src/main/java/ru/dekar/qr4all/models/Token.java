package ru.dekar.qr4all.models;

import java.util.Date;

public class Token {

    public String token;
    public int id;
    public Date expires;

    public Token(int id, String token, Date expires)
    {
        this.id = id;
        this.token = token;
        this.expires = expires;
    }
}
