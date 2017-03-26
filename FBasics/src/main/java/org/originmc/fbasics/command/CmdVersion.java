package org.originmc.fbasics.command;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;
import org.originmc.fbasics.FBasics;

import static org.bukkit.ChatColor.*;

public final class CmdVersion extends CommandExecutor {

    public static final ImmutableList<String> VERSION_MESSAGE = ImmutableList.of(
            DARK_GREEN + "DreamRaids " + DARK_GRAY + "// " + WHITE + "Using version 1.1",
            DARK_GREEN + "DreamRaids " + DARK_GRAY + "// " + WHITE + "Written by Road",
            DARK_GREEN + "DreamRaids " + DARK_GRAY + "// " + WHITE + "Website: " + GRAY + "http://dreamraids.co/"
    );

    public CmdVersion(FBasics plugin, CommandSender sender, String[] args, String permission) {
        super(plugin, sender, args, permission);
    }

    @Override
    public boolean perform() {
        for (String msg : VERSION_MESSAGE) {
            getSender().sendMessage(msg.replace("{version}", getPlugin().getDescription().getVersion()));
        }
        return true;
    }

}
