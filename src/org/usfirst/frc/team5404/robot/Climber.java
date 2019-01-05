package org.usfirst.frc.team5404.robot;

/****************************************************************************
The requirements for the climb module are as follows. There are 3 components
to control: 
 - Climber arm piston 
 - Detacher piston
 - Climber motor
 
 The initial state is: 
  - The climber piston is off
  - The detacher piston is on
  - The climber motor power is zero
  
 The controls to use are:
  - Left trigger - Climber arm piston
  - Left bumper - Detacher piston
  - Back button - Climber motor
  
 To climb, the following steps will be performed.
  1) The operator presses the left trigger and the climber arm extends.
  2) The driver moves the robot to position the arm above the bar.
  3) The operator presses the left trigger again and the climber arm goes down.
  4) If the arm misses the bar, the operator can go back to step 1
  5) When the arm is resting on the bar, the operator presses the left bumper to detach the hook
  6) The driver backs up allowing the hook to pull away from the arm and the arm to come down fully.
  7) When they are ready to climb, the operator holds down the back button to make the climber
     motor run. Releasing the button will cause the motor to stop. Pressing it again will make it run.
 

****************************************************************************/

public class Climber {
	private static boolean climberArmDown = true;
	private static boolean detached = false;
	
	public static void init() {
		//Hardware.climberDetachPiston.set(false);
	}
	
	public static void detach() {
			//if (!climberArmDown) {Hardware.climberDetachPiston.set(false);}
	}
		
	public static void startMotor() {
		if (detached) {Hardware.climberMotor.set(Constants.climberMaxPower);}
	}
		
	public static void stopMotor() {
		Hardware.climberMotor.set(0);
	}
	
	
	public static void toggleArmPiston() {
		if (climberArmDown) {
			//Hardware.climberArmPiston.set(true);
			climberArmDown = false;
		} else {
		//	Hardware.climberArmPiston.set(false);
			climberArmDown = true;
		}
	}
	
	
	
}