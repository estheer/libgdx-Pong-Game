package ViewGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRender {
	
	private OrthographicCamera camera;
	WorldHandler worldHandler;
	
	ShapeRenderer shapeRenderer;
	
	static final float BOX_STEP=1/60f;  
    static final int BOX_VELOCITY_ITERATIONS=10;  
    static final int BOX_POSITION_ITERATIONS=6; 
    
	
	// font stuff
    SpriteBatch spriteBatch;
    BitmapFont font;
    CharSequence str = "0123456789";
	
	
	public WorldRender(WorldHandler world){
		this.worldHandler = world;
		
		camera = new OrthographicCamera(50, 50);
		
		camera.position.set(25,25, 0);
		camera.zoom = 1.01f;
		camera.update();
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/fonts/test.fnt"),Gdx.files.internal("data/fonts/test.png"),false);
		

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		//camera = new OrthographicCamera(100, 100 * h / w);
		
		
		
	}
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if(worldHandler.resetVaribal){
			worldHandler.reset();
		}
		
		shapeRenderer.setColor(Color.WHITE);
		renderball();
		renderwallAndPaddels();
		
		//render.render(world, camera.combined);
		
		spriteBatch.begin();
        font.draw(spriteBatch, ""+worldHandler.player1Score, 150, 300);
        font.draw(spriteBatch, ""+worldHandler.player2Score, 250, 300);
        	
       
        switch (worldHandler.winnerText) {
		case 1:
			font.setScale(0.4f, 1);
			font.draw(spriteBatch, "the winner is player 1", 70, 200);
			font.draw(spriteBatch, "H 	for new game", 70, 100);
			break;
		case 2:
			font.setScale(0.4f, 1);
			font.draw(spriteBatch, "the winner is player 2", 70, 200);
			font.draw(spriteBatch, "H 	for new game", 70, 100);

			break;
			
		}
        spriteBatch.end();
        
        
		worldHandler.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
	}
	private void renderball() {
		// TODO Auto-generated method stub
		
		shapeRenderer.begin(ShapeType.FilledCircle);
	
		shapeRenderer.filledCircle(worldHandler.ball.getWorldPoint(worldHandler.ball.getLocalCenter()).x, worldHandler.ball.getWorldPoint(worldHandler.ball.getLocalCenter()).y, 1,10);
		shapeRenderer.end();
	}
	private void renderwallAndPaddels() {
		// TODO Auto-generated method stub
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.filledRect(0, worldHandler.top.getWorldPoint(worldHandler.ball.getLocalCenter()).y-1, 50, 2);
		shapeRenderer.filledRect(0, worldHandler.buttom.getWorldPoint(worldHandler.ball.getLocalCenter()).y-1, 50, 2);
		shapeRenderer.filledRect(0, 0, 2, 50);
		shapeRenderer.filledRect(worldHandler.right.getWorldCenter().x-1, 0, 2, 50);
		shapeRenderer.filledRect(worldHandler.paddle1.getPosition().x-1, worldHandler.paddle1.getPosition().y-10, worldHandler.PADDLE_SIZEX, worldHandler.PADDLE_SIZEY);
		shapeRenderer.filledRect(worldHandler.paddle2.getPosition().x-1, worldHandler.paddle2.getPosition().y-10, worldHandler.PADDLE_SIZEX,worldHandler.PADDLE_SIZEY);
		renderMiddleLine();

		shapeRenderer.end();
	}
	private void renderMiddleLine() {
		// TODO Auto-generated method stub
		shapeRenderer.setColor(Color.GREEN);
		for(int i=0;i<15;i++){
		shapeRenderer.filledRect(camera.viewportWidth/2-1, 3+(3*i), 1, 2);
		}
	}

}
