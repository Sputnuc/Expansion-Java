package expansion.content;

public class ExpLoader {
    public static void load(){
        ExpansionStatuses.load();
        ExpansionItems.load();
        ExpansionLiquids.load();
        ExpansionUnits.load();
        ExpansionBlocks.loadSerpuloContent();
        ExpansionBlocks.loadErekirContent();
        UtBlocks.load();
        ExpansionEnv.load();
        ExpansionTT.serpuloTechTreeLoad();
        ExpansionTT.erekirTechTreeLoad();
    }
}
