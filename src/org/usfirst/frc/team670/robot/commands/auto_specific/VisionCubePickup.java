package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionCubePickup extends CommandGroup {

    public VisionCubePickup() {
    	addSequential(new Pivot(Robot.sensors.getAngleToCube()));
    	addSequential(new LocatePowerCube());
    }
}
