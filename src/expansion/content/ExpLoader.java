package expansion.content;

public class ExpLoader {
    public static void load(){
        ExpansionStatuses.load();
        ExpansionItems.load();
        ExpansionLiquids.load();
        ExpansionUnits.load();
        ExpansionBlocks.load();
        UtBlocks.load();
        ExpansionEnv.load();
        ExpansionTT.load();
    }
}
