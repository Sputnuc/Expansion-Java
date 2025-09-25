package expansion.world.blocks;

import arc.Core;
import arc.Events;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.io.Reads;
import arc.util.io.Writes;
import expansion.content.ExpFx;
import mindustry.entities.Effect;
import mindustry.game.EventType;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItemEfficiency;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.consumers.ConsumeItemFlammable;

import java.awt.*;

public class BurnCrafter extends GenericCrafter {
    public BurnCrafter(String name) {
        super(name);
    }

    public @Nullable ConsumeItemFilter filterItem;

    @Override
    public void init(){
        filterItem = findConsumer(c -> c instanceof ConsumeItemFilter);
        super.init();
    }

    @Override
    public void setBars(){
        super.setBars();
        if(explodeOnFull) addBar("expPressure", (BurnCrafterBuild entity) -> new Bar(
                () -> Core.bundle.format("bar.expPressure",  Strings.autoFixed(entity.pressure/maxPressure*100, 0)),
                () -> Pal.redSpark,
                () -> entity.pressure/maxPressure
        ));
    }

    public boolean explodeOnFull = false;

    public float pressureUpSpeed = 1f;

    public float maxPressure = 300;

    public  boolean effectOnPressure = true;

    public Effect pressureEffect = ExpFx.steamPressure;

    public int pressureDamageInterval = 30;

    public float pressureDamage = 10;

    public class BurnCrafterBuild extends GenericCrafterBuild {
        public float pressure;
        public float efficiencyMultiplier;
        int counter = 0;

        @Override
        public float efficiencyScale() {
            if (filterItem != null) {
                float itemEffM = filterItem.efficiencyMultiplier(this);
                if (itemEffM > 0) {
                    return(itemEffM);
                }
            }
            return (1f);
        }
        @Override
        public void updateTile(){
            super.updateTile();
            if(outputLiquid != null){
                if(explodeOnFull && liquids.get(outputLiquid.liquid) >= liquidCapacity * 0.95f){
                    counter++;
                    if(pressure < maxPressure){
                        pressure += pressureUpSpeed;
                    }else {
                        pressure = maxPressure;
                    }
                    if(counter >= pressureDamageInterval & pressure >= maxPressure){
                        counter = 0;
                        damage(pressureDamage);
                        Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                        if(effectOnPressure) {
                            pressureEffect.at(this.x, this.y);
                        }
                    }
                } else {
                    if(explodeOnFull) {
                        if (pressure > 0) {
                            pressure -= pressureUpSpeed * delta();
                        } else {
                            pressure = 0;
                        }
                    }
                }
            }
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(pressure);
            write.i(counter);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            pressure = read.f();
            counter = read.i();
        }
    }
}
