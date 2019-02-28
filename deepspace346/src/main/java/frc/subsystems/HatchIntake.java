/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class HatchIntake {
    private TalonSRX sArm;
    private Hatch sHatch;
    private boolean encoder = false;
    public HatchIntake(Hatch _hatch, boolean _encoder){
        this.init(_encoder);
        this.encoder = _encoder;
        this.sHatch = _hatch;
    }
    public void init(boolean _encoder){
        sArm = new TalonSRX(RobotMap.kHatchIntakeArm);
        if(_encoder){
            sArm.configFactoryDefault();
            sArm.configSelectedFeedbackSensor(FeedbackDevice.Analog,
                                                RobotMap.kPIDLoopIdxArm, 
                                                RobotMap.kTimeoutMs);

            sArm.setSensorPhase(false);
            sArm.setInverted(false);
            
            sArm.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
            sArm.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed, 0);
            
            sArm.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
            sArm.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);

            sArm.configNominalOutputForward(0, RobotMap.kTimeoutMs);
            sArm.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
            sArm.configPeakOutputForward(1, RobotMap.kTimeoutMs);//.35
            sArm.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

            sArm.selectProfileSlot(RobotMap.kSlotIdxArm, RobotMap.kPIDLoopIdxArm);
            sArm.config_kF(RobotMap.kSlotIdxArm, RobotMap.kGainsHatchArm.kF, RobotMap.kTimeoutMs);
            sArm.config_kP(RobotMap.kSlotIdxArm, RobotMap.kGainsHatchArm.kP, RobotMap.kTimeoutMs);
            sArm.config_kI(RobotMap.kSlotIdxArm, RobotMap.kGainsHatchArm.kI, RobotMap.kTimeoutMs);
            sArm.config_kD(RobotMap.kSlotIdxArm, RobotMap.kGainsHatchArm.kD, RobotMap.kTimeoutMs);
            
        
            
            sArm.configMotionCruiseVelocity(RobotMap.kArmVel, RobotMap.kTimeoutMs);
            sArm.configMotionAcceleration(RobotMap.kArmAcc, RobotMap.kTimeoutMs);
        }
    }
    public void togglePos(){
        
    }
    public void armSequence(){
        
    }
}
