package Elements;

import Physicsengine.Physics;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.util.Vector;


/**
 * This class represents the ball obj with all the information and physical calculations about the given physical object

 * @author Maksymilian Hutyra
 */



public class Ball extends Circle implements Physics{

    /**
     * Constructor
     */
    public Ball(){

         boolean collision = false;




    }

    /**
     * Physical Properties of the Circle
     */
    private static double radius;
    //Position in 3 dimensions
    static public double xPos,yPos,zPos;
    //Direction in 3 dimensions
    public double xdir,ydir,zdir;
    //Velocity in 3 dimensions
    public static double vx,vy,vz;
    //Acceleration in 3 dimensions
    static public double ax,ay,az;
    public double g = 9.8;
    public double defaultMass = 0.03;
    public double kineticForce;
    public double potentialForce;

    // The state vector is a vector where all the physical properties are places
    Vector statevector = new Vector();






    private static double[] velocity = {vx,vy,vz};
    private static double[] acceleration = {ax,ay,az};

    /**
     *  Bounce Checking if the ball is moving as a prerequisite for the second
     *  and ongoing itterationand also not calculating when the ball has stopped to move
     * @return true or false
     * @Author Maksymilian Hutyra
     */
    public static boolean ballIsMoving(Circle ball){

        double xVel = velocity[0];
        double yVel = velocity[1];

        if (xVel != 0.0 && yVel != 0.0){
            return true;
        } else{
            return false;
        }
    }


    /**
     * Getting the radius of the javaFx Circle element in our scene
     * @param circle
     * @return radius
     * @Author Maksymilian Hutyra
     */
     static public double getRadius(Circle circle ){

         radius = circle.getRadius();
         return  radius;
    }

    /**
     * Function to get the current position of the Ball and put it into a position Array to describe the dimensions
     * @return positionArray[]
     * @Autor Maksymiian Hutyra
     */
        static public double[] getCurrentPosition(Circle ball){

        xPos = ball.getCenterX();
        yPos = ball.getCenterY();
        zPos = ball.getTranslateZ();
        double position[] = {xPos,yPos,zPos};
        return  position;
    }


    /**
     * Function for getting the height Difference/ only used in change of Layers
     * @return zPosiion
     * @Autor Maksymilian Hutyra
     *
     */
     static public double getZTranslate(Circle ball){
        zPos = ball.getTranslateZ();
        return zPos;
    }

    /**
     * Funtion to set the new Position of the Ball
     * @param newPosition
     * @Autor Maksymilian Hutyra
     */
    static public void setNewPosition(Circle ball, double newPosition[]){

        xPos = newPosition[0];
        yPos = newPosition[1];
        zPos = newPosition[2];

        ball.setCenterX(xPos);
        ball.setCenterY(yPos);
        ball.setTranslateZ(zPos);
    }

    /**
     * Fuction for calculation the new velocity Vector of the Ball in two dimension
     * and then overwriting the old velocity vector
     * @param position,currentVelocity,
     * return Newly overwirten Velocity Vector
     * @autor Maksymilian Hutyra
     */
     static public double[] calculateNewVelocityOfTheBall(Circle ball,double[] position,double deltaTime,double[] acceleration){

        double currentVelX = velocity[0];
        double currentVelY = velocity[1];
        double currentVelZ = velocity[2];

        //Calculate new Position, each dimension by its own
        double newVelocityX = Physics.calcNewVelocity(currentVelX,acceleration[0],deltaTime);
        double newVelocityY = Physics.calcNewVelocity(currentVelY,acceleration[1],deltaTime);
        double newVelocityZ = Physics.calcNewVelocity(currentVelZ,acceleration[2],deltaTime);
        //overwriting the old Position
        velocity[0] = newVelocityX;
        velocity[1] = newVelocityY;
        velocity[2] = newVelocityZ;

        return velocity;

    }

    /**
     * Function to calculate the new position of the ball
     * @param
     * @param deltaTime
     * @return overwriten Position Array
     * @Autor Maksymilian Hutyra
     */
     static public double[] calculateNewPosition( Circle ball,double deltaTime,double[] velocity){

         double []position = Ball.getCurrentPosition(ball);
        double positionX = position[1];
        double positionY = position[1];
        double positionZ = position[2];
        double velocityX = velocity[0];
        double velocityY = velocity[1];
        double velocityZ = velocity[2];


        double newXPos = Physics.calculateNewPosition(velocityX,positionX,acceleration[0],deltaTime);
        double newYPos = Physics.calculateNewPosition(velocityY,positionY,acceleration[1],deltaTime);
        double newZPos = Physics.calculateNewPosition(velocityZ,positionZ,acceleration[2],deltaTime);

        position[0] = newXPos;
        position[1] = newYPos;
        position[2] = newZPos;

        return position;

    }



    /**
     * Function to apply the forces for the ball
     */
    public static void applyForce(){

    }





}
