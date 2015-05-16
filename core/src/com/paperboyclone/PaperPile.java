package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PaperPile extends BasicGameEntity {
	
	private boolean isCollidingWithPlayer;
	private PlayerStatsListener playerStatsListener;

	public PaperPile(Vector2 position, Texture texture) {
		super(position, texture);
		playerStatsListener = new PlayerStatsListener();
	}
	
	public PaperPile(Vector2 position) {
		super(position, Assets.getTexture("paperPile.png"));
		playerStatsListener = new PlayerStatsListener();
	}
	
	public PaperPile() {
		super();
	}
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!isCollidingWithPlayer) {
				playerStatsListener.pickupPaperPile();
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
