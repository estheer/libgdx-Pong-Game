package com.me.mygdxgame;

import Sceens.GameScreen;
import Sceens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

public class pongGameMainClass  extends Game {  
	   
		public static final String GAMENAME = "box2d testing";
		public static final String VERSION = "0.0.0.01 pre-alpha";
		public static final String LOG = "box2d testing";
		FPSLogger log;
	    @Override  
	    public void create() {  
	    	log = new FPSLogger();
			setScreen(new SplashScreen(this));
	    }  
	    @Override  
	    public void dispose() {  
	    }  
	    @Override  
	    public void render() {            
	    	super.render();
			log.log();
	    }  
	    @Override  
	    public void resize(int width, int height) {  
	    	
	    }  
	    @Override  
	    public void pause() {  
	    	
	    }  
	    @Override  
	    public void resume() {  
	    }  
	}  