package expansion.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.*;

public class ExpansionLiquids {
    public static Liquid
    steam, hypercoologen;
    public static void load(){
        steam = new Liquid("steam", Color.white){{
            temperature = water.temperature * 1.5f;
            gas = true;
        }};
        hypercoologen = new Liquid("hypercoologen", Color.valueOf("a9b7eb")){{
            temperature = cryofluid.temperature * 0.75f;
            heatCapacity = 1.1f;
            effect = ExpansionStatuses.hyperfreezing;
            lightColor = Color.valueOf("c2cfff").a(0.3f);
        }};
    }
}
