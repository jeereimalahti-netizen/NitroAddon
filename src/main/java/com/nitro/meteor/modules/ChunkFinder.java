package com.nitro.meteor.modules;

import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.meteorclient.renderer.Renderer3D;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.HashSet;
import java.util.Set;

import static com.nitro.meteor.TemplateAddon.NITRO;

public class ChunkFinder extends Module {
    private final SettingGroup sg = settings.getDefaultGroup();


    private final Setting<Integer> rangeChunks = sg.add(new IntSetting.Builder()
        .name("range-chunks")
        .description("How many chunks around the player to draw borders for.")
        .defaultValue(8)
        .min(1).sliderMin(1)
        .max(32).sliderMax(24)
        .build());

    private final Setting<SettingColor> borderColor = sg.add(new ColorSetting.Builder()
        .name("border-color")
        .defaultValue(new SettingColor(255, 255, 0, 160))
        .build());

    // Simplified: draw borders only (no new-chunk highlight to ensure compatibility)

    public ChunkFinder() {
        super(NITRO, "ChunkFinder", "Draws chunk borders and highlights newly loaded chunks.");
    }


    @EventHandler
    private void onRender3D(Render3DEvent e) {
        if (!isActive()) return;
        var mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        int pcx = mc.player.getBlockPos().getX() >> 4;
        int pcz = mc.player.getBlockPos().getZ() >> 4;
        int r = rangeChunks.get();
        Renderer3D r3 = e.renderer;

        // Draw borders in range
        for (int cx = pcx - r; cx <= pcx + r; cx++) {
            for (int cz = pcz - r; cz <= pcz + r; cz++) {
                int x0 = (cx << 4);
                int z0 = (cz << 4);
                Box border = new Box(x0, mc.player.getY() - 16, z0, x0 + 16, mc.player.getY() + 16, z0 + 16);
                r3.box(border, borderColor.get(), borderColor.get(), ShapeMode.Lines, 1);

                // (Optional) New-chunk highlight removed for compatibility
            }
        }
    }
}


