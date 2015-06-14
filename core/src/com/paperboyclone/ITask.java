package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ITask {
	
	public void doTask(GameWorld gameworld, float deltaTime);
	public void draw(SpriteBatch batch);
	public boolean isAlive();
	
}
