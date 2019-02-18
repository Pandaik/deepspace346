/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.*;
/**
 * This controls the arm that moves the intake around
 */
public class Arm {
    private TalonSRX sArmMaster;
    // private TalonSRX sArmSlave;
    public final int NEUTRAL = 0, HIGH = 1, INTAKE = 2, TRAVEL=3,CARGO=4,BAY=5,CARGO_BACK=6,FLIP = 7;
    public final int[] POSITION = {RobotMap.kArmNeutral, RobotMap.kArmUp, RobotMap.kArmIntake,
        RobotMap.kArmTravel,RobotMap.kArmCargo,RobotMap.kArmBay,RobotMap.kArmBackCargo, 40};
   
    private int armPos = NEUTRAL;
    // private int currArmPos = armPos;
    public Arm(){
        this.init();
    }
    // public void setArmSpeed(){
    //     sArmMaster.configMotionCruiseVelocity(RobotMap.kArmVel, RobotMap.kTimeoutMs);
    //     sArmMaster.configMotionAcceleration(RobotMap.kArmAcc, RobotMap.kTimeoutMs);
    // }
    public void setArmPos(int _armPos){
        // currArmPos = armPos;
        this.armPos = _armPos;
    }
    // move the arm in a different position based on the value of armPos
    public void moveArm(Lights _lights, boolean _isFlipping){
        switch(this.armPos){
            case NEUTRAL: //Neutral
                _lights.lightEnabled();
                this.moveArmNeutral();
                break;
            case TRAVEL:
                _lights.lightEnabled();
                sArmMaster.set(ControlMode.MotionMagic, POSITION[armPos]);
                if(Math.abs(sArmMaster.getSelectedSensorPosition() - POSITION[armPos]) < 10){
                    // currArmPos = armPos;
                }
            default:
                sArmMaster.set(ControlMode.MotionMagic, POSITION[armPos]);
                if(Math.abs(sArmMaster.getSelectedSensorPosition() - POSITION[armPos]) < 10){
                    // currArmPos = armPos;
                }

        }
        System.out.println(sArmMaster.getSelectedSensorPosition());
    }
    public void init(){
        sArmMaster = new TalonSRX(RobotMap.kArmMasterP);
        // sArmSlave = new TalonSRX(RobotMap.kArmSlaveP);

        sArmMaster.configFactoryDefault();
		sArmMaster.configSelectedFeedbackSensor(FeedbackDevice.Analog,
											RobotMap.kPIDLoopIdxArm, 
											RobotMap.kTimeoutMs);

		sArmMaster.setSensorPhase(false);
        sArmMaster.setInverted(false);
        
        sArmMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
        sArmMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
		
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);

		sArmMaster.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		sArmMaster.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputForward(1, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

        sArmMaster.selectProfileSlot(RobotMap.kSlotIdxArm, RobotMap.kPIDLoopIdxArm);
		sArmMaster.config_kF(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kF, RobotMap.kTimeoutMs);
		sArmMaster.config_kP(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kP, RobotMap.kTimeoutMs);
		sArmMaster.config_kI(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kI, RobotMap.kTimeoutMs);
		sArmMaster.config_kD(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kD, RobotMap.kTimeoutMs);
        
       
        
        sArmMaster.configMotionCruiseVelocity(RobotMap.kArmVel, RobotMap.kTimeoutMs);
        sArmMaster.configMotionAcceleration(RobotMap.kArmAcc, RobotMap.kTimeoutMs);
        
        // sArmMaster.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdxArm, RobotMap.kTimeoutMs);

        // sArmSlave.set(ControlMode.Follower,RobotMap.kArmMasterP);
    }

    public void driveArmTest(boolean _fwd, boolean _rev){
        if(_fwd){
            sArmMaster.set(ControlMode.PercentOutput, .25);
        }else if(_rev){
            sArmMaster.set(ControlMode.PercentOutput, -.25);
        }else{
            sArmMaster.set(ControlMode.PercentOutput, 0);
        }
        System.out.println(sArmMaster.getSelectedSensorPosition());
        
    }

    private void moveArmNeutral(){
        sArmMaster.set(ControlMode.MotionMagic, RobotMap.kArmNeutral);
        if(Math.abs(sArmMaster.getSelectedSensorPosition() - RobotMap.kArmNeutral) < 10){
            // currArmPos = armPos;
        }

    }
}
