package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.commands.intake.SpinIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootCube extends CommandGroup {

	public ShootCube() {
		setTimeout(0.25);  

		addParallel(new OpenIntake(false));
		addParallel(new SpinIntake(0.50, 10)); //Check how this works in practice matche
		addSequential(new Delay(0.2));
		addSequential(new OpenIntake(true));
	}

	public boolean isFinished()
	{
		return isTimedOut();
	}
}
