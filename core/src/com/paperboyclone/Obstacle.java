package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * <code>Obstacle</code> represents obstacles in the game and provides collision detection.
 * 
 * @author Martin Freudenberg
 *
 */
public class Obstacle extends BasicGameEntity{

	private boolean isCollidingWithPlayer;
	private boolean isCollidingWithPaper;
	private PlayerStatusListener playerStatsListener;
	
	public Obstacle(Vector2 position, Texture texture){
		super(position,texture);
		playerStatsListener = new PlayerStatusListener();
	}

	public Obstacle() {
		super();
	}
	
	/**
	 * the method that is called on collision with another entity in the game. It triggers 
	 * if Type is a <code>Player</code> or a <code>Paper</code>.
	 * 
	 * @param collidedObject	the object that collides with the obstacle
	 * @param Type				the type of the object that colliedes with the obstacle
	 */
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!isCollidingWithPlayer) {
				playerStatsListener.crashWithObstacle();
			}
			this.isCollidingWithPlayer = true;
		}
		if(Type == Paper.class) {
			if(!isCollidingWithPaper) {
				System.out.println("You hit an Obstacle!");
			}
		}
	}

	/**
	 * the method that is called to as long there is no collision with another entity in the game. It triggers 
	 * if Type is a <code>Player</code> or a <code>Paper</code>.
	 * 
	 * @param collidedObject	the object that collides with the house
	 * @param Type				the type of the object that colliedes with the obstacle
	 */
	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
		if(Type == Paper.class) {
			this.isCollidingWithPaper = false;
		}
	}

}
