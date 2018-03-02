package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Time_Drive;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.commands.intake.SpinIntake;
import org.usfirst.frc.team670.robot.constants.Field;
import org.usfirst.frc.team670.robot.constants.RoboConstants;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class center_left_switch_straight extends CommandGroup {

    public center_left_switch_straight() {
    	addSequential(new Drive(Robot.length+6));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.SWITCH_LENGTH - 35.8));
    	addSequential(new Pivot(90));
    	addParallel(new Encoders_Elevator(ElevatorState.SWITCH));
    	addSequential(new Drive(Field.DS_TO_BASELINE - Robot.length - 6));
//    	addSequential(new Time_Drive(1.5, 0.45));
    	addSequential(new DropCube());
    }
}
