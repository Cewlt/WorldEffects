package com.colt.WorldEffects;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WorldEffects extends JavaPlugin implements Listener {
	
	public void onEnable() {
		    saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void move(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		List<String> worlds = getConfig().getStringList("worlds");
		for(String w : worlds) {
			if(p.getWorld().equals(w) &&
				(!p.hasPotionEffect(PotionEffectType.JUMP) || (!p.hasPotionEffect(PotionEffectType.JUMP)))) {
					PotionEffect jump = new PotionEffect(PotionEffectType.JUMP, 100000000, getConfig().getInt("jumpamp"));
					p.addPotionEffect(jump);
					PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 100000000, getConfig().getInt("speedamp"));
					p.addPotionEffect(speed);
			}
		}
	}
}
