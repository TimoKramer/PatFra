package com.paperboyclone;

public class MenuFactory {
	
	
	static public Menu createStartMenu(final BasicScreen OnScreen, final PaperboyClone app){
		
		
		Menu menu = new Menu("Startmenu");
		MenuItem m = new MenuItem("Spiel starten");
		m.setOnSelectAction(new IMenuItemAction(){
			public void doAction() {
				app.setScreen(new DifficultySelectionScreen(app));
				OnScreen.dispose();
			}
		});
		menu.add(m);
		menu.add(new MenuItem("Option"));
		menu.add(new MenuItem("Beenden"));
		return menu;
	}
	
	static public Menu createDifficultySelectionMenu(final BasicScreen OnScreen, final PaperboyClone app){
			
			
			Menu menu = new Menu("Schwiergkeitsgrad waehlen:");
			
			MenuItem m = new MenuItem("EASY");
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
		
}
