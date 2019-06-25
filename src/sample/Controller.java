package sample;

import Elements.Ball;
import Physicsengine.Physics;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
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
import java.util.*;


public class Controller implements Initializable {



    @FXML
    public Pane target, targetEbene1, elemPane;
    @FXML
    public Rectangle element1, element2, element3, element4, element5, element6, element7, element8, element9, element10;
    @FXML
    public Pane loeschElement;




    /**
     * Animation
     */
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Obtaining delta Time by subtracting the time of the start from the tim during the loop
            double frametime = System.nanoTime();
            double deltaT = (frametime-startTime)/(3.6*Math.pow(10,12));

            position = Ball.getCurrentPosition(kugel);
            //System.out.println(deltaT);
            startTime = frametime;
            System.out.println(Arrays.toString(position));

             vel= Ball.calculateNewVelocityOfTheBall(kugel,position,deltaT,a);

             position = Ball.calculateNewPosition(kugel,deltaT,vel);
             Ball.setNewPosition(kugel,position);







            /**if (changeLayer(position,Ball.getCurrentPosition(endpoint))== true){
                changeLayer(position,Ball.getCurrentPosition(endpoint));
            }**/





        }
    };

    Path path = new Path();
    PathTransition pathTransition = new PathTransition();

    /**
     * This is the ball object, where the physical properties of a ball are applied on
     */
    @FXML
    public Circle kugel;
    public BoundingBox kugelBounds;
    public  Ball castedKugel = ((Ball)kugel);

    int running;



    private double newX, newY;
    private double ballStartX = 460;
    public double ballStartY = 110;
    public double[] position;
    public double[] startPosition = {ballStartX, ballStartY};

    //This time will be determined at the start of the process and the beginning of the physics loops
    private double startTime;






    public String imgUrl;
    public int elemNum, x;

    private double vel[] ={0,1,0};
    private double gravity = -9.8/60;
    private double ax,ay;
    private double a[] = {ax,ay,gravity};
    private double rotation;

    @FXML
    public Slider slider;
    @FXML
    public Label VelocityLabel;
    @FXML
    //TapPane is the stack of panes
    public TabPane tapPane;
    @FXML
    public Tab ebene1,ebene2,ebene3,ebene4,ebene5;
    public  Tab Tabs[] = {ebene1,ebene2,ebene3,ebene4,ebene5};
    @FXML
    public Circle endpoint;





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



    @FXML
    public boolean changeLayer(double[] ballPos,double[] endpointPos){

        double radiusSum = kugel.getRadius() + endpoint.getRadius();

        if(Physics.collisionDetection(kugel,ballPos,endpoint,endpointPos,radiusSum,vel) == true){

            tapPane.getSelectionModel().selectNext();
            Tab curretlyTab = tapPane.getSelectionModel().getSelectedItem();

            return true;



        }else {
            return false;
        }




    }




    /**
     * Methode for starting the animation and getting the starting position of the ball, so we can reset our animation later
     *
     * @autor Maksymilian Huytra
     */
    @FXML
    public double[] startAnimation() {

        startTime = System.nanoTime();
        pathTransition.setDuration(Duration.millis(30000 * handleSliderChange()));
        pathTransition.setCycleCount(1);
        pathTransition.play();

        position = new double[]{ballStartX, ballStartY};

        animationTimer.start();
        running = 1;


        kugel.setCenterX(ballStartX);
        kugel.setCenterY(ballStartY);



        return position;

    }

    /**
     * Methode for stopping  the animation
     *
     * @autor Maksymilian Hutyra
     */
    @FXML
    public void stopAnimation() {
        animationTimer.stop();
        running = 0;
        pathTransition.pause();

    }

    @FXML
    public void resetAnimation() {


        animationTimer.stop();
        pathTransition.setDuration(Duration.millis(30000 * handleSliderChange()));
        pathTransition.playFromStart();


        kugel.setCenterX(ballStartX);
        kugel.setCenterY(ballStartY);


        vel[0] = 0;
        vel[1] = 1;
    }





    /**public double moveBall() {
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

        //System.out.println("geschw: " + newVelosity);

        return newVelosity;


    }*/






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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        kugel.setCenterX(ballStartX);
        kugel.setCenterY(ballStartY);




        //Method for the pathtracing

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
                c.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        // sollte erst beim drop passieren
                        Object c = event.getSource();
                        targetEbene1.getChildren().remove(c);

                        Dragboard db = element3.startDragAndDrop(TransferMode.MOVE);

                        /* Put a string on a dragboard */
                        ClipboardContent content = new ClipboardContent();
                        content.putString("rectangle");
                        db.setContent(content);
                        SnapshotParameters param = new SnapshotParameters();
                        param.setFill(Color.TRANSPARENT);
                        db.setDragView(element2.snapshot(param, null));

                        event.consume();

                        System.out.println("Drag detected");
                    }
                });
                c.setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {

                    }
                });



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



}









