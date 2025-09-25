package expansion.world.meta;

import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.Stats;

public class ExpStat {
    public static final Stat
        regenerationPerTick = new Stat("expansion-regenPerTick", StatCat.function),
        canCounters = new Stat("expansion-can-counters", StatCat.function),
        reloadFrom = new Stat("expansion-reload-at-start", Stat.reload.category),
        reloadTo = new Stat("expansion-reload-at-end", Stat.reload.category),
        counterWallCooldown = new Stat("expansion-counter-wall-cooldown", StatCat.function),
        overrides = new Stat("expansion-overrides"),
        produceChance = new Stat("expansion-result-chance", StatCat.function),
        counterWallCooldownOnDestroy = new Stat("expansion-counter-wall-cooldown-on-destroy", StatCat.function);
}