package expansion.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.TankUnitType;

public class ExpansionUnits {
    public static UnitType
    warrior, sight, dew;

    public static void load(){
        warrior = new TankUnitType("warrior"){{
            constructor = TankUnit::create;
            outlineColor = Pal.darkerMetal;
            hitSize = 9;
            speed = 0.75f;
            health  = 400;
            rotateSpeed = 2;
            range = 200;
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
                        reload = 120;
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
    }
}
