package jp.ac.oit.sclab.hoernchen.util;

public class LissUtil {
	

	public static class State{
		public static final int STATE_IN_ROOM = 1;
		public static final int STATE_RETURN_HOME = 2;
		public static final int STATE_IN_LECTURING = 3;
		public static final int STATE_ON_CAMPUS = 4;
		public static final int STATE_OFF_CAMPUS = 5;
		public static final int STATE_UNKOWN = -1;
		
	


	public static String createStateString(int stateId){
		String state = null;
		switch(stateId){
		
		case STATE_IN_ROOM:
			state = "在室";
			break;
			
		case STATE_RETURN_HOME:
			state = "帰宅";
			break;
			
		case STATE_ON_CAMPUS:
			state = "学内";
			break;
		case STATE_OFF_CAMPUS:
			state = "学外";
			break;
		case STATE_IN_LECTURING:
			state = "授業";
			break;
		case STATE_UNKOWN:
		default:
			state = "不明";
		
		}
		
		return state;
	}




	
	
}
	

}
