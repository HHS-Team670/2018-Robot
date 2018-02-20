package org.usfirst.frc.team670.robot.commands.auto_specific;

import org.usfirst.frc.team670.robot.commands.drive.Time_Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDropSwitchStraight extends CommandGroup {

    public AutoDropSwitchStraight() {
    	
    	//Lift up the elevator to a point above the switch
    	//Drive forward by time for 2 second at 50 percent speed
    	//addParallel delay for 1 seconds
    	//Drop the cube
    	
    	addSequential(new Encoders_Elevator(ElevatorState.SWITCH));
    	addParallel(new Time_Drive(2, 0.5));
    	addSequential(new Delay(1));
    	addSequential(new DropCube());
    	
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