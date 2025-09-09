package expansion.world.additional;

import mindustry.world.Block;

import static mindustry.world.meta.BuildVisibility.*;

public class ResearchCat extends Block {
    public ResearchCat(String name) {
        super(name);
        researchCostMultiplier = 0;
        buildVisibility = debugOnly;
        alwaysUnlocked = true;
    }
    @Override
    public void setStats(){}
}
