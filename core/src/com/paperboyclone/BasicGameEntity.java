package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BasicGameEntity implements IBasicGameEntity{

	//Position des Objekts in der Welt
	protected Vector2 position;
	//Darstellung des Objekts
	protected Sprite sprite;
	
	//Kollisions Rechteck, erstmal unwichtig
	protected Rectangle BoundingBox;
	
	protected GameWorld gameworld;
	
	public BasicGameEntity(Vector2 position, Texture texture){
		this.position = new Vector2(position);
		this.sprite = new Sprite(texture);
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


	
	
}
