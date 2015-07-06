package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Player represents the user's Character in the game. It extends 
 * <code>BasicGameEntity</game> and cares for the interaction with 
 * the user and other entities.
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer
 * @version 1.0
 */
public class Player extends BasicGameEntity {

	private Vector2 velocity;
	private boolean isThrown;
	private PlayerStatsListener playerStatsListener;
	private AnimationManager animations;
	private float minSpeed;
	private float maxSpeed;
	
	/**
	 * The initialization of a Player sets the position, a texture, basic velocity,
	 * a listener for the stats, animations and a BoundingBox for collisions.
	 * 
	 * @param position	the initial position
	 * @param texture	the texture to draw on screen
	 */
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
	
	@Override
	public void draw(SpriteBatch batch){
		Rectangle r = animations.getAnimationRegion();
		batch.draw(sprite.getTexture(), position.x, position.y, r.width, r.height, (int)r.x, (int)r.y, (int)r.width, (int)r.height, false, false);			
	}
	
	@Override
	public void update(float delta){
		animations.updateCurrentAnimation(delta);
		checkForMovement(delta);
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
		
	}
	
	public void setMinSpeed(float mSpeed){
		minSpeed = mSpeed;
	}

	/**
	 * Checks if a paper is available and if yes creates a <code>Paper</code>
	 * with velocity relative to <code>Player</code>-movement and position same
	 * as <code>Player</code> and adds it to the <code>Gameworld</code>.
	 */
	public void throwLeft() {
		if(playerStatsListener.isPaperAvailable()) {
			gameworld.add(new Paper(new Vector2(
				this.position.x,
				this.position.y + this.boundingBox.getHeight()/2),
				this.velocity,
				true));
			playerStatsListener.throwPaper();
			AudioPlayer.getInstance().playThrowLeftSound();
		}
	}

	/**
	 * Checks if a paper is available and if yes creates a <code>Paper</code>
	 * with velocity relative to <code>Player</code>-movement and position same
	 * as <code>Player</code> and adds it to the <code>Gameworld</code>.
	 */
	public void throwRight() {
		if(playerStatsListener.isPaperAvailable()) {	
			gameworld.add(new Paper(new Vector2(
					this.position.x + this.boundingBox.getWidth(),
					this.position.y + this.boundingBox.getHeight()/2),
					this.velocity,
					false));
			playerStatsListener.throwPaper();
			AudioPlayer.getInstance().playThrowRightSound();
		}
	}

	public void moveRight(){
		velocity.x = 200f;
	}

	public void moveLeft(){
		velocity.x = -200f;
	}

	public void moveStraight(float delta){
		velocity.x = 0;
		if(velocity.y > 310) {
			velocity.y -= delta * 200;
		}
		else if(velocity.y < 290) {
			velocity.y += delta * 200;
		}
		else {
			velocity.y = 300;
		}
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
	
	/**
	 * regularly called method to poll for user input on keyboard or if
	 * no input occurs movement is set back to straight and normal speed.
	 * 
	 * @param delta	is the refreshment rate and is used as factor for relative changes
	 */
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
			moveStraight(delta);
			isThrown = false;
		}
	}
	
	/**
	 * when a collision occurs, a sound is played and the player is respawned at 
	 * a center position on screen 
	 */
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		
		if(Type != PaperPile.class){
			setPosition(1000,position.y);
			AudioPlayer.getInstance().playCrashSound();
		}
			
	}

}
