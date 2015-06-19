package com.paperboyclone;

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
		menu.add(new MenuItem("Option"));
		menu.add(new MenuItem("Quit"));
		return menu;
	}
	
	static public Menu createDifficultySelectionMenu(final BasicScreen OnScreen, final PaperboyClone app){
			
			
			Menu menu = new Menu("Select difficulty:");
			
			MenuItem m = new MenuItem("EASY");
			m.setOffset(50);
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					app.setScreen(new LoadingScreen(app, "EASY"));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			
			m = new MenuItem("NORMAL");
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					app.setScreen(new LoadingScreen(app, "NORMAL"));
					OnScreen.dispose();
				}
			});
			menu.add(m);
			
			m = new MenuItem("HARD");
			m.setOnSelectAction(new IMenuItemAction(){
				public void doAction() {
					app.setScreen(new LoadingScreen(app, "HARD"));
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
		
}
