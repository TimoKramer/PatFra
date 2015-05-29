package com.paperboyclone;

import com.badlogic.gdx.math.Vector2;

public class Paper extends BasicGameEntity {

	private PlayerStatsListener playerStatsListener;
	private boolean isThrownLeft;
	private Vector2 velocity;
	private boolean isCollidingWithMailbox;
	private boolean isCollidingWithHouse;
	
	public Paper(Vector2 position, Vector2 velocity, boolean isThrownLeft) {
		super(position, Assets.getTexture("yellowCircle.png"));
		this.isThrownLeft = isThrownLeft;
		//this.velocity = velocity;
		
		if(this.isThrownLeft){
			this.velocity = new Vector2(-500f, velocity.y + 200); 
		}
		else{
			this.velocity = new Vector2(500f, velocity.y + 200);
		}
		playerStatsListener = new PlayerStatsListener();
		
		this.isCollidingWithHouse = false;
	}
	
	public Paper(){
		super();
		this.isThrownLeft = true;
	}
	
	public void update(float delta){
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
	}
		
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Mailbox.class){
			Mailbox mailbox = (Mailbox) convertInstanceOfObject(collidedObject, Type);
			if(!this.isCollidingWithMailbox) {
				if(mailbox.isSubscriber()) {
					playerStatsListener.hitSubscriberMailbox();		
				} 
			}
			isCollidingWithMailbox = true;
			gameworld.erase(this,Paper.class);
		}
		if(Type == House.class) {
			House house = (House) convertInstanceOfObject(collidedObject, Type);
			if(!this.isCollidingWithHouse) {
				if(house.isSubscriber()) {
					playerStatsListener.hitSubscriberHouse();
				}
			}
			isCollidingWithHouse = true;
		}
		//bei kollision paper anhalten
		velocity.x = 0;
		velocity.y = 0;
		
	}

	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Mailbox.class) {
			this.isCollidingWithMailbox = false;
		}
		if(Type == House.class) {
			//this.isCollidingWithHouse = false;
		}
	}

}
