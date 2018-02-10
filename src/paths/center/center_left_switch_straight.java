package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.switches.RunIntake;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.ElevatorState;
import org.usfirst.frc.team670.robot.utilities.Field;

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
    	
    	addSequential(new Drive(Robot.length));
    	addSequential(new Pivot(-Math.atan((Field.DSToSwitch-1.5*Robot.length)/(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth)))));
    	addSequential(new Drive(Math.sqrt((Math.pow(Field.DSToSwitch-1.5*Robot.length, 2)) + (Math.pow(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth), 2)))));
    	addSequential(new Pivot(Math.atan((Field.DSToSwitch-1.5*Robot.length)/(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth)))));
    	addSequential(new Drive(0.5*Robot.length));
    	addSequential(new Encoders_Elevator(ElevatorState.SWITCH, Constants.elevatorAutonSpeed));
    	addSequential(new RunIntake(-0.8, Constants.intakeRunTime));
    	addSequential(new Drive(-0.5*Robot.length));    
		addSequential(new Encoders_Elevator(ElevatorState.DOWN, Constants.elevatorAutonSpeed));

    }
}
