package com.nees.senativos.requesthandler;

import java.util.Queue;

public class EmotionsHandler {
	
	private static Queue<String> emotions = EmotionQueue.getInstance();
	
	public static Queue<String> getEmotions() {
		return emotions;
	}
	
}
