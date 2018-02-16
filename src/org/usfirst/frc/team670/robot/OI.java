/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.elevator.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.intake.StopIntakeWheels;
import org.usfirst.frc.team670.robot.commands.intake.Deploy;
import org.usfirst.frc.team670.robot.commands.intake.DropCube;
import org.usfirst.frc.team670.robot.commands.intake.CloseIntake;
import org.usfirst.frc.team670.robot.commands.intake.PickupCube;
import org.usfirst.frc.team670.robot.commands.intake.Vision_PowerCube;
import org.usfirst.frc.team670.robot.commands.state_change.Set_DriverControl;
import org.usfirst.frc.team670.robot.commands.state_change.Set_OperatorControl;
import org.usfirst.frc.team670.robot.constants.RobotMap;
import org.usfirst.frc.team670.robot.enums.DriverState;
import org.usfirst.frc.team670.robot.enums.ElevatorState;
import org.usfirst.frc.team670.robot.enums.OperatorState;

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
	private Button toggleClimber = new JoystickButton(operatorStick, 5);
	private Button grab = new JoystickButton(operatorStick, 10);
	private Button release = new JoystickButton(operatorStick, 11);
	
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
	/*private Button tankDrive = new JoystickButton(leftDriveStick, 3);
	private Button reverseTankDrive = new JoystickButton(leftDriveStick, 4);
	private Button singleStickDrive = new JoystickButton(leftDriveStick, 5);*/
	
	public OI() {
		// Operator buttons
		toggleClimber.whenPressed(new Set_OperatorControl(OperatorState.CLIMBER));
		toggleClimber.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleElevator.whenPressed(new Set_OperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new Set_OperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new Set_OperatorControl(OperatorState.NONE));
		grab.whenPressed(new CloseIntake(true));
		grab.whenPressed(new CloseIntake(false));
		// Driver Controls
		/*tankDrive.whenPressed(new Set_DriverControl(DriverState.TANK));
		reverseTankDrive.whenPressed(new Set_DriverControl(DriverState.TANKREVERSE));
		singleStickDrive.whenPressed(new Set_DriverControl(DriverState.SINGLE));*/
		
		// Arcade buttons
		intakedeploy.whenPressed(new Deploy(true)); //1
		intakeretract.whenPressed(new Deploy(false)); //10
		
		pickUpCube.whenPressed(new Vision_PowerCube(0.4));//2
		dropCube.whenPressed(new DropCube());//9
		
		elevatorExchange.whenPressed(new Encoders_Elevator(ElevatorState.EXCHANGE));//3
		elevatorSwitch.whenPressed(new Encoders_Elevator(ElevatorState.SWITCH));//8
		elevatorHighScale.whenPressed(new Encoders_Elevator(ElevatorState.HIGHSCALE));//4
		elevatorMidScale.whenPressed(new Encoders_Elevator(ElevatorState.MIDSCALE));//7
		
		cancelCommand.whenPressed(new CancelCommand()); //5
		printElevator.whenPressed(new testing.PrintElevator()); //6
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
