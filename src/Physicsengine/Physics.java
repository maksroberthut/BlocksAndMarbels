package Physicsengine;

import Elements.Ball;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.time.LocalTime;
import java.util.Vector;

/**
 * Interface that defines the functions for the physics lo0p that gets calculated every Frame
 * This interface simulates rigid bodies in a constraint motion
 * -detects Collision
 * -calculates Distance between Objects
 * -calculates newPosition of Object
 * -calculates newVelocity of the Object
 * -calculates Acceleration
 * -calculates Kinetic nd potential energy
 * -calculates elasticShock
 *
 *
 * @Author Maksymiian Hutyra
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
     * @Author Maksymilian Hutyra
     */
    static double getDistanceBetweenObjects(double x1, double x2,double y1,double y2){

        double xDistance = x2-x1;
        double yDistance = y2-y1;

        return Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
    }

    /**
     * Function to calculate the acceleration in that time frame for one dimension( needs to be
     * called for each dimension
     * on its own
     * Applied to newtons  Law
     * @param deltaVelocity
     * @param deltaTime
     * @return
     * @Author Maksymilian Hutyra
     */
    static double calculateAcceleration(double deltaVelocity, double deltaTime){

        double acceleration = deltaVelocity/deltaTime;
        return  acceleration;
    }


    /**
     * Detects if a collision between to objects has happened
     * depending on the pythagoram
     * @param
     * @param
     * @return
     * @Author Maksymilian Hutyra
     */
    static boolean collisionDetection(Circle ball1,double[] positionBall1, Circle ball2,double[] positionBall2, double radiusSum,double[] vel){

        double deltaX = positionBall2[0]-positionBall1[0];
        double deltaY = positionBall2[1]-positionBall1[1];
        double deltaZ = positionBall2[2]- positionBall1[2];

        if (Physics.getDistanceBetweenObjects(positionBall1[0],positionBall2[0],positionBall1[1],positionBall2[1])
                <= radiusSum*radiusSum ){

            return true;

        }else{
            return false;
        }
    }



    /**
     * Function to calculate the velocity of the object after the collision happend
     * @param mass1
     * @param mass2
     * @param velocity1
     * @param velocity2
     * @return
     * @Author Maksymilian Hutyra
     */
    static double calculateElasticShcok(double mass1, double mass2, double velocity1, double velocity2){

        double newVelocityShock = ((mass1*velocity1)+mass2*(2*velocity2-velocity1))/(mass1+mass2);
        return newVelocityShock;

    }

    static double[] resolveCollision(double[] direction,Circle ball1,double[] positionBall1,
                                                        Shape shape){




        return direction;

    }



    /**
     * Calculating the new Velocity ( running it in the physics loop / overwriting velocity every run)
     * @param velocity
     * @param acceleration
     * @param elapsedTime
     * @return
     * @Author Maksymilian Hutyra
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

        double newPosition = position+velocity*elapsedTime+0.5*acceleration*Math.pow(elapsedTime,2);
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

    /**
     * Calculates the potential energy of the object at the given time
     * @param mass
     * @param height
     * @return
     */
    public static double calculatePotentialEnergy(double mass, double height){

        double potentialEnergy = mass*gravity*height;
        return potentialEnergy;
    }











}
