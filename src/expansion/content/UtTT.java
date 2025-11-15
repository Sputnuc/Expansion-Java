package expansion.content;

import arc.func.Boolf;
import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.ctype.Content;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

public class UtTT extends ExpansionTT{
    public static void load(){

    }
    //System
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

    private static TechTree.TechNode findNode(TechTree.TechNode root, Boolf<TechTree.TechNode> filter){
        if(filter.get(root)) return root;
        for(TechTree.TechNode node : root.children){
            TechTree.TechNode search = findNode(node, filter);
            if(search != null) return search;
        }
        return null;
    }

    private static void rebaseNode(Content next){
        rebaseNode("serpulo", next);
    }

    /** Moves a node from its parent to the context node. */
    private static void rebaseNode(String tree, Content next){
        TechTree.TechNode oldNode = findNode(TechTree.roots.find(r -> r.name.equals(tree)), n -> n.content == next);
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

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
        TechTree.TechNode node = new TechTree.TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechTree.TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives){
        node(content, requirements, objectives, () -> {});
    }

    private static void node(UnlockableContent content, Seq<Objectives.Objective> objectives){
        node(content, content.researchRequirements(), objectives, () -> {});
    }

    private static void node(UnlockableContent content, ItemStack[] requirements){
        node(content, requirements, Seq.with(), () -> {});
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }
}
