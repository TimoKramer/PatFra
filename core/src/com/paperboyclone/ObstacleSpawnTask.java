package com.paperboyclone;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class ObstacleSpawnTask extends SpawnTask{

		
	public ObstacleSpawnTask(int minSpawnDelay, int maxSpawnDelay, OrthographicCamera cam){
		super(minSpawnDelay, maxSpawnDelay, cam);
		
	}
	
	
	protected void spawn(GameWorld gameworld){
		
		Obstacle e = LevelGenerator.createRandomObstacle(camera.position.y + camera.viewportHeight/2);
		gameworld.add(e);
	}
		
	
}
