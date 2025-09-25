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
    public static float sin(float rotation, float offset){
        float angleRad = rotation * Mathf.degRad;
        return Mathf.sin(angleRad) * offset;
    }
    public static float cos(float rotation, float offset){
        float angleRad = rotation * Mathf.degRad;
        return Mathf.cos(angleRad) * offset;
    }
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
            }),
            inclinedWave = new Effect(25, 80, e->{
                Draw.color(e.color, e.color.a(0), e.fin());
                stroke(e.fout()*1.2f + 0.5f);
                float baseOffset = 2f;
                float forwardOffset = e.fin() * -1f;

                float offsetX = cos(e.rotation, baseOffset);
                float offsetY = sin(e.rotation, baseOffset);
                float oX = cos(e.rotation, forwardOffset);
                float oY = sin(e.rotation, forwardOffset);

                Lines.ellipse(e.x + offsetX + oX, e.y + offsetY + oY, 0.65f * e.fin()+ 0.45f, 4, 8, e.rotation);
            }),
            hitBigBulletColor = new Effect(14, e -> {
                color(Color.white, e.color, e.fin());

                e.scaled(7f, s -> {
                    stroke(0.7f + s.fout());
                    Lines.circle(e.x, e.y, s.fin() * 20f);
                });

                stroke(0.5f + e.fout());

                randLenVectors(e.id, 8, e.fin() * 25f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
                });
                randLenVectors(e.id, 8, e.fin(Interp.circleOut) * 65f, e.rotation, 160, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fout() * 2f);
                });

                Drawf.light(e.x, e.y, 20f, e.color, 0.6f * e.fout());
            }),
            hitBigFire = new Effect( 35, e -> {
                color(Pal.lightishOrange, Pal.lightOrange, e.fin());
                stroke(1.4f * e.fout() + 0.5f);
                Lines.circle(e.x, e.y, 25 * e.fin(Interp.circleOut));
                randLenVectors(e.id, 7, e.fin(Interp.circleOut) * 65, e.rotation, 360, (x,y) ->{
                    Fill.circle(e.x + x, e.y + y, e.fout() * 2f);
                });
                randLenVectors(e.id+1, 6, e.fin(Interp.circleOut) * 35, e.rotation, 360, (x,y) ->{
                    Fill.circle(e.x + x, e.y + y, e.fout() * 3f);
                });
                randLenVectors(e.id+2, 4, e.fin() * 25f, (x, y) -> {
                    float ang = Mathf.angle(x, y);
                    lineAngle(e.x + x, e.y + y, ang, e.fout() * 3.5f + 2f);
                });
            }),
            bitRailTrailColor = new Effect(30, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? e.color : Color.white);

                    float m = i == 0 ? 1f : 0.65f;

                    float rot = e.rotation + 180f;
                    float w = 18f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, e.color, 0.6f * e.fout());
            }),
            bitRailTrailCobalt = new Effect(30, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? ExpPal.cobaltPal : Color.white);

                    float m = i == 0 ? 1f : 0.65f;

                    float rot = e.rotation + 180f;
                    float w = 18f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, ExpPal.cobaltPal, 0.6f * e.fout());
            }),
            instBigShootColor = new Effect(24f, e -> {
                e.scaled(10f, b -> {
                    color(Color.white, e.color, b.fin());
                    stroke(b.fout() * 3f + 0.2f);
                    Lines.circle(b.x, b.y, b.fin() * 55f);
                    Lines.circle(b.x, b.y, b.fin() * 85f + 5f);
                });

                color(e.color);

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 15f * e.fout(), 95f, e.rotation + 90f * i);
                    Drawf.tri(e.x, e.y, 15f * e.fout(), 60f, e.rotation + 20f * i);
                }

                Drawf.light(e.x, e.y, 210f, e.color, 0.9f * e.fout());
            }),

    instBigHitColor = new Effect(20f, 200f, e -> {
        color(e.color);

        for(int i = 0; i < 2; i++){
            color(i == 0 ? e.color : Color.white);

            float m = i == 0 ? 1f : 0.8f;

            for(int j = 0; j < 5; j++){
                float rot = e.rotation + Mathf.randomSeedRange(e.id + j, 50f);
                float w = 23f * e.fout() * m;
                Drawf.tri(e.x, e.y, w, (80f + Mathf.randomSeedRange(e.id + j, 40f)) * m, rot);
                Drawf.tri(e.x, e.y, w, 20f * m, rot + 180f);
            }
        }

        e.scaled(10f, c -> {
            color(Color.white);
            stroke(c.fout() * 4f + 0.25f);
            Lines.circle(e.x, e.y, c.fin() * 37f);
        });

        e.scaled(12f, c -> {
            color(e.color);
            randLenVectors(e.id, 28, 5f + e.fin() * 90f, e.rotation, 80f, (x, y) -> {
                Fill.square(e.x + x, e.y + y, c.fout() * 4f, 45f);
            });
        });
    }),
            railHitColor = new Effect(18f, 200f, e -> {
                color(e.color);

                for(int i : Mathf.signs){
                    Drawf.tri(e.x, e.y, 18f * e.fout(), 90f, e.rotation + 140f * i);
                }
            });
}
