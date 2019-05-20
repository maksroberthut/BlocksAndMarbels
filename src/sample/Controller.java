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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;



public class Controller {


  @FXML
    public Pane target;
   @FXML
    public Pane targetEbene1;
   @FXML
   public StackPane element1;
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





@FXML
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

    /* Meysa */
  /*  void insertImage(Image i, Shape rectangle){


        element1.setFill(new ImagePattern(new Image("/resource/Ele1.png")));

    }*/



    @FXML
    public void drag(MouseEvent mouseEvent) {

        /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard db = element1.startDragAndDrop(TransferMode.ANY);
   //     element1.setFill(new ImagePattern(new Image("/resource/Ele1.png")));

        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString("rectangle");
        db.setContent(content);
        SnapshotParameters param = new SnapshotParameters();
        param.setFill(Color.TRANSPARENT);
        db.setDragView(element1.snapshot(param, null));

        mouseEvent.consume();


        System.out.println("Drag detected");
    }

@FXML
    public void dragOver(DragEvent dragEvent) {

        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node
         * and if it has a string data */
        if (dragEvent.getGestureSource() != target &&
                dragEvent.getDragboard().hasString()) {
            /* allow for moving */
            dragEvent.acceptTransferModes(TransferMode.MOVE);
//                    target.setStyle("-fx-background-color: silver");
        }

        dragEvent.consume();
        System.out.println("Drag over");
    }




@FXML
    public void dragEntered(DragEvent dragEvent) {

        /* the drag-and-drop gesture entered the target */
        /* show to the user that it is an actual gesture target */
        if (dragEvent.getGestureSource() != target &&
                dragEvent.getDragboard().hasString()) {

           // target.setStyle("-fx-background-color: silver");
        }

        dragEvent.consume();
        System.out.println("Drag entered");
    }



@FXML
    public void dragExited(DragEvent dragEvent) {
        /* mouse moved away, remove the graphical cues */
//      target.setFill(Color.BLACK);
      //  target.setStyle("-fx-background-color: white");
        dragEvent.consume();
        System.out.println("Drag exited");
    }

@FXML
    public void dragDropped(DragEvent dragEvent) {
        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;


        if (db.hasString()) {
//                    target.setText(db.getString());
            if (db.getString().equals("rectangle")) {


                         Rectangle c = new Rectangle(dragEvent.getX(), dragEvent.getY(), 72, 72);
                         c.setFill(new ImagePattern(new Image("/resource/Ele1.png")));


                if (dragEvent.getTarget() instanceof Pane) {
                    Pane targetPane = (Pane) dragEvent.getTarget();
                    targetPane.getChildren().add(c);

                }
                success = true;
                dragEvent.setDropCompleted(success);

                dragEvent.consume();
                System.out.println("Drag Dropped");
            }
            /* let the source know whether the string was successfully
             * transferred and used */
            ;

        }

    }

    @FXML
    public void dragDone(DragEvent dragEvent) {
        /* the drag and drop gesture ended */
        /* if the data was successfully moved, clear it */
//                if (dragEvent.getTransferMode() == TransferMode.MOVE) {
//
//                    elementNeu.setRadius(10);
//                }
        dragEvent.consume();
        System.out.println("Drag done");
    }

}
