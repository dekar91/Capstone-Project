package ru.dekar.qr4all.database;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class UpdateItemViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private AppDatabase db;
    private int itemId;

    public UpdateItemViewModelFactory(AppDatabase database, int mitemId)
    {
        db = database;
        itemId = mitemId;
    }

    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new UpdateItemViewModel(db, itemId);
    }
}
