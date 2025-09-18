package com.nitro.meteor.modules;

import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.meteorclient.renderer.Renderer3D;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.orbit.EventHandler;

import static com.nitro.meteor.TemplateAddon.NITRO;

public class ExampleModule extends Module {
    private final SettingGroup sg = settings.getDefaultGroup();

    private final Setting<SettingColor> color = sg.add(new ColorSetting.Builder()
        .name("color")
        .defaultValue(new SettingColor(0, 255, 0, 160))
        .build());

    public ExampleModule() {
        // Use custom Nitro category
        super(NITRO, "ExampleModule", "Draws a demo box around the player.");
    }

    @EventHandler
    private void onRender3D(Render3DEvent e) {
        if (!isActive()) return;
        var mc = meteordevelopment.meteorclient.MeteorClient.mc;
        if (mc.player == null) return;
        var pos = mc.player.getBlockPos();
        Renderer3D r = e.renderer;
        r.box(pos, color.get(), color.get(), ShapeMode.Lines, 1);
    }
}

