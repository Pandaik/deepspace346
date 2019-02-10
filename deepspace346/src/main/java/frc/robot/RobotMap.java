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
    public static final int kFlipMasterRightP = 0;//unlabled
    public static final int kFlipMasterLeftP = 32;
    public static final int kFlipSlaveRightP1 = 33;
    public static final int kFlipSlaveLeftP1 = 34;

    public static final int kDriveLeftMasterP = 31;
    public static final int kDriveLeftSlaveP1 = 5;
    public static final int kDriveLeftSlaveP2 = 6;

    public static final int kDriveRightMasterP = 30;
    public static final int kDriveRightSlaveP1 = 4;
    public static final int kDriveRightSlaveP2 = 3;

    public static final int kIntakeP = 2;

    public static final int kArmMasterP = 46;
    // public static final int kArmSlaveP = 21;

    public static final int kControllerP = 0;
    
    public static double kMaxTurnSpeed = .75;
    public static double kIntakeSpeed = .2;
    public static double kOuttakeSpeed = .5;

    // public final static int kArmEncoderRev = 1000;
    
    public static final int kArmNeutral = 0;
    public static final int kArmUp = 256;
    public static final int kArmIntake = 512;
    
    public static final int kSlotIdxArm = 0;
	public static final int kPIDLoopIdxArm = 0;
	public static final int kTimeoutMs = 30;
    public static final Gains kGainsArm = new Gains(5,0,0,0,0,0);

    public static Gains kPIDsRightFlipper = new Gains(5,0,0,0,0,0);
    public static Gains kPIDsLeftFlipper = new Gains(5,0,0,0,0,0);
    public static final int kSlotIdxFlipper = 0;
    public static final int kPIDLoopIdxFlipper = 0;

    public static final int kFlipPoint = 768;

    public static final int kHatchPushP = 3;
    public static final int kHatchGrabP = 2;

    public static final int kLightP = 0;

    public static int kFlipperVel = 100;
    public static int kFlipperAcc = 50;

    public static final double kLightEnabled = -0.79;
    
    public static final int kArmVel = 15000;
    public static final int kArmAcc = 6000;

    public static final int kGrabActive = 4;
    public static final int kGrabInactive = 6;

    public static final int kPushActive = 5;
    public static final int kPushInactive = 7;
}
