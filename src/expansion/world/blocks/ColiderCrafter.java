package expansion.world.blocks;

import arc.math.Mathf;
import arc.util.Time;
import expansion.world.meta.ExpStat;
import mindustry.world.blocks.production.GenericCrafter;

public class ColiderCrafter extends GenericCrafter{
    // what chance it to produce item/liquid. 1 = 100%
    public double produceChance = 0.5f;

    public ColiderCrafter(String name){
        super(name);
    }

    @Override
    public void setStats(){
        stats.add(ExpStat.produceChance, (Math.round(produceChance * 100)/100f) * 100f + "%");
        super.setStats();
    }

    public class ColiderCrafterBuild extends  GenericCrafterBuild{
        public int craftCount;
        @Override
        public void craft(){
           consume();
           craftCount++;
           if (craftCount > 10000) craftCount = 0;

           long seed = id + craftCount;

            boolean chanced = Mathf.randomSeed(seed, 1f) <= produceChance;

            if(outputItems != null&chanced){
                for(var output : outputItems){
                    for(int i = 0; i < output.amount; i++){
                        offload(output.item);
                    }
                }
            }
            if(wasVisible&chanced){
                craftEffect.at(x, y);
            }
            progress %= 1f;
        }
    }
}
