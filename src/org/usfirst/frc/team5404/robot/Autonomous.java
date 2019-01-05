package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	public static int programCounter = 0;
	public static int programStage;
	private static double forwardDistanceSetpoint_in = 0;
	static Timer m_timer = new Timer();
	static int timeTick; 
	
	public static void init() {
		programCounter = 1;				
		forwardDistanceSetpoint_in = 0;
//		forwardMaxSpeed_pct = 0;		
//		rotationSetpoint_deg = 0;
		
		Hardware.leftDriveEncoder.reset();
		Hardware.rightDriveEncoder.reset();
		Hardware.gyro.reset();
			
		timeTick=0;
		DataLog.logAutoDetail("------- Autonomous Data Log ------------");
		DataLog.logAutoDetail("Time"
			+ "\t" + "Action" 
			+ "\t" + "Stage"
			+ "\t" + "Distance"
			+ "\t" + "Rate"
			+ "\t" + "Heading"
			+ "\t" + "Speed"
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
	
	public static void periodic() {
		// timeTick is incremented first so that if events are logged by parts of the 
		// program they can use the timeTick to tie it to the detail log. It does 
		// mean that the first log entry will be 1, not 0.
		timeTick += 1;
		DataLog.logAutoDetail(String.format("%d", timeTick)
			+ "\t" +String.format("%d", programCounter) 
			+ "\t" + String.format("%d", programStage) 
			+ "\t" + String.format("%f", Robot.combinedEncoderDistance_in)
			+ "\t" + String.format("%.2f", Robot.combinedEncoderRate_ips)
			+ "\t" + String.format("%.2f", Robot.gyroAngle_deg)
			+ "\t" + String.format("%.2f", speed_pct)
			+ "\t" + String.format("%.2f", Robot.pdpVoltage)
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getTotalCurrent())
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightFrontPWM))
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightBackPWM))
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorRightMiddlePWM))
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftFrontPWM))
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftBackPWM))
			+ "\t" + String.format("%.1f", Hardware.powerDistPanel.getCurrent(Hardware.driveMotorLeftMiddlePWM))
		);		
		
		SmartDashboard.putNumber("Program counter", programCounter);
				
		if (programCounter == 1) {
			programCounter += accelerateToLowSpeedInit();
		} else if (programCounter == 2) {
			// 36 inches forward
			programCounter += accelerateToLowSpeedRun();
		} else if (programCounter == 3) {
			programCounter += leftTurnInit();
		} else if (programCounter ==4) {
			programCounter += leftTurnRun();
		} else if (programCounter == 5) {		
			programCounter += rightTurnInit();
		} else if (programCounter == 6) {
			programCounter += rightTurnRun();
		} else if (programCounter == 7) {
			programCounter += leftTurnInit();
		} else if (programCounter ==4) {
			programCounter += leftTurnRun();
		} else if (programCounter == 5) {		
			programCounter += slowToStopInit();
		} else if (programCounter == 6) {
			programCounter += slowToStopRun();
		} else if (programCounter == 7) {
			Hardware.driveSystem.arcadeDrive(0, 0,true);
		}

		
		
		
		
		//		case 2:{
//			programCounter += motorBrakingInit();
//			break;
//		}
//		case 3:{
//			programCounter += motorBraking();
//			break;
//		}
//		case 4: {
//			programCounter += constantSpeedMoveInit(36, 24);
//			break;
//		}
//		case 5:{
//			programCounter += constantSpeedMove();
//			break;
//		}
//		default:{
//			Drive.Driving.arcadeDrive(0, 0);
//		}
//		
		/*
		case 2:{
			programCounter += fullSpeedAheadInit(48);
			break;
		}
		case 3:{
			programCounter += fullSpeedAhead();
			break;
		}*/
//		case 2:{
//			// 36 inches forward
//			programCounter += slowToStopInit();
//			break;
//		}
//		case 3:{
//			programCounter += slowToStopRun();
//		}
		
		// Total: 120 inches = 10 feet forward
		
	}

//------------------------------------------------------------------------------------------------------	
	
	public static double speed_pct;
	static double softStartValue = 0.05;
	static double PID_P = .002;
	
	public static int accelerateToLowSpeedInit() {
		// magic numbers
		m_timer.start();
		programStage = 1;
		forwardDistanceSetpoint_in = 12;
		speed_pct = .2;
			
//		Constants.rotateAnglePID.reset();
//		Constants.rotateAnglePID.enable();
//		Constants.rotateAnglePID.setSetpoint(rotationSetpoint_deg);
						
		DataLog.logSummary("Set distance \t" + String.format("%.2f", forwardDistanceSetpoint_in) );
		DataLog.logSummary("Soft start value \t" + String.format("%.2f", softStartValue) );
		DataLog.logSummary("Rate setpoint \t" + String.format("%.2f", Constants.autoLowRate_ips) );
		DataLog.logSummary("P = \t" + String.format("%.4f", PID_P) );
		
		DataLog.logSummary("" );
		Hardware.leftDriveEncoder.reset();
		Hardware.rightDriveEncoder.reset();
		return 1;
	}		
	
	public static int accelerateToLowSpeedRun() {
		int actionComplete = 0;
						
		if (programStage==1) { // use soft start logic to accelerate to target speed as quickly as possible.
			speed_pct = Math.min(speed_pct + softStartValue, 1);
			Hardware.driveSystem.arcadeDrive(speed_pct, 0);
			if (Robot.combinedEncoderRate_ips > Constants.autoLowRate_ips*.9) {
				programStage +=1;
				speed_pct = .65;
				DataLog.logSummary("Exit stage 1. encoder rate = " + String.format("%.2f", Robot.combinedEncoderRate_ips) );
			};
		} else if (programStage==2) { // use PID speed controller to maintain constant speed until distance is reached.
//			Constants.forwardDriveSpeedPID.reset();
//			Constants.forwardDriveSpeedPID.enable();
//			Constants.forwardDriveSpeedPID.setSetpoint(Constants.autoLowRate_ips);
			speed_pct = speed_pct - (Robot.combinedEncoderRate_ips - Constants.autoLowRate_ips) * PID_P;
			Hardware.driveSystem.arcadeDrive(speed_pct, 0);
			if (Robot.combinedEncoderDistance_in > forwardDistanceSetpoint_in) {
				programStage +=1;
				actionComplete=1;
			}
		}	
		return actionComplete;
	}// end accelerateToHighSpeedRun()
	
//----------------------------------------------------------------------------------------------------	
	
	static double creepSpeed_pct = .35;
	static double creepStopSpeed_pct = -.3;
	static int creepStopDuration_tick = 3;
	static int creepStopTimer = 0;
	
	public static int slowToStopInit() {
		programStage = 1;
		forwardDistanceSetpoint_in +=18;
		creepStopTimer = 0;
		Hardware.driveSystem.arcadeDrive(-.2,0);
		return 1;
	}
	
	public static int slowToStopRun() {
		int actionComplete = 0;
		

		if (programStage==1) {
			if (Robot.combinedEncoderRate_ips<1) {
				programStage = 2;
			}
		} else if (programStage==2) {
			Hardware.driveSystem.arcadeDrive(creepSpeed_pct,0);
			if (Robot.combinedEncoderDistance_in>=forwardDistanceSetpoint_in) {
				programStage = 3;
			}
		} else if (programStage==3) {
			Hardware.driveSystem.arcadeDrive(creepStopSpeed_pct,0);
			creepStopTimer +=1;
			if (creepStopTimer>=creepStopDuration_tick) {
				programStage = 4;
			}
		} else if (programStage==4) {
			Hardware.driveSystem.arcadeDrive(0,0);
			actionComplete=1;
		}
		return actionComplete;
	}

//----------------------------------------------------------------------------------------------------
	
	public static int leftTurnInit() {
		Hardware.gyro.reset();
		return 1;
	}
	
	public static int leftTurnRun() {
		speed_pct = speed_pct - (Robot.combinedEncoderRate_ips - Constants.autoLowRate_ips) * PID_P;
		Hardware.driveSystem.arcadeDrive(speed_pct, -.6 );
		if (Robot.gyroAngle_deg < -85) {
			return 1;
		} else {
			return 0;
		}
	}
	
//----------------------------------------------------------------------------------------------------
	
	public static int rightTurnInit() {
		Hardware.gyro.reset();		
		return 1;
	}
	
	public static int rightTurnRun() {
		speed_pct = speed_pct - (Robot.combinedEncoderRate_ips - Constants.autoLowRate_ips) * PID_P;
		Hardware.driveSystem.arcadeDrive(speed_pct, .6 );
		if (Robot.gyroAngle_deg > 85) {
			return 1;
		} else {
			return 0;
		}
	}	
	
//----------------------------------------------------------------------------------------------------	
	public static int slowReverse() {
		Hardware.leftMotorGroup.set(-.4);
		Hardware.rightMotorGroup.set(.4);
		if (Robot.combinedEncoderDistance_in<0 ) {
			return 1;
		} else {
			return 0;
		}
	}
	

//=====================================================================================================
		
//	// Auto drive methods - move to another file?
//	
//	
//	private static double forwardMaxSpeed_pct = 0;
//	
//	private static double rotationSetpoint_deg = 0;
//	private static double rotationMaxSpeed = 0;
//	
//	public static int motorBrakingInit() {
//		return moveForwardInit(0, 0, false);
//	}
//	
//	public static int motorBraking() {
//		if(Robot.combinedEncoderRate_ips <= 2) {
//			Hardware.driveSystem.arcadeDrive(0, 0);
//			return 1;
//		} else {
//			Hardware.driveSystem.arcadeDrive(-0.5, 0);
//			return 0;
//		}
//	}
//	
//	private static double pidSpeedSetpoint = 0;
//	
//	public static int constantSpeedMoveInit(double distance, double ips) {
//		pidSpeedSetpoint = ips;
//		return moveForwardInit(distance, 0, false);
//	}
//	
//	public static int constantSpeedMove() {
//		double distanceRemaining = forwardDistanceSetpoint_in - Hardware.rightDriveEncoder.getDistance();
//		
//		if(distanceRemaining <= 0) {
//			Hardware.driveSystem.arcadeDrive(-0.5, 0);
//			return 1;
//		} else {
//			double power = 0.35; //Constants.forwardDriveSpeedPID.get();
//			double rotation = Constants.forwardDriveAnglePID.get();
//			Hardware.driveSystem.arcadeDrive(power, rotation);
//			
//			return 0;
//		}
//	}
//	
//	// internal use only - ugly
//	private static int moveForwardInit(double distance, double speed, boolean resetSetpoint) {
//		
//		if(resetSetpoint) {
//			forwardDistanceSetpoint_in = Hardware.rightDriveEncoder.getDistance() + distance;
//		} else {
//			forwardDistanceSetpoint_in += distance;
//		}
//		forwardSoftSpeed_pct = 0; // used for soft speed ramp up in accelerateToMaxSpeed()
//		forwardMaxSpeed_pct = speed;
//		
//		Constants.forwardDriveDistancePID.reset();
//		Constants.forwardDriveDistancePID.enable();
//		Constants.forwardDriveDistancePID.setSetpoint(forwardDistanceSetpoint_in);
//		Constants.forwardDriveDistancePID.setOutputRange(-forwardMaxSpeed_pct, forwardMaxSpeed_pct);
//		
//		Constants.forwardDriveAnglePID.reset();
//		Constants.forwardDriveAnglePID.enable();
//		Constants.forwardDriveAnglePID.setSetpoint(rotationSetpoint_deg);
//		
//		Constants.forwardDriveSpeedPID.reset();
//		Constants.forwardDriveSpeedPID.enable();
//		Constants.forwardDriveSpeedPID.setSetpoint(pidSpeedSetpoint);
//		
//		// move on in the program
//		return 1;
//	}
//	
////---------------------------------------------------------------------------------------------------	
//	
//	
//
//	public static int fullSpeedAheadInit(double distance) {
//		return moveForwardInit(distance, forwardMaxSpeed_pct, false);
//	}
//	
//	public static int fullSpeedAhead() {
//		double forwardDistanceRemaining = forwardDistanceSetpoint_in - Robot.combinedEncoderDistance_in;
//		
//		if(forwardDistanceRemaining < 0) {
//			// done
//			Hardware.driveSystem.arcadeDrive(0, 0);
//			Constants.forwardDriveAnglePID.disable();
//			return 1;
//		} else {
//			double power = forwardMaxSpeed_pct;
//			double rotation = Constants.forwardDriveAnglePID.get();
//			
//			Hardware.driveSystem.arcadeDrive(power, rotation);
//			return 0;
//		}
//	}
//	
//
//	
//	
////	public static int slowToStopInit() {
////		// magic numbers
////		return moveForwardInit(36, forwardMaxSpeed, false);
////	}
////	
////	/** ------------------- TAKES 36 INCHES ------------------ **/
////	public static int slowToStop() {
////		double forwardError = Constants.forwardDriveDistancePID.getError();
////		double forwardSpeed = Hardware.rightDriveEncoder.getRate();
////		SmartDashboard.putNumber("Forward error", forwardError);
////		
////		if(Math.abs(forwardError) < Constants.forwardDriveDistanceThreshold
////				&& Math.abs(forwardSpeed) < Constants.forwardDriveSpeedThreshold) {
////			// done
////			Drive.Driving.arcadeDrive(0, 0);
////			Constants.forwardDriveDistancePID.disable();
////			Constants.forwardDriveAnglePID.disable();
////			return 1;
////		} else {
////			// must drive further
////			double power = Constants.forwardDriveDistancePID.get();
////			double rotation = Constants.forwardDriveAnglePID.get();
////			Drive.Driving.arcadeDrive(power, rotation);
////			return 0;
////		}
////	}
//	
//	public static int rotateInit(double angle, double speed) {
//		
//		// Do not reset gyro.
//		rotationSetpoint_deg += angle;
//		rotationMaxSpeed = speed;
//		
//		Constants.rotateAnglePID.reset();
//		Constants.rotateAnglePID.enable();
//		Constants.rotateAnglePID.setSetpoint(rotationSetpoint_deg);
//		Constants.rotateAnglePID.setOutputRange(-rotationMaxSpeed, rotationMaxSpeed);
//		
//		return 1;
//	}
//	
//	public static int rotate() {
//		double error = Constants.rotateAnglePID.getError();
//		SmartDashboard.putNumber("Rotate error", error);
//		
//		if(Math.abs(error) < Constants.rotateAngleThreshold) {
//			// done
//			Hardware.driveSystem.arcadeDrive(0, 0);
//			Constants.rotateAnglePID.disable();
//			return 1;
//		} else {
//			// must drive further
//			double rotation = Constants.rotateAnglePID.get();
//			Hardware.driveSystem.arcadeDrive(0, rotation);
//			return 0;
//		}
//	}
}