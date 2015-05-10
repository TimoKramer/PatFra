package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	private Player player;
	private OrthographicCamera camera;
	private BackgroundManager background;
	
	private GameWorld gameworld;
	CollisionTask<Player, Obstacle> t;
	
	public GameScreen(PaperboyClone app) {
		super(app);
		
		font = new BitmapFont();
		//Groesse der Kamera noch unklar
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(1000, Gdx.graphics.getHeight(),0);
		camera.update();
		
		player = new Player(
				new Vector2(1000, Gdx.graphics.getHeight()),
				Assets.getTexture("redCircle.png")
				);
		
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
		gameworld = new GameWorld();
		for(House h : LevelGenerator.generateHouses()){
			gameworld.add(h);
		}
		gameworld.add(player);
		gameworld.add(new CollisionTask<Player, Obstacle>(Player.class, Obstacle.class));
		gameworld.add(new CollisionTask<Player, House>(Player.class, House.class));
		gameworld.add(new CollisionTask<Paper, Obstacle>(Paper.class, Obstacle.class));
		gameworld.add(new CollisionTask<Paper, House>(Paper.class, House.class));
		//todo: Mailbox CollisionChecks 
		gameworld.add(new ObstacleSpawnTask(1000,2500,camera));

	}
	
	public void render(float delta) {
	
	    update(delta);
	    
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		App.batch.begin();
		draw();
		font.draw(App.batch,player.getPosition().toString(), player.getPosition().x , player.getPosition().y);
		App.batch.end();
	}
	
	//alles was aktualisiert werden muss, input -> aenderung der werte, kollisions checks etc.
	private void update(float delta){
		
	    gameworld.update(delta);
	    //Kamera auf Spieler-Position setzen
	    camera.position.set(player.getPosition().x,player.getPosition().y+300,0);
		
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	}
	
	//alles was angezeigt werden muss
	private void draw(){
		
		background.draw(App.batch);
		gameworld.draw(App.batch, camera);
		
	}

			
	public void dispose(){
		
	}
}
