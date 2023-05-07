package me.lynix.villagerschedules.helpers;

import java.util.Objects;

public class Colors {
    public static final int lightGray = Integer.parseInt("bebebe", 16);
    public static final int lightRed = Integer.parseInt("db4f4f", 16);
    public static final int lightYellow = Integer.parseInt("e0ed53", 16);
    public static final int lightOrange = Integer.parseInt("edb753", 16);
    public static final int lightGreen = Integer.parseInt("3ade65", 16);
    public static final int lightBlue = Integer.parseInt("15e1f9", 16);
    public static final int white = 0x00E0E0E0;

    public static int getVillagerTextColor(String text)
    {
        if(Objects.equals(text, "Wander"))
        {
            return lightBlue;
        }
        if(Objects.equals(text, "Work"))
        {
            return lightGreen;
        }
        if(Objects.equals(text, "Gather"))
        {
            return lightYellow;
        }
        if(Objects.equals(text, "Sleep"))
        {
            return lightGray;
        }
        if(Objects.equals(text, "Play"))
        {
            return lightOrange;
        }
        return white;
    }
}
