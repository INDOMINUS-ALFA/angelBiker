package com.example.angelbiker.ui.dialogs.categories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.location.CategoryAndCountModel;

import java.util.List;
import java.util.Objects;

public class CategoriesCountDialogFragment extends DialogFragment {

    private Context context;
    private List<CategoryAndCountModel> categoriesAndCount;
    private CategoriesCountDialogFragment categoriesCountDialogFragment;

    public CategoriesCountDialogFragment() {
        categoriesCountDialogFragment = this;
    }


    public static CategoriesCountDialogFragment newInstance(Context context, List<CategoryAndCountModel> categoriesAndCount) {
        CategoriesCountDialogFragment dialogFragment = new CategoriesCountDialogFragment();
        dialogFragment.context = context;
        dialogFragment.categoriesAndCount = categoriesAndCount;
        return dialogFragment;
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_categoriescount, null);
        ListView listViewCategories = view.findViewById(R.id.listViewCategories);

        ArrayAdapter<CategoryAndCountModel> adapter = new ArrayAdapter<CategoryAndCountModel>(context, R.layout.listview_item_twotexts, R.id.text1, categoriesAndCount){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.text1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);

                text1.setText(categoriesAndCount.get(position).getCategory().getCategory());
                text2.setText(String.valueOf(categoriesAndCount.get(position).getCount()));

                view.setOnClickListener(v -> {
                    ((CategorySelectionManager) context).onCategorySelected(categoriesAndCount.get(position).getCategory());
                    categoriesCountDialogFragment.dismiss();
                });

                return view;
            }
        };

        listViewCategories.setAdapter(adapter);

        builder.setView(view);
        return builder.create();
    }
}