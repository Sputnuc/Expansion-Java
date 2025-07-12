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
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

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

        stats.add(ExpStat.reloadFrom, reload/60, StatUnit.seconds);
        stats.add(ExpStat.reloadTo, (reload / (1 + maxAccel))/60, StatUnit.seconds);
    }

    public  class AccelTurretBuild extends ItemTurretBuild {
        public float speedUp = 0;
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
        public void updateShooting(){
            //override shooting method
            if (reloadCounter >= reload) {

                BulletType type = peekAmmo();

                shoot(type);
                reloadCounter = 0;
            }
            else
            {
                reloadCounter += (1 + speedUp) * edelta() * baseReloadSpeed() * baseReloadSpeed() * peekAmmo().reloadMultiplier  * (coolantMultiplier * liquids.current().heatCapacity);
            }
        }
        @Override
        public void shoot(BulletType type){
            //speedUp per shoot
            super.shoot(type);
            if (speedUp < maxAccel){
                speedUp += speedUpPerShoot * edelta();
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

