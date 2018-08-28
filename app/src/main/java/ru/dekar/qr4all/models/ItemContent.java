package ru.dekar.qr4all.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.dekar.qr4all.AppExecutors;
import ru.dekar.qr4all.database.AppDatabase;
import ru.dekar.qr4all.database.ItemDao;

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

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mDatabase.itemDao().loadAllItems().size() == 0)
                    for (int i = 1; i <= COUNT; i++) {
                        ItemEntity mEntry = createDummyItem(i);
                        mDatabase.itemDao().insertItem(mEntry);

                    }
            }
        });

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
              List<ItemEntity> listEntities = mDatabase.itemDao().loadAllItems();

                for (int i=0; i <  listEntities.size(); i++)
                    addItem(listEntities.get(i));


            }
        });


    }

    /**
     * A map of sample (dummy) items, by ID.
     */
    public final Map<String, ItemEntity> ITEM_MAP = new HashMap<String, ItemEntity>();

    private static final int COUNT = 25;


    private void addItem(ItemEntity item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.getId()), item);
    }

    private static ItemEntity createDummyItem(int position) {
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
