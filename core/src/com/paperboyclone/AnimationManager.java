package com.paperboyclone;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * 
 * the <code>AnimationManager</code> is responsible for holding multiple <code>SpriteSheetAnimation</code>s
 * the <code>SpriteSheetAnimation</code>s are mapped to a unique name to identify each animation
 * holds a reference to the currently selected <code>SpriteSheetAnimation</code>
 * 
 * @author Martin Freudenberg
 * @version 1.0
 */
public class AnimationManager {
	
	private ObjectMap<String, SpriteSheetAnimation>animations;
	private SpriteSheetAnimation currentAnimation;
	
	public AnimationManager(){
		animations = new ObjectMap<String,SpriteSheetAnimation>();
		currentAnimation = null;
	}
	
	/**
	 * adds a new <code>SpriteSheetAnimation</code> to the manager
	 * checks if the animation is already added
	 * 
	 *@param UniqueName a string to identify the <code>SpriteSheetAnimation</code>
	 *@param a			the <code>SpriteSheetAnimation</code> to be added
	 *@return a flag whether the animation could successfully added or not
	 * 
	 */
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
	
	/**
	 * starts playing the current <code>SpriteSheetAnimation</code>
	 *
	 */
	public void play(){
		if(currentAnimation != null){
			currentAnimation.play();
		}
	}
	
	/**
	 * stops playing the current <code>SpriteSheetAnimation</code>
	 *
	 */
	public void stop(){
		if(currentAnimation != null){
			currentAnimation.stop();
		}
	}
	/**
	 * continue playing the current <code>SpriteSheetAnimation</code>
	 * 
	 */
	public void resume(){
		if(currentAnimation != null){
			currentAnimation.resume();
		}
	}
	
	/**
	 * sets the current <code>SpriteSheetAnimation</code> to frame 0
	 *
	 */
	public void reset(){
		if(currentAnimation != null){
			currentAnimation.reset();
		}
	}
	
	/**
	 * changes the current <code>SpriteSheetAnimation</code> to the <code>SpriteSheetAnimation</code> with the given identifier
	 *@param AnimationName name of the animation which should now be played
	 */
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
	
	/**
	 * plays the current <code>SpriteSheetAnimation</code> 
	 *@param delta time between frames
	 */
	public void updateCurrentAnimation(float delta){
		if(currentAnimation != null){
			currentAnimation.update(delta);
		}
	}
	
	/**
	 *Returns the current Texture region of the current <code>SpriteSheetAnimation</code> 
	 *@return a rectangle with the location of the current Frame on the Sprite Sheet
	 */
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
