
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
    private final JoystickButton _onboardDriveButton = new JoystickButton(_onboardStick, 2);
    
    private final PowerScaler _tankDriveScaler;
    
    public OI() {
        
        //_gearShiftButton.whenPressed(new );
        
        // use power scaling from traction mode on 2014 bot //
        _tankDriveScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.0, 0.0),
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.80, 1.0)
            });
        
        _onboardDriveButton.whenPressed(new OnboardOperateDriveTrain());
        
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
    
    public PowerScaler getTankDriveScaler() {
        return _tankDriveScaler;
    }
}

