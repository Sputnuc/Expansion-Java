package expansion.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.content.Fx;
import mindustry.entities.abilities.ShieldRegenFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.unit.TankUnitType;

import static mindustry.Vars.*;
import static mindustry.content.Fx.*;
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
        //Tanks
        warrior = new TankUnitType("warrior"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 10;
            speed = 0.75f;
            health  = 270;
            rotateSpeed = 2;
            range = 175;
            armor = 2;
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
            health = 645;
            armor = 9;
            range = 125;
            treadRects = new Rect[] {
                    new Rect(-31f, -39f, 62, 83)
            };
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
                    shootEffect = ExpFx.tankSmokeLen;
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
            treadRects = new Rect[] {
                    new Rect(-34f, -51f, 68, 108)
            };
            rotateSpeed = 0.8f;
            hitSize = 24;
            health = 1350;
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
                            smokeEffect = new MultiEffect( ExpFx.tankSmoke, ExpFx.tankSmokeLen);
                            backColor =  trailColor = Color.valueOf("e59155");
                            frontColor = hitColor = Color.valueOf("fff3eb");
                            despawnEffect = Fx.massiveExplosion;
                            trailLength = 6;
                            trailWidth = 2.2f;
                            trailInterval = 1;
                            trailEffect = ExpFx.trailSmoke;
                            fragBullets = 4;
                            fragLifeMin = 0.3f;
                            fragLifeMax = 1.2f;
                            fragBullet = new BasicBulletType(3,12){{
                                trailLength = 1;
                                trailWidth = 0.5f;
                                width = 8.5f;
                                height = 8.75f;
                                lifetime = 25;
                                backColor = trailColor = Color.valueOf("e59155");
                                frontColor = hitColor = Color.valueOf("fff3eb");
                            }};
                        }};
                    }}
            );
        }};
        chaos = new TankUnitType("chaos"){{
            constructor = TankUnit::create;
            treadPullOffset = 8;
            abilities.add(new ShieldRegenFieldAbility(50f, 300f, 60f * 2.5f, 40f));
            treadRects = new Rect[] {
                    new Rect(-58f, -64f, 116, 136)
            };
            outlineColor = Pal.darkerMetal;
            hitSize = 34;
            outlineRadius = 4;
            health = 11000;
            armor = scepter.armor + 6;
            speed = (float) 2.5 * 8 / 60;
            rotateSpeed = 0.9f;
            crushDamage = 1.5f;
            weapons.add(new Weapon("expansion-chaos-weapon"){{
                x = y = 0;
                reload = 95;
                shootY = 16;
                recoil = 4.5f;
                rotate = true;
                mirror = false;
                shake = 6.5f;
                rotateSpeed = 0.55f;
                shootSound = Sounds.mediumCannon;
                inaccuracy = 5.5f;
                velocityRnd = 0.15f;
                shootCone = 3.5f;
                shoot.shots = 5;
                shoot.shotDelay = 7f;
                bullet = new BasicBulletType(14.25f,125){{
                    pierce = pierceBuilding = true;
                    pierceCap = 2;
                    splashDamage = 99;
                    scaleLife = true;
                    splashDamageRadius = 3 * tilesize;
                    lifetime = 17;
                    sprite = "missile-large";
                    width = 15.75f;
                    height = 20.5f;
                    backColor =  trailColor = Color.valueOf("e59155");
                    frontColor = hitColor = Color.valueOf("f7dac6");
                    shootEffect = ExpFx.tankFireShoot;
                    trailLength = 7;
                    trailWidth = 3f;
                    trailInterval = 1;
                    trailChance = 1;
                    hitEffect = despawnEffect = Fx.massiveExplosion;
                }};
            }});
        }};
        war = new TankUnitType("war"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 40;
            outlineRadius = 4;
            health = 26000;
            armor = reign.armor + 9;
            speed = (float) 1.7 * 8 / 60;
            rotateSpeed = 0.6f;
            crushDamage = 3.5f;
            weapons.add(new Weapon("expansion-war-weapon"){{
                x = 0; y = -15/4f;
                mirror = false;
                rotate = true;
                rotateSpeed = 0.45f;
                reload = 240;
                shake = 8;
                shootSound = Sounds.railgun;
                inaccuracy = 0;
                shootCone = 1;
                recoil = 6;
                cooldownTime = 350;
                bullet = new RailBulletType(){{
                    shootEffect = Fx.railShoot;
                    length = 50*8;
                    pointEffectSpace = 35f;
                    pierceEffect = Fx.railHit;
                    pointEffect = Fx.railTrail;
                    hitEffect = massiveExplosion;
                    smokeEffect = Fx.shootBig2;
                    despawnEffect = Fx.instBomb;
                    damage = 1650;
                    pierceDamageFactor = 0.35f;
                }};
            }});
        }};
        //Flyers
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
                bullet = new BasicBulletType(8.25f, 7.5f){{
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
                reload = 55;
                shoot.shots = 2;
                shoot.shotDelay = 3;
                inaccuracy = 5;
                shootSound = Sounds.missile;
                velocityRnd = 0.35f;
                bullet = new MissileBulletType(5,12){{
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
        storm = new UnitType("storm"){{
            constructor = UnitEntity::create;
            flying = true;
            trailLength = 6;
            health = 7000;
            hitSize = 54/2f;
            armor = 4;
            rotateSpeed = 2.5f;
            speed = 2;
            accel = 0.035f;
            drag = 0.01f;
            lowAltitude = true;
            engineSize = 5f; engineOffset = 80/4f;
            weapons.add(new Weapon("storm-missile-launcher"){{
                shootSound = Sounds.missileLarge;
                x = 54f / 4f;
                y = -16f / 4f;
                mirror = true;
                rotate = false;
                baseRotation = -45;
                shootCone = 180;
                reload = 120;
                recoil = 0;
                shoot.shots = 7;
                shoot.shotDelay = 5;
                inaccuracy = 5;
                bullet = new BulletType(){{
                    smokeEffect = Fx.shootSmokeTitan;
                    shake = 2f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = false;
                    spawnUnit = new MissileUnitType("storm-missile"){{
                        targetAir = false;
                        lifetime = 45;
                        rotateSpeed = 3.5f;
                        speed = 5f;
                        maxRange = 5f;
                        outlineColor = Pal.darkerMetal;
                        health = 25;
                        homingDelay = 2f;
                        lowAltitude = true;
                        engineSize = 1f;
                        trailLength = 5;
                        engineLayer = Layer.effect;
                        weapons.add(new Weapon(){{
                            shootCone = 360f;
                            mirror = false;
                            reload = 1f;
                            shootOnDeath = true;
                            bullet = new ExplosionBulletType(95,23f){{
                                collidesAir = false;
                            }};
                        }});
                    }};
                }};
            }},
             new Weapon("expansion-storm-weapon"){{
                 x = 0;
                 y = 0;
                 reload = 60;
                 shootSound = Sounds.cannon;
                 recoil = 2.5f;
                 cooldownTime = 45;
                 rotateSpeed = 0.9f;
                 rotate = true;
                 mirror = false;
                 shootY = 10;
                 bullet = new BasicBulletType(8,40){{
                     splashDamage = 45;
                     splashDamageRadius = 3 * tilesize;
                     bulletInterval = 5;
                     trailWidth = 2.5f;
                     trailLength = 12;
                     width = 13;
                     height = 19;
                     lifetime = 30;
                     frontColor = hitColor = Color.valueOf("f5ec9f");
                     backColor = trailColor = Color.valueOf("ebde6a");
                     trailEffect = ExpFx.impulseTrail;
                     despawnEffect = hitEffect = ExpFx.impulseExplosion;
                     trailInterval = 2;
                     lightning = 6;
                     lightningLength = 19;
                     lightningDamage = 22;
                     lightningColor = Color.valueOf("f5ec9f");
                     intervalBullet = new LightningBulletType(){{
                         lightningColor = Color.valueOf("f5ec9f");
                         damage = 45;
                         lightningLength = 16;
                         lifetime = 7;
                         inaccuracy = 360;
                     }};
                 }};
             }}
            );
        }};

        //Navalers
        dew = new UnitType("dew"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 3.5f;
            hitSize = 11;
            speed = 1.2f;
            health = 255;
            armor = risso.armor;
            weapons.add(
                    new Weapon("expansion-dew-weapon"){{
                x = 4f;
                y = -1f;
                reload = 20f;
                rotateSpeed = 1.7f;
                inaccuracy = 1.5f;
                mirror = true;
                shootSound = Sounds.bolt;
                rotate = true;
                bullet = new LaserBulletType(12){{
                    sideWidth = 3;
                    sideLength = 8;
                    width = 2.4f;
                    length = 20 * 8;
                    pierce = false;
                    pierceBuilding = false;
                    pierceCap = 1;
                    shootEffect = sparkShoot;
                    colors = new Color[]{Pal.bulletYellow, Pal.bulletYellow, Color.white};
                    lifetime = 10;
                }};
            }},
                    new Weapon("expansion-dew-weapon2"){{
                        x = 0;
                        y = -10f / 4f;
                        reload = 35;
                        rotateSpeed = 1.5f;
                        mirror = false;
                        shootSound = Sounds.missile;
                        rotate = true;
                        bullet = new MissileBulletType(4, 12f){{
                            keepVelocity = true;
                            width = 8f;
                            height = 8f;
                            shrinkY = 0f;
                            homingRange = 60f;
                            splashDamageRadius = 25f;
                            splashDamage = 10f;
                            lifetime = 45f;
                            trailColor = Color.gray;
                            backColor = Pal.bulletYellowBack;
                            frontColor = Pal.bulletYellow;
                            hitEffect = Fx.blastExplosion;
                            despawnEffect = Fx.blastExplosion;
                            weaveScale = 8f;
                            weaveMag = 2f;
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
            armor = minke.armor;
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
        pressure = new UnitType("pressure"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 1.45f;
            hitSize = 24;
            speed = 0.8f;
            armor = 6;
            health = bryde.health + 100;
            accel = 0.5f;
            weapons.add(new Weapon("expansion-pressure-mount-weapon"){{
                x = -41f / 4f; y = -32f / 4f;
                reload = 6;
                rotateSpeed = 1.5f;
                shootSound = Sounds.shootBig;
                mirror = rotate = true;
                shake = 2;
                inaccuracy = 4;
                bullet = new BasicBulletType(3.5f, 15){{
                    lifetime = 55;
                    width = 14; height = 15;
                }};
            }},
            new Weapon("expansion-pressure-main-weapon"){{
                x = 0; y = -2;
                reload = 60;
                rotateSpeed = 1.2f;
                shootSound = Sounds.plasmaboom;
                mirror = false;
                rotate = true;
                shake = 4;
                shoot.shots = 5;
                inaccuracy = 6;
                bullet = new BasicBulletType(8, 65){{
                    lifetime = 32;
                    width = 12; height = 13.5f;
                    pierce = pierceBuilding = true;
                    pierceCap = 2;
                    trailWidth = 2;
                    trailLength = 10;
                    trailColor = Color.valueOf("f5db84");
                    weaveScale = 4; weaveMag = 2;
                    despawnEffect = hitEffect = ExpFx.yellowPlasmaExplosion;
                }};
            }}
            );
        }};
        aurora = new UnitType("aurora"){{
            constructor = UnitWaterMove::create;
            rotateSpeed = 1.45f;
            speed = 3f * 8f / 60f;
            armor = 14;
            hitSize = 28;
            health = 12000;
            weapons.add(new Weapon("expansion-aurora-mount-weapon"){{
                x = 58/4f; y = 17/4f;
                mirror = rotate = true;
                shake = 5;
                rotateSpeed = 1.6f;
                reload = 40;
                inaccuracy = 11;
                shoot.shots = 3;
                velocityRnd = 0.1f;
                shootSound = Sounds.artillery;
                rotateSpeed = 1.4f;
                bullet = new BasicBulletType(4, 45){{
                    splashDamage = 65f;
                    splashDamageRadius = 3.6f * tilesize;
                    width = 13; height = 15;
                    trailLength = 2;
                    despawnShake = 1.2f;
                    lifetime = 55;
                    trailWidth = 1.1f;
                    frontColor = Pal.bulletYellow; backColor = trailColor = Pal.bulletYellowBack;
                    despawnEffect = hitEffect = blastExplosion;
                    despawnSound = Sounds.explosion;
                    scaleLife = true;
                }};
            }},
             new Weapon("expansion-aurora-beam-weapon"){{
                 x = 0; y = -5;
                 shootY = 15;
                 mirror = false;
                 rotate = true;
                 rotateSpeed = 1.1f;
                 continuous = true;
                 reload = 270;
                 cooldownTime = 270;
                 shootSound = Sounds.beam;
                 bullet = new ContinuousLaserBulletType(42){{
                     length = 20 * tilesize;
                     width = 8;
                     lifetime = 300;
                 }};
             }});
        }};
    }
}
