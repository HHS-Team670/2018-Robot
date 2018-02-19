package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.SpinIntake;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class center_left_switch_straight extends CommandGroup {

    public center_left_switch_straight() {
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
    	
    	//Kishore needs to check it, this will not work
    	addParallel(new Deploy(true));
//    	addSequential(new Drive(Robot.length));
    	addSequential(new Drive(Field.DS_TO_CUBEPILE - Robot.length/2));
//    	addSequential(new Pivot(Math.atan((Field.DS_TO_SWITCH-1.5*Robot.length)/(0.5*(Field.SWITCH_LENGTH-Robot.width-Field.EXCHANGE_WIDTH)))));
    	addSequential(new Pivot(-90));
//    	addSequential(new Drive(Math.sqrt((Math.pow(Field.DS_TO_SWITCH-1.5*Robot.length, 2)) + (Math.pow(0.5*(Field.SWITCH_LENGTH-Robot.width-Field.EXCHANGE_WIDTH), 2)))));
    	addSequential(new Drive(Field.SWITCH_LENGTH/2 - Robot.length/2 + 4.75)); // taking into account the off-center-ness of being in the center (4.75" off center)
//    	addSequential(new Pivot(-Math.atan((Field.DS_TO_SWITCH-1.5*Robot.length)/(0.5*(Field.SWITCH_LENGTH-Robot.width-Field.EXCHANGE_WIDTH)))));
    	addSequential(new Pivot(90));
    	addSequential(new Encoders_Elevator(ElevatorState.SWITCH));
//    	addSequential(new Drive(0.3*Robot.length));
//    	addParallel(new Drive(0.25*Robot.length));
    	addSequential(new Drive(Field.CUBEPILE_LENGTH + Robot.length/2 + Field.TOLERANCE));
//    	addParallel(new Drive(Field.TOLERANCE));
    	addSequential(new DropCube());
    	addSequential(new Drive(-0.5*Robot.length));    
		addSequential(new Encoders_Elevator(ElevatorState.EXCHANGE));

    }
}
