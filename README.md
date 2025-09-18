Meteor Addon (1.21.4)

Versions
- Minecraft: 1.21.4
- Fabric Loader: 0.15.11
- Meteor Client: 0.5.6
- Java: 21

Build
```powershell
cd MeteorAddon
./gradlew.bat build
```

Artifact
- build\libs\MeteorAddon-0.1.0.jar (use this one, not -sources or -dev)

Install (Meteor Client)
- Put the JAR into .minecraft\mods (Fabric + Meteor installed)
- Launch game (1.21.4), open ClickGUI (Right Shift)
- Category: NITRO → modules: ExampleModule, ChunkFinder

Release
- Create a GitHub Release and upload build\libs\MeteorAddon-0.1.0.jar
  - Tag: v0.1.0
  - Title: NitroAddon v0.1.0 (MC 1.21.4)

Notes
- Entry point: com.nitro.meteor.TemplateAddon (fabric.mod.json → "meteor")
- If modules don’t appear, make sure versions match and Meteor loads addons.

