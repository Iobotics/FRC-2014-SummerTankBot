/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.MaintainPneumaticPressure;

/**
 *
 * @author wkd
 */
public class Pneumatics extends Subsystem {
    private Compressor _compressor;
    private boolean _state;
    
    public void init()  {
        _compressor = new Compressor(RobotMap.pressureRegulatorDIO, RobotMap.compressorRelay);
    }
    
    public void setEnabled(boolean state)   {
        if(state)   {
            _compressor.start();
        }
        else    {
            _compressor.stop();
        }
        _state = state;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MaintainPneumaticPressure());
    }
}
