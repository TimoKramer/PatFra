package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Mailbox extends BasicGameEntity{
	
	private boolean full;
	
	public Mailbox(Vector2 position,Texture texture){
		super(position,texture);
		full = false;
		
	}
	public Mailbox(){
		super();
		full = false;
		
	}
	
	public boolean isFull(){
		
		return full;
	}
	
	public void setFull(){
		full = true;
		//todo texture aendern -> mailbox_full.png
	}
	
}
