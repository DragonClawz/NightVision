package com.randomepicfails.nightvision.commands;

import com.randomepicfails.nightvision.NightVision;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.PotionEffectData;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;
//wat
public class ActivateNightVision implements CommandExecutor {
    public NightVision plugin;
    public ActivateNightVision(NightVision plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        PotionEffect potion = PotionEffect.builder().potionType(PotionEffectTypes.NIGHT_VISION).duration(Integer.MAX_VALUE).amplifier(5).build();

        if (src instanceof Player) {
            Player player = (Player) src;

            if (player.get(Keys.POTION_EFFECTS).isPresent()) {
                List<PotionEffect> effects = player.get(Keys.POTION_EFFECTS).get();
                effects.clear();
                player.offer(Keys.POTION_EFFECTS, effects);
                src.sendMessage(Text.of(TextColors.RED, "Nightvision is now deactivated."));
            } else {
                PotionEffectData effectss = player.getOrCreate(PotionEffectData.class).get();
                effectss.addElement(potion);
                player.offer(effectss);
                src.sendMessage(Text.of(TextColors.RED, "Nightvision is now actived."));
            }

            return CommandResult.success(); // Successful
        } else {
            src.sendMessage(Text.of("The CommandSource is not a Player."));
            return CommandResult.empty(); // Empty is used when not successful.
        }
    }
}
