package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Menu {
	
	private Array<MenuItem>MenuItems;
	
	private int currentSelection;
	private String headline;
	private Vector2 position;
	
	private Color selectionColor;
	private Color basicColor;
	private Color headlineColor;
	
	public Menu(String Headline){
		
		MenuItems = new Array<MenuItem>();
		headline = Headline;
		currentSelection = 0;
		position = new Vector2(Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight() * 0.9f);
		
		selectionColor = Color.RED;
		basicColor = Color.WHITE;
		headlineColor = Color.BLUE;
	}
	
	public void add(MenuItem mi){
		mi.setMenu(this);
		MenuItems.add(mi);
	}
	
	public void draw(SpriteBatch batch){
		
		BitmapFont f = Assets.getFont();
		f.setColor(headlineColor);
		float x = position.x;
		float y = position.y;
		f.getData().setScale(1.f);
		f.draw(batch,headline,x,y);
		
		y-= 200;
		for(int i = 0; i<MenuItems.size; i++){
			
			if(i == currentSelection){
				f.setColor(selectionColor);
				f.getData().setScale(1.2f);
			}
			else{
				f.setColor(basicColor);
				f.getData().setScale(1.f);
			}
			
			y -= MenuItems.get(i).getOffset();
			MenuItems.get(i).draw(x, y , batch, f);
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
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
}
