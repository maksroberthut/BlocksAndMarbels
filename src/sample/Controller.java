package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            moveBall();
        }
    };



    public Label ballRollt;
    public GridPane canvas;
    public Label ballSteht;
    public Circle kugel;
    public TabPane tapPane;

    double newX, newY;



    @FXML
    public Slider slider;


    @FXML
    public void initilize(){

    }


    /**
     *  This is a method, that lets us get the slider Value and later multiply it with our
     *  velocity to slow the Ball down
     * @return double slider Value
     * @author Maksymilian Hutyra
     */
    @FXML
    public  double handleSliderChange(){

        double sliderValue = slider.getValue();
        System.out.println(sliderValue);
        return sliderValue;
    }



    /**
     * Methode for starting our animation
     * @autor Maksymilian Huytra
     */
    @FXML
    public void startAnimation() {
          animationTimer.start();
    }

    /**
     * Methode for stopping  the animation
     * @autor Maksymilian Hutyra
     */
    @FXML
    public void  stopAnimation(){
        animationTimer.stop();
    }
















/*    public void starteSpiel(ActionEvent actionEvent) {
        //hier greift das Programm auf die Zeit zu
        System.out.println("Started");
        //https://www.programcreek.com/java-api-examples/?api=javafx.animation.AnimationTimer
        //60 fps
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                moveBall();
            }
        }.start();
    }*/

    //geschwindigkeit und beschleunigung auf wurden initialisiert
    double geschw;
    double beschl = 9.81*1/60;

    public void moveBall(){
        double sliderValue = handleSliderChange();
        double newVelosity;
        // formel für die geschwindigkeit
        geschw = geschw + beschl;
        newVelosity = geschw * sliderValue;


        //kugel soll für jeden Frame eine neue Position annehmen
        newX = kugel.getCenterX();
        newY = kugel.getCenterY() + geschw;

        kugel.setCenterX(newX);
        kugel.setCenterY(newY);

        System.out.println("geschw: " + newVelosity);


       /* double finalY=580;
        ball1.setLayoutY(finalY - ball1.getLayoutBounds().getMinY());*/
    }

    public void stoppeBall(ActionEvent actionEvent) {

    }

    public void vonVorne(ActionEvent actionEvent) {

    }







}
