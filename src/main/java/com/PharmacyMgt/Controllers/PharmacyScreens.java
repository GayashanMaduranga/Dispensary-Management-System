package com.PharmacyMgt.Controllers;

import com.common.BaseEnum;

/**
 * Based on 'MyScreens' class Created by gayashan on 8/14/2017.
 *
 */

public enum PharmacyScreens implements BaseEnum{

    DASHBOARD_SCREEN("MainView", "/com/PharmacyMgt/MainView.fxml"),
    INVOICE_VIEW_SCREEN("ViewInvoice", "/com/PharmacyMgt/InvoiceView.fxml");

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
