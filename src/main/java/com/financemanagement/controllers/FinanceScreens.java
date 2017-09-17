package com.financemanagement.controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum FinanceScreens implements BaseEnum{

    EXPENSES_SCREEN("Expenses", "com/financemanagement/Expenses.fxml"),
    FINALBALANCE_SCREEN("FinalBalance","com/financemanagement/Final Balance.fxml"),
    FINANCE_MAIN_SCREEN("FinanceMain","com/financemanagement/Finace_Main.fxml"),
    REVENUE_SCREEN("Revenue","com/financemanagement/Revenue.fxml"),
    LOGINFORM_SCREEN("LoginForm","com/financemanagement/LoginForm.fxml");


    String path;
    String id;

    FinanceScreens(String id, String path) {
        this.path = path;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }
}
