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
        Events.on(ClientLoadEvent.class, e -> {
            loadSetting();
            UnlockContent.unlock();
        });
    }

    @Override
    public void loadContent(){
        ExpLoader.load();
    }
    public void loadSetting(){
        ui.settings.addCategory(bundle.get("setting.exp-setting-cat"), Icon.book, t -> {
            t.checkPref("unlock-content", false);
        });
    }
}