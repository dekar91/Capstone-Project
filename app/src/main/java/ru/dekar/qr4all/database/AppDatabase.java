package ru.dekar.qr4all.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import ru.dekar.qr4all.models.ItemEntity;
import ru.dekar.qr4all.models.TokenEntity;

@Database(entities = {ItemEntity.class, TokenEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TokenDao tokenDao();
    public abstract ItemDao itemDao();

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final  String DATABASE_NAME = "qrforall";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context)
    {
        if (sInstance == null)
        {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext()
                ,AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();

            }
        }

        return sInstance;
    }



}
