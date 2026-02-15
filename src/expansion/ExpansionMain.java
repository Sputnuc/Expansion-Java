package expansion;

import arc.*;
import expansion.content.ExpLoader;
import expansion.utils.UnlockContent;
import mindustry.content.Blocks;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;

import static arc.Core.bundle;
import static mindustry.Vars.ui;

public class ExpansionMain extends Mod{
    public ExpansionMain(){
    }

    @Override
    public void loadContent(){
        ExpLoader.load();
    }
}