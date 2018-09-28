package com.apptest.accenture.accentureinterview.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;

import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mExpandableListTitle;
    private Map<String, List<String>> mExpandableListDetail;
    private LayoutInflater mLayoutInflater;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       Map<String, List<String>> expandableListDetail) {
        mContext = context;
        mExpandableListTitle = expandableListTitle;
        mExpandableListDetail = expandableListDetail;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }

        TextView expandedListTextView = convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        expandedListTextView.setTypeface(null, Typeface.BOLD);

        if (expandedListText.equals("Cadastrar Cartão"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_credit_card, 0, 0, 0);
        else if (expandedListText.equals("Listar Cartões"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list, 0, 0, 0);
        else if (expandedListText.equals("Cadastrar Ctga. Entrada"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder, 0, 0, 0);
        else if (expandedListText.equals("Cadastrar Entrada"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_income, 0, 0, 0);
        else if (expandedListText.equals("Listar Entradas"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list, 0, 0, 0);
        else if (expandedListText.equals("Cadastrar Ctga. Despesa"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder, 0, 0, 0);
        else if(expandedListText.equals("Cadastrar Despesa"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_expense, 0, 0, 0);
        else if(expandedListText.equals("Listar Despesas"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list, 0, 0, 0);
        else if(expandedListText.equals("Cadastrar Item"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_shopping_bag, 0, 0, 0);
         else if(expandedListText.equals("Listar Itens"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list, 0, 0, 0);
         else if(expandedListText.equals("Aprovar Usuário"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_manager, 0, 0, 0);
         else if(expandedListText.equals("Logout"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_logout, 0, 0, 0);
         else if(expandedListText.equals("Cadastrar Cliente"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_customer, 0, 0, 0);
         else if(expandedListText.equals("Listar Clientes"))
            expandedListTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list, 0, 0, 0);

        expandedListTextView.setCompoundDrawablePadding(64);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return mExpandableListDetail.get(mExpandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return mExpandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return mExpandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listTitleTextView = convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
