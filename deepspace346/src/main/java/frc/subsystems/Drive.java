/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;
import frc.robot.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
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
    private double maxSpeed;
    //Modes are 0 for one stick throttle one stick turns

    public Drive(){
        this.init();
    }
    public void speedBoost(boolean _HELLAFAST){
        if(_HELLAFAST){
            maxSpeed = 1;
        }else{
            maxSpeed = RobotMap.kMaxSpeed;
        }
    }
    public void init(){
        sDriveLeftMaster = new TalonSRX(RobotMap.kDriveLeftMasterP);
        sDriveLeftSlave1 = new VictorSPX(RobotMap.kDriveLeftSlaveP1);
        sDriveLeftSlave2 = new VictorSPX(RobotMap.kDriveLeftSlaveP2);

        sDriveLeftSlave1.follow(sDriveLeftMaster);
        sDriveLeftSlave2.follow(sDriveLeftMaster);

        sDriveRightMaster = new TalonSRX(RobotMap.kDriveRightMasterP);
        sDriveRightSlave1 = new VictorSPX(RobotMap.kDriveRightSlaveP1);
        sDriveRightSlave2 = new VictorSPX(RobotMap.kDriveRightSlaveP2);
        sDriveRightMaster.setNeutralMode(NeutralMode.Brake);
        sDriveLeftMaster.setNeutralMode(NeutralMode.Brake);
        sDriveRightSlave1.follow(sDriveRightMaster);
        sDriveRightSlave2.follow(sDriveRightMaster);

        // sDriveRightMaster.setInverted();
    }
    public void driveWithController(double[] _left, double[] _right){
        double lrcon = _left[0]*RobotMap.kMaxTurnSpeed;
        double fcon = _right[1]*maxSpeed;
        double rspd = (fcon + lrcon);
        double lspd = -(fcon - lrcon);

        sDriveLeftMaster.set(ControlMode.PercentOutput, rspd);
        sDriveRightMaster.set(ControlMode.PercentOutput, lspd);
    }
    public void driveForward(double _percent){
        sDriveLeftMaster.set(ControlMode.PercentOutput, _percent);
        sDriveRightMaster.set(ControlMode.PercentOutput, -_percent);
    }
}
