package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * <code>ITask</code> is the interface for all the tasks that need to be
 * done during running the game.
 * 
 * @author Martin Freudenberg
 *
 */
public interface ITask {
	
	public void doTask(GameWorld gameworld, float deltaTime);
	public void draw(SpriteBatch batch);
	public boolean isAlive();
	
}
