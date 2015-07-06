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
	
	private Array<IMenuItem>MenuItems;
	
	private int currentSelection;
	private String headline;
	private Vector2 position;
	
	private Color selectionColor;
	private Color basicColor;
	private Color headlineColor;
	
	private BitmapFont font;
	
	public Menu(String Headline){
		
		MenuItems = new Array<IMenuItem>();
		headline = Headline;
		currentSelection = 0;
		font = Assets.getFont();
		
		center();
		selectionColor = Color.RED;
		basicColor = Color.WHITE;
		headlineColor = Color.BLUE;
	}
	
	public void center(){
		GlyphLayout layout = new GlyphLayout(font, headline);
		float width = layout.width;
		position = new Vector2(Gdx.graphics.getWidth()/2 - width/2, Gdx.graphics.getHeight() * 0.9f);
		
	}
	
	public void add(IMenuItem m){
		m.setMenu(this);
		MenuItems.add(m);
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
			AudioPlayer.getInstance().playSound(Assets.getSound("pop.ogg"), 0.2f, 1, 0);
			if(currentSelection == MenuItems.size - 1){
				currentSelection = 0;
			}
			else{
				currentSelection += 1;
			}
			break;
		case Keys.UP:
			AudioPlayer.getInstance().playSound(Assets.getSound("pop.ogg"), 0.2f, 1, 0);
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
			
		case Keys.LEFT:
			IMenuItem m = MenuItems.get(currentSelection);
			if(m.getClass() == SelectionMenuItem.class){
				SelectionMenuItem smi = (SelectionMenuItem) m;
				smi.onSelectionChange(false);
			}
			break;
			
		case Keys.RIGHT:
			IMenuItem m1 = MenuItems.get(currentSelection);
			
			if(m1.getClass() == SelectionMenuItem.class){
				SelectionMenuItem smi = (SelectionMenuItem) m1;
				smi.onSelectionChange(true);
			}
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
