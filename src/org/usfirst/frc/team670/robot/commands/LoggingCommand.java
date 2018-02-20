package org.usfirst.frc.team670.robot.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import edu.wpi.first.wpilibj.command.Command;

public abstract class LoggingCommand extends Command {
	private int executeCount = 0;
	protected int executeLoggingInterval = 10;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	protected void logInitialize(Map<String, Object> data) {
		log("initialize", data);
	}

	protected void logExecute(Map<String, Object> data) {
		if (executeCount % executeLoggingInterval == 0) {
			data.put("executionCount", executeCount);
			log("execute", data);
		}
		executeCount++;
	}

	protected void logFinished(Map<String, Object> data) {
		log("finished", data);
	}

	private void log(String stage, Map<String, Object> data) {
		System.out.print(sdf.format(new Date()) + " command=" + this.getClass().getName() + " stage=" + stage + " {");
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			System.out.print(entry.getKey() + "=" + entry.getValue().toString() + " ");
		}
		System.out.println("}");
	}
	
	protected void interrupted() {
		logFinished(new HashMap<String, Object>() {{
    		put("Interrupted", "interrupted");
		}});	}

}
