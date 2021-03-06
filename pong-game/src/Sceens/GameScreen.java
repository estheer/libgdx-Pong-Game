package Sceens;

import ViewGame.WorldHandler;
import ViewGame.WorldRender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.mygdxgame.pongGameMainClass;

public class GameScreen implements Screen {

	pongGameMainClass game;
	WorldHandler world;
	WorldRender render;
	 float dt = Gdx.graphics.getRawDeltaTime();
	
	public GameScreen(pongGameMainClass game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		world = new WorldHandler(game);
		render = new WorldRender(world);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		world.update(dt);
		render.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		world.update(dt);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
