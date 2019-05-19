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


    /**
     *
     */
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            moveBall();
            vel = moveBall();
            double roundVel = Math.ceil(vel);
            String velstring = Double.toString(roundVel);
            VelocityLabel.setText(velstring);

        }
    };



    public Circle kugel;
    private double newX, newY;
    public double ballStartX;
    public double ballStarty;
    public double startposition[];

    private double vel;
    private double gravity = 9.81/60;



    @FXML
    public Slider slider;
    @FXML
    public Label VelocityLabel;


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
     * Methode for starting the animation and getting the starting position of the ball, so we can reset our animation later
     * @autor Maksymilian Huytra
     */
    @FXML
    public double[] startAnimation() {

        ballStartX = kugel.getCenterX();
        ballStarty = kugel.getCenterY();

        startposition = new double[]{ballStartX,ballStarty};

        animationTimer.start();

          return startposition;

    }

    /**
     * Methode for stopping  the animation
     * @autor Maksymilian Hutyra
     */
    @FXML
    public void  stopAnimation(){
        animationTimer.stop();
    }
    @FXML
    public void resetAnimation(){


        animationTimer.stop();

        kugel.setCenterY(startposition[0]);
        kugel.setCenterY(startposition[1]);

        vel = 0;



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



    public double moveBall(){
        double sliderValue = handleSliderChange();
        double newVelosity;
        // formel für die geschwindigkeit
        vel = vel + gravity;
        newVelosity = vel * sliderValue;


        //kugel soll für jeden Frame eine neue Position annehmen
        newX = kugel.getCenterX();
        newY = kugel.getCenterY() + vel;

        kugel.setCenterX(newX);
        kugel.setCenterY(newY);

        System.out.println("geschw: " + newVelosity);

        return newVelosity;



    }








}
