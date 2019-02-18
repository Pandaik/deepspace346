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

    private int grabPhase = 0;
    private int grabTimer = 0;

    public boolean canPush = true;//reset to false

    public boolean returnable = false;

    public boolean pushed;
    public boolean grabbed;
    public boolean grabState=true; //set true to grab, false to release
    public boolean grabDone = true;
    public Hatch(){
        this.init();
    }
    public void init(){
        pushed = false;
        grabbed = true;
        sPush = new DoubleSolenoid(RobotMap.kPushActive,RobotMap.kPushInactive);
        sGrab = new DoubleSolenoid(RobotMap.kGrabActive,RobotMap.kGrabInactive);

        //Forward is reverse. Don't worry about it
        sPush.set(Value.kReverse);
        sGrab.set(Value.kForward);
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
    
    public void toggleGrabPhase(){
        toggleGrab();
        grabState = grabbed;
    }

    public void grabControl(boolean _grabToggle, boolean _return){
        if(_grabToggle){
            if(grabState){
                grabState = false;
            }else{
                grabState = true;
            }
            grabDone = false;
        }
        if(!grabDone){
            if(grabTimer<10){
                grabPhase = 0;
            }else if(grabTimer < 20){
                grabPhase = 1;
            }
            if(_return) returnable = true;
            if(returnable && grabTimer>20){
                grabPhase = 2;
            }
            if(grabState){
                this.grab();
            }else{
                this.release();
            }
            grabTimer+=1;
        }
    }

    public void grab(){
        switch(grabPhase){
            case 0:
                if(!pushed){
                    this.togglePush();
                }
                break;
            case 1:
                if(!grabbed){
                    this.toggleGrab();
                }
                break;
            case 2:
                if(pushed){
                    this.togglePush();
                }
                grabDone = true;
                grabPhase = 0;
                grabTimer = 0;
                returnable = false;

        }
        
    }
    public void release(){
        switch(grabPhase){
            case 0:
                if(!pushed){
                    this.togglePush();
                }
                break;
            case 1:
                if(grabbed){
                    this.toggleGrab();
                }
                break;
            case 2:
                if(pushed){
                    this.togglePush();
                }
                grabDone = true;
                grabPhase = 0;
                grabTimer = 0;
                
        }
    }
    

}