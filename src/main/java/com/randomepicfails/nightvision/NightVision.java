package com.randomepicfails.nightvision;

import com.google.inject.Inject;
import com.randomepicfails.nightvision.commands.ActivateNightVision;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

@Plugin(
        id = "nightvision",
        name = "NightVision",
        description = "Give Night Vision effect to players",
        authors = {
                "RandomEpicFails"
        },
        version = "0.11"
)

public class NightVision {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("NightVision plugin has successfully loaded.");
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        createAndRegisterCommands();
    }

    public void createAndRegisterCommands() {
        CommandSpec activatenv = CommandSpec.builder()
                .description(Text.of("Activate Night Vision"))
                .permission("nightvision.use")
                .executor(new ActivateNightVision(this))
                .build();
        Sponge.getCommandManager().register(this, activatenv, "nv", "nightvision");
    }
}
