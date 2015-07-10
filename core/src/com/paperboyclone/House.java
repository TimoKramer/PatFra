package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Class of the <code>House</code> entity
 * can be a subscriber house
 * 
 * @author Martin Freudenberg
 */
public class House extends BasicGameEntity{
	
	private boolean subscriber;
	private boolean isCollidingWithPlayer;
	private PlayerStatusListener playerStatsListener;
	private boolean isHit = false;
	
	public House(Vector2 position, Texture HouseTexture, Texture MailboxTexture){
		super(position,HouseTexture);
		subscriber = false;
		playerStatsListener = new PlayerStatusListener();
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
	
	public void setHit(){
		this.isHit = true;
	}
	
	public boolean isHit(){
		return this.isHit;
	}
	/**
	 * flip the texture horizontally, used when the house gets placed on the left side of the road 
	 */
	public void flipRight(){
		
		sprite.flip(true,false);
		
	}
	/**
	 * onCollision with player, players live decreased 
	 */
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
