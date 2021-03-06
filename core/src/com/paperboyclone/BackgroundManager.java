package com.paperboyclone;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;



/**
 * Handles the background texture of the Game
 * Holds two references to the background texture as a <code>Sprite</code>
 * Makes the background textures loop infinitely based on the camera position 
 * 
 * @author Martin Freudenberg
 * 
 */
public class BackgroundManager {
	
	
	private Sprite background1;
	private Sprite background2;
	
	public BackgroundManager(Vector2 position, Texture backgroundTexture){
		
		background1 = new Sprite(backgroundTexture);
		background2 = new Sprite(backgroundTexture);
		
		background1.setPosition(position.x, position.y);
		background2.setPosition(position.x, position.y + background1.getHeight());
		
	}
	
	public void setTexture(Texture texture){
		background1.setTexture(texture);
		background2.setTexture(texture);
		
	}
	public void draw(SpriteBatch batch){
		background1.draw(batch);
		background2.draw(batch);
	}

	/**
	 *Updates the positions of the background textures based on the camera position.
	 * 
	 * If background1 is out of the screen it gets set to the top of background2
	 * and vice versa.
	 * 
	 * 
	 * @param camera used camera on the <code>GameScreen</code>
	 */
	public void update(OrthographicCamera camera){
		
		Vector3 camPosition = camera.position;
		float camHeight = camera.viewportHeight;
		
		//Falls Background2 unterhalb der oberen Kante des Kamera ausschnitts liegt -> Background1 ueber Background2 setzen
		if((background2.getY() + background2.getHeight() <= camPosition.y + camHeight/2) &&
			background1.getY() < background2.getY()
			){
			
			background1.setY(background2.getY()+background2.getHeight());
		}//das selbe nur umgekehrt,  Background2 ueber Background1 setzen
		else if((background1.getY() + background1.getHeight() <= camPosition.y + camHeight/2) &&
				background2.getY() < background1.getY()
				){
			
			background2.setY(background1.getY()+background1.getHeight());
		}
		
		
		
	}
}
