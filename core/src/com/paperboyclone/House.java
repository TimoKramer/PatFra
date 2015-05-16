package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class House extends BasicGameEntity{
	
	private boolean subscriber;
	private boolean isCollidingWithPlayer;
	private PlayerStatsListener playerStatsListener;
	
	public House(Vector2 position, Texture HouseTexture, Texture MailboxTexture){
		super(position,HouseTexture);
		subscriber = false;
		playerStatsListener = new PlayerStatsListener();
	}
	
	public void draw(SpriteBatch batch){
		drawSprite(batch);
	}
	
	public void subscribe(){
		subscriber = true;
	
	}
	
	public boolean isSubscriber(){
		return subscriber;
	}
	
	public void flipRight(){
		
		sprite.flip(true,false);
		
	}
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!this.isCollidingWithPlayer) {
				playerStatsListener.crashWithHouse();
			}
			this.isCollidingWithPlayer = true;
		}
	}

	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
	}


}
