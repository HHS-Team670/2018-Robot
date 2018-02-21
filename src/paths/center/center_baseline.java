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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *@pre starts next to the exchange
 */
public class center_baseline extends CommandGroup {

	/*
	 * Drive robot length may not be ending, maybe make it a slightly longer distance to insure it finishes
	 */
    public center_baseline() {
    	addParallel(new Deploy(true));
    	addParallel(new Encoders_Elevator(ElevatorState.CLEARANCE));
    	addSequential(new Drive(Robot.length+6));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.EXCHANGE_WIDTH/2 - Field.CUBEPILE_WIDTH));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.DS_TO_BASELINE - Robot.length + Field.TOLERANCE));
    }
}
