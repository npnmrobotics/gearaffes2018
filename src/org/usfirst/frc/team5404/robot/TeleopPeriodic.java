package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopPeriodic {
	public static double maxVolt;
	public static double minVolt;
	public static double totalVolt; // used to calculate average
	public static int timeTick;
	
	
	public static void init() {
		timeTick=0;
		DataLog.logTeleopDetail("------- Teleop Data Log ------------");
		DataLog.logTeleopDetail("Time"
				+ "\t" + "Distance"
				+ "\t" + "Rate"
				+ "\t" + "Heading"
				+ "\t" + "Speed control"
				+ "\t" + "Speed"
				+ "\t" + "Turbo"
				+ "\t" + "Steer control"
				+ "\t" + "Steer"
				+ "\t" + "Voltage"
				+ "\t" + "Amps total"
				+ "\t" + "Amps RF"
				+ "\t" + "Amps RB"
				+ "\t" + "Amps RM"
				+ "\t" + "Amps LF"
				+ "\t" + "Amps LB"
				+ "\t" + "Amps LM"
				);
	}
	
	public static void period20MS() {
		// timeTick is incremented first so that if events are logged by parts of the 
		// program they can use the timeTick to tie it to the detail log. It does 
		// mean that the first log entry will be 1, not 0.
		timeTick += 1;
		DataLog.logTeleopDetail(String.format("%d", timeTick)
				+ "\t" + String.format("%f", Robot.combinedEncoderDistance_in)
				+ "\t" + String.format("%.2f", Robot.combinedEncoderRate_ips)
				+ "\t" + String.format("%.2f", Robot.gyroAngle_deg)
				+ "\t" + String.format("%.2f", Drive.speedControlSave_pct)
				+ "\t" + String.format("%.2f", Drive.speed_pct)
				+ "\t" + String.format("%b", Drive.turboOn)
				+ "\t" + String.format("%.2f", Drive.steerControlSave_pct)
				+ "\t" + String.format("%.2f", Drive.steer_pct)
//				+ "\t" + String.format("%.1f", Robot.pdpVoltage)
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getTotalCurrent())
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightFrontPWM))
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightBackPWM))
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightMiddlePWM))
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftFrontPWM))
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftBackPWM))
//				+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftMiddlePWM))
				);
		SmartDashboard.putNumber("Left encoder", Hardware.leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right encoder", Hardware.rightDriveEncoder.getDistance());
	}
	
	public static void period500MS() {
	
	}
	
	public static void period1000MS() {
		
	}
}
