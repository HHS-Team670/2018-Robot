package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.IR_Drive;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionCubePickup extends CommandGroup {

	public VisionCubePickup() {
			double angle = 0;
			if(Robot.sensors != null)
			{
				angle = Robot.sensors.getAngleToCube();
				addParallel(new OpenIntake(true));
				addSequential(new Pivot(angle));
				addSequential(new IR_Drive());
				addSequential(new PickupCube());
			}
	}
}
