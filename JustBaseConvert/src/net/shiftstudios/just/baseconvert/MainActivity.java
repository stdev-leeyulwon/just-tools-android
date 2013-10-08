package net.shiftstudios.just.baseconvert;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.*;

public class MainActivity extends Activity {
	
	public int					iBase = 2, oBase = 2;
	public int					w, h;
	public static long			maxValue = Long.MAX_VALUE;
	public long					in = 0, out = 0;
	public AlphaAnimation		a;
	public EditText				iUnparsed;
	public TextWatcher			watcher;
	public TextView				oParsed, iB1, oB1, convLimit;
	public static int			MAX_BASE = 36;
	public String[]				baseNames = {null,
		//1						2						3						4						5
		"Unary",				"Binary",				"Ternary",				"Quarternary",			"Quinary",
		//6						7						8						9						10
		"Senary",				"Septenary",			"Octal",				"Nonary",				"Decimal",
		//11					12						13						14						15
		"Undecimal",			"Duodecimal",			"Tridecimal",			"Tetradecimal",			"Pentadecimal",
		//16					17						18						19						20
		"Hexadecimal",			"Heptadecimal",			"Octadecimal",			"Nonadecimal",			"Vigesimal",
		//21					22						23						24						25
		"Unvigesimal",			"Duovigecimal",			"Trivigesimal",			"Quadrivigesimal",		"Quinquevigesimal",
		//26					27						28						29						30
		"Sexavigesimal",		"Septivigesimal",		"Octovigesimal",		"Novemvigesimal",		"Trigesimal",
		//31					32						33						34						35
		"Untrigesimal",			"Duotrigesimal",		"Tritrigesimal",		"Tetratrigesimal",		"Pentatrigesimal",
		//36					37						38						39						40
		"Hexatrigesimal",		"Septitrigesimal",		"Octotrigesimal",		"Novemtrigesimal",		"Quadragintimal",
		//41					42						43						44						45
		"Unquadragintimal",		"Duoquadragintimal",	"Triquadragintimal",	"Tetraquadragintimal",	"Pentaquadragintimal",
		//46					47						48						49						50
		"Hexaquadragintimal",	"Septiquadragintimal",	"Octoquadragintimal",	"Novemquadragintimal",	"Quinquagintimal",
		//51					52						53						54						55
		"Unquinquagintimal",	"Duoquinquagintimal",	"Triquinquagintimal",	"Tetraquinquagintimal",	"Pentaquinquagintimal",
		//56					57						58						59						60
		"Hexaquinquagintimal",	"Septiquinquagintimal",	"Octoquinquagintimal",	"Novemquinquagintimal",	"Sexagintimal",
		//61					62
		"Unsexagintimal",		"Duosexagintimal"
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();  // deprecated
		h = display.getHeight();  // deprecated
		
		a = new AlphaAnimation(0, 1);
		a.setDuration(166);
		
		iUnparsed		= (EditText)	findViewById(R.id.editText1);
		oParsed			= (TextView)	findViewById(R.id.textView10);
		iB1				= (TextView)	findViewById(R.id.textView2);
		oB1				= (TextView)	findViewById(R.id.textView6);
		convLimit		= (TextView)	findViewById(R.id.textView9);
		
		watcher = new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				updateU();
				
			}};
			
		iUnparsed.addTextChangedListener(watcher);
		
	}
	
	public void updateU(){
		if (iBase <= 36) {
			update();
		} else {
			update62();
		}
	}
	
	public String outBase(int i){
		if (i <= 10) {
			return baseNames[i] + "\nBase" + i + ", 0~" + (i - 1);
		} else if (i == 11) {
			return baseNames[i] + "\nBase" + i + ", 0~9, A";
		} else if (i <= 36) {
			return baseNames[i] + "\nBase" + i + ", 0~9, A~" + Integer.toString(i - 1, i).toUpperCase();
		} else if (i == 37) {
			return baseNames[i] + "\nBase" + i + ", 0~9, A~Z, a\n(Case sensitive)";
		} else {
			return baseNames[i] + "\nBase" + i + ", 0~9, A~Z, a~" + Integer.toString(i - 27, i - 26).toLowerCase() + "\n(Case sensitive)";
		}
	}
	
	public void iPlus(View v){
		iB1.startAnimation(a);
		if (iBase < MAX_BASE){
			iBase++;
			iB1.setText(outBase(iBase));
		}
		updateU();
	}
	
	public void iMinus(View v){
		iB1.startAnimation(a);
		if (iBase > 2){
			iBase--;
			iB1.setText(outBase(iBase));
		}
		updateU();
	}

	public void oPlus(View v){
		oB1.startAnimation(a);
		if (oBase < MAX_BASE){
			oBase++;
			oB1.setText(outBase(oBase));
		}
		updateU();
	}

	public void oMinus(View v){
		oB1.startAnimation(a);
		if (oBase > 2){
			oBase--;
			oB1.setText(outBase(oBase));
		}
		updateU();
	}
	
	public void update(){
		try {
			iUnparsed.removeTextChangedListener(watcher);
			in = Parse32(iUnparsed.getText().toString().toUpperCase(), iBase);
			oParsed.setText(toString32(in, oBase));
			convLimit.setText("Convert Limit: " + toString32(Long.MAX_VALUE, iBase).toUpperCase());
			iUnparsed.addTextChangedListener(watcher);
		} catch (Exception e) {
			iUnparsed.removeTextChangedListener(watcher);
			oParsed.setText("ERROR");
			convLimit.setText("Convert Limit: " + toString32(Long.MAX_VALUE, iBase).toUpperCase());
			iUnparsed.addTextChangedListener(watcher);
		}
	}
	
	public void update62(){
		try {
			iUnparsed.removeTextChangedListener(watcher);
			in = Parse62(iUnparsed.getText().toString(), iBase);
			oParsed.setText(toString62(in, oBase));
			convLimit.setText("Convert Limit: " + toString62(Long.MAX_VALUE, iBase));
			iUnparsed.addTextChangedListener(watcher);
		} catch (Exception e) {
			iUnparsed.removeTextChangedListener(watcher);
			oParsed.setText("ERROR");
			convLimit.setText("Convert Limit: " + toString62(Long.MAX_VALUE, iBase));
			iUnparsed.addTextChangedListener(watcher);
		}
	}
	
	public long Parse32(String e, int base){
		return Long.parseLong(e, base);
	}
	
	
	public long Parse62(String e, int base){
		long l = 0;
		for (int i = 0; i<e.length(); i++){
			if (replaceChar(e.charAt(i)) != -1){
				l = l * base + replaceChar(e.charAt(i));
			} else {
				return 0;
			}
		}
		return l;
	}
	
	public String toString32(long l, int i){
		return Long.toString(l, i);
	}
	
	public String toString62(long l, int i){
		String s = "";
		int j;
		for (j = 0; j < 100; j++){
			if (l <= Math.pow(i, j + 1)){
				break;
			}
		}
		
		for(int k = 0; k < j; k++){
			if (charReplace((int)l % i) != (char)255){
				s = s + charReplace((int)l % i);
				l = (long) (l - (l % i) * Math.pow(i, j + 1 - k));
			} else {
				return s;
			}
		}
		return s;
	}
	
	public int replaceChar(char c){
		if ((int) c >= 48 && (int) c <= 57) {
			return (int) c - 48;
		} else if ((int) c >= 65 && (int) c <= 90) {
			return (int) c - 65 + 10;
		} else if ((int) c >= 97 && (int) c <= 122) {
			return (int) c - 97 + 36;
		} else {
			return -1;
		}
	}
	
	public char charReplace(int c){
		if ((int) c >= 0 && (int) c <= 9) {
			return (char) (c + 48);
		} else if ((int) c >= 10 && (int) c <= 36) {
			return (char) ((int) c + 65 - 10);
		} else if ((int) c >= 37 && (int) c <= 62) {
			return (char) ((int) c + 97 - 36);
		} else {
			return (char) 255;
		}
	}
}
