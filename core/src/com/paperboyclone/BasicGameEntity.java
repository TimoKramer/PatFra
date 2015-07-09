package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The <code>BasicGameEntity</code>-class represents the elements of the game
 * that one can interact with. It implements <code>IBasicGameEntity</code>-interface.
 * All entities of the game extend <code>BasicGameEntity</code>.
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer
 */
public class BasicGameEntity implements IBasicGameEntity{

	//Position des Objekts in der Welt
	protected Vector2 position;
	//Darstellung des Objekts
	protected Sprite sprite;
	//Kollisions Rechteck
	protected Rectangle boundingBox;
	
	protected GameWorld gameworld;
	
	public BasicGameEntity(Vector2 position, Texture texture){
		this.position = new Vector2(position);
		this.sprite = new Sprite(texture);
		boundingBox = new Rectangle(0,0,sprite.getWidth(),sprite.getHeight());
		gameworld = null;
	}
	
	public BasicGameEntity(){
		position = new Vector2(0f,0f);
		sprite = new Sprite();
	}
	
	public void setGameWorld(GameWorld gameworld){
		this.gameworld = gameworld;
	}
	
	public void setTexture(Texture texture){
		sprite.setTexture(texture);		
	}
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public void setPosition(Vector2 Position){
		position = new Vector2(Position);
	}
	
	public Vector2 getPosition(){
		return new Vector2(position);		
	}
	
	public void drawSprite(Batch batch){
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void update(float delta) {
		
		
	}

	public void draw(SpriteBatch batch) {
		drawSprite(batch);
	}

	@Override
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public <T> void notColliding(IBasicGameEntity collidedObject, Class<T> Type) {
		// TODO Auto-generated method stub
	}

	/**
	 * Sets a bounding box for collision detection
	 * 
	 * @param newBoundingBox	the rectangle to use for collision detection
	 */
	public void setBoundingBox(Rectangle newBoundingBox){
		boundingBox = new Rectangle(newBoundingBox);
	}

	/**
	 * @return 	the bounding box that is used for collision detection
	 */
	public Rectangle getBoundingBox() {
		return new Rectangle(position.x + boundingBox.x,position.y + boundingBox.y, boundingBox.width, boundingBox.height);
	}

	/**
	 * a method to get the class of an object.
	 * 
	 * @param o		the object you want to know the class of
	 * @param clazz	the generic of the supposed class
	 * @return		class of object if successfully converted or null if not successful
	 */
	public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
		try {
			return clazz.cast(o);
		} catch(ClassCastException e) {
			return null;
		}
	}
	
}
