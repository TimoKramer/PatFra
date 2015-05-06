package com.paperboyclone;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class LevelGenerator {
	
	private static final int MaxHouses = 300;
	private static final int HouseMinSpace = 150;
	private static final int HouseMaxSpace = 600;
	private static final float SubscriberRate = 0.66f;

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
		String houseSprite;
		
		Array<House> Houses = new Array<House>();
		//rechte Seite
		for(int i = 0 ; i<MaxHouses/2; i++){
			
			//todo: zufalls hausgrafik
			houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
			House h = new House(new Vector2(x, y), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
			Houses.add(h);
			y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
			y = 800;
			x = 50;
		//linke seite
		for(int i = 0 ; i<MaxHouses/2; i++){
				
				//todo: zufalls hausgrafik
			    houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
				House h = new House(new Vector2(x, y), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
				h.flipRight();
				Houses.add(h);
			
				y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
		//Abonnenten setzen
		//Berechnen wie viele Abonneten gesetzt werden muessen
		int subscribers = MathUtils.ceil((MaxHouses * SubscriberRate));
		if(subscribers > MaxHouses){
			System.out.println("LevelGenerator: Error, more subscriber than houses set");
			return Houses;
		}
		
		int subscriberCounter = subscribers;
		int atempts = 0;
		while(subscriberCounter != 0){
			
			//zufaelliges Haus auswaehlen
			House aHouse = Houses.random();
			//falls noch kein Abonnent -> zum Abonnenten machen
			if(!aHouse.isSubscriber()){
				aHouse.subscribe();
				subscriberCounter--;
			}
			
			atempts++;
			//falls setzen der Abonnenten zu lang dauert -> While-Schleife verlassen um Stillstand des Programms zu verhindern
			if(atempts > 100*subscribers){
				System.out.println("LevelGenerator: Subscriber set atempts reached!");
				break;
			}
		}
		
		String s = "LevelGenerator: Created %d Houses.\n";
		System.out.printf(s,Houses.size);
		s = "LevelGenerator: Created %d subscribers in %d atempts.\n";
		System.out.printf(s,subscribers, atempts);
		
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
