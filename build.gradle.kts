plugins {
    id("fabric-loom") version "1.5.7"
    id("maven-publish")
}

val minecraftVersion = "1.21.4"
val loaderVersion = "0.15.11"
val yarnMappings = "1.21.4+build.1"
val meteorVersion = "0.5.6"

group = "com.nitro"
version = "0.1.0"

repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://maven.meteordev.org/releases")
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    modImplementation("meteordevelopment:meteor-client:$meteorVersion")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

tasks.processResources {
    inputs.property("version", version)
    filesMatching("fabric.mod.json") { expand(mapOf("version" to version)) }
}

