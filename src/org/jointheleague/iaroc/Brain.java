package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.Uart;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

import java.util.Random;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
        int tDistence = 0;
        int tAngle = 0;
    }

    int distance;
    int angle;

    //boolean active = true;

    Random randomizer = new Random();

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Self Destruct In T Minus 10 Seconds");



        /*for(int i = 1; i < 5; i++)
        {
            record("Round: " + i);
            straightForward(500 - randomizer.nextInt(1000), randomizer.nextInt(500));
            readSensors(SENSORS_DISTANCE);
            distance = distance + getDistance();
            record("Moved distance: " + getDistance());
            record("Total distance: " + distance);

            stop();
            SystemClock.sleep(500);

            turnClockwise(500 - randomizer.nextInt(1000), randomizer.nextInt(500));
            readSensors(SENSORS_ANGLE);
            angle = angle + getAngle();
            record("Moved angle: " + getAngle());
            record("Total angle: " + angle);

            stop();
            SystemClock.sleep(500);
        }
        stop();
        dashboard.speak("Done!");
        record("Total distance: " + distance);
        record("Total angle: " + angle);*/

        /* //Read test
        straightForward(500, 1000);
        readSensors(SENSORS_DISTANCE);
        dashboard.log("Moved forward: " + getDistance());
        turnClockwise(500, 1000);
        readSensors(SENSORS_ANGLE);
        dashboard.log("Turned clockwise: " + getAngle());

        straightForward(-500, 1000);
        readSensors(SENSORS_DISTANCE);
        dashboard.log("Moved backward: " + getDistance());
        turnClockwise(-500, 1000);
        readSensors(SENSORS_ANGLE);
        dashboard.log("Turned counterclockwise: " + getAngle());

        stop();*/

        //what would you like me to do, Clever Human?
        /*
        dashboard.speak("BEEP");
        driveDirect(500, 500);
        distance = 0;*/


        /*
        int perimeter = 0;

        for(int i = 0; i < 4; i++) {
            straightForward(500, 1000);
            turnClockwise(500, 500);
            readSensors(SENSORS_DISTANCE);
            dashboard.log("Distance = " + getDistance());
            readSensors(SENSORS_ANGLE);
            dashboard.log("Angles = " + getAngle());


            perimeter = perimeter + getDistance();
        }

        dashboard.log("Total distance = " + perimeter);
        */

        /*dashboard.speak("Error! Error!");

        for(int i = 0; i < 50; i++){
            int random = randomizer.nextInt(3);

            if(random == 2) {
                turningForward(randomizer.nextInt(500), randomizer.nextInt(500), randomizer.nextInt(1000));
            }
            else if(random == 1) {
                turnClockwise(500 - randomizer.nextInt(1000), randomizer.nextInt(1000));
            }
            else if(random == 0) {
                straightForward(randomizer.nextInt(500), randomizer.nextInt(1000));
            }
        }

        stop();*/


    }
    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {

        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        //Directly into the wall
        if(isBumpLeft() && isBumpRight())
        {
            turningForward(250, -100, 100);
            dashboard.log("Turning right...");
        }
        //Hugging the left wall
        else if(isBumpLeft() && !isBumpRight())
        {
            turningForward(225, 250, 100);
            dashboard.log("Pushing left...");
        }
        //Look for the left wall
        else
        {
            turningForward(50, 250, 100);
            dashboard.log("Looking for wall...");
        }
        /* //Bump and back up
        straightForward(100, 100);
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        if(isBumpLeft() && isBumpRight())
        {
            dashboard.log("Backing up...");
            straightForward(-500, 1000);
        }*/

        /*readSensors(SENSORS_DISTANCE);
        distance = distance + getDistance();

        if (distance >= 1000){
            driveDirect(0,0);
            dashboard.speak("DONE!");
            dashboard.log("Distance = " + distance);
            dashboard.speak("Distance = " + distance);
        }*/
/*        if(active){
            readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
            if(isBumpLeft() && isBumpRight())
            {
                dashboard.speak("Oh no!");
                //active = false;
                turnClockwise(500, 500);
                driveDirect(500, 500);
            }


        }*/
    }
    public void turningForward(int left, int right, int time) throws  ConnectionLostException {
        driveDirect(right, left);
        SystemClock.sleep(time);
    }

    public void straightForward(int speed, int time) throws  ConnectionLostException{
        driveDirect(speed, speed);
        SystemClock.sleep(time);
    }

    public void turnClockwise(int speed, int time) throws ConnectionLostException{
        driveDirect(speed, -speed);
        SystemClock.sleep(time);
    }

    public void stop() throws ConnectionLostException{
        driveDirect(0, 0);
    }

    public void record(String message) throws ConnectionLostException{
        dashboard.log(message);
        //dashboard.speak(message);
    }
}