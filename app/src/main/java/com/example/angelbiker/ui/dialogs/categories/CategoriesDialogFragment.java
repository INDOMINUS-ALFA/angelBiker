package com.example.angelbiker.ui.dialogs.categories;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.location.CategoryModel;

import java.util.List;
import java.util.Objects;

public class CategoriesDialogFragment extends DialogFragment {

    private Activity activity;
    private List<CategoryModel> categoryModels;
    private int initialSelected;
    private CategoryModel categorySelected;

    public CategoriesDialogFragment() {

    }

    public static CategoriesDialogFragment newInstance(Activity activity, List<CategoryModel> categoryModels, int InitialSelected) {
        CategoriesDialogFragment fragment = new CategoriesDialogFragment();
        fragment.activity = activity;
        fragment.categoryModels = categoryModels;
        fragment.initialSelected = InitialSelected;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_categories, null);

        ListView listViewCategories = view.findViewById(R.id.listViewCategories);
        listViewCategories.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        CategoryModel[] array = categoryModels.toArray(new CategoryModel[0]);
        String[] arrayStrings = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayStrings[i] = array[i].getCategory();
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(activity.getBaseContext(), android.R.layout.simple_list_item_single_choice, arrayStrings);
        listViewCategories.setAdapter(arrayAdapter);
        listViewCategories.setItemChecked(array.length - initialSelected - 1, true);
        listViewCategories.setOnItemClickListener((parent, view1, position, id) -> categorySelected = array[position]);

        view.findViewById(R.id.cancel_button).setOnClickListener(v -> {
            this.dismiss();
        });

        view.findViewById(R.id.confirm_button).setOnClickListener(v -> {
            if (categorySelected != null && listViewCategories.getSelectedItemPosition() != array.length - initialSelected - 1) {
                ((CategorySelectionManager) activity).onCategorySelected(categorySelected);
            }
            this.dismiss();
        });

        builder.setView(view);
        return builder.create();
    }

}