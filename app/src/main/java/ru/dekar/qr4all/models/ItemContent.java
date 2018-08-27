package ru.dekar.qr4all.models;

import java.util.ArrayList;
import java.util.Date;
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
    public static final List<ItemModel> ITEMS = new ArrayList<ItemModel>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ItemModel> ITEM_MAP = new HashMap<String, ItemModel>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(ItemModel item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ItemModel createDummyItem(int position) {
        return new ItemModel(String.valueOf(position), "Item " + position, makeDetails(position), "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", "https://sun9-2.userapi.com/c848528/v848528244/5e0b9/jeKl_8DcVoY.jpg", null);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ItemModel {
        public final String id;
        public final String name;
        public final String details;
        public final String imageUrl;
        public final String codeUrl;
        public final Date updatedAt;

        public ItemModel(String id, String name, String details, String imageUrl, String codeUrl, Date updatedAt) {
            this.id = id;
            this.name = name;
            this.details = details;
            this.imageUrl = imageUrl;
            this.codeUrl = codeUrl;

            if(null== updatedAt)
                this.updatedAt = new Date();
            else
                this.updatedAt = updatedAt;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
