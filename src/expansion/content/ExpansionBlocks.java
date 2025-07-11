package expansion.content;

import arc.graphics.Color;
import expansion.graphic.ExpPal;
import expansion.world.blocks.AccelTurret;
import expansion.world.blocks.BurnCrafter;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.*;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;

import static expansion.content.ExpansionItems.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.UnitTypes.*;

public class ExpansionBlocks {
        public static Block
        tebriyAlloySmelter, tebriySinteser, boiler, siliconAlloyFurnace, collapseTurret;

        public static void load() {
            tebriyAlloySmelter = new GenericCrafter("tebriy-alloy-smelter"){{
                requirements(Category.crafting, ItemStack.with(copper, 95, lead, 55, graphite, 75, titanium, 45));

                consumeItems(ItemStack.with(tebriy, 2, titanium, 3));
                consumePower(96/60f);
                outputItem = new ItemStack(tebriyAlloy, 2);
                craftTime = 60;

                size = 3;
                ambientSound = Sounds.smelter; ambientSoundVolume = 0.075f;

                drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                    flameRadius = 4.5f;
                    flameRadiusIn = 2.5f;
                    flameRadiusScl = 8f;
                    flameRadiusMag = 1.5f;
                    flameRadiusInMag = 0.8f;
                }});
                craftEffect = new ParticleEffect(){{
                    particles = 7;
                    cone = 360;
                    sizeFrom = 1.4f;
                    sizeTo = 0f;
                    colorFrom = Color.valueOf("d9c771");
                    colorTo = Color.valueOf("bfbeb8");
                    lifetime = 20;
                    length = 32;
                    baseLength = 2;
                }};
            }};
            boiler = new BurnCrafter("solid-boiler"){{
                requirements(Category.crafting, ItemStack.with(copper, 55, lead, 35, graphite, 45, metaglass, 25));
                size = 2;
                consume(new ConsumeItemFlammable(0.5f));
                consumeLiquids(new LiquidStack(water, 15/60f));
                outputLiquid = new LiquidStack(ExpansionLiquids.steam, 30/60f);
                craftTime = 3 * 60;
                updateEffectChance = 0.3f;
                updateEffect = new ParticleEffect(){{
                    particles = 1;
                    colorFrom = Color.valueOf("0d0d0d");
                    colorTo = Color.valueOf("2e2e2e");
                    lifetime = 60;
                    sizeFrom = 2;
                    sizeTo = 0;
                    length = 4;
                    cone = 360;
                }};
            }};
            siliconAlloyFurnace = new GenericCrafter("silicon-alloy-furnace"){{
                requirements(Category.crafting, ItemStack.with(lead, 120, graphite, 100, titanium, 175, silicon, 120, plastanium, 75));

                consumeItems(ItemStack.with(tebriy, 1, silicon, 2));
                consumePower(120/60f);
                outputItem = new ItemStack(siliconAlloy, 1);
                craftTime = 90;

                size = 3;
                ambientSound = Sounds.smelter; ambientSoundVolume = 0.075f;

                drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                    flameRadius = 4.5f;
                    flameRadiusIn = 2.5f;
                    flameRadiusScl = 8f;
                    flameRadiusMag = 1.5f;
                    flameRadiusInMag = 0.8f;
                }});
                craftEffect = new ParticleEffect(){{
                    particles = 7;
                    cone = 360;
                    sizeFrom = 2f;
                    sizeTo = 0f;
                    colorFrom = ExpPal.silAlloyPal;
                    colorTo = Color.valueOf("63747f");
                    lifetime = 35;
                    length = 22;
                    baseLength = 2;
                }};
            }};
            collapseTurret = new AccelTurret("collapse"){{
                requirements(Category.turret, ItemStack.with(copper, 85, graphite, 55, titanium, 45, silicon, 45, tebriyAlloy, 55));
                size = 2;
                maxAccel = 10f;
                speedUpPerShoot = 0.2f;
                cooldownSpeed = 0.045f;
                reload = 180;
                recoil = 0.6f;
                range = 200;
                shake = 2;
                inaccuracy = 3;
                shootSound = Sounds.shootBig;
                consumeCoolant(0.2f);
                ammo(
                        graphite, new BasicBulletType(4f,21f ){{
                            width = 10.5f;
                            height = 11.5f;
                            lifetime = 50;
                            frontColor = Pal.graphiteAmmoFront;
                            backColor = hitColor = Pal.graphiteAmmoBack;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 4;
                            reloadMultiplier = 0.8f;
                        }},
                        silicon, new BasicBulletType(8f,16.5f ){{
                            width = 10;
                            height = 10;
                            lifetime = 25;
                            frontColor = Pal.siliconAmmoFront;
                            backColor = hitColor = trailColor = Pal.siliconAmmoBack;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 3;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            homingPower = 0.3f;
                            homingRange = 50;
                            reloadMultiplier = 1.85f;
                        }},
                        siliconAlloy, new BasicBulletType(12.5f,22f ){{
                            width = 10;
                            height = 10;
                            lifetime = 16;
                            frontColor = ExpPal.silAlloyPal;
                            backColor = hitColor = trailColor = Color.valueOf("8f8571");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 2;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            pierce = true;
                            pierceCap = 2;
                        }},
                        cobalt, new BasicBulletType(7.7f,21f ){{
                            splashDamage = 17;
                            splashDamageRadius = 16;
                            width = 12;
                            height = 15;
                            lifetime = 26;
                            frontColor = ExpPal.cobaltPal;
                            backColor = hitColor = trailColor = Color.valueOf("485596");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 1;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            reloadMultiplier = 0.60f;
                        }}
                        );
            }};
        }
}
