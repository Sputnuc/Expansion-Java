package expansion.content;

import arc.graphics.Color;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;

public class ExpFx{
    public static final Effect
    trailSmoke = new ParticleEffect(){{
        particles = 2;
        length = 9;
        lifetime = 45;
        colorFrom = Color.valueOf("fff3eb");
        colorTo = Color.valueOf("e5915500");
        sizeFrom = 1.3f;
        sizeTo = 2.7f;
    }},
    tankSmoke = new MultiEffect(new ParticleEffect(){{
        cone = 55;
        particles = 8;
        baseLength = 4.5f;
        length = 21;
        sizeFrom = 4f;
        sizeTo = 0;
        colorFrom = Color.valueOf("e0e0e0");
        colorTo = Color.valueOf("94918f");
        lifetime = 35;
    }},
     new ParticleEffect(){{
         cone = 15;
         particles = 7;
         baseLength = 10;
         length = 38;
         sizeFrom = 3f;
         sizeTo = 0;
         colorFrom = Color.valueOf("e0e0e0");
         colorTo = Color.valueOf("94918f");
         lifetime = 25;
     }}
    );
}
