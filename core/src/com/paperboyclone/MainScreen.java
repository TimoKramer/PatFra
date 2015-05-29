package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


//spaeteres Hauptmenu
//todo: Maus unterstuetzung impl
//todo: background grafik etc.
public class MainScreen extends BasicScreen {

	BitmapFont font;
	String [] MenuItems;
	int selection;
	
	public MainScreen(PaperboyClone app){
		super(app);
		
		font = new BitmapFont();
		selection = 0;
		MenuItems = new String[3];
		MenuItems[0] = "Spiel starten";
		MenuItems[1] = "Option";
		MenuItems[2] = "Beenden";
		
	}
	//bei Tastendruck uebergang zum loading screen
	public boolean keyDown(int keycode) {
		
		 switch (keycode)
	      {
	        case Keys.UP:
	            selection--;
	            if(selection < 0){
	            	selection = MenuItems.length-1;	            
	            }
	            
	            break;
	        case Keys.DOWN:
	            selection++;
	            if(selection >= MenuItems.length){
	            	selection = 0;
	            }
	            break;
	            
	        case Keys.ENTER:
	        	String s = MenuItems[selection];
	        	
	        	if(s == MenuItems[0]){
	        		//starte Spiel
	        		System.out.println(s+" selected");
	        		App.setScreen(new LoadingScreen(App));
	        		dispose();
	        	}
	        	else if(s == MenuItems[1]){
	        		System.out.println(s+" selected");
	        		//option screen
	        	}
	        	else if(s == MenuItems[2]){
	        		System.out.println(s+" selected");
	        		//beenden
	        		
	        	}
	        	break;
	      }
		return false;
	}
	

	public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		//todo: Menu zentrieren
		int y =  (int) (Gdx.graphics.getHeight()*0.65);
		for(int i = 0; i<MenuItems.length; i++){
			
			if(i == selection){
				font.setColor(Color.RED);
			}
			else{
				font.setColor(Color.WHITE);
			}
			font.draw(App.batch,MenuItems[i],(float) (Gdx.graphics.getWidth()*0.45) ,y);
			y-= 30;
		}
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}

}
