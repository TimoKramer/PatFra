package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PaperPile extends BasicGameEntity {
	
	public PaperPile(Vector2 position, Texture texture) {
		super(position, texture);
	}
	
	public PaperPile(Vector2 position) {
		super(position, Assets.getTexture("paperPile.png"));
	}
	
	public PaperPile() {
		super();
	}
}
