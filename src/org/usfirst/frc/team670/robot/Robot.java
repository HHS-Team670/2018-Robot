/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Drive;
import org.usfirst.frc.team670.robot.commands.actions.components.NavX_Pivot;
import org.usfirst.frc.team670.robot.commands.autonomous.Auto_Center;
import org.usfirst.frc.team670.robot.commands.autonomous.Auto_Left;
import org.usfirst.frc.team670.robot.commands.autonomous.Auto_Right;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.constants.PathFinder;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Elevator;
import org.usfirst.frc.team670.robot.subsystems.Intake;

/**
 * @author vsharma
 */

public class Robot extends TimedRobot {
	public static final double width = 33.25, length = 38; //LENGTH AND WIDTH WITH BUMPERS
	public static final Elevator elevator = new Elevator();
	public static final DriveBase driveBase = new DriveBase();
	public static final Intake intake = new Intake();
	public static PathFinder finder = new PathFinder();
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(RobotMap.pdp);
	
	public static Aggregator sensors;
	public static OI oi;
	
	Command m_autonomousCommand;
	public static SendableChooser<Command> m_chooser = new SendableChooser<>();
	public static SendableChooser<Double> autonomousDelay = new SendableChooser<>();
	public static SendableChooser<Boolean> ApproachType = new SendableChooser<>();
	public static SendableChooser<Boolean> tryLeft = new SendableChooser<>();
	public static SendableChooser<Boolean> tryRight = new SendableChooser<>();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		sensors = new Aggregator();
		
		m_chooser.addDefault("Do Nothing", new CancelCommand());
		m_chooser.addObject("Turn Right 90 degrees", new NavX_Pivot(90));
		m_chooser.addObject("Turn Left 90 degrees", new NavX_Pivot(-90));
		m_chooser.addObject("Turn Right 60 degrees", new NavX_Pivot(60));
		m_chooser.addObject("Turn Left 60 degrees", new NavX_Pivot(-60));
		m_chooser.addObject("1ft_encoders", new Encoders_Drive(1));
		m_chooser.addObject("1ft_encoders_back", new Encoders_Drive(-1));
		 
		
		/*m_chooser.addDefault("Do Nothing", new CancelCommand());
		m_chooser.addObject("Right Position", new Auto_Right());
		m_chooser.addObject("Center Position", new Auto_Center());
		m_chooser.addObject("Left Position", new Auto_Left());
		*/
		autonomousDelay.addDefault("0 Second", 0.0);
		autonomousDelay.addObject("1 Second", 1.0);
		autonomousDelay.addObject("2 Second", 2.0);
		autonomousDelay.addObject("3 Second", 3.0);
		autonomousDelay.addObject("4 Second", 4.0);
		autonomousDelay.addObject("5 Second", 5.0);
		
		ApproachType.addDefault("Straight", true);
		ApproachType.addObject("Side", false);
		
		tryLeft.addDefault("Try left", true);
		tryLeft.addObject("Do not try left", false);
		
		tryRight.addDefault("Try right", true);
		tryRight.addObject("Do not try right", false);
		
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Auton Delay", autonomousDelay);
		SmartDashboard.putData("Approach Type", ApproachType);
		SmartDashboard.putData("Try Left from Center", tryLeft);
		SmartDashboard.putData("Try Right from Center", tryRight);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */ 
	@Override
	public void autonomousInit() {
		
		m_autonomousCommand = m_chooser.getSelected();

		/*	 
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}
	
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
