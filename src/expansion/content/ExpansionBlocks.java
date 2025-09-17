package expansion.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.EnumSet;
import arc.struct.Seq;
import expansion.graphic.ExpPal;
import expansion.world.blocks.AccelTurret;
import expansion.world.blocks.AdvancedLiquidTurret;
import expansion.world.blocks.BurnCrafter;
import expansion.world.blocks.CounterWall;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeItemExplode;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockFlag;

import static expansion.content.ExpFx.*;
import static expansion.content.ExpansionItems.*;
import static mindustry.content.Fx.none;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.with;

public class ExpansionBlocks {
        public static Block
        //production
                steamDrill,
        //crafting
               tebriySynthezer, hydraulicGraphitePress, tebriyAlloySmelter, tebriyAlloyForge, boiler, siliconAlloyFurnace, siliconAlloyOven,
       //power
                combustionPowerPlant, steamTurbine,
        //turrets
                collapseTurret, incandescence, flood,
        //walls
                tebriyWall, tebriyWallLarge, cobaltWall, cobaltWallLarge,
        //unit blocks
                baseFactory, upgradeReconstructor, improvingReconstructor, progressiveReconstructor, quantumReconstructor;
        public static void load() {
            //production
            steamDrill = new Drill("steam-drill"){{
                requirements(Category.production, ItemStack.with(copper, 25, graphite, 35, tebriyAlloy, 55));
                consumeLiquid(ExpansionLiquids.steam, 0.05f);
                size = 2;
                tier = 4;
                drillTime = 300;
                liquidBoostIntensity = 1;
            }};
            //Craft
            tebriySynthezer = new GenericCrafter("tebriy-synthezer"){{
                requirements(Category.crafting, ItemStack.with(copper, 75, graphite, 65, titanium, 35, silicon, 45));
                size = 3;
                consumeItems(with(copper, 4, lead, 3));
                consumePower(120/60f);
                craftTime = 120;
                outputItem = new ItemStack(tebriy, 4);
            }};

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
                    sizeFrom = 1.6f;
                    sizeTo = 0f;
                    colorFrom = Color.valueOf("d9c771");
                    colorTo = Color.valueOf("bfbeb8");
                    lifetime = 30;
                    length = 32;
                    baseLength = 2;
                }};
            }};
            tebriyAlloyForge = new GenericCrafter("tebriy-alloy-forge"){{
                requirements(Category.crafting, ItemStack.with(lead, 95, graphite, 75, titanium, 55, thorium, 65,tebriyAlloy, 25, siliconAlloy, 45));

                consumeItems(with(tebriy, 6, titanium, 9, pyratite, 2));
                consumePower(180/60f);
                outputItem = new ItemStack(tebriyAlloy, 9);
                itemCapacity = 25;
                craftTime = 90;

                size = 4;
                ambientSound = Sounds.smelter; ambientSoundVolume = 0.1f;

                drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                    flameRadius = 6.5f;
                    flameRadiusIn = 3.5f;
                    flameRadiusScl = 9f;
                    flameRadiusMag = 1.5f;
                    flameRadiusInMag = 0.8f;
                }});
                craftEffect = new ParticleEffect(){{
                    particles = 13;
                    cone = 360;
                    sizeFrom = 3.5f;
                    sizeTo = 0f;
                    colorFrom = Color.valueOf("d9c771");
                    colorTo = Color.valueOf("bfbeb8");
                    lifetime = 45;
                    length = 55;
                    baseLength = 4;
                }};
            }};
            siliconAlloyFurnace = new GenericCrafter("silicon-alloy-furnace"){{
                requirements(Category.crafting, ItemStack.with(lead, 85, graphite, 55, silicon, 60, plastanium, 45));

                consumeItems(ItemStack.with(tebriy, 1, silicon, 2));
                consumePower(120/60f);
                outputItem = new ItemStack(siliconAlloy, 1);
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
                    sizeFrom = 2f;
                    sizeTo = 0f;
                    colorFrom = ExpPal.silAlloyPal;
                    colorTo = Color.valueOf("63747f");
                    lifetime = 35;
                    length = 22;
                    baseLength = 2;
                }};
            }};
            siliconAlloyOven = new GenericCrafter("silicon-alloy-oven"){{
                requirements(Category.crafting, ItemStack.with(lead, 120, titanium, 85, plastanium, 85, thorium, 95, siliconAlloy, 120));
                size = 4;
                consumeItems(ItemStack.with(tebriy, 5, silicon, 8, pyratite, 1));
                consumeLiquid(Liquids.cryofluid, 0.1f);
                consumePower(295/60f);
                ambientSound = Sounds.smelter; ambientSoundVolume = 0.09f;
                itemCapacity = 15;
                liquidCapacity = 35;
                outputItem = new ItemStack(siliconAlloy, 7);
                craftTime = 75;
                craftEffect = new ParticleEffect(){{
                    particles = 9;
                    cone = 360;
                    sizeFrom = 3.5f;
                    sizeTo = 0f;
                    colorFrom = ExpPal.silAlloyPal;
                    colorTo = Color.valueOf("63747f");
                    lifetime = 40;
                    length = 30;
                    baseLength = 3;
                    interp = Interp.circleOut;
                }};

                drawer = new DrawMulti(new DrawDefault(), new DrawFlame(){{
                    flameRadius = 8f;
                    flameRadiusIn = 4f;
                    flameRadiusScl = 10f;
                    flameRadiusMag = 1.25f;
                    flameRadiusInMag = 0.95f;
                }});
            }};
            boiler = new BurnCrafter("solid-boiler"){{
                requirements(Category.crafting, ItemStack.with(copper, 55, lead, 35, graphite, 45, metaglass, 25));
                size = 2;
                consume(new ConsumeItemFlammable(0.5f));
                consumeLiquids(new LiquidStack(Liquids.water, 15/60f));
                outputLiquid = new LiquidStack(ExpansionLiquids.steam, 30 / 60f);
                craftTime =  60;
                updateEffectChance = 0.3f;
                explodeOnFull = true;
                effectOnPressure = true;
                pressureEffect = ExpFx.steamPressure;
                liquidCapacity = 120;
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

            //Power
            combustionPowerPlant = new ConsumeGenerator("combustion-power-plant"){{
                requirements(Category.power, ItemStack.with(copper, 75, lead, 85, graphite, 10, silicon, 25));
                size = 3;
                itemDuration = 90;
                powerProduction = 210f / 60f;
                drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());

                consume(new ConsumeItemFlammable());
                consume(new ConsumeItemExplode());
            }};
            steamTurbine = new ConsumeGenerator("steam-turbine"){{
                requirements(Category.power, ItemStack.with(lead, 75, metaglass, 45, titanium, 55, silicon, 45, tebriyAlloy, 25));
                size = 3;
                consumeLiquid(ExpansionLiquids.steam,  15/60f);
                outputsPower = true;
                powerProduction = 390f/60f;
                liquidCapacity = 60;
                drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(ExpansionLiquids.steam),new DrawBlurSpin("-rotator", 14) , new DrawDefault());
                effectChance = 1;
                generateEffect = new ParticleEffect(){{
                    particles = 1;
                    length = 70;
                    lifetime = 60;
                    cone = 10;
                    baseRotation = 45;
                    useRotation = false;
                    sizeFrom = 0;
                    sizeTo = 3.5f;
                    colorFrom = Color.white;
                    colorTo = Color.valueOf("ffffff00");
                }};
            }};

            //Turret
            collapseTurret = new AccelTurret("collapse"){{
                requirements(Category.turret, ItemStack.with(copper, 85, graphite, 55, titanium, 45, silicon, 45, tebriyAlloy, 55));
                size = 2;
                health = 890;
                maxAccel = 20f;
                speedUpPerShoot = 0.75f;
                cooldownSpeed = 0.045f;
                reload = 60;
                recoil = 0.6f;
                range = 200;
                shake = 2;
                inaccuracy = 3;
                shootSound = Sounds.shootBig;
                consumeCoolant(0.25f);
                liquidCapacity = 30;
                ammo(
                        graphite, new BasicBulletType(4f,25f ){{
                            width = 10.5f;
                            height = 11.5f;
                            lifetime = 50;
                            shootEffect = inclinedWave;
                            frontColor = Pal.graphiteAmmoFront;
                            backColor = hitColor = Pal.graphiteAmmoBack;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 4;
                            reloadMultiplier = 0.8f;
                        }},
                        silicon, new BasicBulletType(8f,19.5f ){{
                            width = 10;
                            height = 10;
                            lifetime = 25;
                            shootEffect = inclinedWave;
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
                        siliconAlloy, new BasicBulletType(12.5f,29 ){{
                            width = 10;
                            height = 10;
                            lifetime = 16;
                            shootEffect = inclinedWave;
                            frontColor = ExpPal.silAlloyPal;
                            backColor = hitColor = trailColor = Color.valueOf("8f8571");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            ammoMultiplier = 2;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            pierce = true;
                            pierceCap = 2;
                        }},
                        cobalt, new BasicBulletType(7.7f,35f ){{
                            splashDamage = 19;
                            splashDamageRadius = 16;
                            width = 12;
                            height = 15;
                            lifetime = 26;
                            shootEffect = inclinedWave;
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
            incandescence = new AdvancedLiquidTurret("incandescence"){{
                requirements(Category.turret, ItemStack.with(lead, 35, titanium, 55, silicon, 75, siliconAlloy, 25, plastanium, 55));
                size = 3;
                liquidCapacity = 120f;
                liquidConsumeMultiplier = 10;
                shootCone = 10;
                range = 16 * 13;
                shootSound = Sounds.flame;
                reload = 5;
                inaccuracy = 2;
                shootEffect = bigFireShoot;
                recoil = 0;
                cooldownTime = 120;
                consumePower(510f / 60f);
                shoot = new ShootSpread(3, 3);
                ammo(
                        oil, new BulletType(16, 55){{
                            lifetime = 13;
                            hitSize = 2 * 8;
                            hitEffect = despawnEffect = none;
                            status = StatusEffects.melting;
                            statusDuration = 5 * 60;
                            pierce = true;
                            ammoMultiplier = 1;
                        }}
                );
            }};
            flood = new AdvancedLiquidTurret("flood"){{
                requirements(Category.turret, with(Items.metaglass, 570, Items.thorium, 350, Items.plastanium, 250, ExpansionItems.siliconAlloy, 230, ExpansionItems.tebriyAlloy, 180));
                ammo(
                        Liquids.water, new LiquidBulletType(Liquids.water){{
                            lifetime = 31f;
                            speed = 8f;
                            knockback = 1.9f;
                            puddleSize = 8f;
                            orbSize = 5f;
                            drag = 0.001f;
                            ammoMultiplier = 0.5f;
                            statusDuration = 60f * 4f;
                            damage = 0.95f;
                            layer = Layer.bullet - 2f;
                        }},
                        Liquids.slag,  new LiquidBulletType(Liquids.slag){{
                            lifetime = 31f;
                            speed = 8f;
                            knockback = 1.6f;
                            puddleSize = 8f;
                            orbSize = 5f;
                            damage = 8f;
                            drag = 0.001f;
                            ammoMultiplier = 0.5f;
                            statusDuration = 60f * 4f;
                        }},
                        Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid){{
                            lifetime = 31f;
                            speed = 8f;
                            knockback = 1.9f;
                            puddleSize = 8f;
                            orbSize = 5f;
                            drag = 0.001f;
                            ammoMultiplier = 0.5f;
                            statusDuration = 60f * 4f;
                            damage = 0.95f;
                        }},
                        Liquids.oil, new LiquidBulletType(Liquids.oil){{
                            lifetime = 31f;
                            speed = 8f;
                            knockback = 1.9f;
                            puddleSize = 8f;
                            orbSize = 5f;
                            drag = 0.001f;
                            ammoMultiplier = 0.5f;
                            statusDuration = 60f * 4f;
                            damage = 1.7f;
                            layer = Layer.bullet - 2f;
                        }},
                        ExpansionLiquids.hypercoologen, new LiquidBulletType(ExpansionLiquids.hypercoologen){{
                            lifetime = 31f;
                            speed = 8f;
                            knockback = 1.9f;
                            puddleSize = 8f;
                            orbSize = 5f;
                            drag = 0.001f;
                            ammoMultiplier = 0.5f;
                            statusDuration = 60f * 5f;
                            damage = 0.65f;
                        }}
                );
                size = 4;
                reload = 1f;
                liquidConsumeMultiplier = 4f;
                shoot = new ShootAlternate(){{
                    shots = 2;
                    spread = 7;
                }};
                velocityRnd = 0.2f;
                inaccuracy = 4f;
                recoil = 1f;
                shootCone = 45f;
                liquidCapacity = 90f;
                shootEffect = Fx.shootLiquid;
                range = 30f * 8f;
                scaledHealth = 260;
                flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
            }};

            //Walls
            cobaltWall = new CounterWall("cobalt-wall"){{
                requirements(Category.defense, ItemStack.with(silicon, 7, cobalt, 5));
                health = 300;
                canCountered = 5;
                coolDown = 60;
                coolDownOnDestroy = 5 * 60;
                consumePower(35f / 60f);
            }};
            cobaltWallLarge =new CounterWall("cobalt-wall-large"){{
                requirements(Category.defense, ItemStack.with(silicon, 28, cobalt, 20));
                size = 2;
                health = 300*4;
                canCountered = 10;
                coolDown = 60;
                coolDownOnDestroy = 5 * 60;
                consumePower(35f / 60f * 4);
            }};

            //Unit blocks
            baseFactory = new UnitFactory("base-factory"){{
                requirements(Category.units, ItemStack.with(copper, 75, lead, 55, silicon, 55, tebriyAlloy, 25));
                size = 3;
                consumePower(90/60f);
                plans = Seq.with(
                        new UnitPlan(ExpansionUnits.sight, 60f * 15, with(silicon, 10, graphite, 15)),
                        new UnitPlan(ExpansionUnits.warrior, 60f * 35, with(silicon, 35, lead, 15, titanium, 25)),
                        new UnitPlan(ExpansionUnits.dew, 60f * 35, with(silicon, 35, metaglass, 25, tebriyAlloy, 15))
                );
            }};
            upgradeReconstructor = new Reconstructor("upgrade-reconstructor"){{
                requirements(Category.units, ItemStack.with(copper, 95, lead, 55, graphite, 35, titanium, 45, silicon, 45, tebriyAlloy, 25));
                size = 3;
                consumePower(120/60f);
                consumeItems(with(silicon, 45, graphite, 35, tebriyAlloy, 15));
                constructTime = 60f * 20f;
                upgrades.addAll(
                        new UnitType[]{ExpansionUnits.sight, ExpansionUnits.glare},
                        new UnitType[]{ExpansionUnits.warrior, ExpansionUnits.armada},
                        new UnitType[]{ExpansionUnits.dew, ExpansionUnits.deep}
                );
            }};
            improvingReconstructor = new Reconstructor("improving-reconstructor"){{
                requirements(Category.units, ItemStack.with(copper, 750, silicon, 500,titanium ,230 ,thorium, 350, tebriyAlloy, 250));

                size = 5;
                consumePower(420/60f);
                consumeItems(with(silicon, 195, metaglass, 125, tebriyAlloy, 95, siliconAlloy, 55));

                constructTime = 40 * 60;

                upgrades.addAll(
                        new UnitType[]{ExpansionUnits.armada, ExpansionUnits.flame},
                        new UnitType[]{ExpansionUnits.glare, ExpansionUnits.sunset},
                        new UnitType[]{ExpansionUnits.deep, ExpansionUnits.pressure}
                );
            }};
        }
}
