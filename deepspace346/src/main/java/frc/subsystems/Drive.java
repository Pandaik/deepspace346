/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;
import frc.robot.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Drive {
    //Modes are 0 for one stick throttle one stick turns
    public Drive(){

    }
    public void driveWithController(double _left, double _right){
        double lrcon = _left;
        double fcon = _right;
        double rspd = fcon + lrcon*RobotMap.kMaxTurnSpeed;
        double lspd = fcon - lrcon*RobotMap.kMaxTurnSpeed;

        rightDriveM.set(ControlMode.PercentOutput, rspd);
        leftDriveM.set(ControlMode.PercentOutput, -lspd);
        
        
    }
}
