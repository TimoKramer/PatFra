package com.paperboyclone;

import com.badlogic.gdx.utils.Array;

/**
 * <code>ObstacleTypes</code> is an enum that specifies the names of the different <code>Obstacles</code> 
 * 
 * @author Martin Freudenberg
 */
public enum ObstacleTypes {
	//Todo: passende Namen hinzufuegen
		OB_1, 
		OB_2,
		PARKED_CAR;
	
		public static ObstacleTypes getRandomType(){	
			Array<ObstacleTypes> d = new Array<ObstacleTypes>(ObstacleTypes.values());
			return d.random();

		}
}
