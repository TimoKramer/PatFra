package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PaperPile extends BasicGameEntity {
	
	private boolean isCollidingWithPlayer;
	private PlayerStatsListener playerStatsListener;
	private float scaleProcessTime;

	public PaperPile(Vector2 position, Texture texture) {
		super(position, texture);
		playerStatsListener = new PlayerStatsListener();
		scaleProcessTime = 0f;
	}
	
	public PaperPile(Vector2 position) {
		super(position, Assets.getTexture("paperPile.png"));
		playerStatsListener = new PlayerStatsListener();
	}
	
	public void update(float deltaTime){
		scaleProcessTime += 0.5f * deltaTime;
		float s = 90f + (float) (10 * Math.cos(Math.PI * 2 * scaleProcessTime / 0.4));
		sprite.setScale(s/100);

	}
	
	public PaperPile() {
		super();
	}
	
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
