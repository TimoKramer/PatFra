package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**

 *Interface for all entities.
 *Used to handle all entities the same way in the <code>GameWorld</code> class
 *
 * @see BasicGameEntity
 * @author Martin Freudenberg
*
 */
public interface IBasicGameEntity {
		
	
	public void update(float delta);
	public void draw(SpriteBatch batch);
	public Rectangle getBoundingBox();
	public void setGameWorld(GameWorld gameworld);
	public <T> void onCollision(IBasicGameEntity collidedObject, java.lang.Class<T> Type);
	public <T> void notColliding(IBasicGameEntity collidedObject, java.lang.Class<T> Type);
	
}
