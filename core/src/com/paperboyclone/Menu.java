package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
	
	private BitmapFont font;
	
	public Menu(String Headline){
		
		MenuItems = new Array<MenuItem>();
		headline = Headline;
		currentSelection = 0;
		font = Assets.getFont();
		GlyphLayout layout = new GlyphLayout(font, headline);
		float width = layout.width;
		position = new Vector2(Gdx.graphics.getWidth()/2 - width/2, Gdx.graphics.getHeight() * 0.9f);
		
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
		font.setColor(headlineColor);
		float x = position.x;
		float y = position.y;
		font.getData().setScale(1.f);
		font.draw(batch,headline,x,y);
		
		y-= 75;
		for(int i = 0; i<MenuItems.size; i++){
			
			if(i == currentSelection){
				font.setColor(selectionColor);
				font.getData().setScale(1.2f);
			}
			else{
				font.setColor(basicColor);
				font.getData().setScale(1.f);
			}
			
			y -= MenuItems.get(i).getOffset();
			MenuItems.get(i).draw(x, y , batch, f);
			y-=30;
		}
		
		font.setColor(Color.WHITE);
		font.getData().setScale(1.f);
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
	
	public Vector2 getPosition(){
		return position;
	}
}
