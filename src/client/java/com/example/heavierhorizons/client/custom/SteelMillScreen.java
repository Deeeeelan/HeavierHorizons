package com.example.heavierhorizons.client.custom;

import com.example.heavierhorizons.HeavierHorizons;
import com.example.heavierhorizons.screen.custom.SteelMillScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SteelMillScreen extends HandledScreen<SteelMillScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(HeavierHorizons.MOD_ID, "textures/gui/steel_mill/steel_mill_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(HeavierHorizons.MOD_ID, "textures/gui/arrow_progress.png");

    public SteelMillScreen(SteelMillScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // half of the RenderSystem methods got removed?

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            Identifier texture2 = Identifier.of("fabric-docs-reference", "textures/gui/test-uv-drawing.png");

            context.drawTexture(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE, x + 73, y + 35, 0, 0, handler.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
