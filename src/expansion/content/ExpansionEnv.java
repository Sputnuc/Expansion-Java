package expansion.content;

import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;

import static expansion.content.ExpansionItems.*;

public class ExpansionEnv {
    public static Block
    oreTebriy, oreCobalt,
    //erekir ores
    oreCalcite, oreNickel;
    public static void load(){
        oreTebriy = new OreBlock("tebriy-ore"){{
            itemDrop = tebriy;
            variants = 3;
        }};
        oreCobalt = new OreBlock("cobalt-ore"){{
            itemDrop = cobalt;
            variants = 3;
        }};
        oreCalcite = new OreBlock("calcite-ore"){{
            itemDrop = calcite;
            variants = 3;
        }};
        oreNickel = new OreBlock("nickel-ore"){{
            itemDrop = calcite;
            variants = 3;
        }};
    }
}
