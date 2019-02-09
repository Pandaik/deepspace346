/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.*;
/**
 * This controls the arm that moves the intake around
 */
public class Arm {
    private TalonSRX sArmMaster;
    private TalonSRX sArmSlave;

    public final int  NEUTRAL = 0, HIGH = 1, INTAKE = 2;
    private int armPos = NEUTRAL;
    public Arm(){
        this.init();
    }

    public void setArmPos(int _armPos){
        this.armPos = _armPos;
    }
    // move the arm in a different position based on the value of armPos
    public void moveArm(){
        switch(this.armPos){
            case INTAKE:
                this.moveArmIntake();
                break;
            case HIGH:
                this.moveArmHigh();
                break;
            case NEUTRAL: 
            default:
                this.moveArmNeutral();

        }
    }
    public void init(){
        sArmMaster = new TalonSRX(RobotMap.kArmMasterP);
        sArmSlave = new TalonSRX(RobotMap.kArmSlaveP);

        sArmMaster.configFactoryDefault();
		sArmMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
											RobotMap.kPIDLoopIdx, 
											RobotMap.kTimeoutMs);

		sArmMaster.setSensorPhase(false);
		sArmMaster.setInverted(false);

		
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);

		sArmMaster.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		sArmMaster.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputForward(12, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputReverse(-12, RobotMap.kTimeoutMs);

        sArmMaster.selectProfileSlot(RobotMap.kSlotIdx, RobotMap.kPIDLoopIdx);
		sArmMaster.config_kF(RobotMap.kSlotIdx, RobotMap.kGains.kF, RobotMap.kTimeoutMs);
		sArmMaster.config_kP(RobotMap.kSlotIdx, RobotMap.kGains.kP, RobotMap.kTimeoutMs);
		sArmMaster.config_kI(RobotMap.kSlotIdx, RobotMap.kGains.kI, RobotMap.kTimeoutMs);
		sArmMaster.config_kD(RobotMap.kSlotIdx, RobotMap.kGains.kD, RobotMap.kTimeoutMs);
        
        sArmSlave.set(ControlMode.Follower,RobotMap.kArmMasterP);
        
        sArmMaster.configMotionCruiseVelocity(15000, RobotMap.kTimeoutMs);
        sArmMaster.configMotionAcceleration(6000, RobotMap.kTimeoutMs);
        
        sArmMaster.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
    }
    private void moveArmHigh(){
        sArmMaster.set(ControlMode.MotionMagic, RobotMap.kArmUp);
    }
    private void moveArmIntake(){
        sArmMaster.set(ControlMode.MotionMagic, RobotMap.kArmIntake);
    }
    private void moveArmNeutral(){
        sArmMaster.set(ControlMode.MotionMagic, RobotMap.kArmNeutral);
    }
}
