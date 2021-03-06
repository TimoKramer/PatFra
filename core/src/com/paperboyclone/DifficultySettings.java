package com.paperboyclone;

/**
 * The Singleton <code>DifficultySettings</code> is handling the settings
 * that the user chooses from the <code>DifficultySelectionScreen</code>
 * 
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer
 */
public class DifficultySettings {

	private float ObstacleSpawnDelayModifier;
	private float PlayerMinSpeed;
	private String currentMode;
	
	private static final class InstanceHolder {
		static final DifficultySettings INSTANCE = new DifficultySettings();
	}
	
	private DifficultySettings(){
		
		setMode("NORMAL");
	}
	
	public static DifficultySettings getInstance() {
		return InstanceHolder.INSTANCE;
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
	
	/**
	 * @return	a float used as a factor for the frequency of <code>Obstacle</code>-objects 
	 */
	public float getObstacleSpawnDelayModifier(){
		return this.ObstacleSpawnDelayModifier;
	}
	
	/**
	 * @return	a float used as a factor for the player's minimum speed
	 */
	public float getPlayerMinSpeed(){
		return this.PlayerMinSpeed;
	}

	/**
	 * @return	a string specifies the current level
	 */
	public String getCurrentMode() {
		return new String(currentMode);
	}
	
}
