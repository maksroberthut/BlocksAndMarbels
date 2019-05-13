package Physicsengine;

import java.time.LocalTime;

/**
 * Interface that defines the functions for the physics lopp that gets calculated every Frame
 */
public interface Physics {

    //Constants
    double gravity = 9.8;//Gravitational Force downward




    /**
     * Function to Calculate the velocity
     * @param firstPosition
     * @param secondPosition
     * @param firstFrame
     * @param secondFrame
     * @return velocity
     */
    static  double calculateVelocity(int firstPosition, int secondPosition, int firstFrame, int secondFrame){

        int deltaposition = secondPosition - firstPosition;
        int deltaframe = secondFrame - firstFrame;

        int vel = deltaposition / deltaframe;
        return vel;

    }

    /**
     * Function to Calculate acceleration
     * @param firtsVel
     * @param secondVel
     * @param frame
     * @return accel
     */
    static double calculateAcceleration(double firtsVel, double secondVel, double frame){

        double deltaVel = secondVel - firtsVel;
        double accel = deltaVel / frame;

        return accel;

    }

    /**
     * Function to calculate the kinetic Force
     * @param mass
     * @param vel
     * @return kineticForce
     */
    static double calculateKinetic(double mass, int vel){

        double kineticForce = 0.5 * mass * (vel*vel);
        return  kineticForce;

    }


    /**
     * Function to calculate the potential Force
     * @param mass
     * @param height
     * @return potentialForce
     */
    static double calculatePotetial(double mass, double height){

        double potentialForce = mass * gravity * height;
        return potentialForce;

    }

    static double [] updatePhysics(double  firstposition, double secondposition, LocalTime fistFrame, LocalTime secondframe, double mass, double height){

        double physicsproperties[] = {};

        return physicsproperties;
    }






}
