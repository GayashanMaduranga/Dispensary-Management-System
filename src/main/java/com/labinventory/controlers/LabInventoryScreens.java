package com.labinventory.controlers;

import com.common.BaseEnum;

/**
 * Created by chamara on 8/14/2017.
 */
public enum LabInventoryScreens implements BaseEnum{



    LAB_EQUIPMENT_SCREEN("labEquipment","/com/labinventory/labEquipment.fxml"),
    LAB_INVENTORY_SCREENS("labinventory","/com/labinventory/LabInventory.fxml"),
    LAB_MACHINE_SCREEN("labmachine", "/com/labinventory/LabMachine.fxml"),
    LAB_MAINTENAANCE_SCREEN("labmaintance","/com/labinventory/LabMaintance.fxml"); //new added one 13/11
    

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
