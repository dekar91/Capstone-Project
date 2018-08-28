package ru.dekar.qr4all.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

import ru.dekar.qr4all.AppExecutors;
import ru.dekar.qr4all.R;
import ru.dekar.qr4all.database.AppDatabase;
import ru.dekar.qr4all.models.ItemContent;
import ru.dekar.qr4all.models.ItemEntity;
import ru.dekar.qr4all.services.UpdateItemService;

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
    private int itemId;

    public ItemEntity mItemEntity;


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
            itemId =  Integer.parseInt(getArguments().getString(ARG_ITEM_ID));
        }

    }

    public void updateItem(View rootView)
    {
        mItemEntity.setName( ((EditText) rootView.findViewById(R.id.inputItemName)).getText().toString());
        mItemEntity.setDetails( ((EditText) rootView.findViewById(R.id.inputItemDetails)).getText().toString());
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDatabase = AppDatabase.getsInstance(getContext());

                mDatabase.itemDao().updateItem(mItemEntity);
            }
        });

    }

    public void updateUi(final View rootView, final int itemId)
    {
        final Activity act = getActivity();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDatabase = AppDatabase.getsInstance(getContext());

                 final ItemEntity mItem = mDatabase.itemDao().loadById(itemId);


                if(mItem != null)
                {
                    mItemEntity = mItem;

                    ((MultiAutoCompleteTextView) rootView.findViewById(R.id.inputItemDetails)).setText(mItem.getDetails());
                    ((TextInputEditText) rootView.findViewById(R.id.inputItemName)).setText(mItem.getName());

                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(Uri.parse(mItem.getImageUrl())).into((ImageView) rootView.findViewById(R.id.itemPhoto));

                        }
                    });

                    Button img = rootView.findViewById(R.id.showQrCode);
                    img.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity((new Intent(getActivity(), ShowQrActivity.class)).putExtra("itemId", mItem.getId()));
                                }
                            });


                    // Update widget
                    UpdateItemService.startUpdateItemService(getContext(), mItem);
                }
                }

            });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.item_detail, container, false);

        EditText eName = (EditText) rootView.findViewById(R.id.inputItemName);
        EditText eDetails = (EditText)  rootView.findViewById(R.id.inputItemDetails);

        eName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateItem(rootView);

            }
        });


        eDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateItem(rootView);

            }
        });


        updateUi(rootView, itemId);

        return rootView;
    }

}