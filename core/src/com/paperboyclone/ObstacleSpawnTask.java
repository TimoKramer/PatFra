package com.paperboyclone;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * <code>ObstacleSpawnTask</code> takes a obstacle from the <code>LevelGenerator</code> and 
 * spawns it in the <code>GameWorld</code>.
 * 
 * @author Martin Freudenberg
 *
 */
public class ObstacleSpawnTask extends SpawnTask{

		
	public ObstacleSpawnTask(int minSpawnDelay, int maxSpawnDelay, OrthographicCamera cam){
		super(minSpawnDelay, maxSpawnDelay, cam);
		
	}
	
	
	protected void spawn(GameWorld gameworld){
		
		Obstacle e = LevelGenerator.createRandomObstacle(camera.position.y + camera.viewportHeight/2);
		gameworld.add(e);
	}


	public void draw(SpriteBatch batch) {
		
		
	}


	public boolean isAlive() {
		
		return true;
	}
		
	
}
