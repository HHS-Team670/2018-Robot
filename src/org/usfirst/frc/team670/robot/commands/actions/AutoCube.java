package org.usfirst.frc.team670.robot.commands.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.actions.components.Ultrasonic_DriveLimit;
import org.usfirst.frc.team670.robot.constants.ElevatorState;
import org.usfirst.frc.team670.robot.constants.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCube extends CommandGroup {

	/**
	 * 
	 * 
	 * @param left True if the Robot is on the left of the switch from the perspective of the DS, false if it is on the right.
	 * @param cube The cube it should pick up counting from the starting side of the switch (between 1 and 6 inclusive)
	 */
    public AutoCube(boolean left, int cube) {
    	
    	addParallel(new Encoders_Elevator(ElevatorState.EXCHANGE));
    	//Drives to the specified cube
    	addSequential(new Drive(Field.SideToSwitch - Robot.width - Field.SideTriangleWidth + Field.DistBetweenCubes * (cube - 1) + Field.CubeWidth * cube - Field.CubeWidth/2));
    	if(left)
    		addSequential(new Pivot(90));
    	else
    		addSequential(new Pivot(-90));
    	addSequential(new Ultrasonic_DriveLimit(0.5, 4));
    	addSequential(new PickupCube());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
