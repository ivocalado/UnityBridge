package com.nees.senativos.requesthandler;

import java.util.LinkedList;
import java.util.Queue;

public class EmotionsHandler {
	
	private static EmotionsHandler instance = null;
	private static Queue<String> emotions;
	
	private EmotionsHandler() {
		emotions = new LinkedList<>();
	}
	
	public static EmotionsHandler getInstance() {
			if(instance == null) {
				instance = new EmotionsHandler();
		}
		return instance;
	}
	public Queue<String> getEmotions() {
		return emotions;
	}
	
}
