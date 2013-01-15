package com.calcul.diabetif.commun.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BarCodeView extends View {

    final static int CODE128_STOP_SYMBOL = 211;

    final static String SEPARATOR= "01010";
    final static String START_AND_END= "101";

    private String mCodeEAN13String = "";
    //private char[] mCode128array = null;
    private float mCodeEAN13FontSize = 14;

    private float mUnitWidth;
    private boolean mDrawBarString;

    private Paint mPaint = new Paint();

    public BarCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCodeEAN13String = "";

        mUnitWidth = -1;
        mDrawBarString = true;
    }

    public void setCode128String(String code128String) {
        this.mCodeEAN13String = code128String;
        this.invalidate();
    }

    public String getCode128String() {
        return mCodeEAN13String;
    }

    public float getCode128FontSize() {
        return mCodeEAN13FontSize;
    }

    public void setCode128FontSize(float code128FontSize) {
        this.mCodeEAN13FontSize = code128FontSize;
        this.invalidate();
    }

    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        Log.d(this.getClass().getName(), "onDraw");
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        float textSize = 0;

//        if (mCode128array == null) {
//            compressCode128();
//        }

        if (mCodeEAN13String.length() > 0) {
            if (mDrawBarString) {
                float fontSize = mCodeEAN13FontSize;
                mPaint.setTextSize(fontSize);

                Rect rect = new Rect();

                mPaint.getTextBounds(mCodeEAN13String, 0, mCodeEAN13String.length(), rect);
                while (this.getWidth() < rect.width()) {
                    if (fontSize > 9) {
                        fontSize -= 0.5;

                        mPaint.setTextSize(fontSize);
                        mPaint.getTextBounds(mCodeEAN13String, 0, mCodeEAN13String.length(), rect);
                    } else {
                        break;
                    }
                }
            }
        }

        if (mCodeEAN13String != null) {
            String fullCodeString = "", codepart="";
            codepart=compressCode13(mCodeEAN13String);
            if(codepart.equals(null)|| codepart.equals(""))
            	return;
            
            fullCodeString=START_AND_END+codepart+START_AND_END;
            
 


            float unitLarg = getUnitWidth();
            float x = 0;
Log.i("CodeBare", fullCodeString);
            for (int i = 0; i < fullCodeString.length(); i++) {
            	
            	if((fullCodeString.charAt(i) - '0')==1)
            		canvas.drawRect(x, 0, (x + unitLarg), (float) this.getHeight() - textSize,
                            mPaint);
                // a number between 1 and 4
               // aLarg = fullCodeString.charAt(i) - '0';

                //compLarg = ((float) aLarg) * unitLarg;

//                if ((i % 2) == 0) {
//                    canvas.drawRect(x, 0, (x + compLarg), (float) this.getHeight() - textSize,
//                            mPaint);
//                }
                // increment the position
                x += unitLarg;
            }
        }
    }

    private float getUnitWidth() {
    	mUnitWidth = (float) this.getWidth() / (float) ((12 * 7) + 11);
//        mUnitWidth = (float) this.getWidth() / (float) ((mCode128array.length * 11) + 2);
        return mUnitWidth;
    }

    
    private String compressCode13(String chaine)
    {
        String codeListe ="";
        
        if (chaine.length() > 0)
        {
        	String	codeSequance= getCodingSequanceTable((int)(chaine.charAt(0)));
            for (int i =1; i<chaine.length() ;i++) {
                char	acar = chaine.charAt(i);
    			if (acar>57 && acar<48)
    				return null;
                
                if(i==7)
                	codeListe +=SEPARATOR;
                
                if (i>6) {
                    
                	codeListe+= getTableCPrintCodeSymbol(chaine.charAt(i));
                } else {
                   
                    if (codeSequance.charAt(i-1)==65) {
                    	codeListe+= getTableAPrintCodeSymbol(chaine.charAt(i)); 
                    } else {
                    	codeListe+= getTableBPrintCodeSymbol(chaine.charAt(i)); 
                       
                    }
                }
                
            }
            return codeListe;
        }
        return null;
    }
    
    
    
    public String getCodingSequanceTable(int codeSymbol)
    {
        switch(codeSymbol)
    	{
            case 48: return "AAAAAA";
            case 49: return "AABABB";
            case 50: return "AABBAB";
            case 51: return "AABBBA";
            case 52: return "ABAABB";
            case 53: return "ABBAAB";
            case 54: return "ABBBAA";
            case 55: return "ABABAB";
            case 56: return "ABABBA";
            case 57: return "ABBABA";
        }
        return null;
    }

    
    public String getTableAPrintCodeSymbol(int codeSymbol)
    {
    	switch(codeSymbol)
    	{
            case 48: return "0001101";
    		case 49: return "0011001";
    		case 50: return "0010011";
    		case 51: return "0111101";
    		case 52: return "0100011";
    		case 53: return "0110001";
    		case 54: return "0101111";
    		case 55: return "0111011";
    		case 56: return "0110111";
            case 57: return "0001011";
                
    	}
    	
    	return null;
    }


    public String getTableBPrintCodeSymbol(int codeSymbol)
    {
    	switch(codeSymbol)
    	{
            case 48: return "0100111";
    		case 49: return "0110011";
    		case 50: return "0011011";
    		case 51: return "0100001";
    		case 52: return "0011101";
    		case 53: return "0111001";
    		case 54: return "0000101";
    		case 55: return "0010001";
    		case 56: return "0001001";
            case 57: return "0010111";
                
    	}
    	
    	return null;
    }
    
    
    public String getTableCPrintCodeSymbol(int codeSymbol)
    {
    	switch(codeSymbol)
    	{
            case 48: return "1110010";
    		case 49: return "1100110";
    		case 50: return "1101100";
    		case 51: return "1000010";
    		case 52: return "1011100";
    		case 53: return "1001110";
    		case 54: return "1010000";
    		case 55: return "1000100";
    		case 56: return "1001000";
            case 57: return "1110100";
                
    	}
    	
    	return null;
    }
    
    
    
    
}
