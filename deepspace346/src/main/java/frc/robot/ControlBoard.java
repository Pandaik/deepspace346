/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * Add your docs here.
 */
public class ControlBoard {
    public XboxController con;
    private static final int    LEFT_STICK_X = 0,   RIGHT_STICK_X = 2,
                                LEFT_STICK_Y = 1,   RIGHT_STICK_Y = 5,
                                LEFT_TRIGGER = 3,   RIGHT_TRIGGER = 4,
                                LEFT_BUMPER = 5,    RIGHT_BUMPER = 6,
                                SQUARE = 1,         X = 2,
                                CIRCLE = 3,         TRIANGLE = 4,
                                SHARE = 9,          OPTIONS = 10,
                                LEFT_STICK = 11,    RIGHT_STICK = 12,
                                PS = 13;


    public ControlBoard(){
        con = new XboxController(RobotMap.kControllerP);
    }

    // Returns an array with [X,Y] and converts to Up is positive and Right is positive
    public double[] getLeftStick(){
        return new double[]{con.getRawAxis(LEFT_STICK_X), -con.getRawAxis(LEFT_STICK_Y)};
    }
    public double[] getRightStick(){
        return new double[]{con.getRawAxis(RIGHT_STICK_X), -con.getRawAxis(RIGHT_STICK_Y)};
    }
    public boolean getButton(int _button){
        return con.getRawButton(_button);
    }
}
