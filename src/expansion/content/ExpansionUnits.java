package expansion.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import expansion.graphic.ExpPal;
import mindustry.content.Fx;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
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
import static mindustry.content.UnitTypes.*;

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
            health  = 230;
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
        armada = new TankUnitType("armada"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 18;
            speed = 0.55f;
            rotateSpeed = 0.8f;
            health = 595;
            armor = 9;
            range = 125;
            treadRects = new Rect[]{new Rect(-32, -39, 16, 83)};
            treadPullOffset= 5;
            weapons.add(
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
                    }},
            new Weapon("expansion-armada-weapon-main"){{
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
            }}
            );
        }};
        flame = new TankUnitType("flame"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            speed = 0.4f;
            treadPullOffset = 5;
            treadRects = new Rect[]{new Rect(13, -38, 17, 76)};
            rotateSpeed = 0.8f;
            hitSize = 24;
            health = 1300;
            armor = 14;
            weapons.add(new Weapon("expansion-flame-weapon-primal"){{
                            x = 8;
                            y = -9;
                            reload = 50;
                            recoil = 1.4f;
                            inaccuracy = 2;
                            rotate = mirror = true;
                            shootSound = Sounds.shoot;
                            shootCone = 3;
                            shoot.shots = 3;
                            shoot.shotDelay = 10;
                            range = 195;
                            bullet = new BasicBulletType(5, 21){{
                                width = height = 10;
                                lifetime = 40;
                            }};
                        }},
                    new Weapon("expansion-flame-weapon-main"){{
                        reload = 75;
                        x = y = 0;
                        shootY = 9;
                        recoil = 4.5f;
                        shake = 4.5f;
                        rotate = true;
                        mirror = false;
                        rotateSpeed = 0.8f;
                        inaccuracy = 1.3f;
                        shootSound = Sounds.artillery;
                        range = 260;
                        bullet = new ArtilleryBulletType(10, 90){{
                            sprite = "missile-large";
                            width = 12f;
                            height = 18;
                            lifetime = 26;
                            splashDamage = 63;
                            splashDamageRadius = 44;
                            shootEffect = Fx.shootBig;
                            smokeEffect = ExpFx.tankSmoke;
                            backColor =  trailColor = Color.valueOf("e59155");
                            frontColor = hitColor = Color.valueOf("fff3eb");
                            despawnEffect = Fx.massiveExplosion;
                            trailLength = 6;
                            trailWidth = 2.2f;
                            trailInterval = 1;
                            trailEffect = ExpFx.trailSmoke;
                        }};
                    }}
            );
        }};
        sight = new UnitType("sight"){{
            constructor = UnitEntity::create;
            flying = true;
            health = 85;
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
        sunset = new UnitType("sunset"){{
            constructor = UnitEntity::create;
            flying = true;
            health = 745;
            hitSize = 17;
            armor = 4;
            rotateSpeed = 3.85f;
            speed = 3;
            accel = 0.055f;
            drag = 0.02f;
            lowAltitude = true;
            engineSize = 3.5f; engineOffset = 12.5f;
            weapons.add( new Weapon("expansion-sunset-weapon-primal"){{
                x = -7.75f; y = 0;
                reload = 20;
                mirror = rotate = true;
                shootSound = Sounds.shoot;
                inaccuracy = 1;
                shoot.shots = 2;
                shoot.shotDelay = 5;
                range = 5.5f * 36;
                bullet = new BasicBulletType(5.5f, 10){{
                    width = height = 13.5f;
                    lifetime = 36;
                }};
            }},
                    new Weapon("expansion-sunset-weapon-main"){{
                            x = 0; y = -4.5f;
                            reload = 40;
                            recoil = 0.3f;
                            mirror = false;
                            shootSound = Sounds.missile;
                            rotate = true;
                            inaccuracy = 8;
                            velocityRnd = 0.2f;
                            range = 7.5f * 30f;
                            shoot = new ShootPattern(){{
                                shots = 3;
                                shotDelay = 8;
                            }};
                            bullet = new MissileBulletType(7.5f, 11){{
                                splashDamage = 19;
                                splashDamageRadius = 3 * tilesize;
                                lifetime = 30;
                                width = height = 11;
                                homingPower = 0.45f;
                                homingDelay = 15;
                                homingRange = 45;
                                weaveScale = 5.5f;
                                weaveMag = 2;
                                hitEffect = despawnEffect = Fx.blastExplosion;
                            }};
                        }}
            );
        }};
        dew = new UnitType("dew"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 3.5f;
            hitSize = 11;
            speed = 1.2f;
            health = 255;
            weapons.add(
                    new Weapon("expansion-dew-weapon"){{
                x = -4.5f;
                y = -3.5f;
                reload = 10f;
                rotateSpeed = 1.7f;
                inaccuracy = 1.5f;
                mirror = true;
                shootSound = Sounds.pew;
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
        deep = new UnitType("deep"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 2;
            hitSize = 16;
            speed = 0.7f;
            health = minke.health+70;
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
