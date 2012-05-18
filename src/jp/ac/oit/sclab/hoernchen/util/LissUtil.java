package jp.ac.oit.sclab.hoernchen.util;

/**
 * @author masanori
 *	プロジェクトで使用する各種ツールクラス類を格納します。
 */
public class LissUtil {
	

	/**
	 * @author masanori
	 *　主にSeatクラスで利用する席情報のクラスです。
	 *　staticフィールドでそれぞれの状態を示します。
	 */
	public static class State{
		public static final int STATE_IN_ROOM = 1;
		public static final int STATE_RETURN_HOME = 2;
		public static final int STATE_IN_LECTURING = 3;
		public static final int STATE_ON_CAMPUS = 4;
		public static final int STATE_OFF_CAMPUS = 5;
		public static final int STATE_UNKNOWN = 0;
		
		public static final String _TEXT_STATE_IN_ROOM = "在室";
		public static final String _TEXT_STATE_RETURN_HOME = "帰宅";
		public static final String _TEXT_STATE_IN_LECTURING = "授業";
		public static final String _TEXT_STATE_ON_CAMPUS = "学内";
		public static final String _TEXT_STATE_OFF_CAMPUS = "学外";
		public static final String _TEXT_STATE_UNKNOWN = "不詳";
	/**
	 * ステートIDから席の状態を文字列で返却します。
	 * @param stateId 席の状態ID
	 * @return
	 */
	public static String createStateFromStateId(int stateId){
		String state = null;
		switch(stateId){
		
		case STATE_IN_ROOM:
			state = _TEXT_STATE_IN_ROOM;
			break;
			
		case STATE_RETURN_HOME:
			state = _TEXT_STATE_RETURN_HOME;
			break;
			
		case STATE_ON_CAMPUS:
			state = _TEXT_STATE_ON_CAMPUS;
			break;
		case STATE_OFF_CAMPUS:
			state = _TEXT_STATE_OFF_CAMPUS;
			break;
		case STATE_IN_LECTURING:
			state = _TEXT_STATE_IN_LECTURING;
			break;
		case STATE_UNKNOWN:
		default:
			state = _TEXT_STATE_UNKNOWN;
		}
		
		return state;
	}

	public static int findStateIdFromStateText(String state){
		if(state.equals(_TEXT_STATE_IN_LECTURING)){return STATE_IN_LECTURING;}
		else if(state.equals(_TEXT_STATE_IN_ROOM)){return STATE_IN_ROOM;}
		else if(state.equals(_TEXT_STATE_OFF_CAMPUS)){return STATE_OFF_CAMPUS;}
		else if(state.equals(_TEXT_STATE_ON_CAMPUS)){return STATE_ON_CAMPUS;}
		else if(state.equals(_TEXT_STATE_RETURN_HOME)){return STATE_RETURN_HOME;}
		else if(state.equals(_TEXT_STATE_UNKNOWN)){return STATE_UNKNOWN;}
		else return STATE_UNKNOWN;
	}


	
	
}
	

}
