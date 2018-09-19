package com.apptest.accenture.accentureinterview.navigation.datasource;

import android.content.Context;
import com.apptest.accenture.accentureinterview.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by msahakyan on 22/10/15.
 */
public class ExpandableListDataSource {

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> itemMenu = Arrays.asList(context.getResources().getStringArray(R.array.item_menu));
        List<String> creditCardMenu = Arrays.asList(context.getResources().getStringArray(R.array.credit_card_menu));
        List<String> incomeCategoryMenu = Arrays.asList(context.getResources().getStringArray(R.array.income_category_menu));
        List<String> incomeMenu = Arrays.asList(context.getResources().getStringArray(R.array.income_menu));
        List<String> expenseCategoryMenu = Arrays.asList(context.getResources().getStringArray(R.array.expense_category_menu));
        List<String> expenseMenu = Arrays.asList(context.getResources().getStringArray(R.array.expense_menu));
        List<String> moreSettingsMenu = Arrays.asList(context.getResources().getStringArray(R.array.more_settings));

        expandableListData.put(context.getResources().getString(R.string.item), itemMenu);
        expandableListData.put(context.getResources().getString(R.string.creditcard), creditCardMenu);
        expandableListData.put(context.getResources().getString(R.string.category_income), incomeCategoryMenu);
        expandableListData.put(context.getResources().getString(R.string.income), incomeMenu);
        expandableListData.put(context.getResources().getString(R.string.expense_category), expenseCategoryMenu);
        expandableListData.put(context.getResources().getString(R.string.expense), expenseMenu);
        expandableListData.put(context.getResources().getString(R.string.more_settings), moreSettingsMenu);

        return expandableListData;
    }
}
