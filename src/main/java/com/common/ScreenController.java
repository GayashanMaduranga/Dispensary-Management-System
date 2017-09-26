package com.common;

import java.io.IOException;
import java.util.HashMap;

import com.employeemanagement.controllers.MyScreens;
import db.UserSession;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;


/**
 * Created by gayashan on 8/13/2017.
 *
 * reference : https://www.youtube.com/watch?v=5GsdaZWDcdY
 */


public class ScreenController extends StackPane{

    private HashMap<String, Node> screens = new HashMap<>();

    private static final Session session = UserSession.getSession();



    public ScreenController() {

        System.out.println("Hello");

    }

    public void addScreen(String name,Node screen){
        screens.put(name, screen);
    }

    public Node getScreen(String name){
        return screens.get(name);
    }

    public boolean loadScreen(String name, String resource){

        System.out.println(resource);
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = loader.load();
            ControlledScreen myScreenController = (ControlledScreen) loader.getController();
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);

            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreen(final String name) {
        if (screens.get(name) != null) {

            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {

                getChildren().remove(0);
                getChildren().add(0, screens.get(name));

            } else
                {

                setOpacity(0.0);
                getChildren().add(screens.get(name));

                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2000), new KeyValue(opacity, 1.0)));

                fadeIn.play();
            }
            return true;
        } else {

            System.out.println("screen hasn't been loaded!!! \n");
            return false;

        }
    }

    public boolean unloadScreen(String name) {

        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }

    }

    public static void changeScreen(ScreenController controller, BaseEnum oldScreen, BaseEnum newScreen){

        controller.loadScreen(newScreen.getId(),newScreen.getPath());
        controller.setScreen(newScreen.getId());
        ((SessionListener)controller).setSession(session);
        controller.unloadScreen(oldScreen.getId());

    }

    public static void changeScreen(BaseEnum screen,Pane content){

        try {

            FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource(screen.getPath()));
            Parent root = loader.load();

                content.getChildren().clear();
                content.getChildren().add(root);


            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void changeScreen(BaseEnum screen,Pane content,SessionListener controller){

        try {

            FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource(screen.getPath()));
            Parent root = loader.load();
            SessionListener listener = (SessionListener)loader.getController();

            listener.setSession(session);
            listener.setMainController(controller);
            content.getChildren().clear();
            content.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return session;
    }


}
