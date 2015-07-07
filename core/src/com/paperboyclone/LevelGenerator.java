package com.paperboyclone;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


/**
 * @author Martin Freudenberg
 *
 */
public class LevelGenerator {
	
	private static final int MaxHouses = 50;
	private static final int HouseMinSpace = 150;
	private static final int HouseMaxSpace = 600;
	private static final float SubscriberRate = 0.66f;

	private static ObjectMap<ObstacleTypes, Array<Float>> ObstacleSpawnLocations;
	private static Array<Float> PaperPileSpawnLocations = new Array<Float>();
	
	
	static {
		//Moegliche Positionen (nur X-Koordinate) an denen Hindernisse erstellt werden koennen
		//Geordnet nach Typ
		ObstacleSpawnLocations = new  ObjectMap<ObstacleTypes, Array<Float>>();
		
		ObstacleSpawnLocations.put(ObstacleTypes.BENCH, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.BENCH).addAll(new Float[]{
				613f,
				1316f
		});
		
		ObstacleSpawnLocations.put(ObstacleTypes.ROADWORKS, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.ROADWORKS).addAll(new Float[]{
				860f,
				1086f,
				1161f
		});
		
		ObstacleSpawnLocations.put(ObstacleTypes.PARKED_CAR, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.PARKED_CAR).addAll(new Float[]{
				1224f,
				730f	
		});
		//Moegliche Positionen an denen Zeitungsstapel erstellt werden koennen
		PaperPileSpawnLocations.addAll(new Float[] {
				615f,
				1350f
		});
		
	}
	
	static public ObjectMap<Class<?>, Array<IBasicGameEntity>> generateHousesAndMailboxes(){
		
		float nextHouseY = 800;
		float x = 1500;
		String houseSprite;
		
		ObjectMap<Class<?>, Array<IBasicGameEntity>> objectMapper = new ObjectMap<Class<?>, Array<IBasicGameEntity>> ();
		objectMapper.put(House.class, new Array<IBasicGameEntity>());
		objectMapper.put(Mailbox.class, new Array<IBasicGameEntity>());
		
	
		//rechte Seite
		for(int i = 0 ; i<MaxHouses/2; i++){
			
			houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
			House house = new House(new Vector2(x, nextHouseY), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
			objectMapper.get(House.class).add(house);
			
			Mailbox mailbox = new Mailbox(new Vector2(), Assets.getTexture("mailbox_empty.png"), house);
			mailbox.setPosition(house.position.x - mailbox.getSprite().getWidth() - 20 , house.position.y + house.getSprite().getHeight() - mailbox.getSprite().getHeight());
			objectMapper.get(Mailbox.class).add(mailbox);
			
			nextHouseY += house.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
			nextHouseY = 800;
			x = 50;
		//linke seite
		for(int i = 0 ; i<MaxHouses/2; i++){
				
		    houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
			House house = new House(new Vector2(x, nextHouseY), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
			house.flipRight();
			objectMapper.get(House.class).add(house);
			
			Mailbox mailbox = new Mailbox(new Vector2(), Assets.getTexture("mailbox_empty.png"), house);
			mailbox.setPosition(house.position.x + house.getSprite().getWidth() + 20, house.getPosition().y + house.getSprite().getHeight() - mailbox.getSprite().getHeight());
			mailbox.getSprite().flip(true, false);
			objectMapper.get(Mailbox.class).add(mailbox);
		
			nextHouseY += house.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
		//Abonnenten setzen
		//Berechnen wie viele Abonneten gesetzt werden muessen
		int subscribers = MathUtils.ceil((MaxHouses * SubscriberRate));
		if(subscribers > MaxHouses){
			System.out.println("LevelGenerator: Error, more subscriber than houses set");
			return objectMapper;
		}
		
		int subscriberCounter = subscribers;
		int atempts = 0;
		while(subscriberCounter != 0){
			
			//zufaelliges Haus auswaehlen
			House aHouse = (House) objectMapper.get(House.class).random();
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
		
		//Mailboxes mit House Subscriber synchronisieren
		for(int i = 0; i<objectMapper.get(House.class).size; i++){
			if(((House) objectMapper.get(House.class).get(i)).isSubscriber()){
				((Mailbox) objectMapper.get(Mailbox.class).get(i)).subscribe();
			}
		}
		
		String s = "LevelGenerator: Created %d Houses.\n";
		System.out.printf(s,objectMapper.get(House.class).size);
		s = "LevelGenerator: Created %d subscribers in %d atempts.\n";
		System.out.printf(s,subscribers, atempts);
		
		return objectMapper;
	}
	
	
	public static Obstacle createRandomObstacle(float positionY){
		
		
		ObstacleTypes randomType = ObstacleTypes.getRandomType();
		Vector2 position = new Vector2(ObstacleSpawnLocations.get(randomType).random(), positionY);
		
		switch (randomType){
			
			case BENCH:
				
				return new Obstacle(position, Assets.getTexture("bench.png"));
				
			case ROADWORKS:
				
				return new Obstacle(position,  Assets.getTexture("roadworks.png"));

			case PARKED_CAR:
				return new Obstacle(position,  Assets.getTexture("parked_car"+MathUtils.random(1,4)+".png"));

			default:
				return new Obstacle();

		
		}	
	}
	
	public static PaperPile createRandomPaperPile(float positionY) {
		
		Vector2 position = new Vector2(PaperPileSpawnLocations.random(), positionY);
		System.out.println("Creating PaperPile at " + position.toString());
		return new PaperPile(position);
	}
	
}
