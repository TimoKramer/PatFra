package com.paperboyclone;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class PaperPileSpawnTask extends SpawnTask{

		
	public PaperPileSpawnTask(int minSpawnDelay, int maxSpawnDelay, OrthographicCamera cam){
		super(minSpawnDelay, maxSpawnDelay, cam);
		
	}
	
	
	protected void spawn(GameWorld gameworld){
		
		PaperPile e = LevelGenerator.createRandomPaperPile(camera.position.y + camera.viewportHeight/2);
		gameworld.add(e);
	}
		
	
}
