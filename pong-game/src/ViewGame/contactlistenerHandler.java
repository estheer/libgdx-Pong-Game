package ViewGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class contactlistenerHandler implements ContactListener {

	WorldHandler handler;
	
	public contactlistenerHandler( WorldHandler handler) {
		// TODO Auto-generated constructor stub
	this.handler = handler;
	
	}

	@Override
	public void beginContact(Contact c) {
		// TODO Auto-generated method stub
		Gdx.app.log("contact listener", c.getFixtureA().getBody().getUserData()+ "");

		

			float ballCenterY = c.getFixtureB().getBody()
					.getWorldCenter().y;
			float paddleCenterY = c.getFixtureA().getBody()
					.getWorldCenter().y;
			float difference = ballCenterY - paddleCenterY;
			float position = difference / 50;
			float returnAngel = 70 * position;
			
			
			Gdx.app.log("returnAngel",returnAngel+" lin vol " + c.getFixtureB().getBody().getLinearVelocity().x +","+c.getFixtureB().getBody().getLinearVelocity().y);
			float Xspeed = c.getFixtureB().getBody().getLinearVelocity().x;	

			if(c.getFixtureB().getBody().getUserData() == "ball" ){
				if ( c.getFixtureA().getBody().getUserData() == "paddle1" ||  c.getFixtureA().getBody().getUserData() == "paddle2"){
				Xspeed  = Xspeed*-1;
				c.getFixtureB().getBody().setLinearVelocity(new Vector2(Xspeed, returnAngel));
				Gdx.app.log("returnAngel after new set",returnAngel+" lin vol " + c.getFixtureB().getBody().getLinearVelocity().x +","+c.getFixtureB().getBody().getLinearVelocity().y + " xSpeed = "+Xspeed);

				}
				}
			if(c.getFixtureB().getBody().getUserData() == "ball" && c.getFixtureA().getBody().getUserData() == "top" ){
				
				
					float angel = (180-90-returnAngel)*-1;
					
					c.getFixtureB().getBody().setLinearVelocity(new Vector2(Xspeed, returnAngel));
					Gdx.app.log("top + ball hit", "angel = " +angel);
					
			}
			if(c.getFixtureB().getBody().getUserData() == "ball" && c.getFixtureA().getBody().getUserData() == "buttom" ){
				
				
				float angel = (180-90-returnAngel)*-1;
				
				c.getFixtureB().getBody().setLinearVelocity(new Vector2(Xspeed, returnAngel));
				Gdx.app.log("top + ball hit", "angel = " +angel);
				
		}
			if(c.getFixtureB().getBody().getUserData() == "ball" && c.getFixtureA().getBody().getUserData() == "left" ){
				
				
				handler.setPlayer2ScorePlusOne();
				Gdx.app.log("Score : ", "player1 :"+handler.getPlayer1Score()+ "  player2 :"+handler.getPlayer2Score() );
				handler.resetVaribal = true;
				
		}
			if(c.getFixtureB().getBody().getUserData() == "ball" && c.getFixtureA().getBody().getUserData() == "right" ){
				
				
				handler.setPlayer1ScorePlusOne();
				Gdx.app.log("Score : ", "player1 :"+handler.getPlayer1Score()+ "  player2 :"+handler.getPlayer2Score() );
				handler.resetVaribal = true;
				
		}
		if(c.getFixtureB().getUserData() == "paddle1" && c.getFixtureA().getUserData() == "buttom" || c.getFixtureA().getUserData() == "top" ){
			}
			
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
