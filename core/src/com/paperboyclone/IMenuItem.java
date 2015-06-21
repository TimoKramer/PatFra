package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
