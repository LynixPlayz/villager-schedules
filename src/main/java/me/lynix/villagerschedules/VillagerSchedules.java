package me.lynix.villagerschedules;

import eu.midnightdust.lib.config.MidnightConfig;
import me.lynix.villagerschedules.integrations.MidnightLib;
import net.fabricmc.api.ModInitializer;

public class VillagerSchedules implements ModInitializer {
    @Override
    public void onInitialize() {
        MidnightConfig.init("villagerschedules", MidnightLib.class);
    }
}
