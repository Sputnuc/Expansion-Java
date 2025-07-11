package expansion;

import arc.*;
import arc.util.*;
import expansion.content.ExpLoader;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ExpansionMain extends Mod{

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
        ExpLoader.load();
    }
}