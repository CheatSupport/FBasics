package org.originmc.fbasics.patches;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.originmc.fbasics.FBasics;
import org.originmc.fbasics.Permissions;

public class BoatPatch implements Listener {

	@SuppressWarnings("unused")
	private FBasics plugin;
	public BoatPatch(FBasics plugin) {
		this.plugin = plugin;
	}


	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

		Player player = e.getPlayer();

		if (player.isInsideVehicle() && !player.hasPermission(Permissions.boat) && e.getTo().distance(e.getFrom()) > 10.0D) {
			e.setTo(e.getFrom());
		}
	}
}
