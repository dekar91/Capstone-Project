package ru.dekar.qr4all.ui;

import android.app.Fragment;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.dekar.qr4all.AppExecutors;
import ru.dekar.qr4all.R;
import ru.dekar.qr4all.database.AppDatabase;
import ru.dekar.qr4all.database.UpdateItemViewModel;
import ru.dekar.qr4all.database.UpdateItemViewModelFactory;
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
    public View rootView;


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
            itemId = Integer.parseInt(getArguments().getString(ARG_ITEM_ID));
        }

    }

    public void updateItem(View rootView) {
        String newName = ((EditText) (rootView.findViewById(R.id.inputItemName))).getText().toString();
        String newDetails = ((EditText) (rootView.findViewById(R.id.inputItemDetails))).getText().toString();
        if (mItemEntity != null && (mItemEntity.getName().equals(newName) || mItemEntity.getDetails().equals(newDetails))) {
            mItemEntity.setName(newName);
            mItemEntity.setDetails(newDetails);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDatabase = AppDatabase.getsInstance(getContext());

                mDatabase.itemDao().updateItem(mItemEntity);
            }
        });
        }
    }

    public void updateUi(final View rootView, final int itemId) {
        final AppCompatActivity act = (AppCompatActivity) getActivity();

        AppDatabase mDb = AppDatabase.getsInstance(act);

        UpdateItemViewModelFactory factory = new UpdateItemViewModelFactory(mDb, itemId);

        final UpdateItemViewModel viewModel = ViewModelProviders.of(act, factory).get(UpdateItemViewModel.class);
        viewModel.getItem().observe(act, new Observer<ItemEntity>() {
            @Override
            public void onChanged(@Nullable ItemEntity itemEntity) {
                if (itemEntity != null) {
                    mItemEntity = itemEntity;

                    ((MultiAutoCompleteTextView) rootView.findViewById(R.id.inputItemDetails)).setText(mItemEntity.getDetails());
                    ((TextInputEditText) rootView.findViewById(R.id.inputItemName)).setText(mItemEntity.getName());

                    Picasso.get().load(Uri.parse(mItemEntity.getImageUrl())).into((ImageView) rootView.findViewById(R.id.itemPhoto));


                    Button img = rootView.findViewById(R.id.showQrCode);
                    img.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity((new Intent(getActivity(), ShowQrActivity.class)).putExtra("itemId", mItemEntity.getId()));
                                }
                            });


                    // Update widget
                    UpdateItemService.startUpdateItemService(getContext(), mItemEntity);
                } else  {
                    Toast toast = Toast.makeText(getActivity(), R.string.barcode_failure, Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getActivity(), ItemListActivity.class);
                    getContext().startActivity(intent);
                }
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        updateItem(rootView);
    }

    @Override
    public void onPause() {
        super.onPause();
        updateItem(rootView);

    }

    @Override
    public void onStop() {
        super.onStop();

        updateItem(rootView);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.item_detail, container, false);
        updateUi(rootView, itemId);

        return rootView;
    }

}