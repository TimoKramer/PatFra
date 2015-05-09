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
	private Array<House> Houses;
	
	private Array<BasicGameEntity>Obstacles;
	private long lastSpawnTime;
	private long currentSpawnDelay;
	
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
		
		Houses = new Array<House>(LevelGenerator.generateHouses());
		
		Obstacles = new Array<BasicGameEntity>();
		lastSpawnTime = TimeUtils.millis();
		currentSpawnDelay = (1 + MathUtils.random(0,1))* 1000;
		
	
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
		
		player.checkForMovement(delta);
		checkForNextObstacleSpawn();
	 
	    player.update(delta);
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
		player.drawSprite(App.batch);
		
		for(House h : Houses){
			//todo: nur rendern was auf dem screen zu sehen ist
			h.draw(App.batch);
		}
		
		for(BasicGameEntity e : Obstacles){
			e.drawSprite(App.batch);
		}
	}

	
	private void checkForNextObstacleSpawn(){
		
		long currentTime = TimeUtils.millis();
		if(lastSpawnTime + currentSpawnDelay <= currentTime){
			
			BasicGameEntity e = LevelGenerator.createRandomObstacle(camera.position.y + camera.viewportHeight/2);
			Obstacles.add(e);
			lastSpawnTime = currentTime;
			currentSpawnDelay = (1 + MathUtils.random(0,2))* 1000;
		}
		//todo: obstacles ausserhalb des screen loeschen
	}
	
			
	public void dispose(){
		
	}
}
