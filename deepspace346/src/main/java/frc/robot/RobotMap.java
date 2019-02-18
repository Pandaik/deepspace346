/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    public static double spd = .3;
    public static final int kFlipperTPC = 4096;
    public static final int kArmTPC = 1024;

    public static final int kFlipMasterRightP = 35;//35
    public static final int kFlipMasterLeftP = 16;//16
    public static final int kFlipSlaveRightP1 = 33;//33
    public static final int kFlipSlaveLeftP1 = 34;//34

    public static final int kDriveLeftMasterP = 31;
    public static final int kDriveLeftSlaveP1 = 5;
    public static final int kDriveLeftSlaveP2 = 6;

    public static final int kDriveRightMasterP = 30;
    public static final int kDriveRightSlaveP1 = 4;
    public static final int kDriveRightSlaveP2 = 3;

    public static final int kIntakeP = 2;//2

    public static final int kArmMasterP = 3;//3
    // public static final int kArmSlaveP = 21;

    public static final int kControllerP = 0;
    

    public static double kMaxTurnSpeed = 1;
    public static double kIntakeSpeed = 1;
    public static double kOuttakeSpeed = -1;

    // public final static int kArmEncoderRev = 1000;
    
    public static final int kArmNeutral = 60;
    public static final int kArmUp = 355;
    public static final int kArmIntake = 520;
    public static final int kArmTravel= 190;
    public static final int kArmCargo=300;
    public static final int kArmBay=112;
    public static final int kArmBackCargo = 170;

    
    public static final int kSlotIdxArm = 0;
	public static final int kPIDLoopIdxArm = 0;
	public static final int kTimeoutMs = 30;
    public static Gains kGainsArm = new Gains(4.7,0,.1
    ,0,0,0);

    public static Gains kPIDsRightFlipper = new Gains(5
    ,0,0,0,0,0);
    public static Gains kPIDsLeftFlipper = new Gains(5,0,0,0,0,0);

    public static final int kSlotIdxFlipper = 0;
    public static final int kPIDLoopIdxFlipper = 0;

    public static final int kFlipPoint = (int)((double)270/360*kFlipperTPC);

    public static final int kHatchPushP = 3;
    public static final int kHatchGrabP = 2;


    public static final int kLightP = 0;

    public static int kFlipperVel = 20;
    public static int kFlipperAcc = 10;

    public static final double kLightEnabled = -0.79;
    public static final double kRocketEnabled=0.61;// red 
    public static final double kShipEnabled=0.73;//lime 
    public static final double kBayEnabled=0.83;// sky blue <3
    
    public static int kArmVel = 500;
    public static int kArmAcc = 250;

    public static final int kGrabActive = 4;
    public static final int kGrabInactive = 6;

    public static final int kPushActive = 0;
    public static final int kPushInactive = 1;
    
}
