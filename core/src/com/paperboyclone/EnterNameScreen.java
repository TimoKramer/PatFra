package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EnterNameScreen extends BasicScreen{
	
	private BackgroundManager background;
	private OrthographicCamera camera;
	private Stage stage;
	private Skin skin;
	private TextField textinput;
	private Text text;
	

	public EnterNameScreen(PaperboyClone app) {
		super(app);
		create();
	}
	
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.update();

		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
				
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		text = new Text("Enter Name");
		text.setSize(300, 60);
		text.setPosition(Gdx.graphics.getWidth()/2 - text.getWidth()/2, Gdx.graphics.getHeight()*2/3);
		stage.addActor(text);
		
		textinput = new TextField("", skin);
		textinput.setSize(300, 60);
		textinput.setPosition(Gdx.graphics.getWidth()/2 - textinput.getWidth()/2, Gdx.graphics.getHeight()/2);
		textinput.setMaxLength(8);
		textinput.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ENTER) {
					onOK();
				}
				return super.keyDown(event, keycode);
			}
		});
		stage.addActor(textinput);
		
		TextButton okButton = new TextButton("OK", skin);
		okButton.setSize(300,  60);
		okButton.setPosition(Gdx.graphics.getWidth()/2 - okButton.getWidth()/2, Gdx.graphics.getHeight()/3);
		okButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				onOK();
			}
		});
		stage.addActor(okButton);

	}
	
	public void onOK() {
		saveHighScore(textinput.getText());
    	App.setScreen(new HighScoreScreen(App));
	}
	
	public void saveHighScore(String name) {
		System.out.println("Saving HighScore for " + name);
		HighScoreHandler.getInstance().writeScore(name, DifficultySettings.getInstance().getCurrentMode(), PlayerStats.getInstance().getScore());
	}
			
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		App.batch.begin();
		
	    background.update(camera);
		stage.draw();
		
		App.batch.end();
	}
	
	public boolean keyDown(int keycode) {
		System.out.println(keycode);
		if(keycode == Keys.ENTER) {
			onOK();
		}
		return true;
	}
	
}

class Text extends Actor {
	private BitmapFont font;
	private GlyphLayout layout;
	private float width;
	private Color headlineColor = Color.BLUE;
	
	public Text(String string) {
		font = Assets.getFont();
		font.setColor(headlineColor);
		font.getData().setScale(1.f);
		layout = new GlyphLayout(font, string);
		width = layout.width;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, layout, getX() + width/2, getY());
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
		return null;
    	
    }
}