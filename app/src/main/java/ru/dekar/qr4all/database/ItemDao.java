package ru.dekar.qr4all.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.dekar.qr4all.models.ItemEntity;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item ORDER BY updated_at")
    List<ItemEntity> loadAllItems();

    @Query("SELECT * FROM item WHERE id = :id ORDER BY updated_at")
    ItemEntity loadById(int id);

    @Insert
    void insertItem(ItemEntity itemEntity);

    @Update
    void updateItem(ItemEntity itemEntity);

    @Delete
    void deleteItem(ItemEntity itemEntity);


}
