package expansion.world.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import expansion.world.meta.ExpStat;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.StatUnit;

import static arc.graphics.g2d.Draw.*;


public class RegenWall extends Wall {

    public RegenWall(String name) {
        super(name);
        update = true;
        canOverdrive = true;
        conductivePower = true;
    }
    public float regenPerTick = 1;
    public Color regenColor = Color.valueOf("ffffff");
    public float effectInterval = 180;

   @Override
    public void setStats(){
       super.setStats();
       stats.add(ExpStat.regenerationPerTick, regenPerTick * 60, StatUnit.perSecond);
   };

   public class RegenWallBuild extends WallBuild{
       public float regenSpeed = regenPerTick;
       private float counter = regenSpeed * effectInterval;

       @Override
       public void updateTile() {
               if (this.health() < maxHealth() && canConsume()) {
                   if (counter >= regenSpeed * effectInterval) {
                       Fx.healBlockFull.at(x, y, block.size, regenColor, block);
                       counter = 0;
                   } else {
                       counter += edelta();
                   }
                   heal(regenSpeed * edelta());
               }
           }
       }
}
