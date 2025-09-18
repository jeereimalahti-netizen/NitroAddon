package com.nitro.meteor;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.Category;

public class TemplateAddon extends MeteorAddon {
    public static final Category NITRO = new Category("Nitro");

    @Override
    public void onInitialize() {
        Modules.get().add(new com.nitro.meteor.modules.ExampleModule());
        Modules.get().add(new com.nitro.meteor.modules.ChunkFinder());
    }

    @Override
    public String getPackage() {
        return "com.nitro.meteor";
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(NITRO);
    }
}

