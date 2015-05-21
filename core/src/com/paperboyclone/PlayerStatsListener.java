package com.paperboyclone;

import com.paperboyclone.PlayerStats;

public class PlayerStatsListener {
	
	PlayerStats playerStats = PlayerStats.getInstance();

	public void crashWithObstacle() {
		playerStats.liveDown(1);
	}
	
	public void crashWithHouse() {
		playerStats.liveDown(1);
	}

	public void hitSubscriberMailbox() {
		playerStats.scoreUp(100);
	}
	
	public void hitSubscriberHouse() {
		playerStats.scoreUp(10);
	}
	
	public void pickupPaperPile() {
		playerStats.paperUp(10);
	}

	public void throwPaper() {
		playerStats.paperDown(1);
	}
	
	public boolean isPaperAvailable() {
		return playerStats.isPaperAvailable();
	}
}
