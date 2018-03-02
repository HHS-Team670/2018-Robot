package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.commands.intake.SpinIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	setTimeout(0.25);
    	
    	addSequential(new OpenIntake(true));
    	addParallel(new SpinIntake(-0.3, 10));
    	addSequential(new Delay(0.2));
		addParallel(new OpenIntake(false));
    }
    
    public boolean isFinished()
    {
    	return isTimedOut();
    }
}