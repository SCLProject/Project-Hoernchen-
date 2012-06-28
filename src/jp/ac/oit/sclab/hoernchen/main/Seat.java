package jp.ac.oit.sclab.hoernchen.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import jp.ac.oit.sclab.hoernchen.db.AccessDB;

/**
 *
 * Seatクラスは各席の情報を示します。
 *
 * @version 0.1
 *
 * @author MasanoriKato
 * @param seatId 席のID
 * @param mStudent 学生情報：Studentクラス
 * @param state	席の状況
 * @param recentAccess 最終アクセス日時
 * @param db データベース:AccessDBクラス
 */
public class Seat {

	public static final String TIME_STAMP_PATTRN = "yyyy-MM-dd HH:mm:ss";
    private int seatId;
    private int state;
    private Calendar recentAccess;
    private Student mStudent;
    private AccessDB db;

    private SimpleDateFormat sdf = new SimpleDateFormat(TIME_STAMP_PATTRN);

    // 在室状況
    public static final int STATE_TAISHITSU = 0;
    public static final int STATE_ZAISHITSU = 1;
    public static final int STATE_GAKUGAI   = 2;
    public static final int STATE_GAKUNAI   = 3;
    public static final int STATE_JYUGYOU   = 4;


    /**
     * 席IDから席情報を取得する
     *
     * @param seatId 欲しい席情報の席ID
     *
     * @return 席情報：Seatクラス
     *
     */
    public Seat(int seatId){
    	db = new AccessDB();


    	Calendar recentTime = Calendar.getInstance();
    	try {
    		System.out.println(""+db.getLastAccessTimeBySeat(seatId));
			recentTime.setTime(sdf.parse(db.getLastAccessTimeBySeat(seatId)));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("RecentTime が取得できません。");
		}

    	setRecentAccess(recentTime);
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
    		// seatId と ユーザーが紐付いてない場合 seatを生成しない
    		// TODO 要議論 (seatオブジェクトを生成しない か GUIでnull対応するか)
    		if(db.getUserIdBySeat(seatId.intValue()) != null)
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

    public Calendar getRecentAccess() {
	return recentAccess;
    }

    public void setRecentAccess(Calendar recentTime) {
	this.recentAccess = recentTime;
    }
}