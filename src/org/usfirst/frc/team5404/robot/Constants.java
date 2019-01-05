package org.usfirst.frc.team5404.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Constants {
	public static final double climberMaxPower = 1;
	
	public static final int LXAxis = 0;
	public static final int LYAxis = 1;
	public static final int RXAxis = 4;
	public static final int RYAxis = 5;
	
	private static class ForwardDriveDistancePID {
		public static double kP = 0.015;
		public static double kI = 0.000;
		public static double kD = 0.008;
	}
	
	private static class ForwardDriveAnglePID {
		public static double kP = 0.02;
		public static double kI = 0;
		public static double kD = 0;
	}
	
	private	static class ForwardDriveSpeedPID {
		public static double kP = 0.5;
		public static double kI = 0.5;
		public static double kD = 0.1;
	}
	
	private static class RotateAnglePID {
		public static double kP = 0.007;
		public static double kI = 0;
		public static double kD = 0;
	}
	
	private static class DummyPIDOutput implements PIDOutput {
		public void pidWrite(double value) {}
	}
	
	private static class EncoderRateSource implements PIDSource {

		private final Encoder e;
		public EncoderRateSource(Encoder e) {
			this.e = e;
			this.pidSource = PIDSourceType.kRate;
		}
		
		private PIDSourceType pidSource;
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			this.pidSource = pidSource;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return pidSource;
		}

		@Override
		public double pidGet() {
			return e.getRate();
		}
		
	}
	
	public static PIDController forwardDriveDistancePID = new PIDController(ForwardDriveDistancePID.kP, ForwardDriveDistancePID.kI, ForwardDriveDistancePID.kD,
			Hardware.rightDriveEncoder, new DummyPIDOutput());
	
	public static PIDController forwardDriveAnglePID = new PIDController(ForwardDriveAnglePID.kP, ForwardDriveAnglePID.kI, ForwardDriveAnglePID.kD,
			Hardware.gyro, new DummyPIDOutput());
	
	public static PIDController forwardDriveSpeedPID = new PIDController(ForwardDriveSpeedPID.kP, ForwardDriveSpeedPID.kI, ForwardDriveSpeedPID.kD,
			new EncoderRateSource(Hardware.rightDriveEncoder), new DummyPIDOutput());
	
	public static PIDController rotateAnglePID = new PIDController(RotateAnglePID.kP, RotateAnglePID.kI, RotateAnglePID.kD,
			Hardware.gyro, new DummyPIDOutput());
	
	
	// Thresholds
	
	public static double forwardDriveDistanceThreshold = 2; // +/- 2 inches
	public static double forwardDriveSpeedThreshold = 10; // +/- 10 inches per second
	
	public static double rotateAngleThreshold = 1; // +/- 1 degree
	
	public static double autoHighRate_ips = 72;
	public static double autoLowRate_ips = 36; // in/sec
}