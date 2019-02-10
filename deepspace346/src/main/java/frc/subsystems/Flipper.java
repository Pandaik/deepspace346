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

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Flipper {
    private TalonSRX sFlipMasterRight;
    private TalonSRX sFlipMasterLeft;
    private TalonSRX sFlipSlaveRight1;
    private TalonSRX sFlipSlaveLeft1;
    public boolean flipping = false;
    private int phase = 0;
    public Flipper(){
        this.init();
    }
    public void init(){
        sFlipMasterRight = new TalonSRX(RobotMap.kFlipMasterRightP);
        sFlipMasterLeft = new TalonSRX(RobotMap.kFlipMasterLeftP);
        sFlipSlaveRight1 = new TalonSRX(RobotMap.kFlipSlaveRightP1);
        sFlipSlaveLeft1 = new TalonSRX(RobotMap.kFlipSlaveLeftP1);

        sFlipSlaveLeft1.set(ControlMode.Follower, RobotMap.kFlipMasterLeftP);
        sFlipSlaveRight1.set(ControlMode.Follower, RobotMap.kFlipMasterRightP);
        
        
        sFlipMasterLeft.configFactoryDefault();
		sFlipMasterRight.configFactoryDefault();

        sFlipMasterLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
            RobotMap.kPIDLoopIdxFlipper, RobotMap.kTimeoutMs);
        sFlipMasterRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,  
            RobotMap.kPIDLoopIdxFlipper, RobotMap.kTimeoutMs);

        sFlipMasterLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		sFlipMasterLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
        sFlipMasterRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
        sFlipMasterRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
        
        sFlipMasterLeft.setInverted(false);
        sFlipMasterLeft.setSensorPhase(false);
        sFlipMasterRight.setInverted(false);
        sFlipMasterRight.setSensorPhase(false);

        sFlipMasterLeft.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		sFlipMasterLeft.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		sFlipMasterLeft.configPeakOutputForward(12, RobotMap.kTimeoutMs);
		sFlipMasterLeft.configPeakOutputReverse(-12, RobotMap.kTimeoutMs);
        sFlipMasterRight.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		sFlipMasterRight.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		sFlipMasterRight.configPeakOutputForward(12, RobotMap.kTimeoutMs);
        sFlipMasterRight.configPeakOutputReverse(-12, RobotMap.kTimeoutMs);
        
        sFlipMasterLeft.configMotionCruiseVelocity(RobotMap.kFlipperVel, RobotMap.kTimeoutMs);
        sFlipMasterLeft.configMotionAcceleration(RobotMap.kFlipperAcc, RobotMap.kTimeoutMs);
        sFlipMasterRight.configMotionCruiseVelocity(RobotMap.kFlipperVel, RobotMap.kTimeoutMs);
        sFlipMasterRight.configMotionAcceleration(RobotMap.kFlipperAcc, RobotMap.kTimeoutMs);

        sFlipMasterRight.setSelectedSensorPosition(0, RobotMap.kSlotIdxFlipper, RobotMap.kTimeoutMs);
        sFlipMasterLeft.setSelectedSensorPosition(0, RobotMap.kSlotIdxFlipper, RobotMap.kTimeoutMs);

        sFlipMasterRight.selectProfileSlot(RobotMap.kSlotIdxFlipper, RobotMap.kPIDLoopIdxArm);
		sFlipMasterRight.config_kF(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsRightFlipper.kF, RobotMap.kTimeoutMs);
		sFlipMasterRight.config_kP(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsRightFlipper.kP, RobotMap.kTimeoutMs);
		sFlipMasterRight.config_kI(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsRightFlipper.kI, RobotMap.kTimeoutMs);
		sFlipMasterRight.config_kD(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsRightFlipper.kD, RobotMap.kTimeoutMs);
        
        sFlipMasterLeft.selectProfileSlot(RobotMap.kSlotIdxFlipper, RobotMap.kPIDLoopIdxArm);
		sFlipMasterLeft.config_kF(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsLeftFlipper.kF, RobotMap.kTimeoutMs);
		sFlipMasterLeft.config_kP(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsLeftFlipper.kP, RobotMap.kTimeoutMs);
		sFlipMasterLeft.config_kI(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsLeftFlipper.kI, RobotMap.kTimeoutMs);
		sFlipMasterLeft.config_kD(RobotMap.kSlotIdxFlipper, RobotMap.kPIDsLeftFlipper.kD, RobotMap.kTimeoutMs);
        
    }
    public void flip(Lights _light){
        switch(phase){
            case 0:
                flipping = true;
                sFlipMasterLeft.set(ControlMode.MotionMagic, RobotMap.kFlipPoint);
                sFlipMasterRight.set(ControlMode.MotionMagic, RobotMap.kFlipPoint);
                if(Math.abs(RobotMap.kFlipPoint/2- sFlipMasterLeft.getSelectedSensorPosition())<10){
                    _light.flipLight(_light.YELLOW);
                }else{
                    _light.flipLight(_light.GREEN);
                }
                if(Math.abs(RobotMap.kFlipPoint- sFlipMasterLeft.getSelectedSensorPosition())<10){
                    phase = 1;
                }
                break;
            case 1:
                _light.flipLight(_light.RED);
                sFlipMasterLeft.set(ControlMode.MotionMagic, 0);
                sFlipMasterRight.set(ControlMode.MotionMagic, 0);
                if(Math.abs(sFlipMasterLeft.getSelectedSensorPosition())<10){
                    phase = 2;
                }
                break;
            case 2:
                _light.flipLight(_light.RED);
        }
        

    }

}
