package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Menu {
	
	private Array<MenuItem>MenuItems;
	private BasicScreen screen;
	private int currentSelection;
	private String headline;
	
	public Menu(String Headline ,BasicScreen OnScreen){
		
		MenuItems = new Array<MenuItem>();
		headline = Headline;
		screen = OnScreen;
		currentSelection = 0;
		
	}
	
	public void add(MenuItem mi){
		mi.setMenu(this);
		MenuItems.add(mi);
	}
	
	public void draw(SpriteBatch batch){
		
		BitmapFont f = Assets.getFont();
		f.setColor(Color.WHITE);
		float x = Gdx.graphics.getWidth() * 0.45f;
		float y = Gdx.graphics.getHeight() * 0.9f;
		f.draw(batch,headline,x,y);
		y-= 200;
		
		for(int i = 0; i<MenuItems.size; i++){
			
			if(i == currentSelection){
				f.setColor(Color.RED);
			}
			else{
				f.setColor(Color.WHITE);
			}
			
			f.draw(batch,MenuItems.get(i).getText(),x,y);
			y-=30;
		}
		
	}
	
	public void handleInput(int keycode){
		
		switch (keycode){
		case Keys.DOWN:
			if(currentSelection == MenuItems.size - 1){
				currentSelection = 0;
			}
			else{
				currentSelection += 1;
			}
			break;
		case Keys.UP:
			
			if(currentSelection == 0){
				currentSelection = MenuItems.size - 1;
			}
			else{
				currentSelection -= 1;
			}
			
			break;
		
		case Keys.ENTER:
			MenuItems.get(currentSelection).onSelect();
			break;
			
		default:
			break;
		}
	}
	
}
