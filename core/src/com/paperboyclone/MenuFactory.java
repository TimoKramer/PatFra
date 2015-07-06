package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @author Martin Freudenberg
 *
 */
public class MenuFactory {
	
	
	static public Menu createStartMenu(final BasicScreen OnScreen, final PaperboyClone app){
		
		
		Menu menu = new Menu("Mainmenu");
		MenuItem m = new MenuItem("New Game");
		m.setOffset(50);
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new DifficultySelectionScreen(app));
				OnScreen.dispose();
			}
		});
		menu.add(m);
		
		m = new MenuItem("Option");
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new OptionScreen(app));
				OnScreen.dispose();
			}
		});
		
		menu.add(m);
		
		m = new MenuItem("Quit");
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				OnScreen.dispose();
				Gdx.app.exit();
			}
		});
		
		menu.add(m);
		return menu;
	}
	
	static public Menu createDifficultySelectionMenu(final BasicScreen OnScreen, final PaperboyClone app){
			
			
			Menu menu = new Menu("Select difficulty:");
			
			MenuItem m = new MenuItem("EASY");
			m.setOffset(50);
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					DifficultySettings.getInstance().setEasy();
					app.setScreen(new LoadingScreen(app));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			
			m = new MenuItem("NORMAL");
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					DifficultySettings.getInstance().setNormal();
					app.setScreen(new LoadingScreen(app));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			
			m = new MenuItem("HARD");
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					DifficultySettings.getInstance().setHard();
					app.setScreen(new LoadingScreen(app));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			
			m = new MenuItem("back");
			m.setOffset(20);
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					app.setScreen(new MainScreen(app));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			return menu;
		}
	
	static public Menu createHighscoreScreenMenu(final BasicScreen OnScreen, final PaperboyClone app){
		
		Menu menu = new Menu("Select:");
		MenuItem m = new MenuItem("New Game");
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new DifficultySelectionScreen(app));
				OnScreen.dispose();
			}
		});
		menu.add(m);
		
		m = new MenuItem("Go to Mainmenu");
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new MainScreen(app));
				OnScreen.dispose();
			}
		});
		menu.add(m);
		return menu;
		
	}
		
	static public Menu createOptionScreenMenu(final BasicScreen OnScreen, final PaperboyClone app){
		
		final Menu menu = new Menu("Option");
		DisplayMode[]displayModes = Gdx.graphics.getDisplayModes();
		Array<String> rvalues = new Array<String>();
		for(DisplayMode dm : displayModes){
			rvalues.add(dm.width+"x"+dm.height);
		}
		final SelectionMenuItem rm = new SelectionMenuItem("Resolution", rvalues);
		rm.setSelectionTo(new String((int)app.settings.resolution.x+"x"+(int)app.settings.resolution.y));
		menu.add(rm);
		
		Array<String> svalues = new Array<String>();
		for(int i = 0; i<=100; i++){
			svalues.add(new Integer(i).toString());
		}
		final SelectionMenuItem sm = new SelectionMenuItem("Sound Volume", svalues);
		sm.setSelectionTo(new Integer(app.settings.SoundVolume).toString());
		menu.add(sm);
		
		MenuItem mi = new MenuItem("Accept");
		mi.setOffset(50);
		mi.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				
				String[] tempres = rm.getSelectedValue().split("x");
				app.settings.resolution = new Vector2(new Float(tempres[0]), new Float(tempres[1]));
				app.settings.SoundVolume = new Integer(sm.getSelectedValue());
				GameSettingsHandler gs = GameSettingsHandler.getInstance();
				Gdx.graphics.setDisplayMode((int)app.settings.resolution.x,(int)app.settings.resolution.y, app.settings.fullscreen);
				gs.saveSettings(app.settings);
				menu.center();
				OnScreen.setDefaultCamera();
			}
		});
		menu.add(mi);
		
		mi = new MenuItem("Back");
		mi.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new MainScreen(app));
				OnScreen.dispose();
			}
		});
		menu.add(mi);
		return menu;
		
	}
		
}
