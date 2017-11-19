package com.main;

import com.EntityClasses.User;
import com.common.ConfirmDialog;
import com.common.ControlledScreen;
import com.common.ScreenController;
import com.common.SessionListener;
import com.main.controllers.MainScreens;
import db.UserSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

//    private static SessionFactory sessionFactory;
    public static boolean dialogCanceled = true;
    private Session session = UserSession.getSession();


    boolean firstLogin(Session session){

        session.beginTransaction();
        Query query = session.createQuery("from User u");
        List<User> musers = query.list();
        session.getTransaction().commit();

        if(musers.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        if (firstLogin(session)) {

            System.out.println("first Login");
            createFirstLogin(primaryStage);

        }else{

            createLogin(primaryStage);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void createLogin(Stage primaryStage){

        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(MainScreens.LOGIN_SCREEN.getId(), MainScreens.LOGIN_SCREEN.getPath());
        mainContainer.setScreen(MainScreens.LOGIN_SCREEN.getId());
        Parent root = mainContainer.getScreen(MainScreens.LOGIN_SCREEN.getId()).getParent();

        //((SessionListener)mainContainer).setSession(ScreenController.getSession());

        primaryStage.setTitle("Login");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //code to center the stage on-screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

    private void createFirstLogin(Stage primaryStage){

        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(MainScreens.FIRST_ADMIN_SCREEN.getId(), MainScreens.FIRST_ADMIN_SCREEN.getPath());
        mainContainer.setScreen(MainScreens.FIRST_ADMIN_SCREEN.getId());
        Parent root = mainContainer.getScreen(MainScreens.FIRST_ADMIN_SCREEN.getId()).getParent();

        //((SessionListener)mainContainer).setSession(ScreenController.getSession());

        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // stop the close event from happening ~ Damsith
            if(ConfirmDialog.show("Admin", "Are you sure?")){
                primaryStage.close();
                System.exit(0);
            }
        });

        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //code to center the stage on-screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

    public static boolean createFadedWindow(Stage primaryStage, Stage owner, String FXML_location) {

        Parent root = null;
        Object object = new Object();

        try {
            root = FXMLLoader.load(object.getClass().getResource(FXML_location));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(255, 255, 255, 0.6));
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(491);
        primaryStage.setMinHeight(615);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.getIcons().add(new Image("/com/Images/Hospital-management-system.png"));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.showAndWait();

        return dialogCanceled;
    }
}

