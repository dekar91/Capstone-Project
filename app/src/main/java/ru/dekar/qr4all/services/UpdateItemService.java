package ru.dekar.qr4all.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import ru.dekar.qr4all.models.ItemEntity;

public class UpdateItemService extends IntentService{

    public static String ITEM_NAME = "ITEM_NAME";
    public static String ITEM_DETAILS = "ITEM_DETAILS";
    public static String INTENT_NAME = "android.appwidget.action.APPWIDGET_PICKITEMS";

    public  UpdateItemService() {super(UpdateItemService.class.toString());}

    public static void startUpdateItemService(Context context, ItemEntity itemEntity)
    {
        Intent intent = new Intent(context, UpdateItemService.class);
        intent.putExtra(ITEM_NAME, itemEntity.getName());
        intent.putExtra(ITEM_DETAILS, itemEntity.getDetails());
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(null != intent)
        {
            String itemName =intent.getStringExtra(ITEM_NAME);
            String itemDetails = intent.getStringExtra(ITEM_DETAILS);
            handleActionUpdateAppWidgets(itemName, itemDetails);
        }
    }

    private void handleActionUpdateAppWidgets(String itemName, String itemDetails)
    {
        Intent intent = new Intent(INTENT_NAME);
        intent.setAction(INTENT_NAME);
        intent.putExtra(ITEM_NAME, itemName);
        intent.putExtra(ITEM_DETAILS, itemDetails);
        sendBroadcast(intent);

    }
}
