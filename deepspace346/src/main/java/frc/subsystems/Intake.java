/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.*;


/**
DONT DO ANYTHING YET ITS NOT DONE
 */
public class Intake {
    private VictorSPX sIntake;
    public Intake(){
        this.init();
    }
    public void init(){
       
        sIntake = new VictorSPX(RobotMap.kIntakeP);
        
        
        this.zero();
    }
    public void zero(){
        sIntake.set(ControlMode.PercentOutput,0);
    }
    public void intake(){
        sIntake.set(ControlMode.PercentOutput,RobotMap.kIntakeSpeed);
    }
    public void outtake(){
        sIntake.set(ControlMode.PercentOutput,RobotMap.kOuttakeSpeed);
    }
    public void controlWithButtons(boolean _intakeButton, boolean _outtakeButton){
            if(_intakeButton){
                this.intake();
            }else{
                this.outtake();
            }
        
    }
}
