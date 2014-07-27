/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperateTankDrive;

/**
 *
 * @author koluke
 */
public class DriveTrain extends Subsystem {
    private Talon    _lTalon;
    private Talon    _rTalon;
    private Solenoid _shiftValve;
    
    private RobotDrive _drive;
    
    public void init()  {
        _lTalon = new Talon(RobotMap.driveLeftTalon);
        _rTalon = new Talon(RobotMap.driveRightTalon);
        
        _drive = new RobotDrive(_lTalon, _rTalon);
        _drive.setSafetyEnabled(false);
        
        _shiftValve = new Solenoid(RobotMap.gearShiftValve);
        _drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        _drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        _drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        _drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }

    public void setArcade(double move, double rotate) {
        _drive.arcadeDrive(move, rotate);
    }
    
    public void setTank(double left, double right) {
        _drive.tankDrive(left, right);
    }
    
    public void setHighGear(boolean high) {
        _shiftValve.set(high);
    }
    
    public boolean getHighGear() {
        return _shiftValve.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OperateTankDrive());
    }
}
