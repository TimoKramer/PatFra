package com.paperboyclone;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class LevelGenerator {
	
	private static final int MaxHouses = 150;
	private static final int HouseMinSpace = 150;
	private static final int HouseMaxSpace = 600;

	private static ObjectMap<ObstacleTypes, Array<Float>> ObstacleSpawnLocations;
	
	
	static {
		//Moegliche Positionen (nur X-Koordinate) an denen Hindernisse erstellt werden koennen
		//Geordnet nach Typ
		ObstacleSpawnLocations = new  ObjectMap<ObstacleTypes, Array<Float>>();
		
		ObstacleSpawnLocations.put(ObstacleTypes.OB_1, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.OB_1).addAll(new Float[]{
				613f,
				1316f
		});
		
		ObstacleSpawnLocations.put(ObstacleTypes.OB_2, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.OB_2).addAll(new Float[]{
				860f,
				970f,
				1086f,
				1161f
		});
		
		ObstacleSpawnLocations.put(ObstacleTypes.OB_3, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.OB_3).addAll(new Float[]{
				1224f,
				730f	
		});

		
	}
	
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
	
	
	public static BasicGameEntity createRandomObstacle(float positionY){
		
		
		ObstacleTypes randomType = ObstacleTypes.getRandomType();
		Vector2 position = new Vector2(ObstacleSpawnLocations.get(randomType).random(), positionY);
		
		switch (randomType){
			
			case OB_1:
				
				return new BasicGameEntity(position, Assets.getTexture("obstacleT_1.png"));
				
			case OB_2:
				
				return new BasicGameEntity(position,  Assets.getTexture("obstacleT_2.png"));

			case OB_3:
				return new BasicGameEntity(position,  Assets.getTexture("obstacleT_3.png"));

			default:
				return new BasicGameEntity();

		
		}
	
		
		
	}
}
