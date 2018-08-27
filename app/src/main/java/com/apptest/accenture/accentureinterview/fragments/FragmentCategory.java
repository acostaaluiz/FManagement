package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.adapters.RecyclerCategoryAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.presenter.PresenterCategory;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Category;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public class FragmentCategory extends Fragment implements Category.View {

    private EditText txtExpenseCategoryValue;
    private Button btnRegister;
    private RecyclerView myRecyclerView;
    private ModelCategory modelCategory;
    private Category.Presenter categoryPresenter;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_expense_category, container, false);

        //listViewCategory = vw.findViewById(R.id.listViewCategory);

        myRecyclerView = vw.findViewById(R.id.myRecyclerView);
        txtExpenseCategoryValue = vw.findViewById(R.id.txtExpenseValue);
        btnRegister = vw.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expenseCategory = txtExpenseCategoryValue.getText().toString();
                modelCategory = new ModelCategory(expenseCategory, "");

                categoryPresenter.creationExpenseCategoryProcess(modelCategory);

            }
        });

        categoryPresenter = new PresenterCategory(this, getActivity());
        categoryPresenter.loadAllExpenseCategory();

        return vw;
    }

    @Override
    public void expenseCategoryEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category));

    }

    @Override
    public void expenseCategoryAlreadyExists() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.category_already_exists));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                getResources().getString(R.string.error_connection_server));
    }

    @Override
    public void thereIsNoInternetConnection() {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                getResources().getString(R.string.no_internet_connection));
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    @Override
    public void successfullyRegister(final ArrayList<ModelCategory> categories) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(getActivity());
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerCategoryAdapter(categories);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);





        /*listViewCategory.setAdapter(new ArrayAdapterCategory(getActivity(), R.layout.listview_category, categories));
        listViewCategory.setTextFilterEnabled(true);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xEB,
                        0xEC, 0xF2)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listViewCategory.setMenuCreator(creator);

        listViewCategory.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        categoryPresenter.deleteExpenseCategory(categories.get(index));
                        break;
                }

                return false;
            }
        });*/
    }

    @Override
    public void initLoadProgressBar() {

        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {

        progressDialog.dismiss();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
