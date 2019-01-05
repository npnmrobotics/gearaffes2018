package org.usfirst.frc.team5404.robot;

public class DriverControlDispatch {
	
//********************   A Button   *************************
	public static void AButtonPressed() 					{Drive.toggleSoftStart();}
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
	public static void leftTriggerPressed() 				{}
	public static void leftTriggerReleased() 				{}
	public static void leftTriggerUp() 						{}
	public static void leftTriggerDown() 					{}
	public static void leftTriggerState(boolean down) 		{}
	public static void leftTriggerAxis(double axisValue) 	{}
	
//********************   Right Trigger   *************************
	public static void rightTriggerPressed() 				{Drive.turboOn();}
	public static void rightTriggerReleased() 				{Drive.turboOff();}
	public static void rightTriggerUp() 					{}
	public static void rightTriggerDown()				 	{}
	public static void rightTriggerState(boolean down) 		{}
	public static void rightTriggerAxis(double axisValue) 	{}

	//********************   Back Button   *************************
	public static void backButtonPressed() 					{}	
	public static void backButtonReleased() 				{}	
	public static void backButtonUp() 						{}	
	public static void backButtonDown() 					{}	
	public static void backButtonState(boolean down) 		{}

	
	//********************   Left Joystick   *************************
	public static void joystickLXAxisValue(double val) 		{}
	public static void joystickLYAxisValue(double val) 		{Drive.setPower(-val);}
	
	//********************   Right Joystick   *************************
	public static void joystickRXAxisValue(double val) 		{Drive.setDirection(val);}
	public static void joystickRYAxisValue(double val)		{}
	
}
