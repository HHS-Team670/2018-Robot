package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Delay;
import org.usfirst.frc.team670.robot.constants.Target;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import paths.left.left_baseline;
import paths.left.left_scale_opposite;
import paths.left.left_scale_side;
import paths.left.left_switch_side;

/**
 *@author vsharma
 */
public class Auto_Left extends CommandGroup {

    public Auto_Left() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	Target t = Robot.finder.getLeftPositionTarget(gameLayout, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
    	
    	addSequential(new Delay(delay));
    	
    	if(t.equals(Target.ScaleStraight))
			addSequential(new left_scale_side());
		else if(t.equals(Target.ScaleOnOtherSide))
			addSequential(new left_scale_opposite());
		else if(t.equals(Target.SwitchFromStraight))
			addSequential(new left_switch_side());
		else if(t.equals(Target.SwitchFromSide))
			addSequential(new left_switch_side());
		else
			addSequential(new left_baseline());
    }
}
