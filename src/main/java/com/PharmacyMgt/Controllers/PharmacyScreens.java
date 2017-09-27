package com.PharmacyMgt.Controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by Viktor on 8/14/2017.
 *
 */

public enum PharmacyScreens implements BaseEnum{

    DASHBOARD_SCREEN("MainView", "/com/PharmacyMgt/pharView_Dashboard.fxml"),
    PHARMACY_BILLING_SCREEN("BillingView", "/com/PharmacyMgt/pharView_billing_new.fxml"),
    PHARMACY_STOCK_SCREEN ("StockView", "/com/PharmacyMgt/pharView_Stock.fxml"),
    PHARMACY_MESSAGE_SCREEN ("MessageView", "/com/PharmacyMgt/pharView_Message.fxml"),
    PHARMACY_PAYMENT_SCREEN ("InvoiceCheckView", "/com/PharmacyMgt/pharView_InvoiceCheck.fxml"),
    PHARMACY_EXPIRY_DATE_SCREEN("ExpiryDateView", "/com/PharmacyMgt/pharView_ExpiryDate.fxml") ;



    String path;
    String id;

    PharmacyScreens(String id, String path) {
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
