/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class Hatch {
    private DoubleSolenoid sPush;
    private DoubleSolenoid sGrab;

    public boolean canPush = false;

    public boolean pushed;
    public boolean grabbed;
    public Hatch(){
        this.init();
    }
    public void init(){
        pushed = false;
        grabbed = false;
        sPush = new DoubleSolenoid(RobotMap.kPushActive,RobotMap.kPushInactive);
        sGrab = new DoubleSolenoid(RobotMap.kGrabActive,RobotMap.kGrabInactive);
        sPush.set(Value.kReverse);
        sGrab.set(Value.kReverse);
    }
    public void togglePush(){
        if(pushed){
            pushed = false;
            sPush.set(Value.kReverse);
        }else{
            if(canPush) {
                pushed = true;
                sPush.set(Value.kForward);
            }
        }
    }
    public void toggleGrab(){
        if(grabbed){
            grabbed = false;
            sGrab.set(Value.kReverse);
        }else{
            grabbed = true;
            sGrab.set(Value.kForward);
        }
        
    }
    

}