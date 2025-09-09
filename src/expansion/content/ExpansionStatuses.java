package expansion.content;

import arc.graphics.Color;
import arc.struct.Seq;
import expansion.world.meta.ExpStat;
import mindustry.content.StatusEffects;
import mindustry.entities.units.*;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

import static mindustry.content.StatusEffects.*;

public class ExpansionStatuses {
    public static StatusEffect hyperfreezing;
    public static void load(){
        hyperfreezing = new StatusEffect("hyperfreezing"){{
            color = Color.valueOf("c2cfff");
            speedMultiplier = 0.4f;
            healthMultiplier = 0.95f;
            effect = ExpFx.hyperFreeze;
            show = true;
            init(() -> {
                opposite(melting, burning);
            });
        }};
    }
}
