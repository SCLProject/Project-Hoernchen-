package jp.ac.oit.sclab.hoernchen.main;

public class Key {


	boolean state = false;
	Seat seat = null;



	public Key(){
		setState(false);
		setSeat(null);


	}



	public void setState(boolean bool){this.state = bool;}
	public boolean getState(){return this.state;}

	public void setSeat(Seat seat){
		this.seat = seat;

	}
	public Seat getSeat(){
		if(this.seat == null){
			throw new NullPointerException();
		}
		return this.seat;
	}


}
