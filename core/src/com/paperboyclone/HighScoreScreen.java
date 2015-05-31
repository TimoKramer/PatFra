package com.paperboyclone;

import java.util.ArrayList;

import javax.swing.table.TableStringConverter;

import org.omg.CORBA.SystemException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class HighScoreScreen extends BasicScreen{
	
	BitmapFont font;
	int highscore;
	private Stage stage;
	private Table table;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private BackgroundManager background;
	private Skin skin;
	HighScoreHandler hsh;
	
	public HighScoreScreen(PaperboyClone app) {
		
		super(app);

		create();
	}
	
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.update();
		
		font = new BitmapFont();
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
		hsh = HighScoreHandler.getInstance();
		hsh.writeScore(highscore);
		int highscore = hsh.getHighScore();
		ArrayList<HighScore> highscoreList = hsh.getHighscoreList();
		
		skin = Assets.getSkin("uiskin.json");
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		shapeRenderer = new ShapeRenderer();
		
	    App.batch.setProjectionMatrix(camera.combined);
	}
		
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		
	    background.update(camera);		
		draw();

		App.batch.end();
	}
	
	private void draw() {
		
		font.draw(App.batch, "Game Over!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 40);
		font.draw(App.batch, String.valueOf(highscore), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 20);
		
		Table highscoreTable = new Table();
		highscoreTable.add(new Label("Player", skin));
		highscoreTable.add(new Label("Date", skin));
		highscoreTable.add(new Label("Score", skin));
		table.add(highscoreTable);
		
		
		table.drawDebug(shapeRenderer);
		
	}

	public void dispose(){
		stage.dispose();
		shapeRenderer.dispose();
	}

	
}
