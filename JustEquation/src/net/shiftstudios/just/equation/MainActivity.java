package net.shiftstudios.just.equation;

import net.shiftstudios.math.Complex;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public Button uiPlus, uiMinus, uiPow, uiBacksp,
	ui1, ui2, ui3, ui4, ui5, ui6, ui7, ui8, ui9, ui0,
	uiEqu, uiX, uiI, uiDot, uiSolve;
	
	public TextView uiEqInput, uiEqShow;
	
	public String curEqu = "";
	public String unLeft, unRight;
	public double[] left = new double[6], right = new double[6], equation = new double[6];
	public String tempEqu = "";
	public int max = 1;
	
	public Typeface Din;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Din = Typeface.createFromAsset(getAssets(), "fonts/DINNextLTPro-Regular.otf");
		
		uiPlus		= (Button)		findViewById(R.id.button1);
		uiMinus		= (Button)		findViewById(R.id.button2);
		uiPow		= (Button)		findViewById(R.id.button3);
		uiBacksp	= (Button)		findViewById(R.id.button4);
		ui1			= (Button)		findViewById(R.id.button13);
		ui2			= (Button)		findViewById(R.id.button14);
		ui3			= (Button)		findViewById(R.id.button15);
		ui4			= (Button)		findViewById(R.id.button9);
		ui5			= (Button)		findViewById(R.id.button10);
		ui6			= (Button)		findViewById(R.id.button11);
		ui7			= (Button)		findViewById(R.id.button5);
		ui8			= (Button)		findViewById(R.id.button6);
		ui9			= (Button)		findViewById(R.id.button7);
		ui0			= (Button)		findViewById(R.id.button17);
		uiEqu		= (Button)		findViewById(R.id.button8);
		uiX			= (Button)		findViewById(R.id.button12);
		uiI			= (Button)		findViewById(R.id.button16);
		uiDot		= (Button)		findViewById(R.id.button18);
		uiSolve		= (Button)		findViewById(R.id.button19);
		
		uiEqInput	= (TextView)	findViewById(R.id.textView1);
		uiEqInput.setTypeface(Din);
		uiEqShow	= (TextView)	findViewById(R.id.textView2);
		uiEqShow.setTypeface(Din);
		
		uiPow		.setVisibility(View.INVISIBLE);
		uiPlus		.setVisibility(View.INVISIBLE);
		uiEqu		.setVisibility(View.INVISIBLE);
		uiSolve		.setVisibility(View.INVISIBLE);
		uiBacksp	.setVisibility(View.INVISIBLE);
	}
	
	public void solve(){
		
		left[5] = 0;
		left[4] = 0;
		left[3] = 0;
		left[2] = 0;
		left[1] = 0;
		left[0] = 0;
		
		right[5] = 0;
		right[4] = 0;
		right[3] = 0;
		right[2] = 0;
		right[1] = 0;
		right[0] = 0;
		
		String[] Splitted = curEqu.split(" = ");
		unLeft = Splitted[0].replaceAll("\\^", "ow");
		unRight = Splitted[1].replaceAll("\\^", "ow");
		
		unLeft = unLeft.replaceAll(" \\+ ", "p").replaceAll(" - ", "p-");
		String[] leftSplitted = unLeft.split("p");
		for(int i = 0; i < leftSplitted.length; i++){
			String curP = leftSplitted[i];
			if (curP.contains("xow5")) {
				Log.d(curP, "5");
				if (curP.replaceAll("xow5", "").length() != 0) {
					left[5] += Double.parseDouble(curP.replaceAll("xow5", ""));
				} else {
					left[5] += 1;
				}
			} else if (curP.contains("xow4")) {
				Log.d(curP, "4");
				if (curP.replaceAll("xow4", "").length() != 0) {
					left[4] += Double.parseDouble(curP.replaceAll("xow4", ""));
				} else {
					left[4] += 1;
				}
			} else if (curP.contains("xow3")) {
				Log.d(curP, "3");
				if (curP.replaceAll("xow3", "").length() != 0) {
					left[3] += Double.parseDouble(curP.replaceAll("xow3", ""));
				} else {
					left[3] += 1;
				}
			} else if (curP.contains("xow2")) {
				Log.d(curP, "2");
				if (curP.replaceAll("xow2", "").length() != 0) {
					left[2] += Double.parseDouble(curP.replaceAll("xow2", ""));
				} else {
					left[2] += 1;
				}
			} else if (curP.contains("xow1")) {
				Log.d(curP, "1");
				if (curP.replaceAll("xow1", "").length() != 0) {
					left[1] += Double.parseDouble(curP.replaceAll("xow1", ""));
				} else {
					left[1] += 1;
				}
			} else if (curP.contains("x")) {
				Log.d(curP, "1x");
				if (curP.replaceAll("x", "").length() != 0) {
					left[1] += Double.parseDouble(curP.replaceAll("x", ""));
				}
			} else {
				left[0] += Double.parseDouble(curP);
			}
		}
		
		unRight = unRight.replaceAll(" \\+ ", "p\\+").replaceAll(" - ", "p-");
		String[] rightSplitted = unRight.split("p");
		for(int i = 0; i < rightSplitted.length; i++){
			String curP = rightSplitted[i];
			if (curP.contains("xow5")) {
				if (curP.replaceAll("xow5", "").length() != 0) {
					right[5] += Double.parseDouble(curP.replaceAll("xow5", ""));
				} else {
					right[5] += 1;
				}
			} else if (curP.contains("xow4")) {
				if (curP.replaceAll("xow4", "").length() != 0) {
					right[4] += Double.parseDouble(curP.replaceAll("xow4", ""));
				} else {
					right[4] += 1;
				}
			} else if (curP.contains("xow3")) {
				if (curP.replaceAll("xow3", "").length() != 0) {
					right[3] += Double.parseDouble(curP.replaceAll("xow3", ""));
				} else {
					right[3] += 1;
				}
			} else if (curP.contains("xow2")) {
				if (curP.replaceAll("xow2", "").length() != 0) {
					right[2] += Double.parseDouble(curP.replaceAll("xow2", ""));
				} else {
					right[2] += 1;
				}
			} else if (curP.contains("xow1")) {
				if (curP.replaceAll("xow1", "").length() != 0) {
					right[1] += Double.parseDouble(curP.replaceAll("xow1", ""));
				} else {
					right[1] += 1;
				}
			} else if (curP.contains("x")) {
				if (curP.replaceAll("x", "").length() != 0) {
					right[1] += Double.parseDouble(curP.replaceAll("x", ""));
				}
			} else {
				right[0] += Double.parseDouble(curP);
			}
		}
		
		equation[5] = left[5] - right[5]; Log.d(equation[5] + "", left[5] + ", " + right[5] + "");
		equation[4] = left[4] - right[4]; Log.d(equation[4] + "", left[4] + ", " + right[4] + "");
		equation[3] = left[3] - right[3]; Log.d(equation[3] + "", left[3] + ", " + right[3] + "");
		equation[2] = left[2] - right[2]; Log.d(equation[2] + "", left[2] + ", " + right[2] + "");
		equation[1] = left[1] - right[1]; Log.d(equation[1] + "", left[1] + ", " + right[1] + "");
		equation[0] = left[0] - right[0]; Log.d(equation[0] + "", left[0] + ", " + right[0] + "");
		
		uiEqShow.setText(curEqu.replaceAll("\\^1", "")
				.replaceAll("\\^2", "²")
				.replaceAll("\\^3", "³")
				.replaceAll("\\^4", "⁴")
				.replaceAll("\\^5", "⁵")+ "\n" +
				equString() + " = 0");
		
		Log.d("MAX", getMax() + "");
		
		int gotMax = getMax();
		
		if (gotMax == 5) {
			Calc5();
		} else if (gotMax == 4) {
			Calc4();
		} else if (gotMax == 3) {
			Calc3();
		} else if (gotMax == 2) {
			Calc2();
		} else if (gotMax == 1) {
			Calc1();
		}
	}
	
	public int getMax(){
	if (equation[5] == 0){	
		if (equation[4] == 0){
			if (equation[3] == 0){
				if (equation[2] == 0){
					if (equation[1] == 0){
						return 0;
					} else {
						return 1;
					}
				} else {
					return 2;
				}
			} else {
				return 3;
			}
		} else {
			return 4;
		}
	} else {
		return 5;
	}
	}
	
	public void Calc1(){
		double a;
		double b;
		
		a = equation[1];
		b = equation[0];
		
		if (b == 0) {
			uiEqInput.setText("x = Anything");
		}
		
		uiEqInput.setText("x = " + (- b/a));
	}
	
	public void Calc2(){
		double a;
		double b;
		double c;
		
		a = equation[2];
		b = equation[1];
		c = equation[0];
		
		double D = b * b - 4 * a * c;
		Complex d = new Complex(D).sqrt();
		
		if (D == 0) {
			uiEqInput.setText("x = " + (- b/(2 * a)));
		} else {
			uiEqInput.setText("x = " + (new Complex(-b).plus(d)).divide(2 * a).toString() + ",\n"
					+ (new Complex(-b).minus(d)).divide(2 * a).toString());
		}
	}
	
	public void Calc3(){
		double a = equation[3];
		double b = equation[2];
		double c = equation[1];
		double d = equation[0];
		
		double Fi = (((3*c)/a - Math.pow((b/a), 2)))/3;
		double Gi = (((2*b*b*b)/(a*a*a)) - ((9*b*c)/(a*a)) + ((27*d)/a))/27;
		double Hi = ((Gi*Gi)/4)+((Fi*Fi*Fi)/27);
		Complex Ri = new Complex(-(Gi/2)).plus(new Complex(Hi).sqrt());
		Complex Si = Ri.cbrt();
		Complex Ti = new Complex(-(Gi/2)).minus(new Complex(Hi).sqrt());
		Complex Ui = Ti.cbrt();
		
		Complex I = new Complex(0, 1);
		
		Complex alp = Si.plus(Ui).minus(new Complex(b / (3*a)));
		Complex bet = ((Si.plus(Ui)).divide(-2)).minus(new Complex(b/(3*a))).plus(((Si.minus(Ui)).times(new Complex(Math.sqrt(3)/2))).times(I));
		Complex gam = ((Si.plus(Ui)).divide(-2)).minus(new Complex(b/(3*a))).plus(((Ui.minus(Si)).times(new Complex(Math.sqrt(3)/2))).times(I));

		uiEqInput.setText("x = " + alp.toString() + ",\n" + bet.toString() + ",\n" + gam.toString());
	}
	
	public Complex[] Calc3a(double a, double b, double c, double d){
		
		Complex[] comp = new Complex[3];
		
		double Fi = (((3*c)/a - Math.pow((b/a), 2)))/3;
		double Gi = (((2*b*b*b)/(a*a*a)) - ((9*b*c)/(a*a)) + ((27*d)/a))/27;
		double Hi = ((Gi*Gi)/4)+((Fi*Fi*Fi)/27);
		Complex Ri = new Complex(-(Gi/2)).plus(new Complex(Hi).sqrt());
		Complex Si = Ri.cbrt();
		Complex Ti = new Complex(-(Gi/2)).minus(new Complex(Hi).sqrt());
		Complex Ui = Ti.cbrt();
		
		Complex I = new Complex(0, 1);
		
		comp[0] = Si.plus(Ui).minus(new Complex(b / (3*a)));
		comp[1] = ((Si.plus(Ui)).divide(-2)).minus(new Complex(b/(3*a))).plus(((Si.minus(Ui)).times(new Complex(Math.sqrt(3)/2))).times(I));
		comp[2] = ((Si.plus(Ui)).divide(-2)).minus(new Complex(b/(3*a))).plus(((Ui.minus(Si)).times(new Complex(Math.sqrt(3)/2))).times(I));
		
		return comp;
	}
	
	public void Calc4(){
		double a = equation[4];
		double b = equation[3];
		double c = equation[2];
		double d = equation[1];
		double e = equation[0];
		
		double am = 1;
		double bm = b / a;
		double cm = c / a;
		double dm = d / a;
		double em = e / a;
				
		double f = cm - (3*bm*bm / 8);
		double g = dm + (bm*bm*bm / 8) - ((bm*cm) / 2);
		double h = em - ((3*bm*bm*bm*bm) / 256) + ((bm*bm*cm) / 16) - ((bm*dm) / 4);
		
		Complex alp3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		Complex bet3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		Complex gam3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		
		Complex alp, bet, gam, del;
		
		Log.d(alp3.toString(), bet3.toString() + "     " + gam3.toString());
		
		if (!alp3.toString().equals("0") && !bet3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "ab");
			Complex p = alp3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!alp3.toString().equals("0") && !bet3.toString().equals("0")){
			Log.d("PICK", "ab");
			Complex p = alp3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!bet3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "bc");
			Complex p = gam3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!alp3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "ac");
			Complex p = alp3.sqrt();
			Complex q = gam3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else {
			Log.d("PICK", "N O N E");
			Complex p = new Complex(e).sqrt().sqrt(0);
			if (d >= 0) {
				p = new Complex(e).sqrt().sqrt().times(-1);
			} else {
				p = new Complex(e).sqrt().sqrt();
			}
			
			alp = p;
			bet = p;
			gam = p;
			del = p;
		}
		
		uiEqInput.setText("x = " + alp.toString() + ",\n" + bet.toString() + ",\n" + gam.toString() + ",\n" + del.toString());
	}
	
	public Complex[] Calc4a(double a, double b, double c, double d, double e){
		Complex[] comp = new Complex[4];
		
		double am = 1;
		double bm = b / a;
		double cm = c / a;
		double dm = d / a;
		double em = e / a;
				
		double f = cm - (3*bm*bm / 8);
		double g = dm + (bm*bm*bm / 8) - ((bm*cm) / 2);
		double h = em - ((3*bm*bm*bm*bm) / 256) + ((bm*bm*cm) / 16) - ((bm*dm) / 4);
		
		Complex alp3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		Complex bet3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		Complex gam3 = Calc3a(1, (f/2), ((f*f - 4*h)/16), -(g*g)/64)[0];
		
		Complex alp, bet, gam, del;
		
		Log.d(alp3.toString(), bet3.toString() + "     " + gam3.toString());
		
		if (!alp3.toString().equals("0") && !bet3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "ab");
			Complex p = alp3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!alp3.toString().equals("0") && !bet3.toString().equals("0")){
			Log.d("PICK", "ab");
			Complex p = alp3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!bet3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "bc");
			Complex p = gam3.sqrt();
			Complex q = bet3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else if (!alp3.toString().equals("0") && !gam3.toString().equals("0")){
			Log.d("PICK", "ac");
			Complex p = alp3.sqrt();
			Complex q = gam3.sqrt();
			Complex r = new Complex(-g).divide(p.times(q).times(8));
			Complex s = new Complex(bm / (4*am));
			
			alp = p				.plus(q)	.plus(r)	.minus(s);
			bet = p				.minus(q)	.minus(r)	.minus(s);
			gam = p	.times(-1)	.plus(q)	.minus(r)	.minus(s);
			del = p	.times(-1)	.minus(q)	.plus(r)	.minus(s);
		} else {
			Log.d("PICK", "N O N E");
			Complex p = new Complex(e).sqrt().sqrt(0);
			if (d >= 0) {
				p = new Complex(e).sqrt().sqrt().times(-1);
			} else {
				p = new Complex(e).sqrt().sqrt();
			}
			
			alp = p;
			bet = p;
			gam = p;
			del = p;
		}
		
		comp[0] = alp;
		comp[1] = bet;
		comp[2] = gam;
		comp[3] = del;
		
		return comp;
	}
	
	public void Calc5(){
		double[] eqPrime = new double[5];
		double[] reduced = new double[5];
		
		eqPrime[0] = equation[1];
		eqPrime[1] = equation[2] * 2;
		eqPrime[2] = equation[3] * 3;
		eqPrime[3] = equation[4] * 4;
		eqPrime[4] = equation[5] * 5;
		
		double x = 10;
		
		for(int i = 0; i < 20; i++){
			x = x - ((insert5(equation[5],
					equation[4],
					equation[3],
					equation[2],
					equation[1],
					equation[0], x))
					/(insert4(eqPrime[4],
							eqPrime[3],
							eqPrime[2],
							eqPrime[1],
							eqPrime[0], x)));
		}
		
		reduced[4] = equation[5];
		reduced[3] = equation[4] + reduced[4] * x;
		reduced[2] = equation[3] + reduced[3] * x;
		reduced[1] = equation[2] + reduced[2] * x;
		reduced[0] = equation[1] + reduced[1] * x;
		
		Complex alp = new Complex(x);
		Complex bet = Calc4a(reduced[4], reduced[3], reduced[2], reduced[1], reduced[0])[0];
		Complex gam = Calc4a(reduced[4], reduced[3], reduced[2], reduced[1], reduced[0])[1];
		Complex del = Calc4a(reduced[4], reduced[3], reduced[2], reduced[1], reduced[0])[2];
		Complex eps = Calc4a(reduced[4], reduced[3], reduced[2], reduced[1], reduced[0])[3];
		
		uiEqInput.setText("x = " + alp.toString() + ",\n" + bet.toString() + ",\n" + gam.toString() + ",\n" + del.toString() + ",\n" + eps.toString());
	}
	
	public double insert4(double a, double b, double c, double d, double e, double x){
		return a*x*x*x*x + b*x*x*x + c*x*x + d*x + e;
	}
	
	public double insert5(double a, double b, double c, double d, double e, double f, double x){
		return a*x*x*x*x*x + b*x*x*x*x + c*x*x*x + d*x*x + e*x + f;
	}
	
	public String equString(){
		String s = "";
		max = 5;
		if (equation[5] < 0) {
			s = " - " + m(equation[5]) + "x⁵";
		} else if (equation[5] > 0) {
			s = m(equation[5]) + "x⁵";
		} else if (equation[5] == 0) {
			max = 4;
		}
		
		if (equation[4] < 0) {
			s = s + " - " + m(equation[4]) + "x⁴";
		} else if (max != 4 && equation[4] > 0) {
			s = s + " + " + m(equation[4]) + "x⁴";
		} else if (max == 4 && equation[4] > 0) {
			s = m(equation[4]) + "x⁴";
		} else if (equation[4] == 0){
			max = 3;
		}
	 	
		if (equation[3] < 0) {
			s = s + " - " + m(equation[3]) + "x³";
		} else if (max != 3 && equation[3] > 0) {
			s = s + " + " + m(equation[3]) + "x³";
		} else if (max == 3 && equation[3] > 0) {
			s = m(equation[3]) + "x³";
		} else if (equation[3] == 0){
			max = 2;
		}
		
		if (equation[2] < 0) {
			s = s + " - " + m(equation[2]) + "x²";
		} else if (max != 2 && equation[2] > 0) {
			s = s + " + " + m(equation[2]) + "x²";
		} else if (max == 2 && equation[2] > 0) {
			s = m(equation[2]) + "x²";
		} else if (equation[2] == 0){
			max = 1;
		}
		
		if (equation[1] < 0) {
			s = s + " - " + m(equation[1]) + "x";
		} else if (max != 1 && equation[1] > 0) {
			s = s + " + " + m(equation[1]) + "x";
		} else if (max == 1 && equation[1] > 0) {
			s = m(equation[1]) + "x";
		} else if (equation[1] == 0){
			max = 0;
		}
		
		if (equation[0] < 0) {
			s = s + " - " + m(equation[0]);
		} else if (max != 1 && equation[0] > 0) {
			s = s + " + " + m(equation[0]);
		} else if (max == 1 && equation[0] > 0) {
			s = m(equation[0]) + "";
		}
		
		return s;
	}
	
	public double m(double d){
    	if (Math.abs(d - Math.rint(d)) < 0.001) {
    		return Math.abs(Math.rint(d));
    	} else {
    		return Math.abs(Math.rint(d * 10000000)/10000000);
    	}
	}
	
	public void allVisible(){
		uiPlus		.setVisibility(View.VISIBLE);
		uiMinus		.setVisibility(View.VISIBLE);
		uiPow		.setVisibility(View.VISIBLE);
		ui1			.setVisibility(View.VISIBLE);
		ui2			.setVisibility(View.VISIBLE);
		ui3			.setVisibility(View.VISIBLE);
		ui4			.setVisibility(View.VISIBLE);
		ui5			.setVisibility(View.VISIBLE);
		ui6			.setVisibility(View.VISIBLE);
		ui7			.setVisibility(View.VISIBLE);
		ui8			.setVisibility(View.VISIBLE);
		ui9			.setVisibility(View.VISIBLE);
		ui0			.setVisibility(View.VISIBLE);
		uiEqu		.setVisibility(View.VISIBLE);
		uiBacksp	.setVisibility(View.VISIBLE);
		uiX			.setVisibility(View.VISIBLE);
		uiI			.setVisibility(View.VISIBLE);
		uiDot		.setVisibility(View.VISIBLE);
		uiSolve		.setVisibility(View.VISIBLE);
	}
	
	public void onPow(){
		uiPlus		.setVisibility(View.INVISIBLE);
		uiMinus		.setVisibility(View.INVISIBLE);
		uiPow		.setVisibility(View.INVISIBLE);
		ui6			.setVisibility(View.INVISIBLE);
		ui7			.setVisibility(View.INVISIBLE);
		ui8			.setVisibility(View.INVISIBLE);
		ui9			.setVisibility(View.INVISIBLE);
		ui0			.setVisibility(View.INVISIBLE);
		uiEqu		.setVisibility(View.INVISIBLE);
		uiX			.setVisibility(View.INVISIBLE);
		uiDot		.setVisibility(View.INVISIBLE);
		uiSolve		.setVisibility(View.INVISIBLE);
	}
	
	public void onBackSp(){
		if (curEqu.length() == 0){
			uiBacksp	.setVisibility(View.INVISIBLE);
		} else if (curEqu.length() == 1){
			curEqu = curEqu.substring(0, curEqu.length() - 1);
			uiBacksp	.setVisibility(View.INVISIBLE);
			uiPow		.setVisibility(View.INVISIBLE);
			uiPlus		.setVisibility(View.INVISIBLE);
			uiEqu		.setVisibility(View.INVISIBLE);
			uiSolve		.setVisibility(View.INVISIBLE);
			uiBacksp	.setVisibility(View.INVISIBLE);
		} else if (curEqu.charAt(curEqu.length() - 1) == 0x20 && curEqu.length() != 3) {
			curEqu = curEqu.substring(0, curEqu.length() - 3);
			update();
		}  else if (curEqu.charAt(curEqu.length() - 1) == 0x20) {
			curEqu = curEqu.substring(0, curEqu.length() - 3);
			uiBacksp	.setVisibility(View.INVISIBLE);
		} else {
			curEqu = curEqu.substring(0, curEqu.length() - 1);
			update();
		}
	}
	
	public void update(){
		if (curEqu.charAt(curEqu.length() - 1) == 0x5E) {
			onPow();
		} else if (curEqu.length() >= 2 && curEqu.charAt(curEqu.length() - 2) == 0x5E) {
			onNumberPow();
		} else if (isInt(curEqu.charAt(curEqu.length() - 1))){
			onNumber();
		} else if (curEqu.charAt(curEqu.length() - 1) == 0x78){
			onX();
		} else if (curEqu.length() >= 2 && curEqu.charAt(curEqu.length() - 2) == 0x2B){
			onPm();
		} else if (curEqu.length() >= 2 && curEqu.charAt(curEqu.length() - 2) == 0x2D){
			onPm();
		} else if (curEqu.length() >= 2 && curEqu.charAt(curEqu.length() - 2) == 0x3D){
			onEqu();
		}
	}
	
	public boolean isInt(char c){
		try {
			Integer.parseInt("" + c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean containsEq(){
		if (curEqu.contains(" = ")) {
			return true;
		}
		return false;
	}
	
	public void onNumber(){
		if (curEqu.length() == 0){
			uiPow		.setVisibility(View.INVISIBLE);
			uiPlus		.setVisibility(View.INVISIBLE);
			uiEqu		.setVisibility(View.INVISIBLE);
		} else if (curEqu.charAt(curEqu.length() - 1) == 0x5E) {
			onNumberPow();
		} else if (curEqu.length() == 1){
			uiPow		.setVisibility(View.INVISIBLE);
			uiPlus		.setVisibility(View.INVISIBLE);
		} else {
			uiPow		.setVisibility(View.INVISIBLE);
			uiPlus		.setVisibility(View.INVISIBLE);
			uiMinus		.setVisibility(View.INVISIBLE);
		}
	}
	
	public void onNumberPow(){
		ui1			.setVisibility(View.INVISIBLE);
		ui2			.setVisibility(View.INVISIBLE);
		ui3			.setVisibility(View.INVISIBLE);
		ui4			.setVisibility(View.INVISIBLE);
		ui5			.setVisibility(View.INVISIBLE);
		ui6			.setVisibility(View.INVISIBLE);
		ui7			.setVisibility(View.INVISIBLE);
		ui8			.setVisibility(View.INVISIBLE);
		ui9			.setVisibility(View.INVISIBLE);
		ui0			.setVisibility(View.INVISIBLE);
		uiX			.setVisibility(View.INVISIBLE);
		uiDot		.setVisibility(View.INVISIBLE);
		uiPow		.setVisibility(View.INVISIBLE);
	}
	
	public void onPm(){
		uiPlus		.setVisibility(View.INVISIBLE);
		uiMinus		.setVisibility(View.INVISIBLE);
		uiPow		.setVisibility(View.INVISIBLE);
		uiEqu		.setVisibility(View.INVISIBLE);
	}
	
	public void onX(){
		ui1			.setVisibility(View.INVISIBLE);
		ui2			.setVisibility(View.INVISIBLE);
		ui3			.setVisibility(View.INVISIBLE);
		ui4			.setVisibility(View.INVISIBLE);
		ui5			.setVisibility(View.INVISIBLE);
		ui6			.setVisibility(View.INVISIBLE);
		ui7			.setVisibility(View.INVISIBLE);
		ui8			.setVisibility(View.INVISIBLE);
		ui9			.setVisibility(View.INVISIBLE);
		ui0			.setVisibility(View.INVISIBLE);
		uiX			.setVisibility(View.INVISIBLE);
		uiDot		.setVisibility(View.INVISIBLE);
	}
	
	public void onEqu(){
		uiPow		.setVisibility(View.INVISIBLE);
		uiPlus		.setVisibility(View.INVISIBLE);
		uiEqu		.setVisibility(View.INVISIBLE);
		uiSolve		.setVisibility(View.INVISIBLE);
	}
	
	public void buttonPress(View v){
		allVisible();
		
		if (containsEq()) {
			uiEqu		.setVisibility(View.INVISIBLE);
		} else {
			uiSolve		.setVisibility(View.INVISIBLE);
		}
		switch(v.getId()){
		case R.id.button1: // +
			curEqu = curEqu + " + ";
			onPm();
			sT();
			break;
		case R.id.button2: // -
			curEqu = curEqu + " - ";
			onPm();
			sT();
			break;
		case R.id.button3: // ^
			curEqu = curEqu + "^";
			onPow();
			sT();
			break;
		case R.id.button4: // backspace
			onBackSp();
			sT();
			break;
		case R.id.button5: // 7
			onNumber();
			curEqu = curEqu + "7";
			sT();
			break;
		case R.id.button6: // 8
			onNumber();
			curEqu = curEqu + "8";
			sT();
			break;
		case R.id.button7: // 9
			onNumber();
			curEqu = curEqu + "9";
			sT();
			break;
		case R.id.button8: // =
			curEqu = curEqu + " = ";
			sT();
			onEqu();
			break;
		case R.id.button9: // 4
			onNumber();
			curEqu = curEqu + "4";
			sT();
			break;
		case R.id.button10: // 5
			onNumber();
			curEqu = curEqu + "5";
			sT();
			break;
		case R.id.button11: // 6
			onNumber();
			curEqu = curEqu + "6";
			sT();
			break;
		case R.id.button12: // x
			onX();
			curEqu = curEqu + "x";
			sT();
			break;
		case R.id.button13: // 1
			onNumber();
			curEqu = curEqu + "1";
			sT();
			break;
		case R.id.button14: // 2
			onNumber();
			curEqu = curEqu + "2";
			sT();
			break;
		case R.id.button15: // 3
			onNumber();
			curEqu = curEqu + "3";
			sT();
			break;
		case R.id.button16: // i
			// curEqu = curEqu + "i";
			break;
		case R.id.button17: // 0
			onNumber();
			curEqu = curEqu + "0";
			sT();
			break;
		case R.id.button18: // .
			curEqu = curEqu + ".";
			sT();
			break;
		case R.id.button19: // solve
			solve();
			break;
		}
		
		
	}
	
	public void sT(){
		uiEqInput.setText(curEqu);
	}
}
