package expansion.content;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.content.TechTree.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static expansion.content.ExpansionBlocks.*;
import static expansion.content.ExpansionItems.*;
import static expansion.content.ExpansionUnits.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.UnitTypes.*;
import static mindustry.content.Items.*;

public class ExpansionTT {
    static TechTree.TechNode context = null;
    public static void load(){
        //Turrets
        vanillaNode(salvo, () -> {
            node(collapseTurret);
        });
        vanillaNode(tsunami, ()->node(flood));
        vanillaNode(scorch, () ->{
            node(incandescence);
        });
        vanillaNode(spectre, ()->{
            node(retribution, Seq.with(new Research(collapseTurret)));
        });
        vanillaNode(combustionGenerator, ()-> node(combustionPowerPlant));
        vanillaNode(steamGenerator, () -> {
            node(boiler);
            node(steamTurbine, Seq.with(new Research(boiler)));
        });
        vanillaNode(siliconSmelter, ()-> {
            node(tebriyAlloySmelter, ()->{
                node(tebriyAlloyForge);
            });
            node(tebriySynthezer, Seq.with(new Research(tebriyAlloySmelter)));
        });
        vanillaNode(tebriyAlloySmelter, ()->{
            node(siliconAlloyFurnace, ()->{
                node(siliconAlloyOven);
                node(collider);
            });
        });
        //Unit blocks
        vanillaNode(groundFactory, ()->{
            node(baseFactory, ()->{
                node(upgradeReconstructor, () ->{
                    node(improvingReconstructor, ()->{
                        node(progressiveReconstructor);
                    });
                });
                //Units
                node(warrior, ()->{
                    //Fly tree
                    node(sight, ()->{
                        node(glare, () ->{
                            node(sunset, Seq.with(new Research(improvingReconstructor)), ()->{
                                node(storm, Seq.with(new Research(progressiveReconstructor)));
                            });
                        });
                    });
                    //Naval tree
                    node(dew, ()->{
                        node(deep, ()->{
                            node(pressure, Seq.with(new Research(improvingReconstructor)), ()->{
                                node(aurora, Seq.with(new Research(progressiveReconstructor)));
                            });
                        });
                    });
                    //Tank tree
                    node(armada, Seq.with(new Research(upgradeReconstructor)), ()->{
                        node(flame, Seq.with(new Research(improvingReconstructor)), ()->{
                            node(chaos, Seq.with(new Research(progressiveReconstructor)));
                        });
                    });
                });
            });
        });
        //items
        vanillaNode(sporePod, ()->{
            node(wood);
        });
        vanillaNode(copper, ()->{
            node(tebriy, ()->{
                node(tebriyAlloy, ()->{
                    node(siliconAlloy, ()->{
                        node(cobalt, ()->{
                            node(carbon);
                        });
                    });
                });
            });
        });
        erekirNode(tungsten, ()->{
            node(nickel, ()->{
                node(calcite);
            });
        });
    }

    //Stolen from MeepOfFaith/Additional Logistic
    //Sorry :/
    private static void vanillaNode(UnlockableContent parent, Runnable children){
        vanillaNode("serpulo", parent, children);
    }


    private static void vanillaNode(String tree, UnlockableContent parent, Runnable children){
        context = findNode(TechTree.roots.find(r -> r.name.equals(tree)), n -> n.content == parent);
        children.run();
    }

    private static void erekirNode(UnlockableContent parent, Runnable children){
        erekirNode("erekir", parent, children);
    }

    private static void erekirNode(String tree, UnlockableContent parent, Runnable children){
        context = findNode(TechTree.roots.find(r -> r.name.equals(tree)), n -> n.content == parent);
        children.run();
    }

    private static TechNode findNode(TechNode root, Boolf<TechNode> filter){
        if(filter.get(root)) return root;
        for(TechNode node : root.children){
            TechNode search = findNode(node, filter);
            if(search != null) return search;
        }
        return null;
    }

    private static void rebaseNode(Content next){
        rebaseNode("serpulo", next);
    }

    /** Moves a node from its parent to the context node. */
    private static void rebaseNode(String tree, Content next){
        TechNode oldNode = findNode(TechTree.roots.find(r -> r.name.equals(tree)), n -> n.content == next);
        oldNode.parent.children.remove(oldNode);
        context.children.add(oldNode);
        oldNode.parent = context;

        if(oldNode.researchCostMultipliers != context.researchCostMultipliers){
            //Reset multipliers
            ItemStack[] req = ItemStack.copy(oldNode.requirements);
            if(oldNode.researchCostMultipliers.size > 0){
                for(ItemStack itemStack : req){
                    itemStack.amount /= oldNode.researchCostMultipliers.get(itemStack.item, 1f);
                }
            }

            //Apply new multipliers
            if(context.researchCostMultipliers.size > 0){
                for(ItemStack itemStack : req){
                    itemStack.amount *= context.researchCostMultipliers.get(itemStack.item, 1f);
                }
            }
            oldNode.requirements = req;
        }
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives){
        node(content, requirements, objectives, () -> {});
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives){
        node(content, content.researchRequirements(), objectives, () -> {});
    }

    private static void node(UnlockableContent content, ItemStack[] requirements){
        node(content, requirements, Seq.with(), () -> {});
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }
}
