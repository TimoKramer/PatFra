package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Interface for all MenuItems 
 * used to handle all MenuItems the same way in the <code>Menu</code> Class
 * 
 * @see <code>MenuItem</code> 
 * @see <code>SelectionMenuItem</code>
 * @author Martin Freudenberg
 */

public interface IMenuItem {
	public void setOnSelectAction(IMenuItemAction iaction);
	public void onSelect();
	public void draw(float x, float y, SpriteBatch batch, BitmapFont font);
	public float getOffset();
	void setOffset(float offset);
	public void setText(String text);
	public String getText();
	public void setMenu(Menu menu);
	
}
