package com.paperboyclone;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class GameWorld {

	private ObjectMap<Class<?>, Array<IBasicGameEntity>> Objects;
	private Array<ITask>Tasks;
	private BitmapFont font;
	
	public GameWorld(){
		 Objects = new ObjectMap<Class<?>, Array<IBasicGameEntity>>();
		 Objects.put(Player.class, new Array<IBasicGameEntity>(1));
		 Objects.put(Paper.class, new Array<IBasicGameEntity>());
		 Objects.put(Obstacle.class, new Array<IBasicGameEntity>());
		 Objects.put(House.class, new Array<IBasicGameEntity>());
		 Objects.put(PaperPile.class, new Array<IBasicGameEntity>());
		 Objects.put(Mailbox.class, new Array<IBasicGameEntity>());
		 
		 Tasks = new Array<ITask>();
		 font = Assets.getFont();
	}
	

	
	public void update(float delta, OrthographicCamera camera){
		
		
		for(ITask t : Tasks){
			t.doTask(this, delta);
			if(!t.isAlive()){
				Tasks.removeValue(t, false);
			}
		}
		
		for(Array<IBasicGameEntity> a : Objects.values()){
			
			for(IBasicGameEntity i : a){
				i.update(delta);
			}
		}
		
		cleanUp(camera);
	}
	
	private void cleanUp(OrthographicCamera camera){
		//falls Objekt aus Bild raus (Auswertung ueber obere Kante des Objekts und untere Kante der Kamera) -> Objekt loeschen 
		float camY = camera.position.y - camera.viewportHeight/2;
		for(Array<IBasicGameEntity> a : Objects.values()){
			
			for(IBasicGameEntity i : a){
				float pY = i.getBoundingBox().y + i.getBoundingBox().height;
				if(pY < camY){
					erase(i,i.getClass());
				}
			}
		}
		
	}
	
	public void draw(SpriteBatch batch, OrthographicCamera camera){
		

		for(Array<IBasicGameEntity> a : Objects.values()){
				
				for(IBasicGameEntity i : a){
					i.draw(batch);
				}
		}
	
		for(ITask t : Tasks){
			t.draw(batch);
		}
		
	}
	
	public void drawWorldInfos(SpriteBatch batch, OrthographicCamera camera){
		
		float x = camera.position.x + (float) (camera.viewportWidth * 0.8f) /2;
		float y = camera.position.y + (float) (camera.viewportHeight * 0.95f) /2;
		font.setColor(Color.WHITE);
		font.getData().setScale(0.6f);
		font.draw(batch, "Houses: " +Objects.get(House.class).size, x, y);
		y-=20;
		font.draw(batch, "Obstacles: " +Objects.get(Obstacle.class).size, x, y);
		y-=20;
		font.draw(batch, "Papers: " +Objects.get(Paper.class).size, x, y);
		y-=20;
		font.draw(batch, "Paper Piles: " +Objects.get(PaperPile.class).size, x, y);
		y-=20;
		font.draw(batch, "Mailboxes: " +Objects.get(Mailbox.class).size, x, y);
		y-=20;
		font.draw(batch, "Tasks: " +Tasks.size, x, y);
		font.getData().setScale(1f);
	}
	
	public int getRemainingHouses() {
		return Objects.get(House.class).size;
	}
	
	private <T> void add(IBasicGameEntity e, java.lang.Class<T> type){
		e.setGameWorld(this);
		Objects.get(type).add(e);
	}
	
	public void add(House house){
		add(house,House.class);
	}
	
	public void add(Mailbox mailbox) {
		add(mailbox, Mailbox.class);
	}
	
	public void add(Obstacle obstacle){
		add(obstacle,Obstacle.class);
	}

	public void add(Player player){
		add(player,Player.class);	
	}
	
	public void add(Paper paper){
		add(paper,Paper.class);
	}
	
	public void add(PaperPile paperPile) {
		add(paperPile, PaperPile.class);
	}
	
	//getObjects(House.class) ...
	public <T> Array<IBasicGameEntity>getAll(java.lang.Class<T> type){
		return Objects.get(type);
				
	}
	
	public <T> void erase(IBasicGameEntity e, java.lang.Class<T> type){
		if(!Objects.get(type).removeValue(e, false)){
			System.out.println("GameWorld: removing failed \nCould not find the object");
		}
	}
	
	public void add(ITask task){
		Tasks.add(task);
	}
}
