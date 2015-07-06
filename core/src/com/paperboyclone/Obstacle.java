package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Martin Freudenberg
 *
 */
public class Obstacle extends BasicGameEntity{

	private boolean isCollidingWithPlayer;
	private boolean isCollidingWithPaper;
	private PlayerStatsListener playerStatsListener;
	
	public Obstacle(Vector2 position, Texture texture){
		super(position,texture);
		playerStatsListener = new PlayerStatsListener();
	}

	public Obstacle() {
		super();
	}
	
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

	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
		if(Type == Paper.class) {
			this.isCollidingWithPaper = false;
		}
	}

}
