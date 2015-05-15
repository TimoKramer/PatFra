package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class House extends BasicGameEntity{
	
	public Mailbox mailbox;
	private boolean subscriber;

	
	public House(Vector2 position, Texture HouseTexture, Texture MailboxTexture){
		super(position,HouseTexture);
		mailbox = new Mailbox(new Vector2(), MailboxTexture);
		mailbox.setPosition(this.position.x - mailbox.getSprite().getWidth() - 20 , this.position.y + getSprite().getHeight() - mailbox.getSprite().getHeight());
		subscriber = false;
	}
	
	
	public void draw(SpriteBatch batch){

			drawSprite(batch);
			mailbox.drawSprite(batch);
	
	}
	
	public void subscribe(){
		subscriber = true;
		mailbox.subscribe();
		mailbox.setTexture(Assets.getTexture("mailbox_empty_subscriber.png"));
	}
	
	public boolean isSubscriber(){
		return subscriber;
	}
	
	
	public void flipRight(){
		
		sprite.flip(true,false);
		mailbox.getSprite().flip(true,false);
		mailbox.setPosition(this.position.x + getSprite().getWidth() + 20, mailbox.getPosition().y);
	
	}

}
