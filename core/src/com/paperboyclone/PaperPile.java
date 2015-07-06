package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * <code>PaperPile</code> is the <code>BasicGameEntity</code> that represents a pile of newspapers 
 * in the game. If the <code>Player</code> picks it up, he gathers a number of <code>Paper</code>s 
 * to throw.
 * 
 * @author Timo Kramer
 * @author Martin Freudenberg
 */
public class PaperPile extends BasicGameEntity {
	
	private boolean isCollidingWithPlayer;
	private PlayerStatsListener playerStatsListener;
	private float scaleProcessTime;

	/**
	 * creates a new <code>PaperPile</code> at a specified position with a specified
	 * texture.
	 * 
	 * @param position	the position to create on the <code>Screen</code>
	 * @param texture	the texture to use for drawing on the <code>Screen</code>
	 */
	public PaperPile(Vector2 position, Texture texture) {
		super(position, texture);
		playerStatsListener = new PlayerStatsListener();
		scaleProcessTime = 0f;
	}
	
	/**
	 * creates a new <code>PaperPile</code> at a specified position
	 * 
	 * @param position	the position to create on the <code>Screen</code>
	 */
	public PaperPile(Vector2 position) {
		super(position, Assets.getTexture("paperPile.png"));
		playerStatsListener = new PlayerStatsListener();
	}
	
	public PaperPile() {
		super();
	}
	
	/**
	 * updates the position of the <code>PaperPile</code> and adds a 'breathing' animation.
	 */
	public void update(float deltaTime){
		scaleProcessTime += 0.5f * deltaTime;
		float s = 90f + (float) (10 * Math.cos(Math.PI * 2 * scaleProcessTime / 0.4));
		sprite.setScale(s/100);
	}
	
	/**
	 * handles the collision-event in case the <code>Player</code> collides with it.
	 */
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!isCollidingWithPlayer) {
				playerStatsListener.pickupPaperPile();
				gameworld.add(new ScoreDisplayTask(position, "+10"));
				AudioPlayer.getInstance().playCollectPaperSound();
			}
			this.isCollidingWithPlayer = true;
			gameworld.erase(this, PaperPile.class);
		}
	}

	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
	}

}
