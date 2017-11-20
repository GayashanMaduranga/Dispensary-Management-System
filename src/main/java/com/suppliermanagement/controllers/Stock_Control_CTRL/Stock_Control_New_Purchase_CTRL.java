package com.suppliermanagement.controllers.Stock_Control_CTRL;

import com.EntityClasses.*;
import com.common.ConfirmDialog;
import com.common.ScreenController;
import com.common.SessionListener;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.main.controllers.MainScreenController;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Naveen Luke Fernando on 2017-09-20.
 */
public class Stock_Control_New_Purchase_CTRL implements SessionListener, Initializable {

    Session session;

    private MainScreenController mainScreenController;

    private PharmacyItem ph;



    private double orderTotal;

    SupplyOrder supplyOrder = new SupplyOrder();

    ObservableList<Supplier> pharmacySupplierList = FXCollections.observableArrayList();
    ObservableList<Supplier> equipmentSupplierList = FXCollections.observableArrayList();
    ObservableList<Supplier> bothSupplierList = FXCollections.observableArrayList();
    ObservableList<Item> equiList = FXCollections.observableArrayList();
    ObservableList<PharmacyItem> productlist = FXCollections.observableArrayList();
    TreeItem<PharmacyItem> root;
    TreeItem<Item> root2;

    PharmacyBatch pB;

    @FXML
    private Label supplier_name;

    @FXML
    private Label sup_addr;

    @FXML
    private Label o_type;

    @FXML
    private Spinner qty_spin;

    @FXML
    private TextField tot;

    @FXML
    private Button add_item_btn;

    @FXML
    private Button clr_btn;

    @FXML
    private Button rm_btn;

    @FXML
    private JFXTreeTableView<PharmacyBatch> order_table;



    @FXML
    private ComboBox<Supplier> suppCombo;

    @FXML
    private OctIconView searchGlyph;

    @FXML
    private ComboBox<String> orderTypeCombo;

    @FXML
    private JFXTreeTableView<PharmacyItem> pick_item;

//    @FXML
//    private JFXTreeTableView<Item> pick_item;

    @FXML
    private Label item_name;


    @FXML
    private Button btnNewSupplier;

    @FXML
    private Button btnCreateOrder;

    @FXML
    private Button btnCancelOrder;

    @FXML
    private Label email;

    @FXML
    private Label date_sup;

    @FXML
    private Label add_lbl;

    @FXML
    private Label o_type_lbl;

    @FXML
    private TextField cost_p;




    @FXML
    public void populateSupplierCombo(){

        if(orderTypeCombo.getSelectionModel().getSelectedItem().equals("Pharmacy")){

            suppCombo.setItems(pharmacySupplierList);


            session = ScreenController.getSession();
            session.flush();
            session.beginTransaction();
            Query productNameQuery = session.createQuery("select s from PharmacyItem  s");
            List<PharmacyItem> product = productNameQuery.list();
            session.getTransaction().commit();


            productlist.clear();
            pick_item.refresh();
            for (PharmacyItem s : product) {

                productlist.add(s);
            }




            //*************** Generating JFX Tree table for item Table **********
            JFXTreeTableColumn<PharmacyItem, String> pro_name = new JFXTreeTableColumn<>("Product Name");
            pro_name.setCellValueFactory(param -> param.getValue().getValue().itemNameProperty());

            JFXTreeTableColumn<PharmacyItem, String> pro_brand = new JFXTreeTableColumn<>("Brand");
            pro_brand.setCellValueFactory(param -> param.getValue().getValue().brandProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_mrp = new JFXTreeTableColumn<>("Manufacure Rate");
            pro_mrp.setCellValueFactory(param -> param.getValue().getValue().MRPProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_reorder = new JFXTreeTableColumn<>("ReOrder Level");
            pro_reorder.setCellValueFactory(param -> param.getValue().getValue().reorderLevelProperty());

            JFXTreeTableColumn<PharmacyItem, Number> pro_stock = new JFXTreeTableColumn<>("Stock Level");
            pro_stock.setCellValueFactory(param -> param.getValue().getValue().stockProperty());

            //pick_item.getColumns().setAll(null, null,null, null, null);
            //pick_item.setRoot(null);

            root = new RecursiveTreeItem<PharmacyItem>(productlist, RecursiveTreeObject::getChildren);

            pick_item.getColumns().setAll(pro_name, pro_brand, pro_mrp, pro_reorder, pro_stock);
            pick_item.setRoot(root);
            pick_item.setShowRoot(false);


        }else if(orderTypeCombo.getSelectionModel().getSelectedItem().equals("Equipment")){

            suppCombo.setItems(equipmentSupplierList);

            session = ScreenController.getSession();
            session.beginTransaction();
            Query equipmentQuery = session.createQuery("select e from Item e where ITEM_TYPE = 'Equipment'");
            List<Item> equipments = equipmentQuery.list();
            session.getTransaction().commit();

            equiList.clear();
            pick_item.refresh();
            for (Item p : equipments){

                equiList.add(p);
            }

            //Create Table
            //Create column

            JFXTreeTableColumn<Item, String> nameCol =  new JFXTreeTableColumn<>("Name");
            nameCol.setCellValueFactory(param -> ((Equipment)param.getValue().getValue()).equipmentNameProperty());

            JFXTreeTableColumn<Item, Number> IDCol =  new JFXTreeTableColumn<>("ID");
            IDCol.setCellValueFactory(param -> param.getValue().getValue().itemIDProperty());

            JFXTreeTableColumn<Item, Number> stockCol =  new JFXTreeTableColumn<>("Stock");
            stockCol.setCellValueFactory(param -> param.getValue().getValue().stockProperty());


            root2 = new RecursiveTreeItem<Item>(equiList, RecursiveTreeObject::getChildren);

           // pick_item.getColumns().setAll(IDCol, nameCol, stockCol);
            pick_item.setRoot(root);
            pick_item.setShowRoot(false);







        }
    }

    ObservableList<PharmacyBatch> orderList = FXCollections.observableArrayList();
    TreeItem<PharmacyBatch> rootPharm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        session = ScreenController.getSession();
        orderTypeCombo.getItems().setAll("Pharmacy", "Equipment","Both");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateview = new Date();
        date_sup.setText(formatter.format(dateview));

        session.beginTransaction();
        Query supplierPharmacyQuery = session.createQuery("select s from Supplier s where s.type = 'Pharmaceutical'");
        //noinspection unchecked
        List<Supplier> pharmaList = supplierPharmacyQuery.list();
        session.getTransaction().commit();
        pharmacySupplierList.addAll(pharmaList);
        //suppCombo.setItems(pharmacySupplierList);

        session.beginTransaction();
        Query supplierEquipmentQuery = session.createQuery("select s from Supplier s where s.type = 'Equipment'");
        //noinspection unchecked
        List<Supplier> equipList = supplierEquipmentQuery.list();
        session.getTransaction().commit();
        equipmentSupplierList.addAll(equipList);

        session.beginTransaction();
        Query supplierBothQuery = session.createQuery("select s from Supplier s where s.type = 'Both'");
        //noinspection unchecked
        List<Supplier> bothList = supplierBothQuery.list();
        session.getTransaction().commit();
        bothSupplierList.addAll(bothList);

//        SpinnerValueFactory <Integer> qty = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
//        this.qty_spin.setValueFactory(qty);
//        qty_spin.setEditable(true);

        JFXTreeTableColumn<PharmacyBatch, String> OrderItemNameCol =  new JFXTreeTableColumn<>("Name");
        OrderItemNameCol.setCellValueFactory(param -> (param.getValue().getValue()).getPharmacyItem().itemNameProperty());

        JFXTreeTableColumn<PharmacyBatch, Number> OrderItemStockCol =  new JFXTreeTableColumn<>("ID");
        OrderItemStockCol.setCellValueFactory(param -> param.getValue().getValue().quantityProperty());

        JFXTreeTableColumn<PharmacyBatch, Number> OrderItemPrice =  new JFXTreeTableColumn<>("Stock");
        OrderItemPrice.setCellValueFactory(param -> param.getValue().getValue().purchasingPriceProperty());

        rootPharm = new RecursiveTreeItem<>(orderList, RecursiveTreeObject::getChildren);

        order_table.getColumns().setAll(OrderItemNameCol, OrderItemStockCol, OrderItemPrice);
        order_table.setRoot(rootPharm);
        order_table.setShowRoot(false);

        pB = new PharmacyBatch();

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void setMainController(SessionListener controller) {
        this.mainScreenController = (MainScreenController)controller;
    }

    @FXML
    void orderTypeSelected(ActionEvent event)  {

        populateSupplierCombo();
        System.out.println("iooooo");

    }


    @FXML
    void sup_select(ActionEvent event) {

        String supname =  suppCombo.getSelectionModel().getSelectedItem().toString();

        System.out.println(supname);

        session = ScreenController.getSession();
        session.beginTransaction();
        Query sup = session.createQuery("from Supplier s where s.supname = :name ");
        sup.setParameter("name",supname);


        List<Supplier> su = sup.list();
        session.getTransaction().commit();




        for (Supplier s : su){

            System.out.println(s.getSupname());
            System.out.print(s.getEmail());

            supplier_name.setText(s.getSupname());
            email.setText(s.getEmail());
            o_type.setText(s.getType());
            sup_addr.setText(s.getAddress());
            add_lbl.setText("Address :");
            o_type_lbl.setText("Order Type :");

        }




    }


    @FXML
    void cost_cal(KeyEvent event) {

        System.out.println("clicked");
        double u = Double.parseDouble( qty_spin.getValue().toString());
        double c = Double.parseDouble(cost_p.getText());
        //System.out.println(c);
        double total = c * u;
        tot.setText(String.valueOf(total));
    }


    @FXML
    void qty_clicked(MouseEvent event) {
        System.out.println("hollaaa");
        int u =Integer.parseInt( qty_spin.getValue().toString());
        System.out.println(u);

        double i = Double.parseDouble( qty_spin.getValue().toString());
        double c = Double.parseDouble(cost_p.getText());
        //System.out.println(c);
        double total = c * i;
        tot.setText(String.valueOf(total));
    }

    @FXML
    void setField() {

        ph = pick_item.getSelectionModel().getSelectedItem().getValue();
        item_name.setText(ph.getItemName().toUpperCase());
        SpinnerValueFactory <Integer> qty = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        this.qty_spin.setValueFactory(qty);
        qty_spin.setEditable(true);

    }

    @FXML
    void addItemToOrder(){

        pB = new PharmacyBatch();
        pB.setPharmacyItem(pick_item.getSelectionModel().getSelectedItem().getValue());
        pB.setQuantity(Integer.parseInt(qty_spin.getValue().toString()));
        pB.setPurchasingPrice(Double.parseDouble(cost_p.getText()));

        orderList.add(pB);
        order_table.refresh();

        orderTotal += Double.parseDouble(tot.getText());

        supplyOrder.getPharmacyBatches().add(pB);
        supplyOrder.setTotal(orderTotal);
        supplyOrder.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        supplyOrder.setSupplier(suppCombo.getSelectionModel().getSelectedItem());

    }

    @FXML
    void createOrder(){



        if (ConfirmDialog.show("", " Do u want create this order?")) {

            session.flush();
            session.clear();
            session.beginTransaction();
            session.save(supplyOrder);
            session.getTransaction().commit();

            supplyOrder = new SupplyOrder();
            orderTotal = 0.0;
            orderList.clear();
            order_table.refresh();


            EmailSend f = new EmailSend("jjnlfernando@gmail.com","Hello ");


        }





//        session.flush();
//        session.clear();
//        session.beginTransaction();
//        session.save(supplyOrder);
//        session.getTransaction().commit();
//
//        supplyOrder = new SupplyOrder();
//        orderTotal = 0.0;
//        orderList.clear();
//        order_table.refresh();
    }
}
