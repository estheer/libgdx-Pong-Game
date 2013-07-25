package ViewGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneLoader;
import com.me.mygdxgame.pongGameMainClass;

public class WorldHandler {
	
	pongGameMainClass game;
	
	private RubeSceneLoader loader;
	private RubeScene scene;
	
	public World world;

	static final float PADDLE_SPEED = 0.2f;
    static final float BALL_SPEEDX = 15;
    static final float BALL_SPEEDY = 0;
    static final int PADDLE_SIZEX = 2;
    static final int PADDLE_SIZEY = 16;

    public Body ball,paddle1,paddle2,left,top,buttom,right;
    int player1Score,player2Score;
    
    public boolean resetVaribal = true;
	
    FPSLogger fps;
    ShapeRenderer shapeRenderer;
    int addBallSpeed=1;
    int fpsCount=1;
    
    // font stuff
    SpriteBatch spriteBatch;
    BitmapFont font;
    CharSequence str = "0123456789";
    int winnerText = 0;
	
	public WorldHandler(pongGameMainClass game) {
		this.game = game;
		
		
		loadWorld();
		box2dStuff();
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/fonts/test.fnt"),Gdx.files.internal("data/fonts/test.png"),false);
		
	}
	
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
	}

	private void loadWorld(){
		loader = new RubeSceneLoader();
		scene = loader.loadScene(Gdx.files.internal("data/test.json"));
		world = scene.getWorld();
		world.setContactListener(new contactlistenerHandler(this));
		scene.clear();
		
	}
	private void box2dStuff() {
		// TODO Auto-generated method stub
		
		Array<Body> bodys = scene.getNamed(Body.class, "ball");
		ball =bodys.get(0);
		ball.setUserData("ball");
		
		bodys = scene.getNamed(Body.class, "paddle1");
		paddle1 = bodys.get(0);
		paddle1.setUserData("paddle1");

		bodys = scene.getNamed(Body.class, "paddle2");
		paddle2 = bodys.get(0);
		paddle2.setUserData("paddle2");
		
		bodys = scene.getNamed(Body.class, "top");
		top = bodys.get(0);
		top.setUserData("top");
		
		bodys = scene.getNamed(Body.class, "buttom");
		buttom = bodys.get(0);
		buttom.setUserData("buttom");
		
		bodys = scene.getNamed(Body.class, "right");
		right = bodys.get(0);
		right.setUserData("right");
		
		bodys = scene.getNamed(Body.class, "left");
		left = bodys.get(0);
		left.setUserData("left");
		
		
		
	}
	private void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Input.Keys.N)){
			reset();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.H)){
			
			winnerText =0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			
			player1Score=10;
			player2Score=10;

		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
		
			if(paddle1.getPosition().y+PADDLE_SIZEY/2 > top.getWorldCenter().y){
			}else{
			paddle1.setTransform(paddle1.getPosition().x, paddle1.getPosition().y+PADDLE_SPEED, 0);
			}
		
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			if(paddle1.getPosition().y -PADDLE_SIZEY/2 < buttom.getWorldCenter().y+3.5){
			}else{
			paddle1.setTransform(paddle1.getPosition().x, paddle1.getPosition().y-PADDLE_SPEED, 0);
			}

			
			}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			if(paddle2.getPosition().y+PADDLE_SIZEY/2 > top.getWorldCenter().y){
			}else{
			paddle2.setTransform(paddle2.getPosition().x, paddle2.getPosition().y+PADDLE_SPEED, 0);
			}
		
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if(paddle2.getPosition().y -PADDLE_SIZEY/2 < buttom.getWorldCenter().y+3.5){
			}else{
			paddle2.setTransform(paddle2.getPosition().x, paddle2.getPosition().y-PADDLE_SPEED, 0);
			}

			
			}
		}
	public void reset(){
		
		if(player1Score >10){
			player1Score = 0;
			player2Score = 0;
			winnerText = 1;
		}else if(player2Score >10){
			player1Score = 0;
			player2Score = 0;
			winnerText = 2;
		}else{
			ball.setTransform(25, 25, 0);
			paddle1.setTransform(4, 25, 0);
			paddle2.setTransform(46, 25, 0);
			ball.setLinearVelocity(BALL_SPEEDX, BALL_SPEEDY);
			resetVaribal = false;
		}
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Body getBall(){
		return ball;
	}
	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	public void setPlayer1ScorePlusOne() {
		this.player1Score = player1Score+1;
	}
	public void setPlayer2ScorePlusOne() {
		this.player2Score = player2Score+1;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

}
