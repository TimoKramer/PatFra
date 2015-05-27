package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	PlayerStats stats;
	private Player player;
	private OrthographicCamera camera;
	private BackgroundManager background;
	private MusicPlayer musicPlayer;
	private long startTime;
	private GameWorld gameworld;
	CollisionTask<Player, Obstacle> t;
	
	
	public GameScreen(PaperboyClone app) {
		super(app);
		
		font = new BitmapFont();
		stats = PlayerStats.getInstance();
		//Groesse der Kamera noch unklar
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(1000, Gdx.graphics.getHeight(),0);
		camera.update();
		
		player = new Player(
				new Vector2(1000, Gdx.graphics.getHeight()),
				Assets.getTexture("Test_Character.png")
				);
		
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		musicPlayer = new MusicPlayer();
		gameworld = new GameWorld();
		
		ObjectMap<Class<?>, Array<IBasicGameEntity>> HandM = LevelGenerator.generateHousesAndMailboxes();
		for(IBasicGameEntity h: HandM.get(House.class)){
			gameworld.add((House)h);
		}
		for(IBasicGameEntity m: HandM.get(Mailbox.class)){
			gameworld.add((Mailbox)m);
		}
	
		gameworld.add(player);
		gameworld.add(new CollisionTask<Player, Obstacle>(Player.class, Obstacle.class));
		gameworld.add(new CollisionTask<Player, House>(Player.class, House.class));
		gameworld.add(new CollisionTask<Player, PaperPile>(Player.class, PaperPile.class));
		gameworld.add(new CollisionTask<Paper, Obstacle>(Paper.class, Obstacle.class));
		gameworld.add(new CollisionTask<Paper, House>(Paper.class, House.class));
		gameworld.add(new CollisionTask<Paper, Mailbox>(Paper.class, Mailbox.class));
		gameworld.add(new ObstacleSpawnTask(1000,2500,camera));
		gameworld.add(new PaperPileSpawnTask(10000, 20000, camera));
		
		startTime = System.currentTimeMillis();
		musicPlayer.run();
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
		
	    gameworld.update(delta, camera);
	    //stats.update();
	    //Kamera auf Spieler-Position setzen
	    camera.position.set(player.getPosition().x,player.getPosition().y+300,0);
		
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	    // Hier wird die Dauer des Spiels in Sekunden festgelegt
	    if((System.currentTimeMillis() - startTime) / 1000 >= 2) {
	    	App.setScreen(new HighScoreScreen(App, PlayerStats.getInstance().getHighScore()));
	    	dispose();
	    }
	}
	
	//alles was angezeigt werden muss
	private void draw(){
		
		background.draw(App.batch);
		gameworld.draw(App.batch, camera);
		gameworld.drawWorldInfos(App.batch, camera);
		stats.draw(App.batch, camera);
	}

			
	public void dispose(){
		musicPlayer.stop();
		
	}
}
