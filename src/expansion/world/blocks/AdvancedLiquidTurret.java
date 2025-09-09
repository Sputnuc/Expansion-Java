package expansion.world.blocks;

import mindustry.entities.bullet.BulletType;
import mindustry.type.Liquid;
import mindustry.world.blocks.defense.turrets.LiquidTurret;

public class AdvancedLiquidTurret extends LiquidTurret {

    public AdvancedLiquidTurret(String name) {
        super(name);
    }
    public float liquidConsumeMultiplier = 5;
    public class AdvancedLiquidTurretBuild extends LiquidTurretBuild{

        @Override
        public BulletType useAmmo(){
            if(cheating()) return ammoTypes.get(liquids.current());
            BulletType type = ammoTypes.get(liquids.current());
            liquids.remove(liquids.current(), 1f / type.ammoMultiplier * liquidConsumeMultiplier);
            return type;
        }

        @Override
        public boolean hasAmmo(){
            return ammoTypes.get(liquids.current()) != null && liquids.currentAmount() >= 1f / ammoTypes.get(liquids.current()).ammoMultiplier * liquidConsumeMultiplier;
        }
    }
}
