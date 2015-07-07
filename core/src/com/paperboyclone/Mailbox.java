package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Martin Freudenberg
 *
 */
public class Mailbox extends BasicGameEntity{
	
	private boolean isHit;
	private boolean subscriber;
	private boolean isCollidingWithPlayer;
	private PlayerStatusListener playerStatusListener;
	private House house;
	
	public Mailbox(Vector2 position,Texture texture){
		super(position,texture);
		isHit = false;
		subscriber = false;
		setBoundingBox(new Rectangle(0,sprite.getHeight()-30,sprite.getWidth(),30));
		playerStatusListener = new PlayerStatusListener();
	}
	
	public Mailbox(Vector2 position,Texture texture, House house){
		super(position,texture);
		isHit = false;
		subscriber = false;
		setBoundingBox(new Rectangle(0,sprite.getHeight()-30,sprite.getWidth(),30));
		playerStatusListener = new PlayerStatusListener();
		this.house = house;
	}

	public Mailbox(){
		super();
		isHit = false;
		subscriber = false;
	}
	
	public boolean isHit(){
		if(this.house.isHit() || this.isHit){
			return true;
		} else {
			return false;
		}
	}
	
	public void setHit(){
		this.house.setHit();
		isHit = true;
		//todo texture aendern -> mailbox_full.png
		if(isSubscriber()){
			sprite = new Sprite(Assets.getTexture("mailbox_full_subscriber.png"));
		}
		else{
			sprite = new Sprite(Assets.getTexture("mailbox_full.png"));
		}
		if(position.x < 1000){
			sprite.flip(true, false);
		}
	}
		
	public void subscribe(){
		subscriber = true;
		setTexture(Assets.getTexture("mailbox_empty_subscriber.png"));
	}
	
	public boolean isSubscriber(){
		return subscriber;
	}
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!this.isCollidingWithPlayer) {
				playerStatusListener.crashWithHouse();
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
