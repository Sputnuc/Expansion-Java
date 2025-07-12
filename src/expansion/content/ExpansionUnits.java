package expansion.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.TankUnitType;

import static mindustry.Vars.*;

public class ExpansionUnits {
    public static UnitType
    //t1 main tree
    warrior, sight, dew,
    //t2 main tree
    armada, glare, deep,
    //t3 main tree
    flame, sunset, pressure,
    //t4 main tree
    chaos, storm, aurora,
    //t5 main tree
    war, terror, neptune,
    //additional units
    strife;
    public static void load(){
        warrior = new TankUnitType("warrior"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 10;
            speed = 0.75f;
            health  = 400;
            rotateSpeed = 2;
            range = 175;
            armor = 2;
            treadPullOffset = 6;
            treadRects = new Rect[]{new Rect(7, -26, 14, 51)};
            weapons.add(new Weapon("expansion-warrior-weapon"){{
                x = y = 0;
                shootY = 4.5f;
                reload = 60;
                mirror = false;
                rotate = true;
                rotateSpeed = 1.4f;
                shootSound = Sounds.shoot;
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 10;
                }};
                bullet = new BasicBulletType(4f, 14){{
                    width = 9;
                    height = 12;
                    lifetime = 45;
                }};
            }});
        }};
        sight = new UnitType("sight"){{
            constructor = UnitEntity::create;
            flying = true;
            health = 170;
            hitSize = 9;
            speed = 4.5f;
            accel = 0.04f;
            drag = 0.015f;
            range = 85;
            rotateSpeed = 6;
            trailLength = 4;
            strafePenalty = 0.7f;
            weapons.add(new Weapon(){{
                shootCone = 30;
                x = 0;
                y = 2;
                mirror = false;
                rotate = false;
                inaccuracy = 3;
                reload = 40;
                shoot.shots = 4;
                shoot.shotDelay = 4;
                bullet = new BasicBulletType(8.25f, 5f){{
                    width = 7;
                    height = 13;
                    trailWidth = 2;
                    trailLength = 3;
                    trailColor = Color.valueOf("96969670");
                    lifetime = 15;
                }};
            }});
        }};
        dew = new UnitType("dew"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 3.5f;
            hitSize = 11;
            speed = 1.2f;
            health = 375;
            weapons.add(
                    new Weapon("expansion-dew-weapon"){{
                x = -4.5f;
                y = -3.5f;
                reload = 10f;
                rotateSpeed = 1.7f;
                inaccuracy = 1.5f;
                mirror = true;
                shootSound = Sounds.shoot;
                rotate = true;
                bullet = new BasicBulletType(3.5f, 8){{
                    width = height = 10;
                    lifetime = 40;
                }};
            }},
                    new Weapon("expansion-dew-weapon2"){{
                        x = 0;
                        y = 2.25f;
                        reload = 65;
                        rotateSpeed = 1.5f;
                        mirror = false;
                        shootSound = Sounds.bang;
                        shake = 2.5f;
                        rotate = true;
                        bullet = new BasicBulletType(8, 25f){{
                            width = height = 12;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            lifetime = 24;
                        }};
                    }}
            );
        }};
        armada = new TankUnitType("armada"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 18;
            speed = 0.55f;
            rotateSpeed = 0.8f;
            health = 700;
            armor = 9;
            range = 125;
            treadRects = new Rect[]{new Rect(-32, -39, 16, 83)};
            treadPullOffset= 5;
            weapons.add(new Weapon("expansion-armada-weapon-main"){{
                x = y = 0;
                shootSound = Sounds.artillery;
                reload = 60;
                shake = 2.5f;
                mirror = false;
                rotate = true;
                rotateSpeed = 1.1f;
                shootCone = 3;
                range = 125;
                bullet = new BasicBulletType(8.5f, 28){{
                    splashDamage = 17;
                    splashDamageRadius = 3 * tilesize;
                    lifetime = 15;
                    width = 13;
                    height = 16;
                    trailInterval = 1;
                    trailEffect = new MultiEffect(
                            new ParticleEffect(){{
                        particles = 3;
                        cone = 360;
                        length = 16;
                        lifetime = 15;
                        sizeFrom = 2;
                        sizeTo = 0;
                        colorFrom = Pal.bulletYellow;
                        colorTo = Color.valueOf("c27655");
                    }},
                            new ParticleEffect(){{
                                lifetime = 35;
                                particles = 2;
                                length = 4;
                                sizeFrom = 2;
                                sizeTo = 3;
                                colorFrom = Color.valueOf("b0a49e");
                                colorTo = Color.valueOf("7a736f00");
                            }}
                    );
                    fragBullets = 5;
                    fragRandomSpread = 55;
                    trailWidth = 2.1f;
                    trailLength = 6;
                    trailColor = this.backColor;
                    despawnEffect = Fx.blastExplosion;
                    fragBullet = new BasicBulletType(6.5f, 9){{
                        lifetime = 16;
                        width = height = 9;
                        trailWidth = 1.1f;
                        trailLength = 3;
                        trailColor = this.backColor;
                    }};
                }};
            }},
                    new Weapon("expansion-armada-weapon-primal"){{
                        x = 7;
                        y = -5;
                        reload = 15;
                        shootSound = Sounds.pew;
                        mirror = rotate = true;
                        rotateSpeed = 1.9f;
                        shootCone = 2;
                        inaccuracy = 2;
                        range = 120;
                        bullet = new BasicBulletType(5.5f, 15){{
                            width = height = 10;
                            lifetime = 22;
                        }};
                    }}
            );
        }};
        glare = new UnitType("glare"){{
            constructor = UnitEntity::create;
            flying = true;
            health = 370;
            armor = 2;
            speed = 2.3f;
            hitSize = 9.5f;
            rotateSpeed = 4.5f;
            range = 148;
            engineSize = 2.85f;
            weapons.add(new Weapon("expansion-glare-weapon"){{
                x = -5.5f;
                y = 0;
                rotate = false;
                mirror = true;
                reload = 45;
                shoot.shots = 2;
                inaccuracy = 5;
                shootSound = Sounds.missile;
                velocityRnd = 0.15f;
                bullet = new MissileBulletType(5,17){{
                    lifetime = 30;
                    height = width = 9;
                    splashDamage = 12;
                    splashDamageRadius = 15;
                    homingPower = 0.12f;
                    homingRange = 32;
                    weaveScale = 3;
                    weaveMag = 1.8f;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                }};
            }});
        }};
        deep = new UnitType("deep"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 2;
            hitSize = 16;
            speed = 1;
            health = 725;
            range = 180;
            weapons.add(new Weapon("expansion-deep-weapons-primal"){{
                x = -5;
                y = 2;
                reload = 10;
                rotateSpeed = 1.7f;
                mirror = rotate = true;
                shootSound = Sounds.shoot;
                range = 180;
                bullet = new BasicBulletType(3,18){{
                    width = height = 11f;
                    lifetime = 60;
                }};
            }},
                    new Weapon("expansion-deep-weapon-main"){{
                        x = 0;
                        y = -3;
                        rotate = true;
                        mirror = false;
                        shake = 4.2f;
                        shootSound = Sounds.bang;
                        rotateSpeed = 1.1f;
                        reload = 75;
                        range = 210;
                        bullet = new BasicBulletType(15,52){{
                            width = 14;
                            height = 16;
                            trailColor = Color.valueOf("ffa665");
                            frontColor = Color.white;
                            trailWidth = 1.7f;
                            trailLength = 6;
                            pierce = pierceBuilding = true;
                            pierceCap = 2;
                            lifetime = 14;
                            despawnEffect = hitEffect = new MultiEffect(Fx.hitBulletBig, Fx.hitBulletSmall);
                        }};
                    }}
            );
        }};
    }
}
