package com.paperboyclone;



import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class GameWorld {

	private ObjectMap<Class<?>, Array<IBasicGameEntity>> Objects;
	private Array<ITask>Tasks;
	
	public GameWorld(){
		 Objects = new ObjectMap<Class<?>, Array<IBasicGameEntity>>();
		 Objects.put(Player.class, new Array<IBasicGameEntity>(1));
		 Objects.put(Paper.class, new Array<IBasicGameEntity>());
		 Objects.put(Obstacle.class, new Array<IBasicGameEntity>());
		 Objects.put(House.class, new Array<IBasicGameEntity>());
		 Objects.put(PaperPile.class, new Array<IBasicGameEntity>());
		 
		 Tasks = new Array<ITask>();
		
	}
	

	
	public void update(float delta){
		
		
		for(ITask t : Tasks){
			t.doTask(this);
		}
		
		for(Array<IBasicGameEntity> a : Objects.values()){
			
			for(IBasicGameEntity i : a){
				i.update(delta);
			}
		}
		
		
	}
	
	public void draw(SpriteBatch batch, OrthographicCamera camera){
		

		for(Array<IBasicGameEntity> a : Objects.values()){
				
				for(IBasicGameEntity i : a){
					i.draw(batch);
				}
		}
	
		
	}
	
	private <T> void add(IBasicGameEntity e, java.lang.Class<T> type){
		e.setGameWorld(this);
		Objects.get(type).add(e);
	}
	
	public void add(House house){
		add(house,House.class);
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
	
	public void add(ITask task){
		Tasks.add(task);
	}
}
