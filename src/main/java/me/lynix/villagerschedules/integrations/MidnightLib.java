package me.lynix.villagerschedules.integrations;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class MidnightLib extends MidnightConfig {
    @Entry(category = "enabled", name = "Enabled") public static boolean enabled = true;
    @Entry(category = "position", name = "x") public static int x = 2;
    @Entry(category = "position", name = "y") public static int y = 0;
}
