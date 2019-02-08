/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.*;


/**
DONT DO ANYTHING YET ITS NOT DONE
 */
public class Intake {
    private TalonSRX sArmMaster;
    private TalonSRX sArmSlave;
    private TalonSRX sIntake;
    public Intake(){
        this.init();
    }
    public void init(){
        sArmMaster = new TalonSRX(RobotMap.kArmMasterP);
        sArmSlave = new TalonSRX(RobotMap.kArmSlaveP);
        sIntake = new TalonSRX(RobotMap.kIntakeP);

        sArmSlave.set(ControlMode.Follower,RobotMap.kArmMasterP);
        this.zero();
    }
    public void zero(){
        sArmMaster.set(ControlMode.PercentOutput, 0);
        sIntake.set(ControlMode.PercentOutput,0);
    }
    public void intake(){
        sIntake.set(ControlMode.PercentOutput,RobotMap.kIntakeSpeed);
    }
    public void outtake(){
        sIntake.set(ControlMode.PercentOutput,RobotMap.kOuttakeSpeed);
    }
}
