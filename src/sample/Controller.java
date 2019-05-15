package sample;

import Physicsengine.Physics;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controller implements Physics {


    public Label ballRollt;
    public GridPane canvas;
    public Label ballSteht;
    public Circle kugel;
    double newX,newY;

    public void starteSpiel(ActionEvent actionEvent) {
        //hier greift das Programm auf die Zeit zu
        System.out.println("Started");
        //https://www.programcreek.com/java-api-examples/?api=javafx.animation.AnimationTimer
        //60 fps
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                moveBall();
            }
        }.start();                                                                  // Was diese?


    }

    //geschwindigkeit und beschleunigung auf wurden initialisiert
    double geschw;
    double beschl = 9.81*1/60;

    public void moveBall(){
        // formel für die geschwindigkeit
        geschw = geschw + beschl;

        //kugel soll für jeden Frame eine neue Position annehmen
        newX = kugel.getCenterX();
        newY = kugel.getCenterY() + geschw;

        kugel.setCenterX(newX);
        kugel.setCenterY(newY);

        System.out.println("geschw: " + geschw);


       /* double finalY=580;
        ball1.setLayoutY(finalY - ball1.getLayoutBounds().getMinY());*/
    }


    public void stoppeBall(ActionEvent actionEvent) {


    }

    public void vonVorne(ActionEvent actionEvent) {

    }
}
