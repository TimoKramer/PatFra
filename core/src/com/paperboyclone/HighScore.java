package com.paperboyclone;

public class HighScore {

	String name;
	long time;
	int score;
	
	public HighScore(String name, long time, int score) {
		this.name = name;
		this.time = time;
		this.score = score;
	}
	
	public HighScore() {
		this.name = "Player";
		this.time = 0L;
		this.score = 0;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
