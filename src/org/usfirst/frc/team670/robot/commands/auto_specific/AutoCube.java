package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.PickupCube;
import org.usfirst.frc.team670.robot.commands.intake.Vision_PowerCube;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.enums.ElevatorState;

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
    	addSequential(new Drive(Field.SIDE_TO_SWITCH - Robot.width - Field.SIDE_TRIANGLE_WIDTH + Field.DIST_BETWEEN_CUBES * (cube - 1) + Field.CUBE_WIDTH * cube - Field.CUBE_WIDTH/2));
    	if(left)
    		addSequential(new Pivot(90));
    	else
    		addSequential(new Pivot(-90));
    	addSequential(new Vision_PowerCube(0.4));
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
