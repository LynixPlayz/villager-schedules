package me.lynix.villagerschedules.hud;

import me.lynix.villagerschedules.helpers.Colors;
import me.lynix.villagerschedules.integrations.MidnightLib;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;


@Environment(EnvType.CLIENT)
public class VSHud {
    private final MinecraftClient client;
    private ClientPlayerEntity player;
    private DrawContext matrices;
    private TextRenderer textRenderer;

    public VSHud(MinecraftClient client) {
        this.client = client;
        textRenderer = client.textRenderer;
    }

    public void render(DrawContext matrices) {
        if(client.getDebugHud().shouldShowDebugHud()) return;

        client.getProfiler().push("villagerSchedulesHud");

        player = client.player;
        this.matrices = matrices;
        if(MidnightLib.enabled) {
            drawLabels(matrices);
        }
        client.getProfiler().pop();
    }

    public void drawLabels(DrawContext context)
    {
        assert MinecraftClient.getInstance().player != null;
        int time = (int) (MinecraftClient.getInstance().player.getWorld().getTimeOfDay() % 24000L);
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
        context.drawTextWithShadow(textRenderer, "Employed: ", MidnightLib.x, fullTextHeight, Colors.white);
        context.drawTextWithShadow(textRenderer, labelText,(int) (textRenderer.getWidth("Employed: ") + ((float) textRenderer.getWidth(labelText) / 2)) + MidnightLib.x, fullTextHeight, Colors.getVillagerTextColor(labelText));
        //Unemployed
        context.drawTextWithShadow(textRenderer, "Unemployed: ", MidnightLib.x, fullTextHeight + 10, Colors.white);
        context.drawTextWithShadow(textRenderer, unemployedLabelText,(int) (textRenderer.getWidth("Unemployed: ") + ((float) textRenderer.getWidth(unemployedLabelText) / 2)) + MidnightLib.x, 10 + fullTextHeight, Colors.getVillagerTextColor(unemployedLabelText));
        //Child
        context.drawTextWithShadow(textRenderer, "Child: ", MidnightLib.x, fullTextHeight + 20, Colors.white);
        context.drawTextWithShadow(textRenderer, childLabelText, (int) (textRenderer.getWidth("Child: ") + ((float )textRenderer.getWidth(childLabelText) / 2)) + MidnightLib.x, 20 + fullTextHeight, Colors.getVillagerTextColor(childLabelText));
    }
}
