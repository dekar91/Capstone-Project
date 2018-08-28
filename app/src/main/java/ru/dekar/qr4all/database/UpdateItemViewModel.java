package ru.dekar.qr4all.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import ru.dekar.qr4all.models.ItemEntity;

public class UpdateItemViewModel extends ViewModel {

    private LiveData<ItemEntity> entity;

    public UpdateItemViewModel(AppDatabase db, int itemId) {
        entity = db.itemDao().loadById(itemId);
    }

    public LiveData<ItemEntity> getItem()
    {
        return entity;
    }

}
