package ru.dekar.qr4all.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import ru.dekar.qr4all.R;
import ru.dekar.qr4all.models.ItemEntity;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    public static ItemEntity mItemEntity;
    protected Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.itemId);
        getWindow().setEnterTransition(slide);

        setContentView(R.layout.activity_item_detail);

        getWindow().setExitTransition(new Explode());
        // Show the Up button in the action bar.

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemsdetails, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;
        }

        if(id == R.id.action_tbShowQr && null != mItemEntity)
        {
            Bundle transitionBundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

            startActivity((new Intent(this, ShowQrActivity.class)).putExtra(ItemDetailFragment.ARG_ITEM_ID, mItemEntity.getId()), transitionBundle);
        }
        return super.onOptionsItemSelected(item);
    }
}
