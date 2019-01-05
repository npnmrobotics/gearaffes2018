package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Test {
	static boolean leftFrontMotor = false;
	static boolean leftBackMotor = false;
	static boolean leftMiddleMotor = false;
	static boolean rightFrontMotor = false;
	static boolean rightBackMotor = false;
	static boolean rightMiddleMotor = false;
	static double testSpeed_pct = .5;
	
	public static void init() {
		SmartDashboard.putBoolean("Test left front motor",leftFrontMotor);
		SmartDashboard.putBoolean("Test left back motor",leftBackMotor);
		SmartDashboard.putBoolean("Test left middle motor",leftMiddleMotor);
		SmartDashboard.putBoolean("Test right front motor",rightFrontMotor);
		SmartDashboard.putBoolean("Test right back motor",rightBackMotor);
		SmartDashboard.putBoolean("Test right middle motor",rightMiddleMotor);
		SmartDashboard.putNumber("Test speed (pct)",testSpeed_pct);
		
	}
	
	public static void periodic() {
		leftFrontMotor = SmartDashboard.getBoolean("Test left front motor",leftFrontMotor);
		leftBackMotor = SmartDashboard.getBoolean("Test left back motor",leftBackMotor);
		leftMiddleMotor = SmartDashboard.getBoolean("Test left middle motor",leftMiddleMotor);
		rightFrontMotor = SmartDashboard.getBoolean("Test right front motor",rightFrontMotor);
		rightBackMotor = SmartDashboard.getBoolean("Test right back motor",rightBackMotor);
		rightMiddleMotor = SmartDashboard.getBoolean("Test right middle motor",rightMiddleMotor);	
		testSpeed_pct = SmartDashboard.getNumber("Test speed (pct)",testSpeed_pct);
	}
	
	public static void start() {
		if (leftFrontMotor) {
			Hardware.driveLeftFrontMotor.set(testSpeed_pct);
		}
		if (leftBackMotor) {
			Hardware.driveLeftBackMotor.set(testSpeed_pct);
		}
		if (leftMiddleMotor) {
			Hardware.driveLeftMiddleMotor.set(testSpeed_pct);
		}
		if (rightFrontMotor) {
			Hardware.driveRightFrontMotor.set(testSpeed_pct);
		}
		if (rightBackMotor) {
			Hardware.driveRightBackMotor.set(testSpeed_pct);
		}
		if (rightMiddleMotor) {
			Hardware.driveRightMiddleMotor.set(testSpeed_pct);
		}
	}
	
	public static void end() {
		
	}
}
