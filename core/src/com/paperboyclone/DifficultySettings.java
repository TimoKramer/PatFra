package com.paperboyclone;

public class DifficultySettings {

	private float ObstacleSpawnDelayModifier;
	private float PlayerMinSpeed;
	private String currentMode;
	
	public DifficultySettings(String Mode){
		
		setMode(Mode);
	}
	
	public void setMode(String Mode){
		
		if(Mode == "EASY"){
			currentMode = Mode;
			setEasy();
		}
		else if(Mode == "NORMAL"){
			currentMode = Mode;
			setNormal();
		}
		else if(Mode == "HARD"){
			currentMode = Mode;
			setHard();
		}
		else{
			System.out.println("DifficultySettings: \"" +Mode+ "\"not supported! changing mode to NORMAL");
			currentMode = "NORMAL";
			setNormal();
		}
	}
	
	public void setEasy(){
		ObstacleSpawnDelayModifier = 1.5f;
		PlayerMinSpeed = 75;
	}
	
	public void setNormal(){
		ObstacleSpawnDelayModifier = 1.0f;
		PlayerMinSpeed = 150;
	}
	
	public void setHard(){
		ObstacleSpawnDelayModifier = 0.5f;
		PlayerMinSpeed = 300;
	}
	
	public float getObstacleSpawnDelayModifier(){
		return new Float(this.ObstacleSpawnDelayModifier);
	}
	
	public float getPlayerMinSpeed(){
		return new Float(this.PlayerMinSpeed);
	}

	public String getCurrentMode() {
		return currentMode;
	}

	
}
