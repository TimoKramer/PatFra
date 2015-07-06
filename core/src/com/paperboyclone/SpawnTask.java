package com.paperboyclone;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * handles the regular creation of entities in the <code>GameWorld</code>.
 * 
 * @author Martin Freudenberg
 */
public abstract class SpawnTask implements ITask{
		
	private long lastSpawnTime;
	private int currentSpawnDelay;
	private int minSpawnDelay;
	private int maxSpawnDelay;
	protected OrthographicCamera camera;
	
	public SpawnTask(int minSpawnDelay, int maxSpawnDelay, OrthographicCamera cam){
		this.minSpawnDelay = minSpawnDelay;
		this.maxSpawnDelay = maxSpawnDelay;
		camera = cam;
		lastSpawnTime = TimeUtils.millis();
		currentSpawnDelay = getSpawnDelay();
	}
	
	private int getSpawnDelay(){
		return (MathUtils.random(minSpawnDelay,maxSpawnDelay));
	}
	
	private boolean IsAllowedToSpawn(long currentTime){
		
		if(lastSpawnTime + currentSpawnDelay <= currentTime){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void resetSpawnTimers(long currentTime){
		
		lastSpawnTime = currentTime;
		currentSpawnDelay = getSpawnDelay();
	}
	
	public void doTask(GameWorld gameworld, float deltaTime) {
		long currentTime = TimeUtils.millis();
		if(this.IsAllowedToSpawn(currentTime)){
			spawn(gameworld);
			resetSpawnTimers(currentTime);
		}
		
	}
	
	protected abstract void spawn(GameWorld gameworld);
	
}
