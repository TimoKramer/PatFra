package com.paperboyclone;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationManager {
	
	private ObjectMap<String, SpriteSheetAnimation>animations;
	private SpriteSheetAnimation currentAnimation;
	
	public AnimationManager(){
		animations = new ObjectMap<String,SpriteSheetAnimation>();
		currentAnimation = null;
	}
	
	
	public boolean add(String UniqueName, SpriteSheetAnimation a){
		
		if(animations.containsKey(UniqueName)){
			System.out.println("Animation Manager: animation \""+UniqueName+"\" already exists!" );
			return false;
		}
		else{
			animations.put(UniqueName, a);
			return true;
		}
		
		
		
	}
	
	public void play(){
		if(currentAnimation != null){
			currentAnimation.play();
		}
	}
	
	public void stop(){
		if(currentAnimation != null){
			currentAnimation.stop();
		}
	}
	
	public void resume(){
		if(currentAnimation != null){
			currentAnimation.resume();
		}
	}
	
	public void reset(){
		if(currentAnimation != null){
			currentAnimation.reset();
		}
	}
	
	
	public void changeTo(String AnimationName){
		
		if(animations.containsKey(AnimationName)){
			if(currentAnimation != null){
				currentAnimation.stop();
			}
			currentAnimation = animations.get(AnimationName);
			currentAnimation.resume();
		}
		else{
			System.out.println("Animation Manager: animation \""+AnimationName+"\" not found!" );
		}
	}
	
	public void updateCurrentAnimation(float delta){
		if(currentAnimation != null){
			currentAnimation.update(delta);
		}
	}
	
	public Rectangle getAnimationRegion(){
		if(currentAnimation != null){
			return currentAnimation.getAnimationRegion();
		}
		else{
			System.out.println("Animation Manager: no animation set!" );
			return new Rectangle();
		}
	}
	
}
