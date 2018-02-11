package com.mac.spe.helpers;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 10/02/2018 at 02:15 PM.
 */
public class ColorHelper {
    
    public static int blend(int a, int b, float factor){
        if(a == 0 || b == 0) return 0;
        
        if(factor == 0) return a;
        else if(factor == 1) return b;
        
        if(factor > 1f) factor = 1f;
        else if(factor < 0f) factor = 0f;
        
        float iFactor = 1f - factor;
        
        int ar = ((a & 0xff0000) >> 16);
        int ag = ((a & 0xff00) >> 8);
        int ab = a & 0xff;

        int br = ((b & 0xff0000) >> 16);
        int bg = ((b & 0xff00) >> 8);
        int bb = b & 0xff;
        
        int re = (int) ((ar * iFactor) + (br * factor));
        int gr = (int) ((ag * iFactor) + (bg * factor));
        int bl = (int) ((ab * iFactor) + (bb * factor));
        
        return re << 16 | gr << 8 | bl;
    }
}
