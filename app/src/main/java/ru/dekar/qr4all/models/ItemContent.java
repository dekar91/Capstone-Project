package ru.dekar.qr4all.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.dekar.qr4all.AppExecutors;
import ru.dekar.qr4all.database.AppDatabase;
import ru.dekar.qr4all.database.ItemDao;
import ru.dekar.qr4all.ui.MainActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ItemContent {

    /**
     * An array of sample (dummy) items.
     */
    public final List<ItemEntity> ITEMS = new ArrayList<ItemEntity>();

    private static  AppDatabase mDatabase;

    public ItemContent(Context context)
    {

        mDatabase = AppDatabase.getsInstance(context);


    }


    public static ItemEntity createDummyItem(int position) {
        return new ItemEntity(position, "Item " + position, makeDetails(position), "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", new Date());
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
