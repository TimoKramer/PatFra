package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * <code>Mailbox</code> represents the mailboxes in the game and provides event handling
 * methods, collision detection and methods to set as and check for subscriber.
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer
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
	
	/**
	 * Initiation of a <code>Mailbox</code>-object needs to be done with a house as parameter
	 * to provide the functionality to only hit one subscriber once.
	 * 
	 * @param position	the position in the <code>GameWorld</code>
	 * @param texture	the texture for a mailbox
	 * @param house		the house to which the mailbox belongs
	 */
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
	
	/**
	 * Sets the mailbox and the associated house as hit so both can not be hit more than once.
	 * Also the texture is changed.
	 */
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
	
	/**
	 * the method that is called on collision with another entity in the game. 
	 * 
	 * @param collidedObject	the object that collides with the mailbox
	 * @param Type				the type of the object that colliedes with the mailbox
	 */
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class){
			if(!this.isCollidingWithPlayer) {
				playerStatusListener.crashWithHouse();
			}
			this.isCollidingWithPlayer = true;
		}
	}

	/**
	 * the method that is called to as long there is no collision with another entity in the game.
	 * 
	 * @param collidedObject	the object that collides with the mailbox
	 * @param Type				the type of the object that colliedes with the mailbox
	 */
	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Player.class) {
			this.isCollidingWithPlayer = false;
		}
	}

}
