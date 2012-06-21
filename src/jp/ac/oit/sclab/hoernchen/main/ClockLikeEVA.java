package jp.ac.oit.sclab.hoernchen.main;

import java.util.Calendar;


import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class ClockLikeEVA extends Parent{

	private Calendar nowCalendar = Calendar.getInstance();
	private Digit[] digits;
	private double sizeY = 0;
	private double sizeX = 0;

	private float normalDigitScaleX = 1.0f;
	private float normalDigitScaleY = 1.0f;
	private float bigDigitScaleX = 1.0f;
	private float bigDigitScaleY = 1.0f;
	
	
	
	private float[] digitLayoutX = 
		{ 	100, 125, 
			150, 175, 
			210, 235, 
			270, 295, 
			335, 370, 
			410, 445, 
			485, 510};
	private float[] digitLayoutY = 
		{  30,  30,  30,  30,  30,  30,  30,  30,  15,  15,  15,  15,  30,  30};
	private float[] digitScaleX ={
			normalDigitScaleX,normalDigitScaleX,
			normalDigitScaleX,normalDigitScaleX,
			normalDigitScaleX,normalDigitScaleX,
			normalDigitScaleX,normalDigitScaleX,
			bigDigitScaleX,bigDigitScaleX,
			bigDigitScaleX,bigDigitScaleX,
			normalDigitScaleX,normalDigitScaleX
			};
	private float[] digitScaleY ={
			normalDigitScaleY,normalDigitScaleY,
			normalDigitScaleY,normalDigitScaleY,
			normalDigitScaleY,normalDigitScaleY,
			normalDigitScaleY,normalDigitScaleY,
			bigDigitScaleY,bigDigitScaleY,
			bigDigitScaleY,bigDigitScaleY,
			normalDigitScaleY,normalDigitScaleY
			};
	
	
	
	
	
	private float[] hyphenLayoutX;
	private float hyphenLayoutY;
	
	private float[] colonLayoutX;
	private float[] colonLayoutY;
	


	public ClockLikeEVA(Color onColor,Color offColor) {
		// TODO 自動生成されたコンストラクター・スタブ

		Glow onEffect = new Glow(1.7f);
		onEffect.setInput(new InnerShadow());
		InnerShadow offEffect = new InnerShadow();

		
		Glow onDotEffect = new Glow(1.7f);
		onDotEffect.setInput(new InnerShadow(5,Color.BLACK));

		digits = new Digit[14];

		
		
		
		for(int i = 0;i<digits.length;i++){
			
			Digit digit = new Digit(onColor,offColor,onEffect,offEffect);
			digit.setLayoutX(digitLayoutX[i]);
			digit.setLayoutY(digitLayoutY[i]);
			
			
			digits[i] = digit;
			getChildren().add(digit);
			
		}
		
		sizeX  = digitLayoutX[digits.length-1]+20;
		sizeY = digitLayoutY[digits.length-1]+43;
		
		
		double base1 = 200;
		double base2 = 260;
		Group hyphens = new Group(
				
				new Polygon(base1 + 0,52,base1 + 7,52,base1 + 7,56,base1 + 0,56),
				
				new Polygon(base2 + 0,52,base2 + 7,52,base2 + 7,56,base2 + 0,56)
				
				);
		
		Group dots = new Group(
				
				new Polygon (402,30,406,30,406,34,402,34),
				
				new Polygon (402,30+27,406,30+27,406,34+27,402,34+27),
				
				new Polygon (477,43,480,43,480,46,477,46),
				
				new Polygon (477,43+18,480,43+18,480,46+18,477,46+18)			
				
				
				);
		
		hyphens.setEffect(onDotEffect);
		dots.setEffect(onDotEffect);
		
		
		getChildren().add(hyphens);
		getChildren().add(dots);
		
		
		
		
		
		
		
		
		
	}


	
	public void refreshClocks(){
	
		nowCalendar = Calendar.getInstance();
		
		int year = nowCalendar.get(Calendar.YEAR);
		int month = nowCalendar.get(Calendar.MONTH)+1;
		int day = nowCalendar.get(Calendar.DATE);
		int hour = nowCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = nowCalendar.get(Calendar.MINUTE);
		int second = nowCalendar.get(Calendar.SECOND);

		
		digits[0].showNumber(year/1000);
		digits[1].showNumber((year%1000)/100);
		digits[2].showNumber((year%100)/10);
		digits[3].showNumber(year%10);
		digits[4].showNumber(month/10);
		digits[5].showNumber(month%10);
		digits[6].showNumber(day/10);
		digits[7].showNumber(day%10);
		digits[8].showNumber(hour/10);
		digits[9].showNumber(hour%10);
		digits[10].showNumber(minute/10);
		digits[11].showNumber(minute%10);
		digits[12].showNumber(second/10);
		digits[13].showNumber(second%10);

		
		
		
		
		
		
		
	}

	public double getWidth(){
		return sizeX;
	}
	public double getHeight(){
		return sizeY;
	}


	private static class Digit extends Parent{

		private final Color onColor;
		private final Color offColor;
		private final Color notColor = new Color(0,0,0,0);
		private final Effect onEffect;
		private final Effect offEffect;






		private static boolean[][] DIGIT_COMBINATIONS = new boolean[][]{

            new boolean[]{true, false, true, true, true, true, true},//0

            new boolean[]{false, false, false, false, true, false, true},//1

            new boolean[]{true, true, true, false, true, true, false},//2

            new boolean[]{true, true, true, false, true, false, true},//3

            new boolean[]{false, true, false, true, true, false, true},//4

            new boolean[]{true, true, true, true, false, false, true},//5

            new boolean[]{true, true, true, true, false, true, true},//6

            new boolean[]{true, false, false, false, true, false, true},//7

            new boolean[]{true, true, true, true, true, true, true},//8

            new boolean[]{true, true, true, true, true, false, true}//9



		};



		private final Polygon[] polygons = new Polygon[]{

				new Polygon(18,1,57,1,65,9,57,17,18,17,10,9),

				new Polygon(10,81,18,73,57,73,65,81,57,89,18,89),

				new Polygon(10,54,18,146,57,146,65,154,57,163,18,163),

				new Polygon(0,19,8,11,16,19,16,71,8,79,0,71),

				new Polygon(59,19,67,11,74,19,74,71,67,79,59,71),

				new Polygon(0,91,8,83,16,91,16,144,8,152,0,144),

				new Polygon(59,91,67,83,74,91,74,144,57,157,59,144),


		};

		

		public Digit(Color onColor,Color offColor,Effect onEffect,Effect offEffect){
			this.onColor = onColor;
			this.offColor = offColor;
			this.onEffect = onEffect;
			this.offEffect = offEffect;
			getChildren().addAll(polygons);

		}

		public void showNumber(int num){
			
			if(0<= num && num <= 9){
				for(int i = 0;i < 7;i++){
					polygons[i].setFill(DIGIT_COMBINATIONS[num][i] ? onColor:offColor);
				//	polygons[i].setEffect(DIGIT_COMBINATIONS[num][i] ? onEffect : offEffect);
				}
			}
		}






	}









}
