package Physicsengine;

import Elements.Ball;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.time.LocalTime;
import java.util.Vector;

/**
 * Interface that defines the functions for the physics lopp that gets calculated every Frame
 * This interface simulates rigid bodies in a constraint motion
 */
public interface Physics {

    double gravity = 9.8;

    /**
     * calculating the distance between objects
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    static double getDistanceBetweenObjects(double x1, double x2,double y1,double y2){

        double xDistance = x2-x1;
        double yDistance = y2-y1;

        return Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
    }

    /**
     * Function to calculate the acceleration in that time frame for one dimension( needs to be called for each dimension
     * on its own
     * Applied to newtons  Law
     * @param deltaVelocity
     * @param deltaTime
     * @return
     */
    static double calculateAcceleration(double deltaVelocity, double deltaTime){

        double acceleration = deltaVelocity/deltaTime;
        return  acceleration;
    }



    static boolean collisionDetection(Bounds bounds1, Bounds bounds2){

        if(bounds1.intersects(bounds2)){
            return  true;
        }


        return true;

    }

    static double[] resolveCollision(double[] direction ){

        return direction;

    }



    /**
     * Calculating the new Velocity ( running it in the physics loop / overwriting velocity every run)
     * @param velocity
     * @param acceleration
     * @param elapsedTime
     * @return
     */
    public static double calcNewVelocity(double velocity,double acceleration,double elapsedTime){

        double newVelocity = velocity+(acceleration*elapsedTime);
        return newVelocity;

    }

    /**
     * Calculating the new Position of the obj for the ongoing itteration
     * @param velocity
     * @param position
     * @param acceleration
     * @param elapsedTime
     * @return
     */
    public static double calculateNewPosition(double velocity,double position,double acceleration, double elapsedTime){

        double newPosition = position+velocity+elapsedTime+0.5*acceleration*Math.pow(elapsedTime,2);
        return  newPosition;

    }

    /**
     * Function to calculate the Kinetic force of the Obj.
     * @param mass
     * @param velocityX
     * @param velocityY
     * @return
     */
    public static double calculateKineticForce(double mass, double velocityX, double velocityY,double velocityZ){

        double fKinX = 0.5*mass*(Math.pow(velocityX,2));
        double fKInY = 0.5*mass*(Math.pow(velocityY,2));
        double fkinZ = 0.5*mass*(Math.pow(velocityZ,2));

        double kineticEnergy = fKinX + fKInY + fkinZ;
        return kineticEnergy;
    }

    public static double calculatePotentialEnergy(double mass, double height){

        double potentialEnergy = mass*gravity*height;
        return potentialEnergy;
    }











}
