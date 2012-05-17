package jp.ac.oit.sclab.hoernchen.main;

/**
 * @author sclab-corei7
 * 鍵情報保存用クラス
 *
 */
public class Key {


	boolean state = false;
	Seat seat = null;



	public Key(){
		setState(false);
		setSeat(null);


	}



	/**
	 * 鍵の状況を設定
	 * @param bool
	 */
	public void setState(boolean bool){this.state = bool;}
	/**
	 * 鍵の状況を取得
	 * @return
	 */
	public boolean getState(){return this.state;}

	/**
	 * 席クラスの設定
	 * @param seat
	 */
	public void setSeat(Seat seat){
		this.seat = seat;

	}
	/**
	 * 席情報の取得
	 * @return 席情報：Seatクラス
	 * @throws NullPointerException
	 */
	public Seat getSeat(){
		if(this.seat == null){
			throw new NullPointerException();
		}
		return this.seat;
	}


}
