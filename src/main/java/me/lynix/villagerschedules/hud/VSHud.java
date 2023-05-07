package me.lynix.villagerschedules.hud;

import me.lynix.villagerschedules.helpers.Colors;
import me.lynix.villagerschedules.integrations.MidnightLib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;


@Environment(EnvType.CLIENT)
public class VSHud {
    private final MinecraftClient client;
    private final TextRenderer textRenderer;
    private ClientPlayerEntity player;
    private MatrixStack matrices;

    public VSHud(MinecraftClient client) {
        this.client = client;
        textRenderer = client.textRenderer;
    }

    public void render(MatrixStack matrices) {
        if(client.options.debugEnabled) return;

        client.getProfiler().push("villagerSchedulesHud");

        player = client.player;
        this.matrices = matrices;

        drawLabels();

        client.getProfiler().pop();
    }

    public void drawLabels()
    {
        assert MinecraftClient.getInstance().player != null;
        int time = (int) (MinecraftClient.getInstance().player.world.getTimeOfDay() % 24000L);
        String labelText = "Sleeping";
        String unemployedLabelText = "Sleeping";
        String childLabelText = "Sleeping";
        if (time <= 12000)
        {
            labelText = "Wander";
            if(time <= 11000)
            {
                labelText = "Gather";
                if(time <= 9000)
                {
                    labelText = "Work";
                    if(time <= 2000)
                    {
                        labelText = "Wander";
                        if(time <= 10)
                        {
                            labelText = "Sleeping";
                        }
                    }
                }
            }
        }
        if (time <= 12000)
        {
            unemployedLabelText = "Wander";
            if(time <= 11000)
            {
                unemployedLabelText = "Gather";
                if(time <= 9000)
                {
                    unemployedLabelText = "Wander";
                    if(time <= 2000)
                    {
                        unemployedLabelText = "Wander";
                        if(time <= 10)
                        {
                            unemployedLabelText = "Sleeping";
                        }
                    }
                }
            }
        }
        if (time <= 12000)
        {
            childLabelText = "Play";
            if(time <= 10000)
            {
                childLabelText = "Wander";
                if(time <= 6000)
                {
                    childLabelText = "Play";
                    if(time <= 3000)
                    {
                        childLabelText = "Wander";
                        if(time <= 10)
                        {
                            childLabelText = "Sleeping";
                        }
                    }
                }
            }
        }
        int maxLineHeight = Math.max(10, textRenderer.getWidth(labelText));
        maxLineHeight = (int) (Math.ceil(maxLineHeight / 5.0D + 0.5D) * 5);
        int scaleHeight = client.getWindow().getScaledHeight();
        int sprintingTop = scaleHeight - maxLineHeight;
        int fullTextHeight = MidnightLib.y;
        //Employed
        textRenderer.drawWithShadow(matrices, "Employed: ", MidnightLib.x, fullTextHeight, Colors.white);
        textRenderer.drawWithShadow(matrices, labelText, textRenderer.getWidth("Employed: ") + ((float) textRenderer.getWidth(labelText) / 2) + MidnightLib.x, fullTextHeight, Colors.getVillagerTextColor(labelText));
        //Unemployed
        textRenderer.drawWithShadow(matrices, "Unemployed: ", MidnightLib.x, fullTextHeight + 10, Colors.white);
        textRenderer.drawWithShadow(matrices, unemployedLabelText, textRenderer.getWidth("Unemployed: ") + ((float) textRenderer.getWidth(unemployedLabelText) / 2) + MidnightLib.x, 10 + fullTextHeight, Colors.getVillagerTextColor(unemployedLabelText));
        //Child
        textRenderer.drawWithShadow(matrices, "Child: ", MidnightLib.x, fullTextHeight + 20, Colors.white);
        textRenderer.drawWithShadow(matrices, childLabelText, textRenderer.getWidth("Child: ") + ((float )textRenderer.getWidth(childLabelText) / 2) + MidnightLib.x, 20 + fullTextHeight, Colors.getVillagerTextColor(childLabelText));
    }
}
