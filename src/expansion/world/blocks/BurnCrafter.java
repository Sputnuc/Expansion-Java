package expansion.world.blocks;

import arc.util.Nullable;
import mindustry.type.ItemStack;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItemEfficiency;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.consumers.ConsumeItemFlammable;

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

    public class BurnCrafterBuild extends GenericCrafterBuild {
        public float efficiencyMultiplier;

        @Override
        public void updateEfficiencyMultiplier() {
            float scale = this.efficiencyScale();
            if (filterItem != null) {
                float itemEffM = filterItem.efficiencyMultiplier(this);
                if (itemEffM > 0) this.efficiencyMultiplier = itemEffM;
            }

        }
        @Override
        public void updateTile(){
            super.updateTile();
        }

        @Override
        public void updateConsumption() {
            if (this.block.hasConsumers && !this.cheating()) {
                if (!this.enabled) {
                    this.potentialEfficiency = this.efficiency = this.optionalEfficiency = 0.0F;
                    this.shouldConsumePower = false;
                } else {
                    boolean update = this.shouldConsume() && this.productionValid();
                    float minEfficiency = 1.0F;
                    this.efficiency = this.optionalEfficiency = 1.0F;
                    this.shouldConsumePower = true;

                    for(Consume cons : this.block.nonOptionalConsumers) {
                        float result = cons.efficiency(this);
                        if (cons != this.block.consPower && result <= 1.0E-7F) {
                            this.shouldConsumePower = false;
                        }

                        minEfficiency = Math.min(minEfficiency, result);
                    }

                    for(Consume cons : this.block.optionalConsumers) {
                        this.optionalEfficiency = Math.min(this.optionalEfficiency, cons.efficiency(this));
                    }
                    if(efficiencyMultiplier != 0f) {
                        this.efficiency = minEfficiency * efficiencyMultiplier;
                    }else {
                        this.efficiency = minEfficiency;
                    }
                    this.optionalEfficiency = Math.min(this.optionalEfficiency, minEfficiency);
                    this.potentialEfficiency = this.efficiency;
                    if (!update) {
                        this.efficiency = this.optionalEfficiency = 0.0F;
                    }

                    this.updateEfficiencyMultiplier();
                    if (update && this.efficiency > 0.0F) {
                        for(Consume cons : this.block.updateConsumers) {
                            cons.update(this);
                        }
                    }

                }
            } else {
                this.potentialEfficiency = this.enabled && this.productionValid() ? 1.0F : 0.0F;
                this.efficiency = this.optionalEfficiency = this.shouldConsume() ? this.potentialEfficiency : 0.0F;
                this.shouldConsumePower = true;
                this.updateEfficiencyMultiplier();
            }
        }
    }
}
