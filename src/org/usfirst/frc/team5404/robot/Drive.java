package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class Drive {
	
	public static boolean softStartOn = true;
	public static double softStartValue_pct = .1;
	
	public static double driveDeadband = .1;
	public static double driveMaxPower_pct = 1;
	public static double driveMinPower_pct = .3;
	public static double driveExponent = 2;
	public static boolean turboOn = false;
	
	public static double steerDeadband = .1;
	public static double steerMaxPower_pct = .8;
	public static double steerMinPower_pct = .3;
	public static double steerExponent = 2;
	public static double steerBias_pct = 0;
	
	public static double steerInPlaceDeadband_pct = .1;
	public static double steerInPlaceMaxPower_pct = 1;
	public static double steerInPlaceMinPower_pct = .2;
	public static double steerInPlaceExponent = 3;
	
	public static double speedControlSave_pct;
	public static double steerControlSave_pct;
	
	// Variables used during run
	public static double speed_pct;
	public static double steer_pct;
	private static double speedChange_pct;
	
	public static void init() {
		SmartDashboard.putBoolean("Soft start", Drive.softStartOn);
		SmartDashboard.putNumber("Soft start value", Drive.softStartValue_pct);
		SmartDashboard.putNumber("Turn power", Drive.steerMaxPower_pct);
		SmartDashboard.getNumber("Turn in place power",steerInPlaceMaxPower_pct);
		
		DataLog.logSummary("Soft start on = " + String.format("%b", softStartOn));
		DataLog.logSummary("Soft start value = " + String.format("%.2f", softStartValue_pct));
		
		DataLog.logSummary("Drive deadband = " + String.format("%.2f", driveDeadband));
		DataLog.logSummary("Drive max power = " + String.format("%.2f", driveMaxPower_pct));
		DataLog.logSummary("Drive min power = " + String.format("%.2f", driveMinPower_pct));
		DataLog.logSummary("Drive exponent = " + String.format("%.2f", driveExponent));
		
		DataLog.logSummary("Steer deadband = " + String.format("%.2f", steerDeadband));
		DataLog.logSummary("Steer max power = " + String.format("%.2f", steerMaxPower_pct));
		DataLog.logSummary("Steer min power = " + String.format("%.2f", steerMinPower_pct));
		DataLog.logSummary("Steer exponent = " + String.format("%.2f", steerExponent));
		
		DataLog.logSummary("Steer in place deadband = " + String.format("%.2f", steerInPlaceDeadband_pct));
		DataLog.logSummary("Steer in place max power = " + String.format("%.2f", steerInPlaceMaxPower_pct));
		DataLog.logSummary("Steer in place min power = " + String.format("%.2f", steerInPlaceMinPower_pct));
		DataLog.logSummary("Steer in place exponent = " + String.format("%.2f", steerInPlaceExponent));
	}
	private static double power, steering;
	public static void setPower(double speedControl_pct) {
		/*
		softStartOn = SmartDashboard.getBoolean("Soft start", softStartOn);
		softStartValue_pct = SmartDashboard.getNumber("Soft start value", softStartValue_pct);
		steerMaxPower_pct = SmartDashboard.getNumber("Turn power", steerMaxPower_pct);
		steerInPlaceMaxPower_pct = SmartDashboard.getNumber("Turn in place power",steerInPlaceMaxPower_pct);
		speedChange_pct = 0;
		
		if (softStartOn) {
			if (Math.abs(speedControl_pct)<driveDeadband) {
				speed_pct=0; // makes it go quickly to zero power when reversing instead of slowly
			} else {						
				double range = driveMaxPower_pct - driveMinPower_pct;
				double offset = driveMinPower_pct;
				double targetSpeed = Math.pow(Math.abs(speedControl_pct),driveExponent) * range + offset;				
				speedChange_pct=targetSpeed-speed_pct;
				if (speedChange_pct > softStartValue_pct) {
					speedChange_pct = softStartValue_pct;
				} 
				speedChange_pct *= Math.signum(speedControl_pct);
			}						
			speed_pct += speedChange_pct;
		}
		else {
			speed_pct=speedControl_pct;
		}				
		Hardware.driveSystem.arcadeDrive(speed_pct, steer_pct,true);	
		
		if (turboOn) {
			Hardware.driveLeftMiddleMotor.setSpeed(1.0 * Math.signum(speedControl_pct));
			Hardware.driveRightMiddleMotor.setSpeed(1.0 * Math.signum(speedControl_pct));
		} else {
			Hardware.driveLeftMiddleMotor.setSpeed(0);
			Hardware.driveRightMiddleMotor.setSpeed(0);
		}
		
		speedControlSave_pct = speedControl_pct; //store so it can be logged
		*/
		power = speedControl_pct;
	}
	
	public static void setDirection(double steerControl_pct) {
		/*
		if (Math.abs(steerControl_pct)<= steerInPlaceDeadband_pct) {
			double range = steerInPlaceMaxPower_pct - steerInPlaceMinPower_pct;
			double offset = steerInPlaceMinPower_pct;
			steer_pct = Math.pow(Math.abs(steerControl_pct), steerExponent) * range + offset;
			steer_pct *= Math.signum(steerControl_pct);
		} else {
			double range = steerMaxPower_pct - steerMinPower_pct;
			double offset = steerMinPower_pct;
			steer_pct = Math.pow(Math.abs(steerControl_pct), steerInPlaceExponent) * range + offset;
			steer_pct *= Math.signum(steerControl_pct);
		}	
		steerControlSave_pct = steerControl_pct; //store so it can be logged
		*/
		steering = steerControl_pct;
		Hardware.driveSystem.curvatureDrive(power, steering, turboOn);
	}
	
	public static void toggleSoftStart () {
		softStartOn = !softStartOn;
		DataLog.logSummary(String.format("Time: %d ", TeleopPeriodic.timeTick 
				+ String.format(" Soft start: %b", softStartOn)));
		SmartDashboard.putBoolean("Soft start", softStartOn);
	}
	
	public static void changeBias(double biasChange) {
		steerBias_pct += biasChange;
		if (steerBias_pct>.2) {
			steerBias_pct = .2;
		}
		if (steerBias_pct <-.2) {
			steerBias_pct = -.2;
		}
		System.out.print("bias = ");
		System.out.println(steerBias_pct);
		SmartDashboard.putNumber("Bias",  steerBias_pct);
	}
	
	public static void turboOn() {
		turboOn = true;
	}

	public static void turboOff() {
		turboOn = false;
	}
}
