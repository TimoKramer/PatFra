package com.paperboyclone;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * <code>ScoreDisplayTask</code> draws the score, position, scale and opacity on the 
 * screen. 
 * 
 * @author Martin Freudenberg
 *
 */
public class ScoreDisplayTask implements ITask{

	private String points;
	private float scale;
	private float opacity;
	private Vector2 position;
	private Color color;
	boolean isAlive;
	
	public ScoreDisplayTask(Vector2 position, String points){
		this.points = points;
		this.position = position;
		this.scale = 1f;
		this.opacity = 1;
		isAlive = true;
		color = new Color(Color.BLUE);
	}
	
	/**
	 * Animates the score popup by scaling an changing the color.
	 * ends the task as soon as the opacity is zero.
	 * 
	 */
	public void doTask(GameWorld gameworld, float deltaTime) {
		// TODO Auto-generated method stub
		position.y += 100 * deltaTime;
		scale += 1.5 * deltaTime;
		opacity -= 0.7 * deltaTime;
		//color.b += 0.8 * deltaTime;
		color.r += 2.6 * deltaTime;
		if(opacity <= 0){
			isAlive= false;
		}
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		Assets.getFont().getData().setScale(scale);
		Assets.getFont().setColor(new Color(0,0,0,opacity));
		Assets.getFont().draw(batch, points, position.x + 3, position.y - 3);
		Assets.getFont().setColor(new Color(color.r,0,1, opacity));
		Assets.getFont().draw(batch, points, position.x, position.y);
		Assets.getFont().getData().setScale(1f);
	}

	@Override
	public boolean isAlive() {
		
		return this.isAlive;
	}

}
