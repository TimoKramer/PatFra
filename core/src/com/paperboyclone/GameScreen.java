package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	private DifficultySettings difficulty;
	BitmapFont font;
	private PlayerStats stats;
	private Player player;
	private OrthographicCamera camera;
	private BackgroundManager background;
	private MusicPlayer musicPlayer;
	//private long startTime;
	private GameWorld gameworld;
	
	
	
	public GameScreen(PaperboyClone app, String diff) {
		super(app);
		difficulty = DifficultySettings.getInstance();
		difficulty.setMode(diff);
		font = new BitmapFont();
		stats = PlayerStats.getInstance();
		stats.set(0, 30, 5);
		//Groesse der Kamera noch unklar
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(1000, Gdx.graphics.getHeight(),0);
		camera.update();
		
		player = new Player(
				new Vector2(1000, Gdx.graphics.getHeight()),
				Assets.getTexture("Test_Character.png")
				);
		player.setMinSpeed(difficulty.getPlayerMinSpeed());
		
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
		gameworld.add(new CollisionTask<Player, Mailbox>(Player.class, Mailbox.class));
		gameworld.add(new CollisionTask<Paper, Obstacle>(Paper.class, Obstacle.class));
		gameworld.add(new CollisionTask<Paper, House>(Paper.class, House.class));
		gameworld.add(new CollisionTask<Paper, Mailbox>(Paper.class, Mailbox.class));
		
		int  ObstacleMinSpawn = Math.round((1000 * difficulty.getObstacleSpawnDelayModifier()));
		int  ObstacleMaxSpawn = Math.round((2500 * difficulty.getObstacleSpawnDelayModifier()));
		gameworld.add(new ObstacleSpawnTask(ObstacleMinSpawn ,ObstacleMaxSpawn,camera));
		gameworld.add(new PaperPileSpawnTask(10000, 20000, camera));
		
		musicPlayer.run();
	}
	
	public void render(float delta) {
	    update(delta);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		draw();
		App.batch.end();
	}
	
	//alles was aktualisiert werden muss, input -> aenderung der werte, kollisions checks etc.
	private void update(float delta){
		
	    gameworld.update(delta, camera);
	    //Kamera auf Spieler-Position setzen
	    camera.position.set(player.getPosition().x,player.getPosition().y+300,0);
		
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    musicPlayer.checkMusicButton();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	    if (gameworld.getRemainingHouses() == 0) {
	    	System.out.println(String.valueOf(PlayerStats.getInstance().getScore()));
	    	//HighScoreHandler.getInstance().writeScore(PlayerStats.getInstance().getScore());
	    	//App.setScreen(new HighScoreScreen(App));
	    	App.setScreen(new EnterNameScreen(App));
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
