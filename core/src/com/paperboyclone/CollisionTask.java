package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Task to check collisions between a pair of entities.
 * Implements <code>ITast</code>-interface
 * Define the pair of entities by giving the class type
 * 
 * @author Martin Freudenberg
 *	
 * @param <T>
 * @param <K>
 */
public class CollisionTask<T, K> implements ITask{
	
	private java.lang.Class<T>a;
	private java.lang.Class<K>b;
	
	public CollisionTask(java.lang.Class<T> typeA, java.lang.Class<K> typeB){
		
		a = typeA;
		b = typeB;
	}
	
	/**
	 *
	 * Loops trough every specified entity from the <code>GameWorld</code> and checks if they overlap
	 * based on the bounding box of the entity.
	 * 
	 * If they overlap the onCollision Method gets called with the object that the entity overlap with and the type of the object.
	 * If not than the notColliding Method gets called which is used to prevent multiple collision handling.
	 *
	 * @param gameworld the <code>GameWorld</code> where the Objects are stored
	 * @param deltaTime time between frames which isn't used in this <code>ITask</code> implementation
	 */
	public void doTask(GameWorld gameworld, float deltaTime) {

		Array<IBasicGameEntity> objectsA = gameworld.getAll(a);
		Array<IBasicGameEntity> objectsB = gameworld.getAll(b);
		
		for(IBasicGameEntity ob1 : objectsA){
			
			for(IBasicGameEntity ob2 : objectsB){

				if(ob1.getBoundingBox().overlaps(ob2.getBoundingBox())){
					ob1.onCollision(ob2, b);
					ob2.onCollision(ob1, a);
				} else {
					ob1.notColliding(ob2, b);
					ob2.notColliding(ob1, a);
				}
			}
		}
	}


	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}


	/**
	 *
	 *Returns always true because a collision task has to be alive the whole game.
	 *
	 */
	public boolean isAlive() {
		
		return true;
	}
	
}
