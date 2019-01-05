package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;


public class Hardware {
//============================== First part is to define the port mappings =========================
	
	//------------------------------  PWM PORTS  ----------------------------------
		// Climber motor controller
		private static final int ClimberMotorPWM = 6;
		public static final int driveMotorRightFrontPWM = 4;
		public static final int driveMotorRightBackPWM = 3;
		public static final int driveMotorRightMiddlePWM = 5;
		public static final int driveMotorLeftFrontPWM = 1;
		public static final int driveMotorLeftBackPWM = 0;
		public static final int driveMotorLeftMiddlePWM = 2;
		
		
	//------------------------------  PCM PORTS -----------------------------------
		// Climber piston
		public static final int ClimberArmPistonPCM = 5;
		public static final int ClimberDetachPistonPCM = 4;
		
	//------------------------------  DIO PORTS -----------------------------------
		
		private static final int leftEncoderDIO1 = 0;
		private static final int leftEncoderDIO2 = 1;
		private static final int rightEncoderDIO1 = 2;
		private static final int rightEncoderDIO2 = 3;
		
//=============================== Second part is where all the hardware objects are declared ========	
		
		public static VictorSP climberMotor = new VictorSP(Hardware.ClimberMotorPWM);
		
		//public static Solenoid climberArmPiston = new Solenoid(ClimberArmPistonPCM);
		//public static Solenoid climberDetachPiston = new Solenoid(ClimberDetachPistonPCM);
		
		//drive motors
		public static VictorSP driveRightFrontMotor = new VictorSP(driveMotorRightFrontPWM);	
		public static VictorSP driveRightBackMotor = new VictorSP(driveMotorRightBackPWM);	
		public static VictorSP driveRightMiddleMotor = new VictorSP(driveMotorRightMiddlePWM);
		public static VictorSP driveLeftFrontMotor = new VictorSP(driveMotorLeftFrontPWM);	
		public static VictorSP driveLeftBackMotor = new VictorSP(driveMotorLeftBackPWM);	
		public static VictorSP driveLeftMiddleMotor = new VictorSP(driveMotorLeftMiddlePWM);
		
		public static SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(Hardware.driveLeftFrontMotor, Hardware.driveLeftBackMotor);
		public static SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(Hardware.driveRightFrontMotor, Hardware.driveRightBackMotor);

		public static DifferentialDrive driveSystem = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
		
		// drive encoders
		public static Encoder leftDriveEncoder = new Encoder(leftEncoderDIO1, leftEncoderDIO2);
		public static Encoder rightDriveEncoder = new Encoder(rightEncoderDIO1, rightEncoderDIO2);
		
		public static void init() {
			// first cal: 0.087368
			// second cal: 0.0891
			// calculated results: 0.087965
			leftDriveEncoder.setDistancePerPulse(0.087965);
			rightDriveEncoder.setDistancePerPulse(-0.087965);
		}
		
		// gyro
		public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
		public static void resetGyro() {
			gyro.reset();
			Robot.gyroAngle_deg = gyro.getAngle();
		}
		
		//power distribution board
		static PowerDistributionPanel powerDistPanel = new PowerDistributionPanel();
		
		
}