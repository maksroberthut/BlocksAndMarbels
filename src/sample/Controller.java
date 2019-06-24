package sample;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    /* private timestepping; um auf timestepping zu greifen*/


    @FXML
    public Pane target, targetEbene1;
    @FXML
    public Rectangle element1, element2, element3, element4, element5, element6, element7, element8, element9, element10, hatch1, hatch2;
    @FXML
    public Pane loeschElement;
    @FXML
    public Tab ebene1,ebene2, ebene3,ebene4,ebene5;
    @FXML
    public TabPane tapPane;

    int counterTest=0;
    /**
     * Animation
     */
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            //moveBall();
            //vel = moveBall();
            double roundVel = Math.ceil(vel);
            String velstring = Double.toString(roundVel);
            VelocityLabel.setText(velstring);
            counterTest++;
            if(counterTest%100==0){
                updateEbene();


            }

        }
    };


    /*  Ebenenwechsel Methoden ab hier
    * */
    public void setHatchLayerIdentification(Rectangle hatch ){
        hatch.setStrokeDashOffset(0.001);

    }
    public void updateEbene(){
        System.out.println("kugelLayoutX: "+kugel.getCenterX());

        System.out.println("kugelX: "+pathTransition.getNode().getTranslateX());

        if(kugel.getCenterX()>(hatch2.getLayoutX()+15) &&  kugel.getCenterX()>(hatch2.getLayoutX()-15) ) {
            System.out.println("Erstes if erreicht");
            if (kugel.getCenterY() > (hatch2.getLayoutY() + 15) && kugel.getCenterY() > (hatch2.getLayoutY() - 15)) {
                System.out.println("Zweites if erreicht");
                setEineEbeneHinunter();
            }
        }
    }

    public void changeEbeneTo(Tab ebene){
        //ebene3.isSelected();
        tapPane.getSelectionModel().select(ebene);

    }


    public void setEineEbeneHinunter(){
        tapPane.getSelectionModel().selectNext();
    }



    Path path = new Path();
    PathTransition pathTransition = new PathTransition();

    public Circle kugel;
    private double newX, newY;
    public double ballStartX;
    public double ballStarty;
    public double startposition[];


    public String imgUrl;
    public int elemNum, x;

    private double vel;
    private double gravity = 9.81 / 60;
    private double rotation;

    @FXML
    public Slider slider;
    @FXML
    public Label VelocityLabel;


    /**
     * This is a method, that lets us get the slider Value and later multiply it with our
     * velocity to slow the Ball down
     *
     * @return double slider Value
     * @author Maksymilian Hutyra
     */
    @FXML
    public double handleSliderChange() {

        double sliderValue = slider.getValue();
        System.out.println(sliderValue);
        return sliderValue;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        path.getElements().add(new MoveTo(460, 110));
        path.getElements().add(new LineTo(460, 300));
        path.getElements().add(new ArcTo(50, 60, -45, 420, 330, false, true));
        path.getElements().add(new ArcTo(50, 60, 45, 390, 350, false, false));
        path.getElements().add(new LineTo(390, 500));
        path.getElements().add(new ArcTo(50, 60, -45, 350, 535, false, true));
        path.getElements().add(new LineTo(230, 535));
        path.getElements().add(new ArcTo(40, 40, 340, 250, 520, true, true));
        path.getElements().add(new LineTo(250, 600));

        pathTransition.setPath(path);
        pathTransition.setNode(kugel);
        //pathTransition.getNode().getTranslateX();
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(false);




        /* Methoden für Rotation der Elemente fangen hier an */
        element1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem1.png";
                rotation = element1.getRotate();

            }
        });


        element4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem4.png";
                rotation = element4.getRotate();
            }
        });

        element5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem5.png";
                rotation = element5.getRotate();
            }
        });

        element7.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem7.png";
                rotation = element7.getRotate();
            }
        });

        element8.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem8.png";
                rotation = element8.getRotate();
            }
        });

        element9.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem9.png";
                rotation = element9.getRotate();
            }
        });

        element10.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem10.png";
                rotation = element10.getRotate();


            }
        });


        element1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element1.setRotate(element1.getRotate() + 90);


            }
        });

        element4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element4.setRotate(element4.getRotate() + 90);


            }
        });

        element5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element5.setRotate(element5.getRotate() + 90);


            }
        });

        element7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element7.setRotate(element7.getRotate() + 90);


            }
        });

        element8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element8.setRotate(element8.getRotate() + 90);


            }
        });

        element9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element9.setRotate(element9.getRotate() + 90);


            }
        });

        element10.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                element10.setRotate(element10.getRotate() + 90);


            }
        });



    }


    /**
     * Methode for starting the animation and getting the starting position of the ball, so we can reset our animation later
     *
     * @autor Maksymilian Huytra
     */
    @FXML
    public double[] startAnimation() {

       // changeEbene(new Tab());
        ballStartX = kugel.getCenterX();
        ballStarty = kugel.getCenterY();
        pathTransition.setDuration(Duration.millis(30000 * handleSliderChange()));
        pathTransition.setCycleCount(1);
        pathTransition.play();

        startposition = new double[]{ballStartX, ballStarty};

        animationTimer.start();

        return startposition;

    }

    /**
     * Methode for stopping  the animation
     *
     * @autor Maksymilian Hutyra
     */
    @FXML
    public void stopAnimation() {
        animationTimer.stop();
        pathTransition.pause();

    }

    @FXML
    public void resetAnimation() {


        animationTimer.stop();
        pathTransition.setDuration(Duration.millis(30000 * handleSliderChange()));
        pathTransition.playFromStart();


        kugel.setCenterY(startposition[0]);
        kugel.setCenterY(startposition[1]);

        vel = 0;


    }



    @FXML
    public double moveBall() {
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




    /* Drag and Drop Methoden fangen hier an */
    @FXML
    public void drag(MouseEvent mouseEvent) {

        /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard db = element3.startDragAndDrop(TransferMode.MOVE);

        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString("rectangle");
        db.setContent(content);
        SnapshotParameters param = new SnapshotParameters();
        param.setFill(Color.TRANSPARENT);
        db.setDragView(element2.snapshot(param, null));

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
        }

        dragEvent.consume();
        System.out.println("Drag entered");
    }


    @FXML
    public void dragExited(DragEvent dragEvent) {
        /* mouse moved away, remove the graphical cues */

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

            if (db.getString().equals("rectangle")) {

                Rectangle c = new Rectangle(dragEvent.getX(), dragEvent.getY(), 72, 72);
                c.setFill(new ImagePattern(new Image(imgUrl)));
                c.setRotate(rotation);

                List<Rectangle> list = new ArrayList<>();
                list.add(c);

                if (dragEvent.getTarget() instanceof Pane) {
                    Pane targetPane = (Pane) dragEvent.getTarget();
                    targetPane.getChildren().add(c);

                }
                success = true;
                dragEvent.setDropCompleted(success);

                dragEvent.consume();
                System.out.println("Drag Dropped");
            }

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




    /*Delete Methoden fangen hier an*/

    public void loeschDrop(DragEvent dragEvent) {

        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;


        if (db.hasString()) {

            if (db.getString().equals("rectangle")) {

                Rectangle c = new Rectangle(dragEvent.getX(), dragEvent.getY(), 72, 72);
                c.setFill(new ImagePattern(new Image(imgUrl)));
                c.setRotate(rotation);

                if (dragEvent.getTarget() instanceof Pane) {
                    Pane loeschElement = (Pane) dragEvent.getTarget();
                    loeschElement.getChildren().remove(c);

                }
                success = true;
                dragEvent.setDropCompleted(success);

                dragEvent.consume();
                System.out.println("Drag Dropped");
            }

        }

    }

    public void loeschEntered(DragEvent dragEvent) {

        /* the drag-and-drop gesture entered the target */
        /* show to the user that it is an actual gesture target */
        if (dragEvent.getGestureSource() != target &&
                dragEvent.getDragboard().hasString()) {
        }

        dragEvent.consume();
        System.out.println("Drag entered");

    }

    public void loeschOver(DragEvent dragEvent) {

        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node
         * and if it has a string data */
        if (dragEvent.getGestureSource() != target &&
                dragEvent.getDragboard().hasString()) {
            /* allow for moving */
            dragEvent.acceptTransferModes(TransferMode.MOVE);

        }

        dragEvent.consume();
        System.out.println("Drag over");

    }

    public void loeschExited(DragEvent dragEvent) {

        /* mouse moved away, remove the graphical cues */

        dragEvent.consume();
        System.out.println("Drag exited");
        
    }


}









