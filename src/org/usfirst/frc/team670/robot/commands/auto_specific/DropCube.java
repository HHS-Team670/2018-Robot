package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropCube extends CommandGroup {

    public DropCube() {
    	setTimeout(1.0);

		addParallel(new OpenIntake(false));
    	addParallel(new SpinIntake(0.70, 10));
    	addSequential(new Delay(0.5));
    	addSequential(new OpenIntake(true));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}