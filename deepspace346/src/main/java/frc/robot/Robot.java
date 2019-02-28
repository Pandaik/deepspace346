/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.subsystems.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
// import edu.wpi.first.wpilibj.Solenoid;
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
    CameraServer.getInstance().startAutomaticCapture();

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
  //Solenoid sol = new Solenoid(3);
  //boolean on = false;
  public void teleopPeriodic() {

    // RobotMap.spd = prefs.getDouble("spd", 1);
    // RobotMap.kArmVel = prefs.getInt("armVel", 50);
    // RobotMap.kArmAcc = prefs.getInt("armAccel", 25);
    // RobotMap.kGainsArm.kP = prefs.getDouble("armP",8);
    // RobotMap.kGainsArm.kI = prefs.getDouble("armI",0);
    // RobotMap.kGainsArm.kD = prefs.getDouble("armD",0);
    // RobotMap.kGainsArm.kF = prefs.getDouble("armF",0);
    // if(prefs.getBoolean("ArmSpeedEdit", false)){
    //   sArm.setArmSpeed();
    // }

    sDrive.driveWithController(sControlBoard.getLeftStick(), sControlBoard.getRightStick());

    // if(sControlBoard.getBoardButton(16)){
    //   sDrive.driveForward(.25);
    // }
    if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_START)){
      sArm.setArmPos(sArm.NEUTRAL);
    }else{
      if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_ROCKET)){
        sArm.setArmPos(sArm.HIGH);
      }else{
        if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_FLOOR)){
          sArm.setArmPos(sArm.INTAKE);
        }
        else if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_TRAV)){
          sArm.setArmPos(sArm.TRAVEL);
        }
        else if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_SHIP)){
          sArm.setArmPos(sArm.CARGO);
        }
        else if(sControlBoard.getBoardButtonPressed(sControlBoard.CARGO_BAY)){
          sArm.setArmPos(sArm.BAY);
        }
      }
      
    }
    sArm.moveArm(sLights,sFlipper.flipTheThing);
    sDrive.speedBoost(sControlBoard.getBoardButton(sControlBoard.SPEED_BOOST));
    if(sControlBoard.getBoardButtonPressed(sControlBoard.HATCH_IN_OUT)){
      sLights.hatchLight();
      sHatch.toggleGrabPhase();
    }
    // if(sControlBoard.getBoardButtonPressed(sControlBoard.HATCH_GRAB)){
    //   sHatch.toggleGrab();
    // }
    // if(sControlBoard.getBoardButtonReleased(sControlBoard.HATCH_IN_OUT)){
    //   sHatch.togglePush();
    // }
    // if(sControlBoard.getBoardButtonReleased(sControlBoard.HATCH_GRAB)){
    //   sHatch.toggleGrab();
    // }

    sHatch.grabControl(sControlBoard.getBoardButtonPressed(sControlBoard.HATCH_GRAB), 
      sControlBoard.getBoardButtonReleased(sControlBoard.HATCH_GRAB));


    sFlipper.testFlip(sControlBoard.getBoardButton(sControlBoard.FLIP),
      sControlBoard.getBoardButton(sControlBoard.REV_FLIP));

    sIntake.controlWithButtons(sControlBoard.getBoardButton(sControlBoard.CARGO_FLOOR)|| sControlBoard.getBoardButton(sControlBoard.INTAKE), 
      sControlBoard.getBoardButton(sControlBoard.OUTTAKE));    
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
    RobotMap.kArmVel = prefs.getInt("armVel", 50);

    RobotMap.kArmAcc = prefs.getInt("armAccel", 25);
    
    // sFlipper.testFlip(sControlBoard.getConButton(sControlBoard.TRIANGLE),
    //   sControlBoard.getConButton(sControlBoard.SQUARE));
    sIntake.controlWithButtons(sControlBoard.getConButton(sControlBoard.SHARE), 
      sControlBoard.getConButton(sControlBoard.OPTIONS));

    
    // if(sControlBoard.getConButton(sControlBoard.SQUARE)){
    //   sArm.setArmPos(sArm.NEUTRAL);
    // }else{
    //   if(sControlBoard.getConButton(sControlBoard.TRIANGLE)){
    //     sArm.setArmPos(sArm.HIGH);
    //   }else{
    //     if(sControlBoard.getConButton(sControlBoard.X)){
    //       sArm.setArmPos(sArm.INTAKE);
    //     }
    //     else if(sControlBoard.getConButton(sControlBoard.CIRCLE)){
    //       sArm.setArmPos(sArm.TRAVEL);
    //     }
    //     else if(sControlBoard.getConButton(sControlBoard.RIGHT_BUMPER))
    //     {
    //       sArm.setArmPos(sArm.CARGO);
    //     }
    //     else if(sControlBoard.getConButton(sControlBoard.LEFT_BUMPER))
    //     {
    //       sArm.setArmPos(sArm.BAY);
    //     }
    //   }
      
    // }

    // sArm.moveArm(sLights,sHatch,sFlipper.flipTheThing);
    sArm.driveArmTest(sControlBoard.getConButton(sControlBoard.TRIANGLE), 
      sControlBoard.getConButton(sControlBoard.SQUARE));
  // }
}
}