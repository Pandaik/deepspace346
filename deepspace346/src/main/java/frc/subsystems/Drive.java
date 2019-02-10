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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Add your docs here.
 */
public class Drive {
    private TalonSRX sDriveLeftMaster;
    private VictorSPX sDriveLeftSlave1;
    private VictorSPX sDriveLeftSlave2;

    private TalonSRX sDriveRightMaster;
    private VictorSPX sDriveRightSlave1;
    private VictorSPX sDriveRightSlave2;
    //Modes are 0 for one stick throttle one stick turns

    public Drive(){
        this.init();
    }
    public void init(){
        sDriveLeftMaster = new TalonSRX(RobotMap.kDriveLeftMasterP);
        sDriveLeftSlave1 = new VictorSPX(RobotMap.kDriveLeftSlaveP1);
        sDriveLeftSlave2 = new VictorSPX(RobotMap.kDriveLeftSlaveP2);

        sDriveLeftSlave1.set(ControlMode.Follower, RobotMap.kDriveLeftMasterP);
        sDriveLeftSlave2.set(ControlMode.Follower, RobotMap.kDriveLeftMasterP);

        sDriveRightMaster = new TalonSRX(RobotMap.kDriveRightMasterP);
        sDriveRightSlave1 = new VictorSPX(RobotMap.kDriveRightSlaveP1);
        sDriveRightSlave2 = new VictorSPX(RobotMap.kDriveRightSlaveP2);

        sDriveRightSlave1.set(ControlMode.Follower, RobotMap.kDriveRightMasterP);
        sDriveRightSlave2.set(ControlMode.Follower, RobotMap.kDriveRightMasterP);
    }
    public void driveWithController(double[] _left, double[] _right){
        double lrcon = _left[0];
        double fcon = _right[1];
        double rspd = fcon - lrcon*RobotMap.kMaxTurnSpeed;
        double lspd = fcon + lrcon*RobotMap.kMaxTurnSpeed;

        sDriveLeftMaster.set(ControlMode.PercentOutput, rspd);
        sDriveRightMaster.set(ControlMode.PercentOutput, lspd);
    }
}
