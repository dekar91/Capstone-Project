package ru.dekar.qr4all.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.squareup.picasso.Picasso;

import ru.dekar.qr4all.R;
import ru.dekar.qr4all.models.ItemContent;
import ru.dekar.qr4all.models.ItemEntity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ItemEntity mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            ItemContent mItemContent = new ItemContent(this.getContext());
            mItem = mItemContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((MultiAutoCompleteTextView) rootView.findViewById(R.id.item_description)).setText(mItem.getDetails());
            ((TextInputEditText) rootView.findViewById(R.id.itemName)).setText(mItem.getName());
            Picasso.get().load(Uri.parse(mItem.getImageUrl())).into((ImageView) rootView.findViewById(R.id.itemPhoto));
//            ((ImageView) rootView.findViewById(R.id.co)).setImageURI(mUri);

             Button img = rootView.findViewById(R.id.showQrCode);
            img.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.v("text","text click");
                            startActivity((new Intent(getActivity(), ShowQrActivity.class)).putExtra("itemId", mItem.getId()));
                        }
                    });
        }

        return rootView;
    }

}