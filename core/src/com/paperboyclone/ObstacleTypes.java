package com.paperboyclone;

import com.badlogic.gdx.utils.Array;
//Typ Bezeichnung eines Hinderis
public enum ObstacleTypes {
	//Todo: passende Namen hinzufuegen
		OB_1, 
		OB_2,
		OB_3;
	
		public static ObstacleTypes getRandomType(){	
			Array<ObstacleTypes> d = new Array<ObstacleTypes>(ObstacleTypes.values());
			return d.random();

		}
}
