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
    private TalonSRX sDriveLeftMaster;
    private TalonSRX sDriveLeftSlave1;
    private TalonSRX sDriveLeftSlave2;

    private TalonSRX sDriveRightMaster;
    private TalonSRX sDriveRightSlave1;
    private TalonSRX sDriveRightSlave2;
    //Modes are 0 for one stick throttle one stick turns

    public Drive(){
        this.init();
    }
    public void init(){
        sDriveLeftMaster = new TalonSRX(RobotMap.kDriveLeftMasterP);
        sDriveLeftSlave1 = new TalonSRX(RobotMap.kDriveLeftSlaveP1);
        sDriveLeftSlave2 = new TalonSRX(RobotMap.kDriveLeftSlaveP2);

        sDriveLeftSlave1.set(ControlMode.Follower, RobotMap.kDriveLeftMasterP);
        sDriveLeftSlave2.set(ControlMode.Follower, RobotMap.kDriveLeftMasterP);

        sDriveRightMaster = new TalonSRX(RobotMap.kDriveRightMasterP);
        sDriveRightSlave1 = new TalonSRX(RobotMap.kDriveRightSlaveP1);
        sDriveRightSlave2 = new TalonSRX(RobotMap.kDriveRightSlaveP2);

        sDriveRightSlave1.set(ControlMode.Follower, RobotMap.kDriveRightMasterP);
        sDriveRightSlave2.set(ControlMode.Follower, RobotMap.kDriveRightMasterP);
    }
    public void driveWithController(double _left, double _right){
        double lrcon = _left;
        double fcon = _right;
        double rspd = fcon + lrcon*RobotMap.kMaxTurnSpeed;
        double lspd = fcon - lrcon*RobotMap.kMaxTurnSpeed;

        sDriveLeftMaster.set(ControlMode.PercentOutput, rspd);
        sDriveRightMaster.set(ControlMode.PercentOutput, lspd);
        
        
    }
}
