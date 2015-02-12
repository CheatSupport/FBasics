package org.originmc.fbasics.hooks.factions;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.originmc.hooks.factions.FactionsHook;

import java.util.List;

public class Factions1695 implements FactionsHook {

    private static final String PERMISSION_TERRITORY = "fbasics.bypass.commands.territory";
    private final String msgFaction;
    private final List<String> factions;

    public Factions1695(String msgFaction, List<String> factions) {
        this.msgFaction = msgFaction;
        this.factions = factions;
    }

    public boolean isInTerritory(Location location) {
        FLocation flocation = new FLocation(location);
        Faction faction = Board.getInstance().getFactionAt(flocation);
        return faction.isNone();
    }

    public boolean isInsideClaim(Player player, List<String> factions) {
        if (factions.isEmpty() || player.hasPermission(PERMISSION_TERRITORY))
            return false;

        FLocation flocation = new FLocation(player.getLocation());
        Faction faction1 = Board.getInstance().getFactionAt(flocation);
        Faction faction2 = FPlayers.getInstance().getByPlayer(player).getFaction();

        for (String f : factions)
            if ((f.equalsIgnoreCase("{MEMBER}") && faction1 == faction2) || f.equalsIgnoreCase(faction1.getTag()) || f.equalsIgnoreCase(faction1.getTag().substring(2)))
                return false;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msgFaction));
        return true;
    }

    public boolean isInFaction(Player player, Location location) {
        FLocation flocation = new FLocation(location);
        Faction faction1 = Board.getInstance().getFactionAt(flocation);
        Faction faction2 = FPlayers.getInstance().getByPlayer(player).getFaction();

        for (String faction : factions) {
            if (faction.equalsIgnoreCase("{MEMBER}") && faction1 == faction2) return false;
            if (faction.equalsIgnoreCase(faction1.getTag()) || faction.equalsIgnoreCase(faction1.getTag().substring(2)))
                return false;
        }

        return true;
    }
}
