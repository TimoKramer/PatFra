package com.paperboyclone;

import com.badlogic.gdx.math.Vector2;

public class Paper extends BasicGameEntity {

	private PlayerStatsListener playerStatsListener;
	private boolean isThrownLeft;
	private Vector2 velocity;
	private boolean isCollidingWithMailbox;
	private boolean isCollidingWithHouse;
	
	private float angle;
	private float angleSpeed;
	
	public Paper(Vector2 position, Vector2 velocity, boolean isThrownLeft) {
		super(position, Assets.getTexture("paper.png"));
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
		
		this.angle = 0f;
		this.angleSpeed = -200.f;
	}
	
	public Paper(){
		super();
		this.isThrownLeft = true;
	}
	
	public void update(float delta){
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
		
		angle += angleSpeed * delta;
		if(Math.abs(angle) >= 360){
			angle = 0;
		}
		
		sprite.setRotation(angle);
	}
		
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		if(Type == Mailbox.class){
			Mailbox mailbox = (Mailbox) convertInstanceOfObject(collidedObject, Type);
			if(!this.isCollidingWithMailbox) {
				if(!mailbox.isFull()){
					
					if(mailbox.isSubscriber()) {	
						playerStatsListener.hitSubscriberMailbox();
						gameworld.add(new ScoreDisplayTask(new Vector2(mailbox.getPosition().x, mailbox.getPosition().y + mailbox.getSprite().getHeight() + 20),"+100"));
					}
					mailbox.setFull();
				}	 
			}
			isCollidingWithMailbox = true;
			gameworld.erase(this,Paper.class);
		}
		if(Type == House.class) {
			House house = (House) convertInstanceOfObject(collidedObject, Type);
			if(!this.isCollidingWithHouse) {
				if(house.isSubscriber()) {
					gameworld.add(new ScoreDisplayTask(new Vector2(house.getPosition().x, house.getPosition().y + house.getSprite().getHeight() + 20),"+10"));
					playerStatsListener.hitSubscriberHouse();
				}
			}
			isCollidingWithHouse = true;
		}
		//bei kollision paper anhalten
		velocity.x = 0;
		velocity.y = 0;
		angleSpeed = 0;
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
