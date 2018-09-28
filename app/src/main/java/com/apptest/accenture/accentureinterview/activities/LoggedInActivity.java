package com.apptest.accenture.accentureinterview.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.CustomExpandableListAdapter;
import com.apptest.accenture.accentureinterview.navigation.FragmentNavigationManager;
import com.apptest.accenture.accentureinterview.navigation.NavigationManager;
import com.apptest.accenture.accentureinterview.navigation.datasource.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoggedInActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private NavigationManager mNavigationManager;

    private Map<String, List<String>> mExpandableListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mExpandableListView = findViewById(R.id.navList);
        mNavigationManager = FragmentNavigationManager.obtain(this);

        initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }

    private void initItems() {
        items = getResources().getStringArray(R.array.item_menu);
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle(R.string.app_name);
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                if (selectedItem.equals("Cadastrar Cartão")) {
                    mNavigationManager.showFragmentCreditCard();
                } else if (selectedItem.equals("Listar Cartões")) {

                    Intent myIntent = new Intent(LoggedInActivity.this, ListCreditCardActivity.class);
                    startActivity(myIntent);

                } else if (selectedItem.equals("Cadastrar Ctga. Entrada")) {
                    mNavigationManager.showFragmentIncomeCategory();
                } else if (selectedItem.equals("Cadastrar Entrada")) {
                    mNavigationManager.showFragmentIncome();
                } else if (selectedItem.equals("Listar Entradas")) {
                    Intent myIntent = new Intent(LoggedInActivity.this, ListIncomeActivity.class);
                    startActivity(myIntent);
                } else if (selectedItem.equals("Cadastrar Ctga. Despesa")) {
                    mNavigationManager.showFragmentExpenseCategory();
                } else if(selectedItem.equals("Cadastrar Despesa")){
                    mNavigationManager.showFragmentExpense();
                } else if(selectedItem.equals("Listar Despesas")){
                    Intent myIntent = new Intent(LoggedInActivity.this, ListExpenseActivity.class);
                    startActivity(myIntent);
                } else if(selectedItem.equals("Cadastrar Item")){
                    mNavigationManager.showFragmentItem();
                } else if(selectedItem.equals("Listar Itens")){
                    Intent myIntent = new Intent(LoggedInActivity.this, ListItemActivity.class);
                    startActivity(myIntent);
                } else if(selectedItem.equals("Cadastrar Cliente")){
                    mNavigationManager.showFragmentCustomer();
                } else if(selectedItem.equals("Listar Clientes")){
                    Intent myIntent = new Intent(LoggedInActivity.this, ListCustomerActivity.class);
                    startActivity(myIntent);
                } else if(selectedItem.equals("Logout")){
                    finish();
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
