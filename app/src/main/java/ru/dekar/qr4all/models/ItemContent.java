package ru.dekar.qr4all.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final List<ItemEntity> ITEMS = new ArrayList<ItemEntity>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ItemEntity> ITEM_MAP = new HashMap<String, ItemEntity>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ItemEntity item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.getId()), item);
    }

    private static ItemEntity createDummyItem(int position) {
        return new ItemEntity(position, "Item " + position, makeDetails(position), "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", null);
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
