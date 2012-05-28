package jp.ac.oit.sclab.hoernchen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author masanori
 *	プロジェクトで使用する各種ツールクラス類を格納します。
 */
public  class LissUtil {
	
	
	public static class DifferenceTime{

		private long differenceMiliSecond = 0;
		
		private Calendar newCalendar = null;
		private Calendar oldCalendar = null;
		
		
		public DifferenceTime(Calendar oldCalendar,Calendar newCalendar){
			
			 setOldCalendar(oldCalendar);
			 setNewCalendar(newCalendar);
				getDiffrence();
			 
			
			
			
			
		}
		public DifferenceTime(Calendar oldCalendar){
			this(oldCalendar,Calendar.getInstance());
		}
		
		private long getDiffrence(){
			differenceMiliSecond =   newCalendar.getTimeInMillis() - oldCalendar.getTimeInMillis();
			return differenceMiliSecond;
		}
		
		private void setOldCalendar(Calendar oldCalendar){
			this.oldCalendar = oldCalendar;
		}
		private void setNewCalendar(Calendar newCalendar){
			this.newCalendar = newCalendar;
			
		}
		

		private int getDifDay(){
			return (int)(differenceMiliSecond/(1000*60*60*24));
			}
		
		private int getDifHour(){
			return (int)(differenceMiliSecond/(1000*60*60));
			}
		private int getDifMinute(){
			return (int)(differenceMiliSecond/(1000*60));		
			}
		private int getDifSecond(){
			return (int)(differenceMiliSecond/(1000));
		}

		public static final int TIME_DAY = 2003;
		public static final int TIME_HOUR = 2004;
		public static final int TIME_MINUTE = 2005;
		public static final int TIME_SECOND = 2006;
		
		public String getDifferenceTime(){
			
			String ret = "";
			int time = get(TIME_SECOND);
			if(time<60){
				ret = get(TIME_SECOND)%60 + "秒";			
			}else if(time<60*60){
				ret = get(TIME_MINUTE)%60 + "分";
			}else if(time< 60*60*5){
				ret = get(TIME_HOUR)%24+ "時間" + get(TIME_MINUTE)%60 + "分" ; 
			}else if(time < 60 * 60 * 24 ){
				ret = get(TIME_HOUR)%24+ "時間";
			}else if(time < 60*60*24*7){
				ret = get(TIME_DAY) + "日" ;
			}else{
				return Tools.dateToString(oldCalendar.getTime(), Tools.FORMAT_TYPE_JP); 
			}
								
							

			
			
			return ret + "前";
					
			
}
		
		private int get(int fieldId){

			
			switch (fieldId) {

			case TIME_HOUR:
				return getDifHour();
				
			case TIME_MINUTE:
				return getDifMinute();
				
			case TIME_SECOND:
				return getDifSecond();
				
			default:
				return getDifDay();
	
			}
			
		}
		
		
	}
	
	
	public static class Tools{
		public static final int FORMAT_TYPE_YYYY_MM_DD = 0x0;
		public static final int FORMAT_TYPE_YYYY_MM = 0x1;
		public static final int FORMAT_TYPE_MM_DD = 0x2;
		public static final int FORMAT_TYPE_MM = 0x3;
		public static final int FORMAT_TYPE_FULL = 0x4;
		public static final int FORMAT_TYPE_DD = 0x5;
		public static final int FORMAT_TYPE_JP = 0x6;

	static final int ORDER_MODE_DESC = 0x1001;
	static final int ORDER_MODE_ASC = 0x1000;




		public static String dateToString(Date date,int formatType){
			SimpleDateFormat df;

			if(formatType == FORMAT_TYPE_YYYY_MM_DD){
				df = new SimpleDateFormat("yyyy-MM-dd");
				return  df.format(date);
			}
			else if(formatType == FORMAT_TYPE_MM_DD){
				df = new SimpleDateFormat("MM-dd");
				return  df.format(date);
			}
			else if(formatType == FORMAT_TYPE_YYYY_MM){
				df = new SimpleDateFormat("yyyy-MM");
				return  df.format(date);
			}
			else if(formatType == FORMAT_TYPE_MM){
				df = new SimpleDateFormat("MM");
				return  df.format(date);
			}
			else if(formatType == FORMAT_TYPE_DD){
				df = new SimpleDateFormat("dd");
				return df.format(date);
			}else if(formatType == FORMAT_TYPE_FULL){
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return  df.format(date);
				
			}else if(formatType == FORMAT_TYPE_JP){
				df = new SimpleDateFormat("yyyy年MM月dd日");
				return  df.format(date);
			}
			
			else{
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return  df.format(date);
			}

		}
		/**
		 * @param dateString only "yyyy-MM-dd HH:mm"
		 * @return Calendar
		 */
		public static Calendar stringToDate(String dateString){
			Calendar date = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				date.setTime(df.parse(dateString));

			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return date;
		}

	}
	
	

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
