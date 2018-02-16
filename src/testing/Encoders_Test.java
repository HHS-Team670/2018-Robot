package testing;

import org.usfirst.frc.team670.robot.Robot;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders_Test extends Command {

	// private char direction;

	private SensorCollection talon;
	Preferences pref;
	double time, speed;
	boolean testVel, testEnc;
	
	public Encoders_Test(TalonSRX talon) {
		pref = Preferences.getInstance();
		this.talon = new SensorCollection(talon);
		this.talon.setPulseWidthPosition(0, 0);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putString(talon.toString() + " test", ""+this.talon.getPulseWidthPosition());
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
