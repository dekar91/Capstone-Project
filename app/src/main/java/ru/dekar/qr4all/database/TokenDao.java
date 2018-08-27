package ru.dekar.qr4all.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import ru.dekar.qr4all.models.TokenEntity;

@Dao
public interface TokenDao {
    
    @Query("SELECT * from token ORDER BY expires")
    List<TokenEntity> loadAllTokens();
    
    @Query("SELECT * from token WHERE expires > :date OR expires == 0 LIMIT 1")
    TokenEntity loadToken(Date date);

    @Insert
    void insertToken(TokenEntity itemEntity);

    @Update
    void updateToken(TokenEntity itemEntity);

    @Delete
    void deleteToken(TokenEntity itemEntity);
}
