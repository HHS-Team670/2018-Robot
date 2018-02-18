/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.auto_specific.AutoCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.AutoDropSwitchStraight;
import org.usfirst.frc.team670.robot.commands.auto_specific.DropCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.PickupCube;
import org.usfirst.frc.team670.robot.commands.auto_specific.Vision_PowerCube;
import org.usfirst.frc.team670.robot.commands.drive.Drive;
import org.usfirst.frc.team670.robot.commands.drive.Pivot;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.elevator.ZeroElevatorEncoders;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.commands.intake.OpenIntake;
import org.usfirst.frc.team670.robot.commands.state_change.Set_DriverControl;
import org.usfirst.frc.team670.robot.commands.state_change.Set_OperatorControl;
import org.usfirst.frc.team670.robot.commands.state_change.Toggle_HardSoft;
import org.usfirst.frc.team670.robot.constants.RobotMap;
import org.usfirst.frc.team670.robot.constants.enums.DriverState;
import org.usfirst.frc.team670.robot.constants.enums.ElevatorState;
import org.usfirst.frc.team670.robot.constants.enums.OperatorState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import testing.PrintElevator;

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
	
	/*
	private Button CancelCommand = new JoystickButton(operatorStick, 10);
	private Button TestElevators = new JoystickButton(operatorStick, 11);
	
	private Button vision = new JoystickButton(operatorStick, 2);
	
	// Driver Controls
	/*private Button tankDrive = new JoystickButton(leftDriveStick, 3);
	private Button reverseTankDrive = new JoystickButton(leftDriveStick, 4);
	private Button singleStickDrive = new JoystickButton(leftDriveStick, 5);*/
	/*private Button open = new JoystickButton(arcadeStick, 1);
	private Button close = new JoystickButton(arcadeStick, 10);
	
	private Button hard = new JoystickButton(arcadeStick, 2);
	private Button soft = new JoystickButton(arcadeStick, 9);
	
	private Button elevatorExchange = new JoystickButton(arcadeStick, 3);
	private Button elevatorSwitch = new JoystickButton(arcadeStick, 8);
	
	private Button elevatorScale = new JoystickButton(arcadeStick, 4);
	private Button elevatorZero = new JoystickButton(arcadeStick, 7);

	private Button PickupAuto = new JoystickButton(arcadeStick, 5);
	private Button DropAuto = new JoystickButton(arcadeStick, 6);*/
	
	private Button b1 = new JoystickButton(arcadeStick, 1);
	private Button b2 = new JoystickButton(arcadeStick, 2);
	private Button b3 = new JoystickButton(arcadeStick, 3);
	private Button b4 = new JoystickButton(arcadeStick, 4);
	private Button b5 = new JoystickButton(arcadeStick, 5);
	private Button b6 = new JoystickButton(arcadeStick, 6);
	private Button b7 = new JoystickButton(arcadeStick, 7);
	private Button b8 = new JoystickButton(arcadeStick, 8);
	private Button b9 = new JoystickButton(arcadeStick, 9);
	private Button b10 = new JoystickButton(arcadeStick, 10);
	private Button b11 = new JoystickButton(operatorStick, 10);
	private Button b12 = new JoystickButton(operatorStick, 11);
	private Button b13 = new JoystickButton(operatorStick, 4);
	private Button b14 = new JoystickButton(operatorStick, 8);
	private Button b15 = new JoystickButton(operatorStick, 9);
	
	public OI() {
		// Operator buttons
		toggleClimber.whenPressed(new Set_OperatorControl(OperatorState.CLIMBER));
		toggleClimber.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleElevator.whenPressed(new Set_OperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new Set_OperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		
		b1.whenPressed(new Drive(12*5));
		b2.whenPressed(new Pivot(90));
		b3.whenPressed(new Encoders_Elevator(ElevatorState.EXCHANGE));
		b4.whenPressed(new Encoders_Elevator(ElevatorState.SWITCH));
		b5.whenPressed(new Encoders_Elevator(ElevatorState.HIGHSCALE));
		b6.whenPressed(new Deploy(true));
		b7.whenPressed(new Deploy(false));
		b8.whenPressed(new OpenIntake(false));
		b9.whenPressed(new OpenIntake(true));
		b10.whenPressed(new PickupCube());
		b11.whenPressed(new DropCube());
		b12.whenPressed(new ZeroElevatorEncoders());
		b13.whenPressed(new Toggle_HardSoft(true));
		b14.whenPressed(new Toggle_HardSoft(false));
		b15.whenPressed(new AutoDropSwitchStraight());
		
		/*open.whenPressed(new CloseIntake(true));
		close.whenPressed(new CloseIntake(false));
		
		hard.whenPressed(new Toggle_HardSoft(false));
		soft.whenPressed(new Toggle_HardSoft(true));
		
		elevatorExchange.whenPressed(new Encoders_Elevator(ElevatorState.EXCHANGE));
		elevatorSwitch.whenPressed(new Encoders_Elevator(ElevatorState.SWITCH));
		elevatorScale.whenPressed(new Encoders_Elevator(ElevatorState.HIGHSCALE));
		elevatorZero.whenPressed(new ZeroElevatorEncoders());
		
		PickupAuto.whenPressed(new PickupCube());
		DropAuto.whenPressed(new DropCube());
		
		CancelCommand.whenPressed(new CancelCommand());
		TestElevators.whenPressed(new PrintElevator());
		
		vision.whenPressed(new Vision_PowerCube(0.4));
		vision.whenReleased(new CancelCommand());*/
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
