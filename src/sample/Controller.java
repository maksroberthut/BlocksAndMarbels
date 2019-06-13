package sample;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {



    @FXML
    public Pane target, targetEbene1, elemPane;
    @FXML
    public Rectangle element1, element2, element3, element4, element5, element6, element7, element8, element9, element10;



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

        }
    };

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
        pathTransition.setCycleCount(1);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(false);


        /**
         elemNum = elemPane.getChildren().size();
         for(x = 0; x < elemNum; x++) {
         elemPane.getChildren().get(x).setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        imgUrl = "/resource/Elem" + x + ".png";
        }
        });
         }
         **/


        element1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem1.png";

            }
        });

        element2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem2.png";
            }
        });

        element3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem3.png";
            }
        });

        element4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem4.png";
            }
        });

        element5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem5.png";
            }
        });

        element6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem6.png";
            }
        });

        element7.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem7.png";
            }
        });

        element8.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem8.png";
            }
        });

        element9.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem9.png";
            }
        });

        element10.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imgUrl = "/resource/Elem10.png";






            }
        });


        element1.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        Dragboard db = element1.startDragAndDrop(TransferMode.MOVE);
        //    Dragboard db2 = element2.startDragAndDrop(TransferMode.MOVE);

        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString("rectangle");
        db.setContent(content);
        //   db2.setContent(content);
        SnapshotParameters param = new SnapshotParameters();
        param.setFill(Color.TRANSPARENT);
        db.setDragView(element1.snapshot(param, null));
        //     db2.setDragView(element2.snapshot(param, null));

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
                c.setFill(new ImagePattern(new Image(imgUrl)));


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









