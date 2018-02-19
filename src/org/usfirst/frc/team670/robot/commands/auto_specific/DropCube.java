package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropCube extends CommandGroup {

    public DropCube() {
    	setTimeout(3);

		addParallel(new OpenIntake(false));
    	addParallel(new SpinIntake(0.30, 10));
    	addSequential(new Delay(1.5));
    	addSequential(new OpenIntake(true));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}