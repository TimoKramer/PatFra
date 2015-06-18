package com.paperboyclone;

public class MenuItem {
	
	private IMenuItemAction onSelectAction;
	private String Text;
	private Menu menu;
	
	public MenuItem(String text){
		Text = text;
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
}
