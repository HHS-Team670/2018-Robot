package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.CloseIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	setTimeout(3);
    	
    	addSequential(new CloseIntake(true));
    	addParallel(new SpinIntake(0.55, 10));
    	addSequential(new Delay(1.5));
		addParallel(new CloseIntake(false));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}
