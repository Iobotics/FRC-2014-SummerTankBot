package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.iolani.frc.OI;
import org.iolani.frc.subsystems.DriveTrain;
import org.iolani.frc.subsystems.Pneumatics;
import org.iolani.frc.OI;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static final DriveTrain drivetrain = new DriveTrain();
    public static final Pneumatics pneumatics = new Pneumatics();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        drivetrain.init();
        pneumatics.init();
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
