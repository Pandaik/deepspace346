/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

// import edu.wpi.first.wpilibj.Preferences;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Flipper {
    // private TalonSRX sFlipMasterRight;
    // private TalonSRX sFlipMasterLeft;
    // private TalonSRX sFlipSlaveRight1;
    // private TalonSRX sFlipSlaveLeft1;
    private CANSparkMax sFlipRight;
    private CANSparkMax sFlipLeft; 
    
    private CANDigitalInput sLimRight;
    private CANDigitalInput sLimLeft;
    public boolean flipTheThing = false;
    public boolean flipping = false;
    public Arm sArm;
    // private int phase = 0;
    public Flipper(Arm _arm){
        this.sArm = _arm;
        this.init();
    }
    public void init(){
        sFlipRight = new CANSparkMax(RobotMap.kFlipMasterRightP,MotorType.kBrushless);
        sFlipLeft = new CANSparkMax(RobotMap.kFlipMasterLeftP,MotorType.kBrushless);
        
        sLimRight = sFlipRight.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
        sLimLeft = sFlipLeft.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
    }
    // public void flip(Lights _light){
    //     if(flipTheThing){
    //         System.out.println(sFlipMasterRight.getSelectedSensorPosition());
    //         System.out.println(sFlipMasterRight.getSelectedSensorVelocity());
    //         switch(phase){
    //             case 0:
    //                 flipping = true;
    //                 sFlipMasterLeft.set(ControlMode.MotionMagic, RobotMap.kFlipPoint);
    //                 sFlipMasterRight.set(ControlMode.MotionMagic, RobotMap.kFlipPoint);
    //                 if(Math.abs(RobotMap.kFlipPoint/2- sFlipMasterLeft.getSelectedSensorPosition())<10){
    //                     _light.flipLight(_light.YELLOW);
    //                 }else{
    //                     _light.flipLight(_light.GREEN);
    //                 }
    //                 if(Math.abs(RobotMap.kFlipPoint- sFlipMasterLeft.getSelectedSensorPosition())<10){
    //                     phase = 1;
    //                 }
    //                 break;
    //             case 1:
    //                 _light.flipLight(_light.RED);
    //                 sFlipMasterLeft.set(ControlMode.MotionMagic, 0);
    //                 sFlipMasterRight.set(ControlMode.MotionMagic, 0);
    //                 if(Math.abs(sFlipMasterLeft.getSelectedSensorPosition())<10){
    //                     phase = 2;
    //                 }
    //                 break;
    //             case 2:
    //                 _light.flipLight(_light.RED);
    //         }
    //     }else{
    //         sFlipMasterLeft.set(ControlMode.MotionMagic, 0);
    //         sFlipMasterRight.set(ControlMode.MotionMagic, 0);
    //     }
        
        

    // }
    public void testFlip(boolean _fwd, boolean _rev){
        if(_fwd){
            sFlipRight.set(RobotMap.FlipperFwdSpeed);
            sFlipLeft.set(-RobotMap.FlipperFwdSpeed);
            sArm.setArmPos(sArm.FLIP);
            // _arm.setArmPos(_arm.FLIP);
            // System.out.println("fwd");
        }else if(_rev){
            // System.out.println("rev");
            sFlipRight.set(RobotMap.FlipperRevSpeed);
            sFlipLeft.set( -RobotMap.FlipperRevSpeed);
        }else{
            sFlipRight.set(0);
            sFlipLeft.set(0);
        }
        

        // System.out.println(sFlipMasterRight.getSelectedSensorPosition());
    }
}
