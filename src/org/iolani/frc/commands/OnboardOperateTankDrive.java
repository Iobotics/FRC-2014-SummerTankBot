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
    private double _lastMag = 0.0;
    private double _lastRot = 0.0;
    
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
        /*PowerScaler scale = oi.getTankDriveScaler();
        if(scale != null) {
            mag = scale.get(mag);
            rot = scale.get(rot);
        }*/
        if(_kidFriendly)  {
            mag *= KIDMAG;
            rot *= KIDROT;
        }
        else {
            double magDelta = mag - _lastMag;
            double rotDelta = rot - _lastRot;
            if(magDelta > MAXMAGDELTA) {
                mag = _lastMag + MAXMAGDELTA;
            }
            else if(magDelta < -MAXMAGDELTA) {
                mag = _lastMag - MAXMAGDELTA;
            }
            if(rotDelta > MAXROTDELTA) {
                rot = _lastRot + MAXROTDELTA;
            }
            else if(rotDelta < -MAXROTDELTA) {
                rot = _lastRot - MAXROTDELTA;
            }
            //System.out.println(mag + " , " + magDelta + " , " + _lastMag);
            _lastMag = mag;
            _lastRot = rot;
        }
        
        if(Math.abs(mag) < DEADBAND) { mag = 0.0; }
        if(Math.abs(rot) < DEADBAND) { rot = 0.0; }
        
        drivetrain.setArcade(mag, rot);
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
