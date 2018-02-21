/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.PickupCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.NavX_Pivot;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.elevator.Hold_Elevator;
import org.usfirst.frc.team670.robot.commands.elevator.ZeroElevatorEncoders;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.commands.state_change.Set_DriverControl;
import org.usfirst.frc.team670.robot.commands.state_change.Set_OperatorControl;
import org.usfirst.frc.team670.robot.commands.state_change.enableHardGrab;
import org.usfirst.frc.team670.robot.constants.RobotMap;
import org.usfirst.frc.team670.robot.constants.enums.DriverState;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;
import org.usfirst.frc.team670.robot.constants.enums.OperatorState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import paths.right.right_baseline;
import paths.right.right_scale_side;
import paths.right.right_switch_side;
import paths.right.right_switch_straight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author vsharma
 */
public class OI {

	private OperatorState os = OperatorState.NONE;
	private DriverState ds = DriverState.TANK;
		
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcadeStick = new Joystick(RobotMap.arcadeStick);

	// Operator Controls
	private Button toggleElevator = new JoystickButton(operatorStick, 3);
	private Button toggleIntake = new JoystickButton(operatorStick, 1);
	private Button toggleClimber = new JoystickButton(operatorStick, 5);
	
	private Button grab = new JoystickButton(arcadeStick, 1);
	private Button release = new JoystickButton(arcadeStick, 10);
	
	private Button deploy = new JoystickButton(arcadeStick, 2);
	private Button retract = new JoystickButton(arcadeStick, 9);
	
	private Button hard = new JoystickButton(arcadeStick, 3);
//	/private Button soft = new JoystickButton(arcadeStick, 8);
	
	private Button pivot_test = new JoystickButton(arcadeStick, 4);

	private Button rightBaseline = new JoystickButton(arcadeStick, 5);
	private Button rightSwitchSide = new JoystickButton(arcadeStick, 6);
	private Button rightSwitchStraight = new JoystickButton(arcadeStick, 7);
	private Button rightScaleSide = new JoystickButton(arcadeStick, 8);
	
	public OI() {
		// Operator buttons
		toggleClimber.whenPressed(new Set_OperatorControl(OperatorState.CLIMBER));
		toggleClimber.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleElevator.whenPressed(new Set_OperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new Set_OperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		
		grab.whenPressed(new OpenIntake(false));
		release.whenPressed(new OpenIntake(true));
		
		deploy.whenPressed(new Deploy(false));
		retract.whenPressed(new Deploy(true));
		
		hard.whenPressed(new enableHardGrab(true));
		hard.whenReleased(new enableHardGrab(false));
	//	soft.whenPressed(new enableHardGrab(false));
		
		pivot_test.whenPressed(new Hold_Elevator(true));
		
		rightBaseline.whenPressed(new right_baseline());
		rightSwitchSide.whenPressed(new right_switch_side());
		rightSwitchStraight.whenPressed(new right_switch_straight());
		rightScaleSide.whenPressed(new right_scale_side());
	}

	public Joystick getLeftStick() {
		return leftDriveStick;
	}

	public Joystick getRightStick() {
		return rightDriveStick;
	}

	public Joystick getOperatorStick() {
		return operatorStick;
	}

	public OperatorState getOS() {
		return os;
	}

	public void setOperatorCommand(OperatorState os) {
		this.os = os;
	}

	public DriverState getDS() {
		return ds;
	}

	public void setDriverState(DriverState ds) {
		this.ds = ds;
	}
}
