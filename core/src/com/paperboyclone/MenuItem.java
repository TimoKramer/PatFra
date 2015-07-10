package com.paperboyclone;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * <code>MenuItem</code> represents the menuitems in the different menus. It 
 * provides eventhandling- and drawing-methods.
 * 
 * @author Martin Freudenberg
 *
 */
public class MenuItem implements IMenuItem{
	
	private IMenuItemAction onSelectAction;
	private String Text;
	private Menu menu;
	private float offset;
	
	public MenuItem(String text){
		Text = text;
		onSelectAction = new IMenuItemAction(){
			public void doAction() {
				
			}	
		};
		setOffset(0);
	}
	
	public void setOnSelectAction(IMenuItemAction iaction){
		onSelectAction = iaction;
	}
	
	public void onSelect(){
		onSelectAction.doAction();
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}
	
	public void setMenu(Menu m){
		menu = m;
	}
	
	public void draw(float x, float y, SpriteBatch batch, BitmapFont font){
		Color c = new Color(font.getColor());
		font.setColor(Color.BLACK);
		font.draw(batch, Text, x+2, y-2);
		font.setColor(c);
		font.draw(batch, Text, x, y);
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}
}
