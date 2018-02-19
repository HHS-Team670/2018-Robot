package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	setTimeout(3);
    	
    	addSequential(new OpenIntake(true));
    	addParallel(new SpinIntake(-0.30, 10));
    	addSequential(new Delay(1.5));
		addParallel(new OpenIntake(false));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}