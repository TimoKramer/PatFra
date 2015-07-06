package com.paperboyclone;

import com.paperboyclone.PlayerStatus;

public class PlayerStatusListener {
	
	PlayerStatus playerStatus = PlayerStatus.getInstance();

	public void crashWithObstacle() {
		playerStatus.liveDown(1);
	}
	
	public void crashWithHouse() {
		playerStatus.liveDown(1);
	}

	public void hitSubscriberMailbox() {
		playerStatus.scoreUp(100);
	}
	
	public void hitSubscriberHouse() {
		playerStatus.scoreUp(10);
	}
	
	public void pickupPaperPile() {
		playerStatus.paperUp(10);
	}

	public void throwPaper() {
		playerStatus.paperDown(1);
	}
	
	public boolean isPaperAvailable() {
		return playerStatus.isPaperAvailable();
	}
}
