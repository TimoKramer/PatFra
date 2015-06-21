package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class SelectionMenuItem extends MenuItem{

	
	private Array<String> values;
	private int currentlySelectedValue;
	private IMenuItemAction onChangeToNextValue;
	private IMenuItemAction onChangeToPreviousValue;
	
	public SelectionMenuItem(String text, Array<String>Values) {
		super(text);
		values =  new Array<String>();
		values = Values;
		currentlySelectedValue = 0;
		onChangeToNextValue = new IMenuItemAction(){

			public void doAction() {
				next();
			}			
		};
		
		onChangeToPreviousValue = new IMenuItemAction(){

			public void doAction() {
				previous();
			}			
		};
		
		if(values.size == 0){
			values.add("0: No values Set!");
			values.add("1: No values Set!");
		}
		
		
	}
	
	public void draw(float x, float y, SpriteBatch batch, BitmapFont font){
		font.draw(batch, this.getText()+": <"+values.get(currentlySelectedValue)+">", x, y);
	}
	
	private void next(){
		if(currentlySelectedValue == values.size -1){
			currentlySelectedValue = 0;
		}
		else{
			currentlySelectedValue +=1;
		}
	}
	
	private void previous(){
		if(currentlySelectedValue == 0){
			currentlySelectedValue = values.size -1;
		}
		else{
			currentlySelectedValue -=1;
		}
	}
	
	public String getSelectedValue(){
		return new String(values.get(currentlySelectedValue));
	}
	
	public void onSelectionChange(boolean toNext){
		if(toNext){
			onChangeToNextValue.doAction();
		}
		else{
			onChangeToPreviousValue.doAction();
		}
	}
	
	public void setSelectionTo(String s){

		for(int i = 0; i<values.size; i++){
			
			if(s.compareTo(values.get(i)) == 0){
				currentlySelectedValue = i;
				break;
			}
			
		}
		
	}
}
