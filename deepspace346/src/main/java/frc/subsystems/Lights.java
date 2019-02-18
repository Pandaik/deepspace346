/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.*;
/**
 * Add your docs here.
 */
public class Lights {
    private Spark sLight;

    public double   GREEN = 0.71, YELLOW = 0.69, RED = -0.11;
    public Lights(){
        this.init();
    }

    public void init(){
        sLight = new Spark(RobotMap.kLightP);
    }

    public void lightEnabled(){
        sLight.setSpeed(RobotMap.kLightEnabled);
    }
    public void lightRocket()
    {
        sLight.setSpeed(RobotMap.kRocketEnabled);
    }
    public void lightShip()
    {
        sLight.setSpeed(RobotMap.kShipEnabled);
    }
    public void flipLight(double color){
        sLight.setSpeed(color);
    }
}
