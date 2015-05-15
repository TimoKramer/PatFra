package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Mailbox extends BasicGameEntity{
	
	private boolean full;
	private boolean subscriber;
	private boolean isCollidingWithPlayer;
	private PlayerStatsListener playerStatsListener;
	
	public Mailbox(Vector2 position,Texture texture){
		super(position,texture);
		full = false;
		subscriber = false;
	}
	
	public Mailbox(){
		super();
		full = false;
		subscriber = false;
	}
	
	public boolean isFull(){
		
		return full;
	}
	
	public void setFull(){
		full = true;
		//todo texture aendern -> mailbox_full.png
	}
	
	public void subscribe(){
		subscriber = true;
	}
	
	public boolean isSubscriber(){
		return subscriber;
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
