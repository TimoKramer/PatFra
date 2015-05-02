package com.paperboyclone;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LevelGenerator {
	
	private static final int MaxHouses = 150;
	private static final int HouseMinSpace = 150;
	private static final int HouseMaxSpace = 600;
	
	static public Array<House> generateHouses(){
		
		float y = 800;
		float x = 1500;
		Array<House> Houses = new Array<House>();
		//rechte Seite
		for(int i = 0 ; i<MaxHouses; i++){
			
			//todo: zufalls hausgrafik
			House h = new House(new Vector2(x, y), Assets.getTexture("house_1.png"), Assets.getTexture("mailbox_empty.png"));
			Houses.add(h);
			//todo: zufall subscriber
			
			y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
			y = 800;
			x = 50;
		//linke seite
		for(int i = 0 ; i<MaxHouses; i++){
				
				//todo: zufalls hausgrafik
				//todo: zufall subscriber
				House h = new House(new Vector2(x, y), Assets.getTexture("house_1.png"), Assets.getTexture("mailbox_empty.png"));
				h.flipRight();
				Houses.add(h);
				
				//todo: zufall subscriber
				y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
		return Houses;
	}
}
