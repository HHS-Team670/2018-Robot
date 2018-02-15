package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Intake;

import org.usfirst.frc.team670.robot.constants.RoboConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private Compressor comp;
	private Solenoid deployer, claw;
	private TalonSRX leftIntake, rightIntake;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		comp = new Compressor(RobotMap.PCModule);
		comp.setClosedLoopControl(true);
		leftIntake = new TalonSRX(RobotMap.intakeLeftTalon);
		rightIntake = new TalonSRX(RobotMap.intakeRightTalon);
		deployer = new Solenoid(RobotMap.PCModule,RobotMap.deployer);
		claw = new Solenoid(RobotMap.PCModule, RobotMap.claw);
	}
	
	public void driveIntake(double speed)
	{
		double batteryVoltage = Robot.pdp.getVoltage();
		double currentLimit = RoboConstants.maxIntakeVoltage/batteryVoltage;
		double current = Robot.pdp.getCurrent(RobotMap.intakeLeftTalon);
		current += Robot.pdp.getCurrent(RobotMap.intakeRightTalon);
		if(current >= currentLimit){
			System.out.println("Intake is under stress");
		}
		leftIntake.set(ControlMode.PercentOutput, speed);
		rightIntake.set(ControlMode.PercentOutput, -speed);
	}
	
	public void deploySupport(boolean deploy)
	{
		deployer.set(deploy);
	}
	
	public void deployGrabber(boolean deploy)
	{	
		claw.set(deploy);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Intake());
    }

	public boolean isIntakeOpen() {
		return claw.get();
		//return false;
	}

	public boolean isIntakeDeployed() {
		return deployer.get();
		//return false;
	}
}

