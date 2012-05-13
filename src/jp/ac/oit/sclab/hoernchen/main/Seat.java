package jp.ac.oit.sclab.hoernchen.main;

import java.util.Iterator;
import java.util.ArrayList;
import jp.ac.oit.sclab.hoernchen.db.AccessDB;

public class Seat {
    private int seatId;
    private int state;
    private String recentAccess;
    private Student mStudent;
    private AccessDB db;

    // 在室状況
    public static final int STATE_TAISHITSU = 0;
    public static final int STATE_ZAISHITSU = 1;
    public static final int STATE_GAKUGAI   = 2;
    public static final int STATE_GAKUNAI   = 3;
    public static final int STATE_JYUGYOU   = 4;

    public Seat(int seatId){
    	db = new AccessDB();

    	setRecentAccess(db.getLastAccessTimeBySeat(seatId));
    	setSeatId(seatId);
    	setState(STATE_TAISHITSU);
    	setmStudent(new Student(seatId));
    }

    /**
     * すべての座席をオブジェクト化してArrayListとして返す
     *
    * @return 座席リスト ArrayList<Seat> 
     */
    public static ArrayList<Seat> getSeatArrayList() {
    	AccessDB db = new AccessDB();
	
    	ArrayList<Seat> list = new ArrayList<Seat>();
    	Iterator<Integer> it = db.getAllSeatId().iterator();
	
    	while(it.hasNext()){
    		Integer seatId = it.next();
    		list.add( new Seat(seatId.intValue()) );
    	}
    	return list;
    }

    public int getSeatId() {
	return seatId;
    }

    public void setSeatId(int seatId) {
	this.seatId = seatId;
    }

    public Student getmStudent() {
	return mStudent;
    }

    public void setmStudent(Student mStudent) {
	this.mStudent = mStudent;
    }

    public int getState() {
	return state;
    }

    public void setState(int state) {
	this.state = state;
    }

    public String getRecentAccess() {
	return recentAccess;
    }

    public void setRecentAccess(String recentAccess) {
	this.recentAccess = recentAccess;
    }
}