package expansion.world.blocks;

import arc.Core;
import arc.graphics.Color;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import expansion.world.meta.ExpStat;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.logic.LogicOp.or;

public class CounterWall extends Wall {
    public CounterWall(String name) {
        super(name);
        update = true;
        canOverdrive = true;
        conductivePower = true;
    }

    public int canCountered = 3;
    public float coolDown = 3 * 60;
    public float coolDownOnDestroy = coolDown * 5;
    public float counterDamageFactor = 0;
    public Effect counterEffect = Fx.none;

    @Override
    public void setStats(){
        super.setStats();
        stats.add(ExpStat.canCounters, canCountered);
        stats.add(ExpStat.counterWallCooldown, coolDown / 60f, StatUnit.seconds);
        stats.add(ExpStat.counterWallCooldownOnDestroy, coolDownOnDestroy / 60f, StatUnit.seconds);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("cooldown", (CounterWall.CounterWallBuild entity) ->
                new Bar(
                        () -> Core.bundle.format("bar.cooldown", Strings.autoFixed((entity.counter / (entity.destroyed ? coolDownOnDestroy : coolDown)) * 100, 0)),
                        () -> Color.valueOf("f0f7c3"),
                        () -> entity.counter / (entity.destroyed ? coolDownOnDestroy : coolDown)
                )
        );
        addBar("counters", (CounterWall.CounterWallBuild entity) ->
                new Bar(
                        () -> Core.bundle.format("bar.counters", Strings.autoFixed(entity.counters, 0)),
                        () -> Pal.accent,
                        () -> (float) entity.counters / canCountered
                )
        );
    }

    public class CounterWallBuild extends WallBuild{
        boolean destroyed = false;
        public int counters = canCountered;
        private float counter = 0;

        @Override
        public void updateTile(){
            if (counters <= 0) destroyed = true;

            if(canConsume()){
                if(destroyed){
                    counter += edelta();
                    if (counter >= coolDownOnDestroy) {
                        destroyed = false;
                        counters++;
                        counter = 0;
                    }
                } else {
                    if (counters < canCountered){
                        counter += edelta();
                        if (counter >= coolDown){
                            counters++;
                            counter = 0;
                        }
                    }
                }
            }

           super.updateTile();
        }

        @Override
        public void damage(float damage){
            if(damage >= maxHealth() * counterDamageFactor){
                if(counters <= 0){
                    super.damage(damage);
                }else if (canConsume()) {
                    counters--;
                    counterEffect.at(this.x, this.y);
                    Fx.healBlockFull.at(x, y, block.size, Color.valueOf("ffffff"), block);
                } else super.damage(damage);
            } else super.damage(damage);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(counter);
            write.i(counters);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            counter = read.f();
            counters = read.i();
        }
    }

}
