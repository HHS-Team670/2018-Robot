/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.actions.DropCube;
import org.usfirst.frc.team670.robot.commands.actions.Grab;
import org.usfirst.frc.team670.robot.commands.actions.PickupCube;
import org.usfirst.frc.team670.robot.commands.actions.components.CancelIntake;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.actions.components.Set_DriverControl;
import org.usfirst.frc.team670.robot.commands.actions.components.Set_OperatorControl;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.constants.DriverState;
import org.usfirst.frc.team670.robot.constants.ElevatorState;
import org.usfirst.frc.team670.robot.constants.OperatorState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	private Button toggleIntake = new JoystickButton(operatorStick, 4);

	// Arcade Controls
	private Button intakedeploy = new JoystickButton(arcadeStick, 1);
	private Button intakeretract = new JoystickButton(arcadeStick, 10);

	private Button pickUpCube = new JoystickButton(arcadeStick, 2);
	private Button dropCube = new JoystickButton(arcadeStick, 9);

	private Button elevatorExchange = new JoystickButton(arcadeStick, 3);
	private Button elevatorSwitch = new JoystickButton(arcadeStick, 8);

	private Button elevatorHighScale = new JoystickButton(arcadeStick, 4);
	private Button elevatorMidScale = new JoystickButton(arcadeStick, 7);
	
	private Button cancelCommand = new JoystickButton(arcadeStick, 5);
	private Button printElevator = new JoystickButton(arcadeStick, 6);

	
	// Driver Controls
	private Button tankDrive = new JoystickButton(leftDriveStick, 3);
	private Button reverseTankDrive = new JoystickButton(leftDriveStick, 4);
	private Button singleStickDrive = new JoystickButton(leftDriveStick, 5);
	
	public OI() {
		// Arcade buttons
		intakedeploy.whenPressed(new Grab(true));
		intakeretract.whenPressed(new Grab(false));
		pickUpCube.whenPressed(new PickupCube());
		pickUpCube.whenReleased(new CancelIntake());
		dropCube.whenPressed(new DropCube());
		dropCube.whenReleased(new CancelIntake());
		elevatorExchange.whenPressed(new Encoders_Elevator(ElevatorState.EXCHANGE));
		elevatorSwitch.whenPressed(new Encoders_Elevator(ElevatorState.SWITCH));
		elevatorHighScale.whenPressed(new Encoders_Elevator(ElevatorState.HIGHSCALE));
		elevatorMidScale.whenPressed(new Encoders_Elevator(ElevatorState.MIDSCALE));
		cancelCommand.whenPressed(new CancelCommand());

		// Driver Controls
		tankDrive.whenPressed(new Set_DriverControl(DriverState.TANK));
		reverseTankDrive.whenPressed(new Set_DriverControl(DriverState.TANKREVERSE));
		singleStickDrive.whenPressed(new Set_DriverControl(DriverState.SINGLE));
		
		// Operator buttons
		toggleElevator.whenPressed(new Set_OperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new Set_OperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		
		printElevator.whenPressed(new testing.PrintElevator());
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
