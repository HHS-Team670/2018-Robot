package org.usfirst.frc.team670.robot.commands.actions.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Grab;
import org.usfirst.frc.team670.robot.commands.actions.Intake;
import org.usfirst.frc.team670.robot.commands.actions.PickupCube;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Vision_LocateCube extends CommandGroup {

    public Vision_LocateCube() {
        double angle = Robot.sensors.getAngle();
        addParallel(new Grab(false));
        addSequential(new Pivot(angle));
        addParallel(new Intake(0.7, 5));
        addSequential(new Ultrasonic_DriveLimit(0.3, 6));
        addSequential(new PickupCube());
        
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
