/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.subsystems.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  public Drive sDrive;
  public Intake sIntake;
  public ControlBoard sControlBoard;
  public Arm sArm;
  public Hatch sHatch;
  public Flipper sFlipper;
  public Lights sLights;
  public Preferences prefs;
  public void robotInit() {
    prefs = Preferences.getInstance();
    sDrive = new Drive();
    sIntake = new Intake();
    sControlBoard = new ControlBoard();
    sArm = new Arm();
    sHatch = new Hatch();
    sFlipper = new Flipper();
    sLights = new Lights();
  }

  public void robotPeriodic() {
  }

  public void autonomousInit() {
    
  }

  public void autonomousPeriodic() {
    
  }
  Solenoid sol = new Solenoid(3);
  boolean on = false;
  public void teleopPeriodic() {
    sDrive.driveWithController(sControlBoard.getLeftStick(), sControlBoard.getRightStick());

    sIntake.controlWithButtons(sControlBoard.getBoardButton(sControlBoard.INTAKE),
      sControlBoard.getBoardButton(sControlBoard.OUTTAKE));
    
    if(sControlBoard.getConButton(sControlBoard.SQUARE)){
      sArm.setArmPos(sArm.NEUTRAL);
    }else{
      if(sControlBoard.getConButton(sControlBoard.X)){
        sArm.setArmPos(sArm.HIGH);
      }else{
        if(sControlBoard.getConButton(sControlBoard.CIRCLE)){
          sArm.setArmPos(sArm.INTAKE);
        }
      }
      
    }
    sArm.moveArm(sHatch);

    if(sControlBoard.getConButtonPressed(sControlBoard.RIGHT_BUMPER)){
      sHatch.togglePush();
    }
    if(sControlBoard.getConButtonPressed(sControlBoard.LEFT_BUMPER)){
      sHatch.toggleGrab();
    }
    if(sControlBoard.getConButtonPressed(sControlBoard.SHARE)&&sControlBoard.getConButtonPressed(sControlBoard.OPTIONS)){
      sFlipper.flipTheThing = true;
    }
    if(sControlBoard.getConButtonPressed(sControlBoard.TRIANGLE)){
      sFlipper.flipTheThing = false;
    }
    sFlipper.flip(sLights);
    if(!sFlipper.flipping){
      sLights.lightEnabled();
    }

    
  }

  public void testPeriodic() {
//Uncomment for fun
//Wont do anything on actual robot tho so...
    // if(sControlBoard.getConButton(sControlBoard.X)){
    //   if(on){
    //     on = false;
        
    //   }else{
    //     on = true;
    //   }
    //   sol.set(on);
    // }

    RobotMap.spd = prefs.getDouble("spd", 1);
    if(sControlBoard.getConButton(sControlBoard.TRIANGLE)){
      sFlipper.testFlip();
    }else if(sControlBoard.getConButton(sControlBoard.SQUARE)){
      sFlipper.testRevFlip();
    }else {
      sFlipper.testFlipDis();
    }
  }
}