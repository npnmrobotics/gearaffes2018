package org.usfirst.frc.team5404.robot;




/* 
This class will have minimal changes. The structure will stay constant. The only changes
will be within any of the functions a possible call to a function in a custom class. The
purpose of this class is just to define the cross-reference between buttons and various
actions. The actions themselves are defined elsewhere.
*/

public class OperatorControlDispatch {

	//********************   A Button   *************************
	public static void AButtonPressed() 					{}
	public static void AButtonReleased() 					{}	
	public static void AButtonUp() 							{}	
	public static void AButtonDown() 						{}	
	public static void AButtonState(boolean down) 			{}		
	
	//********************   B Button   *************************
	public static void BButtonPressed() 					{}
	public static void BButtonReleased() 					{}	
	public static void BButtonUp() 							{}	
	public static void BButtonDown() 						{}	
	public static void BButtonState(boolean down) 			{}		
	
	//********************   X Button   *************************
	public static void XButtonPressed() 					{}
	public static void XButtonReleased() 					{}	
	public static void XButtonUp() 							{}	
	public static void XButtonDown() 						{}	
	public static void XButtonState(boolean down) 			{}	
	
	//********************   Y Button   *************************
	public static void YButtonPressed() 					{}
	public static void YButtonReleased() 					{}	
	public static void YButtonUp() 							{}	
	public static void YButtonDown() 						{}	
	public static void YButtonState(boolean down) 			{}	

//********************   Left Bumper   *************************
	public static void leftBumperPressed() 					{Drive.changeBias(-.05);}
	public static void leftBumperReleased() 				{}
	public static void leftBumperUp() 						{}
	public static void leftBumperDown() 					{}
	public static void leftBumperState(boolean down) 		{}
	
//********************   Right Bumper   *************************
	public static void rightBumperPressed() 				{Drive.changeBias(.05);}
	public static void rightBumperReleased() 				{}
	public static void rightBumperUp() 						{}
	public static void rightBumperDown() 					{}
	public static void rightBumperState(boolean down) 		{}

//********************   Left Trigger   *************************
	public static void leftTriggerPressed() 				{Climber.toggleArmPiston();}
	public static void leftTriggerReleased() 				{}
	public static void leftTriggerUp() 						{}
	public static void leftTriggerDown() 					{}
	public static void leftTriggerState(boolean down) 		{}
	public static void leftTriggerAxis(double axisValue) 	{}
	
//********************   Right Trigger   *************************
	public static void rightTriggerPressed() 				{}
	public static void rightTriggerReleased() 				{}
	public static void rightTriggerUp() 					{}
	public static void rightTriggerDown()				 	{}
	public static void rightTriggerState(boolean down) 		{}
	public static void rightTriggerAxis(double axisValue) 	{}

//********************   Back Button   *************************
	public static void backButtonPressed() 					{Climber.startMotor();}	
	public static void backButtonReleased() 				{Climber.stopMotor();}	
	public static void backButtonUp() 						{}	
	public static void backButtonDown() 					{}	
	public static void backButtonState(boolean down) 		{}
	
	//******************** Left Joystick ********************
	
	public static void joystickLXAxisValue(double val) 		{}
	public static void joystickLYAxisValue(double val) 		{}
	
	//******************** Right Joystick *******************
	
	public static void joystickRXAxisValue(double val) 		{}
	public static void joystickRYAxisValue(double val)		{}
	

}
