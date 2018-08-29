package ru.dekar;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import ru.dekar.qr4all.R;
import ru.dekar.qr4all.services.UpdateItemService;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static String itemName;
    static String itemDetails;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        if(!itemDetails.isEmpty() && !itemName.isEmpty())
        {
            views.setTextViewText(R.id.widget_task_title, itemName);
            views.setTextViewText(R.id.widget_task_description, itemDetails);
        }  else {
            views.setTextViewText(R.id.widget_task_title,context.getResources().getString(R.string.widget_empty_title));
            views.setTextViewText(R.id.widget_task_description, context.getResources().getString(R.string.widget_empty_details));
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, AppWidget.class));

        final String action = intent.getAction();

        if (action.equals(UpdateItemService.INTENT_NAME)) {
            itemName = intent.getStringExtra(UpdateItemService.ITEM_NAME);
            itemDetails = intent.getStringExtra(UpdateItemService.ITEM_DETAILS);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.layout.app_widget);
            //Now update all widgets
            AppWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }
}

