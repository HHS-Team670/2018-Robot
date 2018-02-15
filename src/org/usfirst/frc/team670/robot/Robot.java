/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import paths.center.center_baseline;
import paths.center.center_left_switch_side;
import paths.center.center_left_switch_straight;
import paths.center.center_right_switch_side;
import paths.center.center_right_switch_straight;
import paths.left.left_baseline;
import paths.left.left_scale_opposite;
import paths.left.left_scale_side;
import paths.left.left_switch_side;
import paths.right.right_baseline;
import paths.right.right_scale_opposite;
import paths.right.right_scale_side;
import paths.right.right_switch_side;
import paths.right.right_switch_straight;

import org.usfirst.frc.team670.robot.commands.actions.Delay;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.subsystems.Climber;
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
	public static final Climber climber = new Climber();
	public static PowerDistributionPanel pdp = new PowerDistributionPanel(RobotMap.pdp);
	
	public static Aggregator sensors;
	public static OI oi;
	
	Command m_autonomousCommand;
	private String lastChoice;
	private SendableChooser<String> mainMenu;
	private SendableChooser<Double> autonomousDelay;
    private SendableChooser<String> subMenuRR, subMenuLL, subMenuLR, subMenuRL;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		sensors = new Aggregator();
		lastChoice = null;
		
		//The first character is the switch, the second one is the scale
		mainMenu = new SendableChooser<String>();
		subMenuRR = new SendableChooser<String>();
		subMenuLL = new SendableChooser<String>();
		subMenuRL = new SendableChooser<String>();
		subMenuLR = new SendableChooser<String>();

        mainMenu.addDefault("None", "default");
        mainMenu.addObject("Left", "Left");
        mainMenu.addObject("Center", "Center");
        mainMenu.addObject("Right", "Right");

        autonomousDelay.addDefault("0 Second", 0.0);
        autonomousDelay.addObject("1 Second", 1.0);
        autonomousDelay.addObject("2 Second", 2.0);
        autonomousDelay.addObject("3 Second", 3.0);
        autonomousDelay.addObject("4 Second", 4.0);
        autonomousDelay.addObject("5 Second", 5.0);
        
        SmartDashboard.putData("Position Selector", mainMenu);
        SmartDashboard.putData("Auton Delay", autonomousDelay);
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
		
		if (mainMenu.getSelected() != null) {
			if(!lastChoice.equalsIgnoreCase(mainMenu.getSelected())){
				subMenuRR = populateList(mainMenu.getSelected());
				subMenuLL = populateList(mainMenu.getSelected());
				subMenuRL = populateList(mainMenu.getSelected());
				subMenuLR = populateList(mainMenu.getSelected());	
				subMenuRR.setName("RR");
				subMenuLL.setName("LL");
				subMenuRL.setName("RL");
				subMenuLR.setName("LR");
				lastChoice = mainMenu.getSelected();
			}
        }
		
		SmartDashboard.putData(mainMenu);
		SmartDashboard.putData(subMenuRR);
		SmartDashboard.putData(subMenuLL);
		SmartDashboard.putData(subMenuRL);
		SmartDashboard.putData(subMenuRL);
		
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
		String data = DriverStation.getInstance().getGameSpecificMessage();
		if(data != null){
			data = data.substring(0, 2);
			if(data.equalsIgnoreCase("RR"))
				m_autonomousCommand = getCommand(mainMenu, subMenuRR);
			else if(data.equalsIgnoreCase("LL"))
				m_autonomousCommand = getCommand(mainMenu, subMenuLL);
			else if(data.equalsIgnoreCase("LR"))
				m_autonomousCommand = getCommand(mainMenu, subMenuLR);
			else if(data.equalsIgnoreCase("RL"))
				m_autonomousCommand = getCommand(mainMenu, subMenuRL);
		}
		//RUN THE AUTONOMOUS COMMAND------------------------------
		CommandGroup combined = new CommandGroup();
		combined.addSequential(new Delay(autonomousDelay.getSelected()));
		if (m_autonomousCommand != null) {
			combined.addSequential(m_autonomousCommand);
			System.out.println(m_autonomousCommand.getName());
			//combined.start();
		}
		else
		{
			SmartDashboard.putString("dataStatus", "SMARTDASHBOARD AUTON ERROR");
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

	public SendableChooser<String> populateList(String side)
	{
		SendableChooser<String> list = new SendableChooser<String>();
		
		if(side.equalsIgnoreCase("Left"))
		{
			list.addDefault("left_baseline", "l_base");
			list.addObject("left_scale_opposite", "l_sc_opp");
			list.addObject("left_scale_side", "l_sc_side");
			list.addObject("left_switch_side", "l_sw_side");
			list.addObject("back", "l_back");
	        return list;
		}
		else if(side.equalsIgnoreCase("Center"))
		{
			list.addDefault("center_baseline", "c_base");
			list.addObject("center_left_switch_side", "c_l_sw_side");
			list.addObject("center_left_switch_straight", "c_l_sw_str");
			list.addObject("center_right_switch_straight", "c_r_sw_str");
			list.addObject("center_right_switch_side", "c_r_sw_side");
	        list.addObject("back", "c_back");
	        return list;
		}
		else if(side.equalsIgnoreCase("Right"))
		{
			list.addDefault("right_baseline", "r_base");
			list.addObject("right_scale_opposite", "r_sc_opp");
			list.addObject("right_scale_side", "r_sc_side");
			list.addObject("right_switch_side", "r_sw_side");
	        list.addObject("right_switch_straight", "r_sw_str");
	        list.addObject("back", "r_back");
			return list;
		}
		else
		{
			list.addObject("Please select a side", "default");
			return list;
		}
	}
	
	public Command getCommand(SendableChooser<String> mainMenu, SendableChooser<String> list)
	{
		if (mainMenu.getSelected().equals("Left")) {
	        if (list.getSelected().equals("l_sc_opp"))
	        	return new left_scale_opposite();
	        else if (list.getSelected().equals("l_sc_side"))
	        	return new left_scale_side();
	        else if (list.getSelected().equals("l_sw_side"))
	        	return new left_switch_side();
	        else
	        	return new left_baseline();
	    }
	
	    if (mainMenu.getSelected().equals("Center")) {
	        if (list.getSelected().equals("c_l_sw_side"))
	        	return new center_left_switch_side();
	        else if (list.getSelected().equals("c_l_sw_str"))
	        	return new center_left_switch_straight();
	        else if (list.getSelected().equals("c_r_sw_str"))
	        	return new center_right_switch_straight();
	        else if (list.getSelected().equals("c_r_sw_side"))
	        	return new center_right_switch_side();
	        else 
	        	return new center_baseline();
	    }
	
	    if (mainMenu.getSelected().equals("Right")) {
	        if (list.getSelected().equals("r_sc_opp")) {
	        	return new right_scale_opposite();
	        } else if (list.getSelected().equals("r_sc_side")) {
	        	return new right_scale_side();
	        } else if (list.getSelected().equals("r_sw_side")) {
	        	return new right_switch_side();
	        } else if (list.getSelected().equals("r_sw_str")) {
	        	return new right_switch_straight();
	        } else {
	        	return new right_baseline();
	        }
	    }
	    
	    else
	    	return new CancelCommand();
	}
}

/*m_chooser.addDefault("Do Nothing", new CancelCommand());
m_chooser.addObject("Turn Right 90 degrees", new NavX_Pivot(90));
m_chooser.addObject("Turn Left 90 degrees", new NavX_Pivot(-90));
m_chooser.addObject("Turn Right 45 degrees", new NavX_Pivot(45));
m_chooser.addObject("Turn Left 45 degrees", new NavX_Pivot(-45));

m_chooser.addObject("0.5ft_encoders", new Encoders_Drive(6));
m_chooser.addObject("0.5ft_encoders_back", new Encoders_Drive(-6));
m_chooser.addObject("1.5ft_encoders", new Encoders_Drive(18));
m_chooser.addObject("1.5ft_encoders_back", new Encoders_Drive(-18));
m_chooser.addObject("1ft_encoders", new Encoders_Drive(12));
m_chooser.addObject("1ft_encoders_back", new Encoders_Drive(-12));

/*m_chooser.addDefault("Do Nothing", new CancelCommand());
m_chooser.addObject("Right Position", new Auto_Right());
m_chooser.addObject("Center Position", new Auto_Center());
m_chooser.addObject("Left Position", new Auto_Left());
*/
/*autonomousDelay.addDefault("0 Second", 0.0);
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
SmartDashboard.putData("Try Right from Center", tryRight);*/
