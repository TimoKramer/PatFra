package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * The <code>House</code> represents the houses in the game. It can be a subscriber or not and 
 * can only be hit once.
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer

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
	 * the method that is called on collision with another entity in the game. 
	 * 
	 * @param collidedObject	the object that collides with the house
	 * @param Type				the type of the object that colliedes with the house

	 */
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!this.isCollidingWithPlayer) {
				playerStatsListener.crashWithHouse();
			}
			this.isCollidingWithPlayer = true;
		}
	}
	
	/**
	 * the method that is called to as long there is no collision with another entity in the game.
	 * 
	 * @param collidedObject	the object that collides with the house
	 * @param Type				the type of the object that colliedes with the house
	 */
	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
	}


}
