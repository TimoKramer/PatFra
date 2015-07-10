package com.paperboyclone;


import com.badlogic.gdx.math.Rectangle;

/**
 * Animation of the player-character.
 * Holds a rectangle witch represents the location of the current frame on the sprite sheet.
 * Moves this rectangle based on the animation speed to the next frame of the animation.
 * @author Martin Freudenberg
 */
public class SpriteSheetAnimation {
	
	private Rectangle region;
	private float AnimationSpeed;
	private int frames;
	private float currentFrame; 
	private boolean playing;
	
	public SpriteSheetAnimation(Rectangle startRegion, float animationSpeed, int frames){
		region = new Rectangle(startRegion);
		this.AnimationSpeed = animationSpeed;
		this.frames = frames;
		currentFrame = 0;
		playing = false;
	}
	
	public void stop(){
		playing = false;
	}
	
	public void play(){
		playing = true;
		reset();
	}
	
	public void resume(){
		playing = true;
	}
	
	public void reset(){
		currentFrame = 0;
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void update(float delta){
		if(playing){
			currentFrame +=  AnimationSpeed * delta;
			if(currentFrame >= frames){
				currentFrame = 0;
			}
		}
	}
	
	
	public Rectangle getAnimationRegion(){
		
		return new Rectangle(((int)currentFrame) * region.width, region.y, region.width, region.height);
	}
}
