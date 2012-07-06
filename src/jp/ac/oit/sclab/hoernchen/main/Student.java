package jp.ac.oit.sclab.hoernchen.main;

import jp.ac.oit.sclab.hoernchen.db.AccessDB;

public class Student {
    private String name;
    private String userId;
    private int grade;
    private long felicaId;
    private AccessDB db;


    	public static final int GRADE_3RD = 0;
    	public static final int GRADE_4TH = 1;
    	public static final int GRADE_MASTER = 2;
    	public static final int GRADE_ETC  = 3;

    	public static final String[] GRADE_NAME = {"3rd Grade","4th Grade","Master Grade","Etc"};






    public  Student(int seatId){
	db = new AccessDB();

	setName( db.getUserNameBySeat(seatId) );
	setUserId( db.getUserIdBySeat(seatId) );
	setGrade( db.getUserGradeBySeat(seatId) );
   	setFelicaId( db.getFelicaIdBySeat(seatId) );
    }

    public Student(String userId0,String name0,int grade0,long felicaId0){
	setFelicaId(felicaId0);
	setGrade(grade0);
	setName(name0);
	setUserId(userId0);
    }

    public Student(String userId,String name0,int grade0){
	this(userId,name0,grade0,-1);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public int getGrade() {
	return grade;
    }

    public void setGrade(int grade) {
    	if(grade > GRADE_ETC || grade < GRADE_3RD){
    		throw new IllegalArgumentException("0-3までの数字を指定");

    	}



    	this.grade = grade;
    }

    public long getFelicaId() {
	return felicaId;
    }

    public void setFelicaId(long felicaId) {
	this.felicaId = felicaId;
    }

    @Override
    public String toString() {
    	// TODO 自動生成されたメソッド・スタブ
    	return "名前："+getName()+",学生番号："+getUserId()+",学年："+Student.GRADE_NAME[getGrade()];
    }


}
