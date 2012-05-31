package jp.ac.oit.sclab.hoernchen.main;

import java.util.Calendar;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Shear;

public class Clock extends Parent {

	private Calendar calendar = Calendar.getInstance();
	private Digit[] digits;
	public  static final int HYPHEN = 1002;
	public static final int SPACE = 1003;
	private double sizeX = 0;
	private double sizeY = 0;


	public Clock(Color onColor,Color offColor){


		Glow onEffect = new Glow(1.7f);
		onEffect.setInput(new InnerShadow());

		Glow onDotEffect = new Glow(1.7f);
		onDotEffect.setInput(new InnerShadow(5,Color.BLACK));

		InnerShadow offEffect = new InnerShadow();

		digits = new Digit[19];










		for(int i = 0;i<digits.length;i++){

			Digit digit = new Digit(onColor,offColor,onEffect,offEffect);



				digit.setLayoutX(i*40);




			digits[i] = digit;
			getChildren().add(digit);


		}
		sizeX += (digits.length-1)*40;
		sizeX += 26;
		sizeY = 54;
		int base = 11*40+15;
		Group dots = new Group(

                new Circle(base+40 + 27 + 10, 22, 3, onColor),

                new Circle(base+40 + 27 + 8.5, 32, 3, onColor),

                new Circle(base+(40 * 4) + 27 + 10, 22, 3, onColor),

                new Circle(base+(40 * 4) + 27 + 8.5, 32, 3, onColor));

        dots.setEffect(onDotEffect);

        getChildren().add(dots);
        refreshClocks();



	}
	public double getSizeX(){
		return sizeX;
	}
	public double getSizeY(){
		return sizeY;
	}

	public void refreshClocks(){
		calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);


		digits[0].showNumber(year/1000);
		digits[1].showNumber((year%1000)/100);
		digits[2].showNumber((year%100)/10);
		digits[3].showNumber(year%10);
		digits[4].showNumber(Clock.HYPHEN);
		digits[5].showNumber(month/10);
		digits[6].showNumber(month%10);
		digits[7].showNumber(Clock.HYPHEN);
		digits[8].showNumber(day/10);
		digits[9].showNumber(day%10);
		digits[10].showNumber(Clock.SPACE);
		digits[11].showNumber(hour/10);
		digits[12].showNumber(hour%10);
		digits[13].showNumber(Clock.SPACE);
		digits[14].showNumber(minute/10);
		digits[15].showNumber(minute%10);
		digits[16].showNumber(Clock.SPACE);
		digits[17].showNumber(second/10);
		digits[18].showNumber(second%10);



	}




	public static  class Digit extends Parent {
		private static boolean [] [] DIGIT_COMBINATIONS = new boolean[][]{
            new boolean[]{true, false, true, true, true, true, true},

            new boolean[]{false, false, false, false, true, false, true},

            new boolean[]{true, true, true, false, true, true, false},

            new boolean[]{true, true, true, false, true, false, true},

            new boolean[]{false, true, false, true, true, false, true},

            new boolean[]{true, true, true, true, false, false, true},

            new boolean[]{true, true, true, true, false, true, true},

            new boolean[]{true, false, false, false, true, false, true},

            new boolean[]{true, true, true, true, true, true, true},

            new boolean[]{true, true, true, true, true, false, true}


            };


        private final Polygon[] polygons = new Polygon[]{

                new Polygon(1, 0, 26, 0, 21, 5, 6, 5), //上

                new Polygon(6, 24.5, 21, 24.5, 26, 27, 21, 29.5, 6f, 29.5, 1f, 27f), //中

                new Polygon(6, 49, 21, 49, 26, 54, 1, 54),//下

                new Polygon(0, 1, 5, 6, 5, 28.5, 0, 26),//左上

                new Polygon(22, 6, 27, 1, 27, 26, 22, 23.5),//右上

                new Polygon(0, 28, 5, 30.5, 5, 48, 0, 53),//左下

                new Polygon(22, 30.5, 27, 28, 27, 53, 22, 48),//右下


      };



        private final Color onColor;
        private final Color offColor;
    	private Color notColor = new Color(0,0,0,0);
        private final Effect onEffect;
        private final Effect offEffect;

        public Digit (Color onColor,Color offColor,Effect onEffect,Effect offEffect){
            this.onColor = onColor;

            this.offColor = notColor;

            this.onEffect = onEffect;

            this.offEffect = offEffect;

            getChildren().addAll(polygons);






            getTransforms().add(new Shear(-0.1,0));

        	showNumber(0);


        }

        public void showNumber(int num) {
           if(0<=num && num <= 9){

            for (int i = 0; i < 7; i++) {

                polygons[i].setFill(DIGIT_COMBINATIONS[num][i] ? onColor : offColor);

                polygons[i].setEffect(DIGIT_COMBINATIONS[num][i] ? onEffect : offEffect);

            }
           }else if(num == HYPHEN){
        	   showHyphen();
           }else if(num == SPACE){
        	  showSpace();
           }else{

           }

        }



		public void showHyphen(){
			for(int i = 0; i< polygons.length;i++){
				polygons[i].setFill(offColor);
				polygons[i].setEffect(offEffect);
			}
			polygons[1].setFill(onColor);
			polygons[1].setEffect(onEffect);

		}
		public void showSpace(){

			for(int i = 0; i< polygons.length;i++){
				polygons[i].setFill(notColor);
				polygons[i].setEffect(offEffect);
			}

		}


	}





}
