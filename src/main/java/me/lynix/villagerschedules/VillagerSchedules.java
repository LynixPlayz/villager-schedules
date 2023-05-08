package me.lynix.villagerschedules;

import eu.midnightdust.lib.config.MidnightConfig;
import me.lynix.villagerschedules.integrations.MidnightLib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class VillagerSchedules implements ModInitializer {
    private static KeyBinding keyBinding;

    @Override
    public void onInitialize() {
        MidnightConfig.init("villagerschedules", MidnightLib.class);
    }
}
