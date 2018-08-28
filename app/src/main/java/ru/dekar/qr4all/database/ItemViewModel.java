package ru.dekar.qr4all.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import ru.dekar.qr4all.models.ItemEntity;

public class ItemViewModel extends AndroidViewModel {

    private LiveData<List<ItemEntity>> entities;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getsInstance(this.getApplication());
        entities = database.itemDao().loadAllItems();
    }

    public LiveData<List<ItemEntity>> getItems()
    {
        return entities;
    }

}
