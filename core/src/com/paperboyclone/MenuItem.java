package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuItem {
	
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
		font.draw(batch, Text, x, y);
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}
}
