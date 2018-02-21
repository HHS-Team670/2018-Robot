package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.Delay;
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
 *@pre starts next to the exchange
 */
public class center_baseline extends CommandGroup {

    public center_baseline() {
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
    	
    	
    	//Goal here is to go to the left of the cube stack and pass the baseline, then place cube in exchange
    	addParallel(new Deploy(true));
    	addSequential(new Drive(Robot.length));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.EXCHANGE_WIDTH/2 + Field.CUBEPILE_WIDTH));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.DS_TO_BASELINE - Robot.length + Field.TOLERANCE));

    }
}
