/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.util.PowerScaler;

/**
 *
 * @author iobotics
 */
public class OnboardOperateTankDrive extends CommandBase {
    private static final double DEADBAND = 0.05;  
    private static final double KIDMAG = 0.50;
    private static final double KIDROT = 0.65;
    private static final double MAXMAGDELTA = 1.0 / 25;
    private static final double MAXROTDELTA = 1.0 / 25;
    
    private final boolean _kidFriendly;
    
    public OnboardOperateTankDrive() {
        this(false);
    }
    
    public OnboardOperateTankDrive(boolean kidFriendly) {
        _kidFriendly = kidFriendly;
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double mag = oi.getOnboardStick().getY();
        double rot = oi.getOnboardStick().getX();
        
        drivetrain.setHighGear(oi.getOnboardGearShiftButton().get());
        
        // signal conditioning //
        PowerScaler magScale = oi.getDriveScaler();
        PowerScaler rotScale = oi.getRotationScaler();
        if(magScale != null) {
            mag = magScale.get(mag);
        }
        if(magScale != null) {
            rot = rotScale.get(rot);
        }
        if(_kidFriendly)  {
            mag *= KIDMAG;
            rot *= KIDROT;
        }
        else {
            // Detune steering sensitivity based on magnitude
            rot /= (1 + Math.abs(mag));
        }
        
        if(Math.abs(mag) < DEADBAND) { mag = 0.0; }
        if(Math.abs(rot) < DEADBAND) { rot = 0.0; }
        drivetrain.setArcade(mag * oi.getVariableDrivePower(), rot * oi.getVariableDrivePower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
