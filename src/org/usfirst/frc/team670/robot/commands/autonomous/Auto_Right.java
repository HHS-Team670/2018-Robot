package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Delay;
import org.usfirst.frc.team670.robot.constants.Target;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import paths.left.left_baseline;
import paths.left.left_scale_opposite;
import paths.left.left_switch_side;
import paths.right.right_baseline;
import paths.right.right_scale_opposite;
import paths.right.right_scale_side;
import paths.right.right_switch_side;
import paths.right.right_switch_straight;

/**
 *@author vsharma
 */
public class Auto_Right extends CommandGroup {

    public Auto_Right() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	Target t = Robot.finder.getRightPositionTarget(gameLayout, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
    	
    	addSequential(new Delay(delay));
    	
    	if(t.equals(Target.ScaleStraight))
    		addSequential(new right_scale_side());
		else if(t.equals(Target.ScaleOnOtherSide))
			addSequential(new right_scale_opposite());
		else if(t.equals(Target.SwitchFromStraight))
			addSequential(new right_switch_straight());
		else if(t.equals(Target.SwitchFromSide))
			addSequential(new right_switch_side());
		else
			addSequential(new right_baseline());
    }
}
