
package org.iolani.frc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.iolani.frc.util.PowerScaler;
import org.iolani.frc.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    private final Joystick _onboardStick = new Joystick(3);
    
    private final JoystickButton _gearShiftButton = new JoystickButton(_rStick, 1); 
    private final JoystickButton _onboardGearShiftButton = new JoystickButton(_onboardStick, 1);
    private final JoystickButton _onboardDriveButton = new JoystickButton(_onboardStick, 11);
    private final JoystickButton _kidFriendlyButton = new JoystickButton(_onboardStick, 2);
    
    private final PowerScaler _driveScaler;
    private final PowerScaler _rotationScaler;
    
    public OI() {
        
        //_gearShiftButton.whenPressed(new );
        
        // use power scaling from traction mode on 2014 bot //
        _driveScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.0, 0.0),
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.75, 0.5),
                new PowerScaler.PowerPoint(0.90, 1.0)
            });
        
        _rotationScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.0, 0.0),
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.75, 0.65),
                new PowerScaler.PowerPoint(0.9, 1.0)
            });
        
        _onboardDriveButton.whenPressed(new OnboardOperateTankDrive(false));
        _kidFriendlyButton.whenPressed(new OnboardOperateTankDrive(true));
        
    }
    
    public Button getGearShiftButton() {
        return _gearShiftButton;
    }
    
    public Button getOnboardGearShiftButton()  {
        return _onboardGearShiftButton;
    }
    
    public Joystick getLeftStick()  {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    public Joystick getOnboardStick()  {
        return _onboardStick;
    }
    
    public PowerScaler getDriveScaler() {
        return _driveScaler;
    }
    
    public PowerScaler getRotationScaler() {
        return _rotationScaler;
    }
    
    /**
     * 
     * @return from 0.0 to 1.0
     */
    public double getVariableDrivePower()  {
        return (1 - _onboardStick.getThrottle()) / 2;
    }
    
}