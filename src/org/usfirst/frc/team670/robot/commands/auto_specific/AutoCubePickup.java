package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCubePickup extends CommandGroup {

    public AutoCubePickup() {
    	addSequential(new Pivot(Robot.sensors.getAngleToCube()));
    	addSequential(new LocatePowerCube());
    }
}
