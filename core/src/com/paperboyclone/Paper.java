package com.paperboyclone;

import com.badlogic.gdx.math.Vector2;

public class Paper extends BasicGameEntity {

	private PlayerStatsListener playerStatsListener;
	private boolean isThrownLeft;
	private Vector2 velocity;
	
	public Paper(Vector2 position, boolean isThrownLeft) {
		super(position, Assets.getTexture("yellowCircle.png"));
		this.isThrownLeft = isThrownLeft;
		this.velocity = new Vector2(0f,0f);
		
		if(this.isThrownLeft){
			this.velocity = new Vector2(-500f, 500f); 
		}
		else{
			this.velocity = new Vector2(500f, 500f);
		}
		playerStatsListener = new PlayerStatsListener();
	}
	
	public void update(float delta){
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
	}
	
	public Paper(){
		super();
		this.isThrownLeft = true;
	}
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		
		if(Type == Mailbox.class){
			playerStatsListener.hitSubscriberMailbox();
			
		}
		if(Type == House.class) {
			playerStatsListener.hitSubscriberHouse();
		}
	
		
	}

	
}
