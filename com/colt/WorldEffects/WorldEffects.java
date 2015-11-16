package com.colt.WorldEffects;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WorldEffects extends JavaPlugin implements Listener {
	
	PotionEffect effect;
	
	List<String> worlds = getConfig().getStringList("worlds");
	List<String> effects = getConfig().getStringList("effects");
	
	World world;
	int dur;
	int amp;
	
	public void onEnable() {
        	Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            		@Override
            		public void run() {
                		checkEffects();
            		}
		}, 100L, 60L);
	}
	
	public void checkEffects() {
		for (Player p : getServer().getOnlinePlayers()) {
			for(String w : worlds) {
				if(Bukkit.getWorld(w) != null) {
					world = Bukkit.getWorld(w);
					if(p.getWorld().equals(world)) {
						for(String po : effects) {
							String inf[] = po.split(":");
							if(inf.length == 3) {
								if(PotionEffectType.getByName(inf[0].toUpperCase()) != null) {
									PotionEffectType pot = PotionEffectType.getByName(inf[0].toUpperCase());
									try {
										dur = Math.min(Integer.parseInt(inf[1]), Integer.MAX_VALUE);
									} catch(NumberFormatException e1) {
										dur = Integer.MAX_VALUE;
									} 
									try {
										amp = Math.min(Integer.parseInt(inf[2]), 255);
									} catch(NumberFormatException e1) {
										amp = 255;
									}
									if(!p.hasPotionEffect(pot)) {
										effect = new PotionEffect(pot, dur, amp);
										p.addPotionEffect(effect);	
									} else {
										p.removePotionEffect(pot);
										effect = new PotionEffect(pot, dur, amp);
										p.addPotionEffect(effect);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
