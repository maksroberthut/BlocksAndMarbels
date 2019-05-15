package Physicsengine;
import Elements.Ball;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Timestepping implements Physics {

    private static int   fps = 60;
    private static final double dt = 1.0/fps;
    private double accumulator = 0;
    private double dpx = 0;
    private double dpy = 0;
    private double dpz = 0;


    //Constructor

    /**
     * updating the physics and rendering the game depending on the framerate
     * Gamephysics get updated almost every second frame
     */
        public Timestepping() {

        }

    /**
     * Function to convert a LocalTime Object to a Stringformat and then a Double to use in calculation
     * @param timestamp
     * @return doubleFrame
     */

    public static double timeFormatter(LocalTime timestamp){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss-msms");
            String formattedString = timestamp.format(formatter);
            System.out.println(formattedString);

            //parsing the string to a double
            double doubleframe = Double.parseDouble(formattedString);
            return doubleframe;

        }

    /**
     * Function that does the calculation of physics depending on the framerate
      * @param ball
     */
    public void timestepping(Ball ball){

        //Setting first Frame at current Time;
        LocalTime startframe = LocalTime.now();
        double startframeFormatted = timeFormatter(startframe);

        double Xposition = Ball.getXPosition(ball);
        double Yposition = Ball.getYPosition(ball);
        double Zposition = Ball.getZPosition(ball);



        while(true){

            LocalTime currentTime = LocalTime.now();
            //Store the time elapsed since last frame began
            double currenTimeFormatted = timeFormatter(currentTime);
            accumulator += currenTimeFormatted - startframeFormatted ;


            //Record the starting of the current Frame
            startframeFormatted = currenTimeFormatted;

            //Clamping dt so the updatePhysics function and render game function dont get called on every frame
            if (accumulator> 2/fps){
                accumulator = 2.0/fps;
            }

            while(accumulator>dt){
                // Physics.updatePhysics();
                //RenderGame();
                accumulator -= dt;

            }

            //Todo Thread öffnen //Timer hinzufügen

        }

    }






}
