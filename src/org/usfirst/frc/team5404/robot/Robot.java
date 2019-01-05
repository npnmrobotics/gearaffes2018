/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/



package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	private boolean testMode = false;	
	private int logMode = 0;
	//public SendableChooser chooser;
	
	private Timer m_timer = new Timer();

	private XboxController driverController  = new XboxController(0);
	private XboxController operatorController  = new XboxController(1);
	
	// These variables store the sensor values so that when used, they don't
	// have different values during the cycle.
	public static double leftEncoderDistance_in;
	public static double rightEncoderDistance_in;
	public static double combinedEncoderDistance_in;
	public static double leftEncoderRate_ips;
	public static double rightEncoderRate_ips;
	public static double combinedEncoderRate_ips;
	public static double gyroAngle_deg;
	public static double pdpVoltage;
	public static int timeTick;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Drive.init();
		Climber.init();
		Hardware.init();		
	}
	
	@Override
	public void robotPeriodic() {
		if (testMode) {
			Test.periodic();
			//-------------------------------------  A Button  -------------------------------------------------	
			if (driverController.getAButtonPressed()) {Test.start();}
			if (driverController.getAButtonReleased()) {Test.end();}
			if (driverController.getAButton()) {}
			if (!driverController.getAButton()) {}
			//DriverControlDispatch.AButtonState(driverController.getAButtonPressed());
			return;
		}
		//This is the only place in the program these values are read. This locks in 
		//the value for the whole cycle so everywhere it is used it is the same. When
		//we log data we know that the number we report in the log is exactly the 
		//same as the one the program is seeing.
		leftEncoderDistance_in = Hardware.leftDriveEncoder.getDistance();
		rightEncoderDistance_in = Hardware.rightDriveEncoder.getDistance();
		combinedEncoderDistance_in = (leftEncoderDistance_in + rightEncoderDistance_in) / 2;
		rightEncoderRate_ips = Hardware.rightDriveEncoder.getRate();
		leftEncoderRate_ips = Hardware.leftDriveEncoder.getRate();
		combinedEncoderRate_ips= rightEncoderRate_ips;
		gyroAngle_deg = Hardware.gyro.getAngle();
		//pdpVoltage = Hardware.powerDistPanel.getVoltage();
		
		SmartDashboard.putNumber("Left encoder", leftEncoderDistance_in);
		SmartDashboard.putNumber("Right encoder",rightEncoderDistance_in);
		SmartDashboard.putNumber("Gyro reading", gyroAngle_deg);
		SmartDashboard.putNumber("Speedometer", combinedEncoderRate_ips );
		SmartDashboard.putNumber("Voltage", Hardware.powerDistPanel.getVoltage());
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
		
		Autonomous.init();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Autonomous.periodic();
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	
	public static int ticks;
	
	@Override
	public void teleopInit() {
		ticks = 0;
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	public static boolean driverLeftTriggerDown = false;
	public static boolean driverRightTriggerDown = false;
	public static double driverLeftTriggerAxisValue = 0;
	public static double driverRightTriggerAxisValue = 0;
	
	public static boolean operatorLeftTriggerDown = false;
	public static boolean operatorRightTriggerDown = false;
	public static double operatorLeftTriggerAxisValue = 0;
	public static double operatorRightTriggerAxisValue = 0;
	
	@Override
	public void teleopPeriodic() {
		
		
		// These would be set up for all the controls for both controllers. Then this file is never changed.

		/** OPERATOR **/
		
		//-------------------------------------  A Button  -------------------------------------------------	
		if (operatorController.getAButtonPressed()) {OperatorControlDispatch.AButtonPressed();}
		if (operatorController.getAButtonReleased()) {OperatorControlDispatch.AButtonReleased();}
		if (operatorController.getAButton()) {OperatorControlDispatch.AButtonDown();}
		if (!operatorController.getAButton()) {OperatorControlDispatch.AButtonUp();}
		OperatorControlDispatch.AButtonState(operatorController.getAButtonPressed());
	
		
		//-------------------------------------  B Button  -------------------------------------------------	
		if (operatorController.getBButtonPressed()) {OperatorControlDispatch.BButtonPressed();}
		if (operatorController.getBButtonReleased()) {OperatorControlDispatch.BButtonReleased();}
		if (operatorController.getBButton()) {OperatorControlDispatch.BButtonDown();}
		if (!operatorController.getBButton()) {OperatorControlDispatch.BButtonUp();}
		OperatorControlDispatch.BButtonState(operatorController.getBButtonPressed());
		
		//-------------------------------------  X Button  -------------------------------------------------	
		if (operatorController.getXButtonPressed()) {OperatorControlDispatch.XButtonPressed();}
		if (operatorController.getXButtonReleased()) {OperatorControlDispatch.XButtonReleased();}
		if (operatorController.getXButton()) {OperatorControlDispatch.XButtonDown();}
		if (!operatorController.getXButton()) {OperatorControlDispatch.XButtonUp();}
		OperatorControlDispatch.XButtonState(operatorController.getXButtonPressed());
		
		//-------------------------------------  Y Button  -------------------------------------------------	
		if (operatorController.getYButtonPressed()) {OperatorControlDispatch.YButtonPressed();}
		if (operatorController.getYButtonReleased()) {OperatorControlDispatch.YButtonReleased();}
		if (operatorController.getYButton()) {OperatorControlDispatch.YButtonDown();}
		if (!operatorController.getYButton()) {OperatorControlDispatch.YButtonUp();}
		OperatorControlDispatch.YButtonState(operatorController.getYButtonPressed());
		
		//------------------------------------- Left Bumper -----------------------------------------------
		if (operatorController.getBumperPressed(Hand.kLeft)) {OperatorControlDispatch.leftBumperPressed();}
		if (operatorController.getBumperReleased(Hand.kLeft)) {OperatorControlDispatch.leftBumperReleased();}
		if (operatorController.getBumper(Hand.kLeft)) {OperatorControlDispatch.leftBumperDown();}
		if (!operatorController.getBumper(Hand.kLeft)) {OperatorControlDispatch.leftBumperUp();}
		
		//------------------------------------- Right Bumper -----------------------------------------------
		if (operatorController.getBumperPressed(Hand.kRight)) {OperatorControlDispatch.rightBumperPressed();}
		if (operatorController.getBumperReleased(Hand.kRight)) {OperatorControlDispatch.rightBumperReleased();}
		if (operatorController.getBumper(Hand.kRight)) {OperatorControlDispatch.rightBumperDown();}
		if (!operatorController.getBumper(Hand.kRight)) {OperatorControlDispatch.rightBumperUp();}
				
		//-------------------------------------  Left Trigger  -------------------------------------------------	
		
		operatorLeftTriggerAxisValue=operatorController.getTriggerAxis(Hand.kLeft);
		if (operatorLeftTriggerAxisValue>0.8 && !operatorLeftTriggerDown) {
			operatorLeftTriggerDown = true;
			OperatorControlDispatch.leftTriggerPressed();
		}
		if (operatorLeftTriggerAxisValue<0.79 && operatorLeftTriggerDown) {
			operatorLeftTriggerDown = false;
			OperatorControlDispatch.leftTriggerReleased();
		}
		if (operatorLeftTriggerDown) {OperatorControlDispatch.leftTriggerDown();}
		if (!operatorLeftTriggerDown) {OperatorControlDispatch.leftTriggerUp();}
		OperatorControlDispatch.leftTriggerState(operatorLeftTriggerDown);
		OperatorControlDispatch.leftTriggerAxis(operatorLeftTriggerAxisValue);
		
		//-------------------------------------  Right Trigger -------------------------------------------------
		
		operatorRightTriggerAxisValue=operatorController.getTriggerAxis(Hand.kRight);
		if (operatorRightTriggerAxisValue>0.8 && !operatorRightTriggerDown) {
			operatorRightTriggerDown = true;
			OperatorControlDispatch.rightTriggerPressed();
		}
		if (operatorRightTriggerAxisValue<0.79 && operatorRightTriggerDown) {
			operatorRightTriggerDown = false;
			OperatorControlDispatch.rightTriggerReleased();
		}
		if (operatorRightTriggerDown) {OperatorControlDispatch.rightTriggerDown();}
		if (!operatorRightTriggerDown) {OperatorControlDispatch.rightTriggerUp();}
		OperatorControlDispatch.rightTriggerState(operatorRightTriggerDown);
		OperatorControlDispatch.rightTriggerAxis(operatorRightTriggerAxisValue);
		
		//-------------------------------------  Back Button  -------------------------------------------------	

		if (operatorController.getBackButtonPressed()) {OperatorControlDispatch.backButtonPressed();}
		if (operatorController.getBackButtonReleased()) {OperatorControlDispatch.backButtonReleased();}
		if (operatorController.getBackButton()) {OperatorControlDispatch.backButtonDown();}
		if (!operatorController.getBackButton()) {OperatorControlDispatch.backButtonUp();}
		OperatorControlDispatch.backButtonState(operatorController.getBackButtonPressed());
		
		OperatorControlDispatch.joystickLXAxisValue(operatorController.getRawAxis(Constants.LXAxis));
		OperatorControlDispatch.joystickLYAxisValue(operatorController.getRawAxis(Constants.LYAxis));
		OperatorControlDispatch.joystickRXAxisValue(operatorController.getRawAxis(Constants.RXAxis));
		OperatorControlDispatch.joystickRYAxisValue(operatorController.getRawAxis(Constants.RYAxis));
		
		
		/** DRIVER **/
		
		
		//-------------------------------------  A Button  -------------------------------------------------	
		if (driverController.getAButtonPressed()) {DriverControlDispatch.AButtonPressed();}
		if (driverController.getAButtonReleased()) {DriverControlDispatch.AButtonReleased();}
		if (driverController.getAButton()) {DriverControlDispatch.AButtonDown();}
		if (!driverController.getAButton()) {DriverControlDispatch.AButtonUp();}
		DriverControlDispatch.AButtonState(driverController.getAButtonPressed());
	
		
		//-------------------------------------  B Button  -------------------------------------------------	
		if (driverController.getBButtonPressed()) {DriverControlDispatch.BButtonPressed();}
		if (driverController.getBButtonReleased()) {DriverControlDispatch.BButtonReleased();}
		if (driverController.getBButton()) {DriverControlDispatch.BButtonDown();}
		if (!driverController.getBButton()) {DriverControlDispatch.BButtonUp();}
		DriverControlDispatch.BButtonState(driverController.getBButtonPressed());
		
		//-------------------------------------  X Button  -------------------------------------------------	
		if (driverController.getXButtonPressed()) {DriverControlDispatch.XButtonPressed();}
		if (driverController.getXButtonReleased()) {DriverControlDispatch.XButtonReleased();}
		if (driverController.getXButton()) {DriverControlDispatch.XButtonDown();}
		if (!driverController.getXButton()) {DriverControlDispatch.XButtonUp();}
		DriverControlDispatch.XButtonState(driverController.getXButtonPressed());
		
		//-------------------------------------  Y Button  -------------------------------------------------	
		if (driverController.getYButtonPressed()) {DriverControlDispatch.YButtonPressed();}
		if (driverController.getYButtonReleased()) {DriverControlDispatch.YButtonReleased();}
		if (driverController.getYButton()) {DriverControlDispatch.YButtonDown();}
		if (!driverController.getYButton()) {DriverControlDispatch.YButtonUp();}
		DriverControlDispatch.YButtonState(driverController.getYButtonPressed());
		
		//------------------------------------- Left Bumper -----------------------------------------------
		if (driverController.getBumperPressed(Hand.kLeft)) {DriverControlDispatch.leftBumperPressed();}
		if (driverController.getBumperReleased(Hand.kLeft)) {DriverControlDispatch.leftBumperReleased();}
		if (driverController.getBumper(Hand.kLeft)) {DriverControlDispatch.leftBumperDown();}
		if (!driverController.getBumper(Hand.kLeft)) {DriverControlDispatch.leftBumperUp();}
		
		//------------------------------------- Right Bumper -----------------------------------------------
		if (driverController.getBumperPressed(Hand.kRight)) {DriverControlDispatch.rightBumperPressed();}
		if (driverController.getBumperReleased(Hand.kRight)) {DriverControlDispatch.rightBumperReleased();}
		if (driverController.getBumper(Hand.kRight)) {DriverControlDispatch.rightBumperDown();}
		if (!driverController.getBumper(Hand.kRight)) {DriverControlDispatch.rightBumperUp();}
				
		//-------------------------------------  Left Trigger  -------------------------------------------------	
		
		driverLeftTriggerAxisValue=driverController.getTriggerAxis(Hand.kLeft);
		if (driverLeftTriggerAxisValue>0.8 && !driverLeftTriggerDown) {
			driverLeftTriggerDown = true;
			DriverControlDispatch.leftTriggerPressed();
		}
		if (driverLeftTriggerAxisValue<0.79 && driverLeftTriggerDown) {
			driverLeftTriggerDown = false;
			DriverControlDispatch.leftTriggerReleased();
		}
		if (driverLeftTriggerDown) {DriverControlDispatch.leftTriggerDown();}
		if (!driverLeftTriggerDown) {DriverControlDispatch.leftTriggerUp();}
		DriverControlDispatch.leftTriggerState(driverLeftTriggerDown);
		DriverControlDispatch.leftTriggerAxis(driverLeftTriggerAxisValue);
		
		//-------------------------------------  Right Trigger -------------------------------------------------
		
		driverRightTriggerAxisValue=driverController.getTriggerAxis(Hand.kRight);
		if (driverRightTriggerAxisValue>0.8 && !driverRightTriggerDown) {
			driverRightTriggerDown = true;
			DriverControlDispatch.rightTriggerPressed();
		}
		if (driverRightTriggerAxisValue<0.79 && driverRightTriggerDown) {
			driverRightTriggerDown = false;
			DriverControlDispatch.rightTriggerReleased();
		}
		if (driverRightTriggerDown) {DriverControlDispatch.rightTriggerDown();}
		if (!driverRightTriggerDown) {DriverControlDispatch.rightTriggerUp();}
		DriverControlDispatch.rightTriggerState(driverRightTriggerDown);
		DriverControlDispatch.rightTriggerAxis(driverRightTriggerAxisValue);
		
		//-------------------------------------  Back Button  -------------------------------------------------	
	
		if (driverController.getBackButtonPressed()) {DriverControlDispatch.backButtonPressed();}
		if (driverController.getBackButtonReleased()) {DriverControlDispatch.backButtonReleased();}
		if (driverController.getBackButton()) {DriverControlDispatch.backButtonDown();}
		if (!driverController.getBackButton()) {DriverControlDispatch.backButtonUp();}
		DriverControlDispatch.backButtonState(driverController.getBackButtonPressed());
		
		DriverControlDispatch.joystickLXAxisValue(driverController.getRawAxis(Constants.LXAxis));
		DriverControlDispatch.joystickLYAxisValue(driverController.getRawAxis(Constants.LYAxis));
		DriverControlDispatch.joystickRXAxisValue(driverController.getRawAxis(Constants.RXAxis));
		DriverControlDispatch.joystickRYAxisValue(driverController.getRawAxis(Constants.RYAxis));
		
		ticks += 1;
		TeleopPeriodic.period20MS();
		if ((ticks % 500) == 0) {TeleopPeriodic.period500MS();}
		if ((ticks % 1000) == 0) {TeleopPeriodic.period500MS();}
	}

	
	@Override
	public void testInit() {
		Test.init();
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		Test.periodic();
		//-------------------------------------  A Button  -------------------------------------------------	
		if (driverController.getAButtonPressed()) {Test.start();}
		if (driverController.getAButtonReleased()) {Test.end();}
		if (driverController.getAButton()) {}
		if (!driverController.getAButton()) {}
		//DriverControlDispatch.AButtonState(driverController.getAButtonPressed());
	}
		
	@Override
	public void disabledInit() {
		//DataLog.writeLog();
	}
	
	@Override
	public void disabledPeriodic() {
		if (driverController.getXButtonPressed()) {
			DataLog.writeSummaryLogStart();
		}
		DataLog.writeSummaryLog();
		
		if (driverController.getYButtonPressed()) {
			DataLog.writeAutoDetailStart();
		}
		DataLog.writeAutoDetail();		
		
		if (driverController.getAButtonPressed()) {
			DataLog.writeTeleopDetailStart();
		}
		DataLog.writeTeleopDetail();		
	}	
}


