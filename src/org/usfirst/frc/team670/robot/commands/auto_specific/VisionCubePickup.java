package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Vision_Drive;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionCubePickup extends CommandGroup {

	public VisionCubePickup() {
		if(Math.abs(Robot.sensors.getAngleToCube() - 8000) > 0.01) {
			this.cancel();
		}
		else {
			addParallel(new OpenIntake(true));
			addSequential(new Pivot(Robot.sensors.getAngleToCube()));
			addSequential(new Vision_Drive());
			addSequential(new PickupCube());
		}
	}
}
