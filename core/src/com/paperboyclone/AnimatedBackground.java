package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * The <code>AnimatedBackground</code> implements an animation to the menus' 
 * backgrounds.
 * 
 * @author Martin Freudenberg
 *
 */
public class AnimatedBackground {
	
	
		private Vector2 size;
		private Sprite backgroundPattern;
		private Array<Vector2>positions;
		private float xAmount;
		private float yAmount;
		private Array<Paper>papers;
		
		private long nextSpawnTime;
		private int spawnDelay;
	
		public AnimatedBackground(Color backgroundTint){
	
			init(Assets.getTexture("backgroundPattern.png"));
			setBackgroundTint(backgroundTint);
		}
		
		public void setBackgroundTint(Color backgroundTint){
			backgroundPattern.setColor(backgroundTint);
		}
		
		public void draw(SpriteBatch batch){
			
			for(Vector2 j : positions){
				backgroundPattern.setPosition(j.x, j.y);
				backgroundPattern.draw(batch);
			}
			
			for(Paper p : papers){
				p.draw(batch);
			}
		}
		
		public void update(float delta, OrthographicCamera cam){
						
			//changeColor(delta);
			
			for(Paper p : papers){
				p.update(delta);
				float a = p.getSprite().getColor().a;
				a-= MathUtils.random(0.01f,0.07f) * delta;
				p.getSprite().setAlpha(a);
				if(p.getSprite().getColor().a <= 0){
					papers.removeValue(p,false);
				}
				
				
			}
			
			if(TimeUtils.millis()  >= nextSpawnTime){
	
				papers.add(createNewPaper());
				nextSpawnTime += spawnDelay; 
			}
		
		}
			
		private void init(Texture backgroundPatternTexture){
			
			
			size = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			backgroundPattern = new Sprite(backgroundPatternTexture);
			xAmount = Math.round(size.x/backgroundPattern.getWidth() + 2);
			yAmount = Math.round(size.y/backgroundPattern.getHeight() + 2);
			
			positions = new Array<Vector2>();
					
			float x = 0f;
			float y = 0f;
			for(int i = 0; i<(int) xAmount * yAmount; i++){
				
				
				if(x == xAmount * backgroundPattern.getWidth()){
					x = 0f;
					y += backgroundPattern.getHeight(); 
				}
				
				positions.add(new Vector2(x,y));
				x += backgroundPattern.getWidth();
				
			}
			

			Assets.addTexture("paper.png");
			Assets.loadAll();
			papers = new Array<Paper>();
			for(int i = 0 ; i<2; i++){
				papers.add(createNewPaper());
			}
			spawnDelay = 500;
			nextSpawnTime = TimeUtils.millis() + spawnDelay;
			
		
		}
		
		private Paper createNewPaper(){
			
			float[] px = {0,Gdx.graphics.getWidth()};
			Vector2 pos = new Vector2(px[MathUtils.random(0,1)],MathUtils.random(0,Gdx.graphics.getHeight()));
			boolean l = false;
			if(pos.x != 0){
				l = true;
			}
			
			Paper p = new Paper(pos, new Vector2(),l);
			/*Color randColor = new Color(MathUtils.random(0f,1f),MathUtils.random(0f,1f),MathUtils.random(0f,1f),1f);
			p.getSprite().setColor(randColor);*/
			return p;
		}
		
		/*private void changeColor(float delta){
			float cspeed = 200f;
			tint.g += (cspeed * delta)/255;
			if(tint.g >= 0.7) return;
			backgroundPattern.setColor(tint);
		}*/
}


