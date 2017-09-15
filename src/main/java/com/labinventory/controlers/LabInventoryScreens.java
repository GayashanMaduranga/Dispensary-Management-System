package com.labinventory.controlers;

import com.common.BaseEnum;

/**
 * Created by chamara on 8/14/2017.
 */
public enum LabInventoryScreens implements BaseEnum{



    LAB_EQUIPMENT_SCREEN("updateEmployee", "/com/labinventory/LabEquipment.fxml"),
    LAB_INVENTORY_SCREENS("payroll", "/com/labinventory/LabInventory.fxml"),
    LAB_MACHINE_SCREEN("loan", "/com/labinventory/LabMachine.fxml");


    String path;
    String id;

    LabInventoryScreens(String id, String path) {
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
