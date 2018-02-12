package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Deploy;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Intake;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.constants.ElevatorState;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_scale_side extends CommandGroup {

    public left_scale_side() {
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
    	addParallel(new Deploy(true));
    	addSequential(new Drive(Field.DSToScale - Robot.length + Field.ScaleWidth/2));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.SideToScale - Field.SideTriangleWidth));
    	addSequential(new Encoders_Elevator(ElevatorState.HIGHSCALE));//Raise Elevator
    	addSequential(new Drive(RoboConstants.frontToElevator));
    	addSequential(new Intake(-0.8, RoboConstants.intakeRunTime)); //Place Cube
    	addSequential(new Drive(Robot.length * -1.5));
    	addSequential(new Encoders_Elevator(ElevatorState.EXCHANGE)); //Lower Elevator
    	addSequential(new Pivot(-90));
    }
}
