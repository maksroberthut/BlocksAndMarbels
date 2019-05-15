package Elements;

import Physicsengine.Physics;
import javafx.scene.shape.Circle;

public class Ball extends Circle implements Physics{

    public float xDimension;
    public float yDimension;
    public float zDimension;
    public  static Circle ball;


    /**
     * Construktor for the Ball
     * @param mass
     * @param size
     * @param position
     */
    public Ball( double mass,double size,double position){




        //TODO Adding the fx code for the Circe here and defining the properties

    }

    /**
     * Function for getting the Position of the Ball in the world;
     * @param ball
     * First entry = x-Coordinate, second entry y-Coordinate, third entry z-translate,
     * @return position[]
     */
    public static double[] getBallPosition(Ball ball){

        double xDimension = ball.getCenterX();
        double yDimension = ball.getCenterY();
        double zDimension = ball.getTranslateZ();
        double position[] = {xDimension,yDimension,zDimension};

        return position;
    }

    public static double getxPosition(Ball ball){

        double [] position = Ball.getBallPosition(ball);
        double positionx = position[0];
        return positionx;

    }

    /**
     * Funtion to set the new position of the Ball
     * @param ball
     * @return ball with new positions
     */
    public Circle setPosition(Ball ball){

         double position[]=getBallPosition(ball);
        ball.setCenterX(position[0]);
        ball.setCenterY(position[1]);
        ball.setTranslateZ(position[2]);


        return ball;
    }
}
