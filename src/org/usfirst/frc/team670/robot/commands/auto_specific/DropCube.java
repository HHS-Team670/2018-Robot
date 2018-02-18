package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.CloseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropCube extends CommandGroup {

    public DropCube() {
    	setTimeout(3);

		addParallel(new CloseIntake(false));
    	addParallel(new SpinIntake(-0.55, 10));
    	addSequential(new Delay(1.5));
    	addSequential(new CloseIntake(true));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}
