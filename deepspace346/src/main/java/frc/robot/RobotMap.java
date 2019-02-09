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
    public static final int kDriveLeftMasterP = 0;
    public static final int kDriveLeftSlaveP1 = 0;
    public static final int kDriveLeftSlaveP2 = 0;

    public static final int kDriveRightMasterP = 0;
    public static final int kDriveRightSlaveP1 = 0;
    public static final int kDriveRightSlaveP2 = 0;

    public static final int kIntakeP = 0;

    public static final int kArmMasterP = 5;
    public static final int kArmSlaveP = 0;

    public static final int kControllerP = 0;
    
    public static double kMaxTurnSpeed = .75;
    public static double kIntakeSpeed = .2;
    public static double kOuttakeSpeed = .5;

    // public final static int kArmEncoderRev = 1000;
    
    public static int kArmNeutral = 0;
    public static int kArmUp = 256;
    public static int kArmIntake = 512;
    
    public static final int kSlotIdx = 1;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 30;
    public static final Gains kGains = new Gains(5, 0., 0.0, 0, 0, 0);

    public static final int kHatchPushP = 3;
    public static final int kHatchGrabP = 2;

}
