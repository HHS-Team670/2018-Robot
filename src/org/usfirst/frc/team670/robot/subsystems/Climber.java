package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.joystick_control.Joystick_Climber;
import org.usfirst.frc.team670.robot.constants.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX climbMotor;
	
	public Climber()
	{
		climbMotor = new TalonSRX(RobotMap.climberMotor);
	}
	
	public void climb(double speed)
	{
		climbMotor.set(ControlMode.PercentOutput, speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Climber());
    }
}

