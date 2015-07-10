package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * The <code>GameScreen</code> is the <code>Screen</code> that runs the actual game.
 * 
 * 
 * @author Martin Freudenberg
 * @author Timo Kramer
 */
public class GameScreen extends BasicScreen{

	private DifficultySettings difficulty;
	BitmapFont font;
	private PlayerStatus status;
	private Player player;
	private OrthographicCamera camera;
	private BackgroundManager background;
	private AudioPlayer audioPlayer;
	private GameWorld gameworld;
	private Viewport viewport;
	
	
	public GameScreen(PaperboyClone app) {
		super(app);
		difficulty = DifficultySettings.getInstance();
		font = new BitmapFont();
		status = PlayerStatus.getInstance();
		status.set(0, 30, 5);
		camera = new OrthographicCamera();
		camera.position.set(1000, Gdx.graphics.getHeight(),0);
		camera.update();
		
		float r1 = 4/3;
			
		if(r1 == (float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight()){
			viewport = new FitViewport(1280,1024,camera);
		}
		else{
			viewport = new FitViewport(1280,720,camera);
		}
		viewport.apply();
		
		player = new Player(
				new Vector2(1000, Gdx.graphics.getHeight()),
				Assets.getTexture("character.png")
				);
		player.setMinSpeed(difficulty.getPlayerMinSpeed());
		
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
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
		gameworld.add(new PaperPileSpawnTask(7000, 12000, camera));
		
		audioPlayer = AudioPlayer.getInstance();
		audioPlayer.setBackgroundMusic(new BackgroundMusic());
		audioPlayer.startMusic();
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	public void render(float delta) {
	    update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		draw();
		App.batch.end();
	}
	
	/**
	 * Updates all entities, changes camera position based on player position, checks for user input
	 * @param delta time between frames
	 */
	private void update(float delta){
		
	    gameworld.update(delta, camera);
	    //Kamera auf Spieler-Position setzen
	    camera.position.set(player.getPosition().x,player.getPosition().y+300,0);
		
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    audioPlayer.checkMusicButton();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	    if (gameworld.getRemainingHouses() == 0 || PlayerStatus.getInstance().getLives() == 0) {
	    	System.out.println(String.valueOf(PlayerStatus.getInstance().getScore()));
	    	App.setScreen(new EnterNameScreen(App));
			dispose();
		}
	}
	/**
	 * draws all entities, the background, the player status and debug informations
	 */
	private void draw(){
		
		background.draw(App.batch);
		gameworld.draw(App.batch, camera);
		gameworld.drawWorldInfos(App.batch, camera);
		status.draw(App.batch, camera);
	}

			
	public void dispose(){
		audioPlayer.stopMusic();
		
	}
}
