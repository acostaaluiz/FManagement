package com.apptest.accenture.accentureinterview.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;

import com.apptest.accenture.accentureinterview.model.InvestmentModel;
import com.apptest.accenture.accentureinterview.presenter.JSONData;


import org.json.JSONException;


import java.io.IOException;

/**
 * Created by fcost on 26/06/2018.
 */

public class FragmentInvestment extends android.support.v4.app.Fragment{

    public static String data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        InitApplication initApplication = new InitApplication();
        initApplication.execute();

        return inflater.inflate(R.layout.fragment_investment, container, false);
    }

    public class InitApplication extends AsyncTask<String, String, String> {

        InvestmentModel investmentModel;

        @Override
        protected String doInBackground(String... strings) {

            try {
                JSONData jsonData = new JSONData();
                investmentModel = jsonData.getInvestmentData();
            } catch (JSONException e) {
                e.printStackTrace();
                return e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result.equals("OK"))
                loadScreen(investmentModel);
        }
    }

    public void loadScreen(InvestmentModel investmentModel){

        TextView txtTitle = getActivity().findViewById(R.id.txtTitle);
        TextView txtFundName = getActivity().findViewById(R.id.txtFundName);
        TextView txtWhatIs = getActivity().findViewById(R.id.txtWhatIs);
        TextView txtDefinition = getActivity().findViewById(R.id.txtDefinition);
        TextView txtRiskTitle = getActivity().findViewById(R.id.txtRiskTitle);
        TextView txtInfoTitle = getActivity().findViewById(R.id.txtInfoTitle);
        TextView txtValueCDIMonth = getActivity().findViewById(R.id.txtValueCDIMonth);
        TextView txtValueFundMonth = getActivity().findViewById(R.id.txtValueFundMonth);
        TextView txtValueCDIYear = getActivity().findViewById(R.id.txtValueCDIYear);
        TextView txtValueFundYear = getActivity().findViewById(R.id.txtValueFundYear);
        TextView txtValueCDI12Months = getActivity().findViewById(R.id.txtValueCDI12Months);
        TextView txtValueFund12Months = getActivity().findViewById(R.id.txtValueFund12Months);
        TextView txtInfoValue1 =  getActivity().findViewById(R.id.txtInfoValue1);
        TextView txtInfoValue1Name =  getActivity().findViewById(R.id.txtInfoValue1Name);
        TextView txtInfoValue2 =  getActivity().findViewById(R.id.txtInfoValue2);
        TextView txtInfoValue2Name =  getActivity().findViewById(R.id.txtInfoValue2Name);
        TextView txtInfoValue3 =  getActivity().findViewById(R.id.txtInfoValue3);
        TextView txtInfoValue3Name =  getActivity().findViewById(R.id.txtInfoValue3Name);
        TextView txtInfoValue4 =  getActivity().findViewById(R.id.txtInfoValue4);
        TextView txtInfoValue4Name =  getActivity().findViewById(R.id.txtInfoValue4Name);
        TextView txtInfoValue5 =  getActivity().findViewById(R.id.txtInfoValue5);
        TextView txtInfoValue5Name =  getActivity().findViewById(R.id.txtInfoValue5NameTeste);
        TextView txtInfoValue6 =  getActivity().findViewById(R.id.txtInfoValue6);
        TextView txtInfoValue6Name =  getActivity().findViewById(R.id.txtInfoValue6Name);
        TextView txtInfoValue7 =  getActivity().findViewById(R.id.txtInfoValue7);
        TextView txtInfoValue7Name =  getActivity().findViewById(R.id.txtInfoValue7Name);
        TextView txtDownInfoValue1 =  getActivity().findViewById(R.id.txtDownInfoValue1);
        TextView txtDownInfoValue1Name =  getActivity().findViewById(R.id.txtDownInfoValue1Name);
        TextView txtDownInfoValue2 =  getActivity().findViewById(R.id.txtDownInfoValue2);
        TextView txtDownInfoValue2Name =  getActivity().findViewById(R.id.txtDownInfoValue2Name);
        TextView txtDownInfoValue3 =  getActivity().findViewById(R.id.txtDownInfoValue3);
        TextView txtDownInfoValue3Name =  getActivity().findViewById(R.id.txtDownInfoValue3Name);
        TextView txtDownInfoValue4 =  getActivity().findViewById(R.id.txtDownInfoValue4);
        TextView txtDownInfoValue4Name =  getActivity().findViewById(R.id.txtDownInfoValue4Name);
        TextView txtDownInfoValue5 =  getActivity().findViewById(R.id.txtDownInfoValue5);
        TextView txtDownInfoValue5Name =  getActivity().findViewById(R.id.txtDownInfoValue5Name);

        txtTitle.setText(investmentModel.getTitle());
        txtFundName.setText(investmentModel.getFundName());
        txtWhatIs.setText(investmentModel.getWhatIs());
        txtDefinition.setText(investmentModel.getDefinition());
        txtRiskTitle.setText(investmentModel.getRiskTitle());
        txtInfoTitle.setText(investmentModel.getInfoTitle());
        txtValueCDIMonth.setText(investmentModel.getCdiMonth());
        txtValueFundMonth.setText(investmentModel.getFundMonth());
        txtValueCDIYear.setText(investmentModel.getCdiYear());
        txtValueFundYear.setText(investmentModel.getFundYear());
        txtValueCDI12Months.setText(investmentModel.getCdiMonthyear());
        txtValueFund12Months.setText(investmentModel.getFundMonthyear());
        txtInfoValue1.setText(investmentModel.getInfoValue1());
        txtInfoValue1Name.setText(investmentModel.getInfoValue1Name());
        txtInfoValue2.setText(investmentModel.getInfoValue2());
        txtInfoValue2Name.setText(investmentModel.getInfoValue1Name());
        txtInfoValue3.setText(investmentModel.getInfoValue3());
        txtInfoValue3Name.setText(investmentModel.getInfoValue3Name());
        txtInfoValue4.setText(investmentModel.getInfoValue4());
        txtInfoValue4Name.setText(investmentModel.getInfoValue4Name());
        txtInfoValue5.setText((investmentModel.getInfoValue5()));
        txtInfoValue5Name.setText(investmentModel.getInfoValue5Name());
        txtInfoValue6.setText(investmentModel.getInfoValue6());
        txtInfoValue6Name.setText(investmentModel.getInfoValue6Name());
        txtInfoValue7.setText(investmentModel.getInfoValue7());
        txtInfoValue7Name.setText(investmentModel.getInfoValue7Name());
        txtDownInfoValue1.setText(investmentModel.getDownInfoValue1());
        txtDownInfoValue1Name.setText(investmentModel.getDownInfoValue1Name());
        txtDownInfoValue2.setText(investmentModel.getDownInfoValue2());
        txtDownInfoValue2Name.setText(investmentModel.getDownInfoValue2Name());
        txtDownInfoValue3.setText(investmentModel.getDownInfoValue3());
        txtDownInfoValue3Name.setText(investmentModel.getDownInfoValue3Name());
        txtDownInfoValue4.setText(investmentModel.getDownInfoValue4());
        txtDownInfoValue4Name.setText(investmentModel.getDownInfoValue4Name());
        txtDownInfoValue5.setText(investmentModel.getDownInfoValue5());
        txtDownInfoValue5Name.setText(investmentModel.getDownInfoValue5Name());
    }
}
