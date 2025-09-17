package expansion.world.blocks;

import arc.Core;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import expansion.world.meta.ExpStat;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.ConsumeLiquidFilter;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.tilesize;

public class AccelTurret extends ItemTurret {
    public float speedUpPerShoot = 2;
    public float maxAccel = 0.5f;
    public float cooldownSpeed = 1;

    public AccelTurret(String name){
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("speedingUp", (AccelTurretBuild entity) ->
                new Bar(
                        () -> Core.bundle.format("bar.speedingUp", Strings.autoFixed((entity.speedUp/maxAccel)* 100, 0)),
                        () -> Pal.powerBar,
                        () -> entity.speedUp / maxAccel
                )
        );
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.reload);
        stats.add(ExpStat.reloadFrom, reload / 60f, StatUnit.seconds);
        stats.add(ExpStat.reloadTo, (reload / (maxAccel + 1.0f)) / 60f, StatUnit.seconds);
    }

    public  class AccelTurretBuild extends ItemTurretBuild {
        protected float speedUp = 0;
        protected float coolantSpeedMultiplier;
        @Override
        public void updateTile() {
            //coolDown progress
            if (!isShooting() || !hasAmmo() || !isActive()){

                if(speedUp > 0) {
                    speedUp -= delta() * cooldownSpeed;
                }else {
                    speedUp = 0;
                };
            }
            super.updateTile();
        }
        @Override
        protected void updateCooling(){
            if(coolant != null && coolant.efficiency(this) > 0 && efficiency > 0){
                float capacity = coolant instanceof ConsumeLiquidFilter filter ? filter.getConsumed(this).heatCapacity : (coolant.consumes(liquids.current()) ? liquids.current().heatCapacity : 0.4f);
                float amount = coolant.amount * coolant.efficiency(this);
                coolant.update(this);
                coolantSpeedMultiplier = amount * capacity * coolantMultiplier * ammoReloadMultiplier();
                if(Mathf.chance(0.06 * amount)){
                    coolEffect.at(x + Mathf.range(size * tilesize / 2f), y + Mathf.range(size * tilesize / 2f));
                }
            }
        }
        @Override
        public void updateShooting(){
            //override shooting method
            if (reloadCounter >= reload) {

                BulletType type = peekAmmo();

                shoot(type);
                reloadCounter = 0;
            }
            else
            {
                reloadCounter += (1 + speedUp) * edelta() * baseReloadSpeed();
            }
        }
        @Override
        public void shoot(BulletType type){
            //speedUp per shoot
            super.shoot(type);
            if (speedUp < maxAccel){
                speedUp += speedUpPerShoot * edelta();
                speedUp += coolantSpeedMultiplier;
                if(speedUp>maxAccel) speedUp = maxAccel;
            }else {
                speedUp = maxAccel;
            }
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(speedUp);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            speedUp = read.f();;
        }
    }
}

