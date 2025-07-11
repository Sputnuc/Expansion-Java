package expansion.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.*;

public class ExpansionLiquids {
    public static Liquid
    steam;
    public static void load(){
        steam = new Liquid("steam", Color.white){{
            temperature = water.temperature * 1.5f;
            gas = true;
        }};
    }
}
