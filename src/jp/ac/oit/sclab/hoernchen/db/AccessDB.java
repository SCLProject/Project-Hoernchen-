package jp.ac.oit.sclab.hoernchen.db;

import java.util.ArrayList;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベースアクセスクラス
 * @author Kenta Suetsugu
 */
public class AccessDB {
    private final String HOST = "localhost:3306";
    private final String DB_NAME = "sclab";
    private final String ENCODE = "useUnicode=true&characterEncoding=UTF-8";
    private final String DB_URL = "jdbc:mysql://"+ HOST +"/"+ DB_NAME +"?"+ ENCODE;

    private String dbUserName = "sclab";
    private String dbPass = "sclab";

    /**
     * 登録されている座席idをすべて取得する
     *
     * @return ArrayList 登録されている席番号を返す
     */
    public ArrayList<Integer> getAllSeatId() {
        ArrayList<Integer> list
            = new ArrayList<Integer>();
        Connection c = null;

        try{
            c = getConnection();

            String sql = "select * from seat_t ";
            PreparedStatement pstate
                = c.prepareStatement(sql);
            ResultSet rs = pstate.executeQuery();
            
            while(rs.next()){
                list.add(rs.getInt("seat_id"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally { 
           try{
                if(c != null) c.close();
            } catch (Exception e){
                e.printStackTrace();	
            }
        }
        return list;
    }
    
    /**
     * 座席に所属している人のfelicaIDを返す
     * 
     * @param seatId 座席番号
     * @return 成功 long felicaId 失敗 -1
     */
    public long getFelicaIdBySeat (int seatID) {
	long felicaId = -1;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from felica_t natural join seat_t "+ 
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		felicaId = rs.getLong("felica_id");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();	
	    }
	}

	return felicaId;
    }

    /**
     * 座席に所属している人のGRADEを返す
     * 
     * @param seatId 座席番号
     * @return 成功 int grade 失敗 -1
     */
    public int getUserGradeBySeat (int seatID) {
	int grade = -1;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from parson_t natural join seat_t "+ 
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		grade = rs.getInt("grade");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();	
	    }
	}

	return grade;
    }

    /**
     * 座席に所属している人のIDを返す
     * 
     * @param seatId 座席番号
     * @return 成功 String 人の名前 失敗 null
     */
    public String getUserIdBySeat (int seatID) {
	String userId = null;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from parson_t natural join seat_t "+ 
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		userId = rs.getString("user_id");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();	
	    }
	}

	return userId;
    }

    /**
     * 座席に所属している人の名前を返す
     * 
     * @param seatID 座席番号
     * @return 成功した時はString 失敗した時はnull
     */
    public String getUserNameBySeat (int seatID) {
	String userName = null;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from parson_t natural join seat_t "+ 
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		userName = rs.getString("name");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();	
	    }
	}

	return userName;
    }

    /**
     * 席id から 最終アクセス時間を取得
     *
     * @param seatId
     * @return String型の最終アクセス時刻 発見出来なければ null
     */
    public String getLastAccessTimeBySeat(int seatId) {
	String time = null;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from log_t where seat_id = ? " +
		"order by log_id desc";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatId);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		time = rs.getString("time");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();
	    }
	}

	return time;
    }

    /**
     * felica_id に紐付けた自分人物が座ってる座席を取得する
     *
     * @param felicaID felicaカードのID
     * @return 座席番号 取得に失敗した時は -1
     */
    public int getSeatByFelica(int felicaID){
	int seat = -1;
	Connection c = null;
	try{
	    c = getConnection();

	    String sql =
		"select * from seat_t natural join felica_t"+
		"where felica_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, felicaID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.first())
		seat = rs.getInt("seat_id");

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    } catch (Exception e){
		e.printStackTrace();
	    }
	}
	return seat;
    }

    /**
     * felicaID と userID を紐付けてデータベースに登録
     *
     * @param userID 学籍番号
     * @param felicaID FelicaカードのID
     * @return 成功したらtrue
     */
    public boolean setFelica(int userID, int felicaID){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();
	    
	    String sql =
		"update felica_t set felica_id = ?, user_id = ?";
	    
	    PreparedStatement pstate =
		c.prepareStatement(sql);
	    pstate.setInt(1, felicaID);
	    pstate.setInt(2, userID);

	    pstate.executeUpdate();
	    isSuccess = true;

	    if(isSuccess) c.commit();

	} catch (SQLException e){
	    e.printStackTrace();
	} finally {
	    try{
		if(c != null) c.close();
	    }catch (Exception e){
		e.printStackTrace();
	    }
	}
	return isSuccess;
    }

    protected void initializeDB(boolean forceCreate) {
	// TODO データベースを作ってない場合の初期化の実装
	Connection c = null;
	try {
	    if ((c = getConnection()) == null) {
		return;
	    }

	    Statement stmt = c.createStatement();
	    stmt.setQueryTimeout(30);

	    if (forceCreate) {
		// drop tables
	    }

	    {
		// create tables 
	    }
	    
	    c.commit();
	    stmt.close();
	} catch (SQLException e) {
	    System.out.println(e.toString());
	} finally {
	    try {
		if (c != null) c.close();
	    } catch (SQLException e) {
	    }
	}
    }

    protected Connection getConnection() throws SQLException {
	Connection c = null;
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    c = DriverManager.getConnection(DB_URL, dbUserName, dbPass);
	    c.setAutoCommit(false);
	    return c;
	} catch (ClassNotFoundException e){
	    System.out.println(e.toString());
	    return null;
	}
    }
}
