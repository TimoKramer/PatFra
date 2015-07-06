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
		
		ObstacleSpawnLocations.put(ObstacleTypes.OB_1, new Array<Float>());
		ObstacleSpawnLocations.get(ObstacleTypes.OB_1).addAll(new Float[]{
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
		
		float y = 800;
		float x = 1500;
		String houseSprite;
		
		ObjectMap<Class<?>, Array<IBasicGameEntity>> obj = new ObjectMap<Class<?>, Array<IBasicGameEntity>> ();
		obj.put(House.class, new Array<IBasicGameEntity>());
		obj.put(Mailbox.class, new Array<IBasicGameEntity>());
		
	
		//rechte Seite
		for(int i = 0 ; i<MaxHouses/2; i++){
			
			houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
			House h = new House(new Vector2(x, y), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
			obj.get(House.class).add(h);
			
			Mailbox m = new Mailbox(new Vector2(), Assets.getTexture("mailbox_empty.png"));
			m.setPosition(h.position.x - m.getSprite().getWidth() - 20 , h.position.y + h.getSprite().getHeight() - m.getSprite().getHeight());
			obj.get(Mailbox.class).add(m);
			y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
			y = 800;
			x = 50;
		//linke seite
		for(int i = 0 ; i<MaxHouses/2; i++){
				
			    houseSprite = "house_"+MathUtils.random(1,4)+".png"; 
				House h = new House(new Vector2(x, y), Assets.getTexture(houseSprite), Assets.getTexture("mailbox_empty.png"));
				h.flipRight();
				obj.get(House.class).add(h);
				
				Mailbox m = new Mailbox(new Vector2(), Assets.getTexture("mailbox_empty.png"));
				m.setPosition(h.position.x + h.getSprite().getWidth() + 20, h.getPosition().y + h.getSprite().getHeight() - m.getSprite().getHeight());
				m.getSprite().flip(true, false);
				obj.get(Mailbox.class).add(m);
			
				y+= h.getSprite().getHeight() + MathUtils.random(HouseMinSpace, HouseMaxSpace);
		}
		
		//Abonnenten setzen
		//Berechnen wie viele Abonneten gesetzt werden muessen
		int subscribers = MathUtils.ceil((MaxHouses * SubscriberRate));
		if(subscribers > MaxHouses){
			System.out.println("LevelGenerator: Error, more subscriber than houses set");
			return obj;
		}
		
		int subscriberCounter = subscribers;
		int atempts = 0;
		while(subscriberCounter != 0){
			
			//zufaelliges Haus auswaehlen
			House aHouse = (House) obj.get(House.class).random();
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
		for(int i = 0; i<obj.get(House.class).size; i++){
			if(((House) obj.get(House.class).get(i)).isSubscriber()){
				((Mailbox) obj.get(Mailbox.class).get(i)).subscribe();
			}
		}
		
		String s = "LevelGenerator: Created %d Houses.\n";
		System.out.printf(s,obj.get(House.class).size);
		s = "LevelGenerator: Created %d subscribers in %d atempts.\n";
		System.out.printf(s,subscribers, atempts);
		
		return obj;
	}
	
	
	public static Obstacle createRandomObstacle(float positionY){
		
		
		ObstacleTypes randomType = ObstacleTypes.getRandomType();
		Vector2 position = new Vector2(ObstacleSpawnLocations.get(randomType).random(), positionY);
		
		switch (randomType){
			
			case OB_1:
				
				return new Obstacle(position, Assets.getTexture("obstacleT_1.png"));
				
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
