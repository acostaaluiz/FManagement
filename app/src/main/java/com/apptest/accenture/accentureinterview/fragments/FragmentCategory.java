package com.apptest.accenture.accentureinterview.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.ArrayAdapterCategory;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.presenter.CategoryPresenter;
import com.apptest.accenture.accentureinterview.view.Category;

import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public class FragmentCategory extends android.support.v4.app.Fragment implements Category.View {

    private EditText txtExpenseCategoryValue;
    private Button btnRegister;
    private ListView listViewCategory;
    private ModelCategory modelCategory;
    private Category.Presenter categoryPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_expense_category, container, false);

        listViewCategory = vw.findViewById(R.id.listViewCategory);
        txtExpenseCategoryValue = vw.findViewById(R.id.txtExpenseCategoryValue);
        btnRegister = vw.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expenseCategory = txtExpenseCategoryValue.getText().toString();
                modelCategory = new ModelCategory(expenseCategory, "");

                categoryPresenter.saveExpenseCategory(modelCategory);

            }
        });

        categoryPresenter = new CategoryPresenter(this, getActivity());
        categoryPresenter.loadAllExpenseCategory();

        return vw;
    }

    @Override
    public void expenseCategoryEmptyError() {

        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category));

    }

    @Override
    public void expenseCategoryAlreadyExists() {

        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.category_already_exists));
    }

    @Override
    public void successfullyRegister(ArrayList<ModelCategory> categories) {

        listViewCategory.setAdapter(new ArrayAdapterCategory(getContext(), R.layout.listview_category, categories));
        listViewCategory.setTextFilterEnabled(true);
    }

    @Override
    public void errorRegister() {
        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.error_sqlite_insert));

    }

    private void showDialog(String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
