package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BasicGameEntity {

	//Position des Objekts in der Welt
	protected Vector2 position;
	//Darstellung des Objekts
	protected Sprite sprite;
	
	//Kollisions Rechteck, erstmal unwichtig
	protected Rectangle BoundingBox;
	
	
	public BasicGameEntity(Vector2 position, Texture texture){
		this.position = position;
		this.sprite = new Sprite(texture);
	}
	
	public BasicGameEntity(){
		position = new Vector2(0,0);
		sprite = new Sprite();
	}
	
	public void setTexture(Texture texture){
		sprite.setTexture(texture);		
	}
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public void setPosition(Vector2 Position){
		position = Position;
	}
	
	public Vector2 getPosition(){
		return position;		
	}
	
	public void drawSprite(Batch batch){
		sprite.setPosition(position.x, position.y);
		sprite.draw(batch);
	}
	
	
	
}
