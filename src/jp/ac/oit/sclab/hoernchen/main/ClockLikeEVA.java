package jp.ac.oit.sclab.hoernchen.main;

import java.util.Calendar;

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

	
	private float[] digitLayoutX = {};
	private float[] digitLayoutY = {};
	private float[] digitLocale = {};
	
	
	
	
	
	private float[] hyphenLayoutX;
	private float hyphenLayoutY;
	
	private float[] colonLayoutX;
	private float[] colonLayoutY;
	


	public ClockLikeEVA(Color onColor,Color offColor) {
		// TODO 自動生成されたコンストラクター・スタブ

		Glow onEffect = new Glow(1.7f);
		onEffect.setInput(new InnerShadow());
		InnerShadow offEffect = new InnerShadow();

		digits = new Digit[19];

		
		
		
		for(int i = 0;i<digits.length;i++){
			
			Digit digit = new Digit(onColor,offColor,onEffect,offEffect);
			
			
			
			
			
			
			
		}




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
					polygons[i].setEffect(DIGIT_COMBINATIONS[num][i] ? onEffect : offEffect);
				}
			}
		}






	}









}
