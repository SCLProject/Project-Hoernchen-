package jp.ac.oit.sclab.hoernchen.main;

import jp.ac.oit.sclab.hoernchen.db.AccessDB;

public class Student {
    private String name;
    private String userId;
    private int grade;
    private long felicaId;
    private AccessDB db;

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
	this.grade = grade;
    }

    public long getFelicaId() {
	return felicaId;
    }

    public void setFelicaId(long felicaId) {
	this.felicaId = felicaId;
    }
}
