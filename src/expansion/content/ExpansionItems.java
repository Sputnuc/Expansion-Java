package expansion.content;

import arc.graphics.Color;
import expansion.graphic.ExpPal;
import mindustry.type.Item;

import static mindustry.content.Items.*;

public class ExpansionItems {
        public static Item
        //serpulo items
        tebriy, tebriyAlloy, siliconAlloy, cobalt, carbon, wood,
        //erekir items
        calcite, nickel;
        public static void load() {
            tebriy = new Item("tebriy", ExpPal.tebriyPal){{
                cost = 3;
                hardness = 3;
            }};
            tebriyAlloy = new Item("tebriy-alloy", Color.valueOf("d6c6a5")){{
                cost = 4.5f;
                hardness = 3;
            }};
            siliconAlloy = new Item("silicon-alloy", ExpPal.silAlloyPal){{
                cost = 4;
                hardness = 4;
            }};
            cobalt = new Item("cobalt", ExpPal.cobaltPal){{
                cost = thorium.cost + 1;
                hardness = thorium.hardness;
                charge = surgeAlloy.charge/1.5f;
            }};
            carbon = new Item("carbon", ExpPal.carboniumPal){{
                cost = surgeAlloy.cost;
                hardness = siliconAlloy.hardness--;
            }};
            wood = new Item("wood", Color.valueOf("a37656")){{
                cost = 1;
                hardness = 1;
                flammability = 0.5f;
            }};

            calcite = new Item("calcite", ExpPal.calcitePal){{
                cost = 3.5f;
                hardness = 3;
            }};
            nickel = new Item("nickel", ExpPal.nickelPal){{
                cost = 5;
                hardness = 4;
            }};
            serpuloItems.addAll(tebriy, tebriyAlloy, siliconAlloy, cobalt, carbon, wood);
            erekirItems.addAll(calcite, nickel);
        }

}
