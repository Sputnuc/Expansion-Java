package expansion.content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import expansion.graphic.ExpPal;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;

public class ExpFx {
    public static final Rand rand = new Rand();

    public static final Effect
        trailSmoke = new ParticleEffect() {{
        particles = 2;
        length = 9;
        lifetime = 45;
        colorFrom = Color.valueOf("fff3eb");
        colorTo = Color.valueOf("e5915500");
        sizeFrom = 1.3f;
        sizeTo = 2.7f;
    }},
            tankSmoke = new MultiEffect(new ParticleEffect() {{
                cone = 55;
                particles = 8;
                baseLength = 4.5f;
                length = 21;
                sizeFrom = 4f;
                sizeTo = 0;
                colorFrom = Color.valueOf("e0e0e0");
                colorTo = Color.valueOf("94918f");
                lifetime = 35;
            }}),
            tankSmokeLen = new ParticleEffect() {{
                cone = 15;
                particles = 7;
                baseLength = 10;
                length = 41;
                sizeFrom = 3f;
                sizeTo = 0;
                colorFrom = Color.valueOf("e0e0e0");
                colorTo = Color.valueOf("94918f");
                lifetime = 25;
            }},
            yellowPlasmaExplosion = new MultiEffect(
                    new ParticleEffect() {{
                        particles = 9;
                        length = 19;
                        sizeFrom = 2;
                        sizeTo = 0;
                        colorFrom = Color.valueOf("f5db84");
                        colorTo = Color.valueOf("e8bf38");
                        lifetime = 35;
                    }},
                    new ParticleEffect() {{
                        particles = 5;
                        line = true;
                        length = 16;
                        lenFrom = 3;
                        lenTo = 5;
                        strokeFrom = 1.5f;
                        strokeTo = 0;
                        lifetime = 25;
                        colorFrom = Color.valueOf("f5db84");
                        colorTo = Color.valueOf("e8bf38");
                    }},
                    new WaveEffect() {{
                        sizeFrom = 3;
                        sizeTo = 11;
                        strokeFrom = 2;
                        strokeTo = 0;
                        lifetime = 25;
                        colorFrom = Color.valueOf("ffe799");
                        colorTo = Color.valueOf("face3c");
                    }}
            ),
            smokeTrailEffect = new ParticleEffect() {{
                particles = 2;
                length = 7.5f;
                sizeFrom = 1;
                sizeTo = 2.5f;
                colorFrom = Color.valueOf("fcefe6");
                colorTo = Color.valueOf("1a1a1a00");
                lifetime = 25;
                cone = 360;
            }},
            steamPressure = new ParticleEffect(){{
                particles = 2;
                length = 19;
                lifetime = 15;
                sizeFrom = 2;
                strokeTo = 0;
                cone = 360;
                colorFrom = colorTo = Color.white;
            }},
            tankFireShoot = new Effect(35, e -> {
                Draw.color(Color.valueOf("f7ddd2"), Color.valueOf("e8531300"), e.fin());
                randLenVectors(e.id, 6, 65 * e.fin(), e.rotation, 30f, (x, y) ->{
                    Fill.circle(e.x + x, e.y + y, 4.5f * e.fslope());
                });
                color();
                Drawf.light(e.x, e.y, 20f * e.fslope(), Color.valueOf("fa8039") ,0.4f);
            }),
            impulseTrail = new MultiEffect(
                    new Effect(30, e -> {
                        Draw.color(Color.valueOf("f5ec9f"), Color.valueOf("ebde6a"), e.fin());
                        Drawf.circles(e.x, e.y, 6 * e.fout());
                    }),
                    new Effect(40, e ->{
                        Draw.color(Color.valueOf("f5ec9f"), Color.valueOf("ebde6a"), e.fin());
                        randLenVectors(e.id , 5, 20 * e.fin(Interp.circleOut), e.rotation, 360, (x,y)->{
                            Fill.circle(e.x + x, e.y + y, 2.5f * e.fout(Interp.circleOut));
                        });
                        color();
                    })
            ),
            impulseExplosion = new MultiEffect(
                    new Effect(20, e->{
                        Draw.color(Color.valueOf("f5ec9f"), Color.valueOf("f5ec9f"), e.fin(Interp.sineOut));
                        stroke(e.fout()*2);
                        Lines.circle(e.x, e.y, 35 * e.fin(Interp.circleOut));
                        rand.setSeed(e.id);
                        for(int i = 0; i < 14; i++){
                            stroke(rand.random(0.8f, 1.4f));
                            float angle = rand.random(360f);
                            float lenRand = rand.random(0.5f, 1f);
                            Lines.lineAngle(e.x, e.y, angle, lenRand * 5 * e.fout(Interp.circleOut), 55f * e.fin() * lenRand);
                        };
                    }),
                    new Effect(20, e ->{
                        Draw.color(Color.valueOf("f5ec9f"), Color.valueOf("ebde6a"), e.fin());
                        randLenVectors(e.id , 7, 50f * e.fin(Interp.circleOut), e.rotation, 360, (x,y)->{
                            Fill.circle(e.x + x, e.y + y, 4.5f * e.fout(Interp.circleOut));
                        });
                        color();
                    })
            ),
            bigFireShoot = new MultiEffect(
                    new Effect(35, 300,e->{
                        color(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, e.fin());
                        randLenVectors(e.id, 19, e.finpow() * 16f * 13f * 1.2f, e.rotation, 5f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, 0.95f + e.fout(Interp.circleOut) + 0.3f);
                        });
                    })
            ),
            hyperFreeze = new Effect(45, 80, e->{
                color(ExpansionLiquids.hypercoologen.color);
                randLenVectors(e.id, 3, 3f + e.fin() * 1.25f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout() * 2f);
                });
            });

}
