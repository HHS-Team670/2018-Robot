package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

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
    	if(left)
    	{
        	addSequential(new Drive(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH + Field.DIST_BETWEEN_CUBES * (cube - 1) + Field.CUBE_WIDTH * cube - Field.CUBE_WIDTH/2));
    		addSequential(new Pivot(90));
    	}
    	else
    	{
    		cube = 6 - cube;
        	addSequential(new Drive(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH + Field.DIST_BETWEEN_CUBES * (cube) + Field.CUBE_WIDTH * cube + Field.CUBE_WIDTH/2));
    		addSequential(new Pivot(-90));
    	}
    	
    	if(Robot.sensors.isVisionConnected())
    		addSequential(new Vision_PowerCube(0.3));
    	else
    	{
    		//Just go in using pid and motor constants
    		addSequential(new OpenIntake(true));
    		addParallel(new Drive(51.825-(Field.CUBE_WIDTH/2)));
    		addParallel(new SpinIntake(0.6, 5));
    		addSequential(new Delay(1));
    		addParallel(new OpenIntake(false));
    		addSequential(new Delay(0.5));
    		addSequential(new Drive(-Field.CUBE_WIDTH));
    	}
    	
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
