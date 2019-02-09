/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class Hatch {
    private Solenoid sPush;
    private Solenoid sGrab;
    private boolean pushed;
    private boolean grabbed;
    public Hatch(){
        this.init();
    }
    public void init(){
        pushed = false;
        grabbed = false;
        sPush = new Solenoid(RobotMap.kHatchPushP);
        sGrab = new Solenoid(RobotMap.kHatchGrabP);
        sPush.set(pushed);
        sGrab.set(grabbed);
    }
    public void togglePush(){
        if(pushed){
            pushed = false;
        }else{
            pushed = true;
        }
        sPush.set(pushed);
    }
    public void toggleGrab(){
        if(grabbed){
            grabbed = false;
        }else{
            grabbed = true;
        }
        sGrab.set(grabbed);
    }
    

}
