package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AnimatedBackground {
	
	
		private Vector2 size;
		private Sprite backgroundPattern;
		private Array<Vector2>positions;
		private float xAmount;
		private float yAmount;
		private float speed;
		private Color tint;
	
		public AnimatedBackground(Texture backgroundPatternTexture){
			
			speed = 30f;
			size = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			backgroundPattern = new Sprite(backgroundPatternTexture);
			xAmount = Math.round(size.x/backgroundPattern.getWidth() + 2);
			yAmount = Math.round(size.y/backgroundPattern.getHeight() + 2);
			
			positions = new Array<Vector2>();
					
			float x = 0f;
			float y = 0f;
			for(int i = 0; i<(int) xAmount * yAmount; i++){
				
				
				if(x == xAmount * backgroundPattern.getWidth()){
					x = 0f;
					y += backgroundPattern.getHeight(); 
				}
				
				
				positions.add(new Vector2(x,y));
				x += backgroundPattern.getWidth();
				
			}
			
			tint = new Color(new Color(0f,0f,0f,1));
		}
		
		public void draw(SpriteBatch batch){
			for(Vector2 j : positions){
				backgroundPattern.setPosition(j.x, j.y);
				backgroundPattern.draw(batch);
			}
		}
		
		public void update(float delta, OrthographicCamera cam){
			
			float cx  = cam.position.x - cam.viewportWidth/2f;
			float cy  = cam.position.y + cam.viewportHeight/2f;
			
			float smallestY = Gdx.graphics.getHeight();
			float highestX = 0;
			for(Vector2 k : positions){
				if(k.y <smallestY){
					smallestY = k.y;
				}
				
				if(k.x > highestX){
					highestX  = k.x;
				}
			}
			
			for(Vector2 j : positions){
				
				j.x -= speed * delta;
				j.y += speed * delta;
				
				if(j.x + backgroundPattern.getWidth() < cx){
					j.x = highestX + backgroundPattern.getWidth() - 2;
					
				}
				
				if(j.y > cy){			
					j.y =  smallestY - backgroundPattern.getHeight() + 2;
				}
				
			}

			changeColor(delta);
			
		
		}
	
		
		private void changeColor(float delta){
			float cspeed = 200f;
			tint.g += (cspeed * delta)/255;
			if(tint.g >= 0.7) return;
			backgroundPattern.setColor(tint);
		}
}
