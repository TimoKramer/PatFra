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
	
	private long lastSpawnTime;
	private long currentSpawnDelay;
	
	private GameWorld gameworld;
	
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

		lastSpawnTime = TimeUtils.millis();
		currentSpawnDelay = (1 + MathUtils.random(0,1))* 1000;
		
		gameworld = new GameWorld();
		for(House h : LevelGenerator.generateHouses()){
			gameworld.add(h);
		}
		gameworld.add(player);
		
				
	
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
		
		
		checkForNextObstacleSpawn();
	 
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

	
	private void checkForNextObstacleSpawn(){
		
		long currentTime = TimeUtils.millis();
		if(lastSpawnTime + currentSpawnDelay <= currentTime){
			
			Obstacle e = LevelGenerator.createRandomObstacle(camera.position.y + camera.viewportHeight/2);
			gameworld.add(e);
			lastSpawnTime = currentTime;
			currentSpawnDelay = (1 + MathUtils.random(0,2))* 1000;
		}
		//todo: obstacles ausserhalb des screen loeschen
	}
	

			
	public void dispose(){
		
	}
}
