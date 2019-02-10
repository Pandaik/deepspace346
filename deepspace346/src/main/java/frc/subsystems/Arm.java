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
    // private TalonSRX sArmSlave;
    public final int NEUTRAL = 0, HIGH = 1, INTAKE = 2;
    public final int[] POSITION = {RobotMap.kArmNeutral, RobotMap.kArmUp, RobotMap.kArmIntake};
   
    private int armPos = NEUTRAL;
    private int currArmPos = armPos;
    public Arm(){
        this.init();
    }

    public void setArmPos(int _armPos){
        currArmPos = armPos;
        this.armPos = _armPos;
    }
    // move the arm in a different position based on the value of armPos
    public void moveArm(Hatch _hatch){
        switch(this.armPos){
            case NEUTRAL: //Neutral
                _hatch.canPush = false;
                this.moveArmNeutral();
                break;
            default:
                _hatch.canPush = true;
                sArmMaster.set(ControlMode.MotionMagic, POSITION[armPos]);
                if(Math.abs(sArmMaster.getSelectedSensorPosition() - POSITION[armPos]) < 10){
                    currArmPos = armPos;
                }

        }
        if(_hatch.pushed&&currArmPos != armPos){
            _hatch.togglePush();
        }
    }
    public void init(){
        sArmMaster = new TalonSRX(RobotMap.kArmMasterP);
        // sArmSlave = new TalonSRX(RobotMap.kArmSlaveP);

        sArmMaster.configFactoryDefault();
		sArmMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
											RobotMap.kPIDLoopIdxArm, 
											RobotMap.kTimeoutMs);

		sArmMaster.setSensorPhase(false);
		sArmMaster.setInverted(false);

		
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		sArmMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);

		sArmMaster.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		sArmMaster.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputForward(12, RobotMap.kTimeoutMs);
		sArmMaster.configPeakOutputReverse(-12, RobotMap.kTimeoutMs);

        sArmMaster.selectProfileSlot(RobotMap.kSlotIdxArm, RobotMap.kPIDLoopIdxArm);
		sArmMaster.config_kF(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kF, RobotMap.kTimeoutMs);
		sArmMaster.config_kP(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kP, RobotMap.kTimeoutMs);
		sArmMaster.config_kI(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kI, RobotMap.kTimeoutMs);
		sArmMaster.config_kD(RobotMap.kSlotIdxArm, RobotMap.kGainsArm.kD, RobotMap.kTimeoutMs);
        
       
        
        sArmMaster.configMotionCruiseVelocity(RobotMap.kArmVel, RobotMap.kTimeoutMs);
        sArmMaster.configMotionAcceleration(RobotMap.kArmAcc, RobotMap.kTimeoutMs);
        
        sArmMaster.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdxArm, RobotMap.kTimeoutMs);

        // sArmSlave.set(ControlMode.Follower,RobotMap.kArmMasterP);
    }
    private void moveArmNeutral(){
        sArmMaster.set(ControlMode.MotionMagic, RobotMap.kArmNeutral);
        if(Math.abs(sArmMaster.getSelectedSensorPosition() - RobotMap.kArmNeutral) < 10){
            currArmPos = armPos;
        }
    }
}
