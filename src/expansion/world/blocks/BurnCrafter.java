package expansion.world.blocks;

import arc.util.Nullable;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.consumers.ConsumeItemFlammable;

public class BurnCrafter extends GenericCrafter {
    public BurnCrafter(String name) {
        super(name);
    }

    public @Nullable ConsumeItemFilter filterItem;

    @Override
    public void init(){
        filterItem = findConsumer(c -> c instanceof ConsumeItemFlammable);
    }

    public class BurnCrafterBuild extends GenericCrafterBuild{
        
    }
}
