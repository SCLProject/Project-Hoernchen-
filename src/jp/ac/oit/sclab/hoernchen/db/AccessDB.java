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
    //ディレクトリ (起動した場所から相対的に実行)
    // TODO それぞれの環境で書き換える必要があるかも。
    private final String BASE_DIR = "./";
    private final String PATH = "/sqlite.db";
    private final String DB_URI = "jdbc:sqlite:" + BASE_DIR + PATH;

    public AccessDB(){
	// true で残っているデータベース削除してから新規作成
	// TODO 初期化をコンストラクタで行うか、メソッドとして任意に行うか
	this.initializeDB(false);
    }

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

            String sql = "select * from seat_t order by seat_id ";
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
     * @return 成功 String felicaId 失敗 long -1
     */
    public long getFelicaIdBySeat(int seatID) {
	String felicaId = "-1";
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"select * from felica_t inner join seat_t "
		+" on felica_t.user_id = seat_t.user_id"
		+" where seat_id == ?";
	    PreparedStatement pstate = c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.next()){
		felicaId = rs.getString("felica_id");
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
	return Long.parseLong(felicaId);
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
		"select * from parson_t natural inner join seat_t "+
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.next())
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
		"select * from parson_t natural inner join seat_t "+
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.next())
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
		"select * from parson_t natural inner join seat_t "+
		"where seat_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    ResultSet rs = pstate.executeQuery();

	    if(rs.next())
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
	    if(rs.next())
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
		"select * from seat_t natural inner join felica_t"+
		"where felica_id = ?";
	    PreparedStatement pstate
		= c.prepareStatement(sql);
	    pstate.setInt(1, felicaID);
	    ResultSet rs = pstate.executeQuery();
	    if(rs.next())
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
    public boolean setFelica(String userID, int felicaID){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"update felica_t set felica_id = ? where user_id = ?";

	    PreparedStatement pstate =
		c.prepareStatement(sql);
	    pstate.setInt(1, felicaID);
	    pstate.setString(2, userID);

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

    /**
     * SeatID と userID を紐付けてデータベースに登録
     *
     * @param userID 学籍番号
     * @param SeatID 席ID
     * @return 成功したらtrue
     */
    public boolean setSeat(String userID, int seatID){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"update seat_t set user_id = ? where seat_id = ?";
	    PreparedStatement pstate =
		c.prepareStatement(sql);
	    pstate.setString(1, userID);
	    pstate.setInt(2, seatID);
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

    /**
     * log の追加
     *
     * @param seatID 席ID
     * @param state 状態
     * @return 成功したらtrue
     */
    public boolean addLog(int seatID, int state){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();

	    String sql =
		"insert into log_t(seat_id, state) values(?, ?)";
	    PreparedStatement pstate =
		c.prepareStatement(sql);
	    pstate.setInt(1, seatID);
	    pstate.setInt(2, state);
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

    /**
     * 学生情報をデータベースに追加
     *
     * @param userID 学籍番号
     * @param name 登録される名前
     * @param grade
     * @return 成功したらtrue
     */
    public boolean addStudent(String userID, String name, int grade){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();

	    System.out.println("addStudent: into parson.");
	    String parson_sql =
		"insert into parson_t (user_id, name, grade) values(?, ?, ?)";
	    PreparedStatement pstate =
		c.prepareStatement(parson_sql);
	    pstate.setString(1, userID);
	    pstate.setString(2, name);
	    pstate.setInt(3, grade);
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

    /**
     * user_idから学生情報をデータベースから削除
     *
     * @param userID 学籍番号
     * @return 成功したらtrue
     */
    public boolean delStudent(String userID){
	boolean isSuccess = false;
	Connection c = null;

	try{
	    c = getConnection();

	    String delFelicaSql =
		"update felica_t set user_id = NULL where user_id = ?";
	    String delSeatSql =
		"update seat_t set user_id = NULL where user_id = ?";
	    String delParsonSql =
		"delete from parson_t where user_id = ?";

	    PreparedStatement pstate =
		c.prepareStatement(delFelicaSql);
	    pstate.setString(1, userID);
	    pstate.executeUpdate();

	    pstate = c.prepareStatement(delSeatSql);
	    pstate.setString(1, userID);
	    pstate.executeUpdate();

	    pstate = c.prepareStatement(delParsonSql);
	    pstate.setString(1, userID);
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

    /**
     * table削除のSQL群を返す
     *
     * @return String[]
     */
    private String[] getDropTableSqls(){
	String[] sql =
	    {
		"drop table if exists log_t",
		"drop table if exists felica_t",
		"drop table if exists seat_t",
		"drop table if exists parson_t"
	    };

	return sql;
    }

    /**
     * table作成のSQL文群を返す
     *
     * @return String[]
     */
    private String[] getCreateTableSqls(){
	String[] sql = {
	    // parson_t (人物データベース)
	    "create table if not exists parson_t("+
	    "user_id varchar(6) primary key, "+
	    "name varchar(20), "+
	    "grade numeric(1))",
	    // seat_t (席情報データベース)
	    "create table if not exists seat_t("+
	    "seat_id text primary key, "+
	    "user_id varchar(6), "+
	    "foreign key(user_id) references parson_t(user_id))",
	    // felica_t (felicaカードデータベース)
	    "create table if not exists felica_t("+
	    "felica_id integer primary key, "+
	    "user_id varchar(6), "+
	    "foreign key(user_id) references parson_t(user_id))",
	    // log_t (ログデータベース)
	    "create table if not exists log_t("+
	    "log_id  integer primary key autoincrement, "+
	    "seat_id integer, "+
	    "time    timestamp DEFAULT (DATETIME('now', 'localtime')), "+
	    "state   numeric(1))",
	};

	return sql;
    }

    /**
     * テーブルがない場合のデータベースの作成
     * フラグによる、強制削除からの作成を行える。
     */
    protected void initializeDB(boolean forceCreate) {
	Connection c = null;
	try {
	    if ((c = getConnection()) == null) {
		return;
	    }

	    Statement stmt = c.createStatement();
	    stmt.setQueryTimeout(30);

	    // 引数が true の時、テーブル削除
	    if (forceCreate) {
		String[] dropSqls = getDropTableSqls();

		for(String sql : dropSqls){
		    stmt.executeUpdate(sql);
		}
	    }

	    // テーブル作成
	    String[] createSqls = getCreateTableSqls();
	    for(String sql : createSqls){
		stmt.executeUpdate(sql);
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

    /**
     * DB との接続を返す
     */
    protected Connection getConnection() throws SQLException {
	Connection c = null;
	try {
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection(DB_URI);
	    c.setAutoCommit(false);
	    return c;
	} catch (ClassNotFoundException e){
	    System.out.println(e.toString());
	    return null;
	}
    }
}
