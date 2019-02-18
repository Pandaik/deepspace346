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
    public XboxController board;
    protected final int         LEFT_STICK_X = 0,   RIGHT_STICK_X = 2,
                                LEFT_STICK_Y = 1,   RIGHT_STICK_Y = 5,
                                LEFT_TRIGGER = 3,   RIGHT_TRIGGER = 4,
                                LEFT_BUMPER = 5,    RIGHT_BUMPER = 6,
                                SQUARE = 1,         X = 2,
                                CIRCLE = 3,         TRIANGLE = 4,
                                SHARE = 9,          OPTIONS = 10,
                                LEFT_STICK = 11,    RIGHT_STICK = 12,
                                PS = 13,

                                OUTTAKE = 4,

                                REV_FLIP=1,
                                CARGO_START=2,
                                CARGO_BAY=3,
                                CARGO_FLOOR=5,
                                CARGO_ROCKET=6,
                                CARGO_SHIP=7,
                                FLIP=8,
                                HATCH_IN_OUT=10,
                                INTAKE=12,
                                HATCH_GRAB=13,
                                CARGO_TRAV=14;


                                
    public ControlBoard(){
        con = new XboxController(RobotMap.kControllerP);
        board = new XboxController(1);
    }

    // Returns an array with [X,Y] and converts to Up is positive and Right is positive
    // This is used to make the sticks more manageable :P
    public double[] getLeftStick(){
        return new double[]{con.getRawAxis(LEFT_STICK_X), -con.getRawAxis(LEFT_STICK_Y)};
    }
    public double[] getRightStick(){
        return new double[]{con.getRawAxis(RIGHT_STICK_X), -con.getRawAxis(RIGHT_STICK_Y)};
    }
    public boolean getConButton(int _button){
        return con.getRawButton(_button);
    }
    public boolean getBoardButton(int _button){
        return board.getRawButton(_button);
    }
    public boolean getConButtonPressed(int _button){
        return con.getRawButtonPressed(_button);
    }
    public boolean getBoardButtonPressed(int _button){
        return board.getRawButtonPressed(_button);
    }
    public boolean getConButtonReleased(int _button){
        return con.getRawButtonReleased(_button);
    }
    public boolean getBoardButtonReleased(int _button){
        return board.getRawButtonReleased(_button);
    }

}
