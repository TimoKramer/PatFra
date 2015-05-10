package com.paperboyclone;

import com.badlogic.gdx.utils.Array;
//kollisions check zwischen zwei Objekten
public class CollisionTask<T, K> implements ITask{

	
	private java.lang.Class<T>a;
	private java.lang.Class<K>b;
	
	public CollisionTask(java.lang.Class<T> typeA, java.lang.Class<K> typeB){
		a = typeA;
		b = typeB;
	}
	
	
	public void doTask(GameWorld gameworld) {
		
		Array<IBasicGameEntity> objectsA = gameworld.getAll(a);
		Array<IBasicGameEntity> objectsB = gameworld.getAll(b);
		
		for(IBasicGameEntity ob1 : objectsA){
			
			for(IBasicGameEntity ob2 : objectsB){
				
				if(ob1.getBoundingBox().overlaps(ob2.getBoundingBox())){
					ob1.onCollision(ob2,b);
					ob2.onCollision(ob1,a);
				}
				
			}
			
		}
	}
	
}