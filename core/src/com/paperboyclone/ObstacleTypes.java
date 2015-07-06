package com.paperboyclone;

import com.badlogic.gdx.utils.Array;
//Typ Bezeichnung eines Hinderis
public enum ObstacleTypes {
	//Todo: passende Namen hinzufuegen
		OB_1, 
		ROADWORKS,
		PARKED_CAR;
	
		public static ObstacleTypes getRandomType(){	
			Array<ObstacleTypes> d = new Array<ObstacleTypes>(ObstacleTypes.values());
			return d.random();

		}
}
