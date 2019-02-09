/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  public Drive sDrive;
  public Intake sIntake;
  public ControlBoard sControlBoard;
  public Arm sArm;

  public void robotInit() {
    sDrive = new Drive();
    sIntake = new Intake();
    sControlBoard = new ControlBoard();
    sArm = new Arm();
  }

  public void robotPeriodic() {
  }

  public void autonomousInit() {
    
  }

  public void autonomousPeriodic() {
    
  }

  public void teleopPeriodic() {
    sDrive.driveWithController(sControlBoard.getLeftStick(), sControlBoard.getRightStick());

    sIntake.controlWithButtons(sControlBoard.getBoardButton(sControlBoard.INTAKE),
      sControlBoard.getBoardButton(sControlBoard.OUTTAKE));

    
  }

  public void testPeriodic() {
    sArm.moveArmHigh();
  }
}
