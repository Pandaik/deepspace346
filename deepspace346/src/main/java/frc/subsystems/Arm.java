/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.*;
/**
 * Add your docs here.
 */
public class Arm {
    private TalonSRX sArmMaster;
    private TalonSRX sArmSlave;
    // private int encoderCorrection;
    public Arm(){
        this.init();
    }
    public void init(){
        sArmMaster = new TalonSRX(RobotMap.kArmMasterP);
        sArmSlave = new TalonSRX(RobotMap.kArmSlaveP);

        sArmSlave.set(ControlMode.Follower,RobotMap.kArmMasterP);
    
        sArmMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 1, 0);
        // sArmMaster.setSensorPhase(true);
        this.setPID(3, 0, 0);
        //encoderCorrection = sArmMaster.getSelectedSensorPosition(1);
        //System.out.println(encoderCorrection);
    }
    public void setPID(double _kP, double _kI, double _kD) {
		this.sArmMaster.config_kP(0, _kP, 0);
		this.sArmMaster.config_kI(0, _kI, 0);
		this.sArmMaster.config_kD(0, _kD, 0);
		this.sArmMaster.config_kF(0, 1000/83, 0);
	}
    public void zero(){
        sArmMaster.set(ControlMode.PercentOutput, 0);
    }
    public void moveArmHigh(){
        
    }
}
