package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasicGameEntity {

	private Vector2 velocity;
	private boolean isThrown;
	private PlayerStatsListener playerStatsListener;
	private AnimationManager animations;
	private float minSpeed;
	private float maxSpeed;
	
	public Player(Vector2 position, Texture texture){
		super(position, texture);
		velocity = new Vector2(0f, 300f);
		playerStatsListener = new PlayerStatsListener();
		
		animations = new AnimationManager();
		animations.add("DRIVE_STRAIGHT", new SpriteSheetAnimation(new Rectangle(0,144,32,48),10f,4));
		animations.add("DRIVE_LEFT", new SpriteSheetAnimation(new Rectangle(0,48,32,48),10f,4));
		animations.add("DRIVE_RIGHT", new SpriteSheetAnimation(new Rectangle(0,94,32,48),10f,4));
		animations.changeTo("DRIVE_STRAIGHT");
		
		Rectangle r = animations.getAnimationRegion();
		setBoundingBox(new Rectangle(0,0,r.width, r.height));
		
		maxSpeed = 500;
		minSpeed = 100;

	}

	public Player(){
		super();
	}
	
	public void draw(SpriteBatch batch){
		Rectangle r = animations.getAnimationRegion();
		batch.draw(sprite.getTexture(), position.x, position.y, r.width, r.height, (int)r.x, (int)r.y, (int)r.width, (int)r.height, false, false);			
	}
	
	public void update(float delta){
		animations.updateCurrentAnimation(delta);
		checkForMovement(delta);
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
		
	}
	
	public void setMinSpeed(float mSpeed){
		minSpeed = mSpeed;
	}

	public void throwLeft() {
		if(playerStatsListener.isPaperAvailable()) {
			gameworld.add(new Paper(new Vector2(
				this.position.x,
				this.position.y + this.boundingBox.getHeight()/2),
				this.velocity,
				true));
			playerStatsListener.throwPaper();
		}
	}

	public void throwRight() {
		if(playerStatsListener.isPaperAvailable()) {	
			gameworld.add(new Paper(new Vector2(
					this.position.x + this.boundingBox.getWidth(),
					this.position.y + this.boundingBox.getHeight()/2),
					this.velocity,
					false));
			playerStatsListener.throwPaper();
		}
	}

	public void moveRight(){
		velocity.x = 200f;
	}

	public void moveLeft(){
		velocity.x = -200f;
	}

	public void moveStraight(){
		velocity.x = 0;
		// TODO: get back to normal speed 
		//velocity.y = 300;
	}

	public void moveFaster(float delta){
		if(velocity.y < maxSpeed) {
			velocity.y += 300f * delta;
		}
	}

	public void moveSlower(float delta) {
		if(velocity.y > minSpeed) {
			velocity.y -= 300f * delta;
		}
	}
	
	public void checkForMovement(float delta) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			animations.changeTo("DRIVE_RIGHT");
			moveRight();
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			animations.changeTo("DRIVE_LEFT");
			moveLeft();
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			//TODO: Animationsspeed den Player Speed anpassen
			moveSlower(delta);
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			moveFaster(delta);
		}
		else if(Gdx.input.isKeyPressed(Keys.Y)){
			if(!isThrown) throwLeft();
			isThrown = true;
		}
		else if(Gdx.input.isKeyPressed(Keys.X)){
			if(!isThrown) throwRight();
			isThrown = true;
		}
		else {
			animations.changeTo("DRIVE_STRAIGHT");
			moveStraight();
			isThrown = false;
		}
	}
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
			if(Type != PaperPile.class){
				setPosition(1000,position.y);
			}
			
	}

}
