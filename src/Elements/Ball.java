package Elements;

import Physicsengine.Physics;

public class Ball implements Physics{

    public float xDimension;
    public float yDimension;
    public float zDimension;


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
     * @param xDimension
     * @param yDimension
     * @param zDimension
     * @return position[]
     */
    public double[] getBallPosition(double xDimension, double yDimension, double zDimension){

        double position[] = {xDimension,yDimension,zDimension};

        return position;
    }
}
