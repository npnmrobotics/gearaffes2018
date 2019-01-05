package org.usfirst.frc.team5404.robot;

public class DataLog {
	static int MAX_AUTO_DETAIL = 5000;
	static int writeAutoDetailIndex = MAX_AUTO_DETAIL;
	public static String[] autoDetailLog = new String[MAX_AUTO_DETAIL];
	public static int autoDetailIndex = 0;
	
	static int MAX_TELEOP_DETAIL = 5000;
	static int writeTeleopDetailIndex = MAX_TELEOP_DETAIL;
	public static String[] teleopDetailLog = new String[MAX_TELEOP_DETAIL];
	public static int teleopDetailIndex = 0;
	
	static int MAX_SUMMARY = 500;
	static int writeSummaryIndex = MAX_SUMMARY;		
	public static String[] summaryLog = new String[MAX_SUMMARY];	
	public static int summaryLogIndex = 0;
	
	public static void logAutoDetail(String s) {
		if (autoDetailIndex<MAX_AUTO_DETAIL) {
			autoDetailLog[autoDetailIndex] = s;
		autoDetailIndex+=1;
		}
	}
	
	public static void logTeleopDetail(String s) {
		if (teleopDetailIndex<MAX_TELEOP_DETAIL) {
			teleopDetailLog[teleopDetailIndex] = s;
		teleopDetailIndex+=1;
		}
	}
	
	public static void logSummary(String s) {
		if (summaryLogIndex<MAX_SUMMARY) {
			summaryLog[summaryLogIndex] = s;
			summaryLogIndex+=1;
		}
	}
	
	public static void writeSummaryLogStart() {
		writeSummaryIndex = 0;
	}
	
	public static void writeSummaryLog() {
		if (writeSummaryIndex<MAX_SUMMARY) {
			System.out.println( summaryLog[writeSummaryIndex]);
			writeSummaryIndex +=1;
			if (writeSummaryIndex >= summaryLogIndex) {
				writeSummaryIndex = MAX_SUMMARY;
			}
		}
	}
	
	public static void writeAutoDetailStart() {
		writeAutoDetailIndex = 0;
	}
	
	public static void writeAutoDetail() {
		if (writeAutoDetailIndex<MAX_AUTO_DETAIL) {
			System.out.println( autoDetailLog[writeAutoDetailIndex]);
			writeAutoDetailIndex +=1;
			if (writeAutoDetailIndex >= autoDetailIndex) {
				writeAutoDetailIndex = MAX_AUTO_DETAIL;
			}
		}
	}
	
	public static void writeTeleopDetailStart() {
		writeTeleopDetailIndex = 0;
	}
	
	public static void writeTeleopDetail() {
		if (writeTeleopDetailIndex<MAX_TELEOP_DETAIL) {
			System.out.println( teleopDetailLog[writeTeleopDetailIndex]);
			writeTeleopDetailIndex +=1;
			if (writeTeleopDetailIndex >= teleopDetailIndex) {
				writeTeleopDetailIndex = MAX_TELEOP_DETAIL;
			}
		}
	}
}
