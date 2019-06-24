package Elements;

import Physicsengine.Physics;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.util.Vector;


/**
 * This class represents the ball obj with all the information about the given physical object
 * Might inherert from a Shape Interface
 * @author Maksymilian Hutyra
 */



public class Ball extends Circle implements Physics{

    /**
     * Constructor
     */
    public Ball(){


    }

    /**
     * Physical Properties of the Circle
     */
    private double radius;
    //Position in 3 dimensions
    public double xPos,yPos,zPos;
    //Direction in 3 dimensions
    public double xdir,ydir,zdir;
    //Velocity in 3 dimensions
    public double vx,vy = 0;
    //Acceleration in 3 dimensions
    public double ax,ay;
    public double g = 9.8;
    public double defaultMass = 0.03;
    public double kineticForce;
    public double potentialForce;

    // The state vector is a vector where all the physical properties are places
    Vector statevector = new Vector();






    private double[] velocity = {vx,vy};
    private double[] acceleration = {ax,ay};

    /**
     *  Bounce Checking if the ball is moving as a prerequisite for the second
     *  and ongoing itterationand also not calculating when the ball has stopped to move
     * @return true or false
     */
    public boolean ballIsMoving(){

        double xVel = velocity[0];
        double yVel = velocity[1];

        if (xVel != 0.0 && yVel != 0.0){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Is returning the local Bounds with the translation in the room of the Ball
     * @return
     */
    public Bounds getBounds(){
         Bounds bounds = this.getBoundsInParent();
         return bounds;
    }

    /**
     * Getting the radius of the javaFx Circle element in our scene
     * @param circle
     * @return radius
     */
    public double getRadius(Circle circle ){

         radius = circle.getRadius();
         return  radius;
    }

    /**
     * Function to get the current position of the Ball and put it into a position Array to describe the dimensions
     * @return positionArray[]
     */
    public double[] getCurrentPosition(){

        xPos = this.getCenterX();
        yPos = this.getCenterY();
        double position[] = {xPos,yPos};
        return  position;
    }

    /**
     * Function for getting the height Difference/ only used in change of Layers
     * @return
     */
    public double getZTranslate(){
        zPos = this.getTranslateZ();
        return zPos;
    }

    /**
     * Funtion to set the new Position of the Ball
     * @param position
     */
    public void setNewPosition( double position[]){

        xPos = position[0];
        yPos = position[1];

        this.setCenterX(xPos);
        this.setCenterY(yPos);
    }

    /**
     * Fuction for calculation the new velocity Vector of the Ball in two dimension
     * and then overwriting the old velocity vector
     * @param position,currentVelocity,
     * return Newly overwirten Velocity Vector
     */
    public double[] calculateNewVelocityOfTheBalll(double[] position,double deltaTime){

        double currentVelX = velocity[0];
        double currentVelY = velocity[1];

        //Calculate new Position, each dimension by its own
        double newVelocityX = Physics.calcNewVelocity(currentVelX,acceleration[0],deltaTime);
        double newVelocityY = Physics.calcNewVelocity(currentVelY,acceleration[1],deltaTime);
        //overwriting the old Position
        velocity[0] = newVelocityX;
        velocity[1] = newVelocityY;

        return velocity;

    }

    /**
     * Function to calculate the new position of the ball
     * @param position
     * @param deltaTime
     * @return
     */
    public double[] calculateNewPosition(double[] position,double deltaTime){

        position = this.getCurrentPosition();
        double positionX = position[1];
        double positionY = position[1];
        double velocityX = velocity[0];
        double velocityY = velocity[1];


        double newXPos = Physics.calculateNewPosition(velocityX,positionX,acceleration[0],deltaTime);
        double newYPos = Physics.calculateNewPosition(velocityY,positionY,acceleration[1],deltaTime);

        position[0] = newXPos;
        position[1] = newYPos;

        return position;

    }


    /**
     * Function to apply the forces for the ball
     */
    public static void applyForce(){

    }





}
