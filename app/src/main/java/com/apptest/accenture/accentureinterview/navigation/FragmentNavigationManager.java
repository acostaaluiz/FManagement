package com.apptest.accenture.accentureinterview.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.apptest.accenture.accentureinterview.BuildConfig;
import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.LoggedInActivity;
import com.apptest.accenture.accentureinterview.fragments.FragmentCategory;
import com.apptest.accenture.accentureinterview.fragments.FragmentCategoryIncome;
import com.apptest.accenture.accentureinterview.fragments.FragmentCreditCard;
import com.apptest.accenture.accentureinterview.fragments.FragmentExpense;
import com.apptest.accenture.accentureinterview.fragments.FragmentIncome;
import com.apptest.accenture.accentureinterview.fragments.FragmentItem;

/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private LoggedInActivity mActivity;

    public static FragmentNavigationManager obtain(LoggedInActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(LoggedInActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
            .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }

    @Override
    public void showFragmentItem() {
        FragmentItem fragmentItem = new FragmentItem();
        showFragment(fragmentItem, false);
    }

    @Override
    public void showFragmentCreditCard() {
        FragmentCreditCard fragmentCreditCard = new FragmentCreditCard();
        showFragment(fragmentCreditCard, false);
    }

    @Override
    public void showFragmentIncomeCategory() {
        FragmentCategoryIncome fragmentCategoryIncome = new FragmentCategoryIncome();
        showFragment(fragmentCategoryIncome, false);
    }

    @Override
    public void showFragmentIncome() {
        FragmentIncome fragmentIncome = new FragmentIncome();
        showFragment(fragmentIncome, false);
    }

    @Override
    public void showFragmentExpenseCategory() {
        FragmentCategory fragmentCategory = new FragmentCategory();
        showFragment(fragmentCategory, false);
    }

    @Override
    public void showFragmentExpense() {
        FragmentExpense fragmentExpense = new FragmentExpense();
        showFragment(fragmentExpense, false);
    }
}
