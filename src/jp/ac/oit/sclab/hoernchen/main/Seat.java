package jp.ac.oit.sclab.hoernchen.main;

public class Seat {

	private Student mStudent=null;
	private int state=0;
	private String recentAccess="";





	public Seat(){



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
