package org.noteusoft.mireiyu.thplugin.race.skill;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class THSkillKAM implements Listener {
	//�ړ��X�L���n

	//�U���X�L���n
	///�p�b�V�u�n
	//�_���[�W�n
	public static void kami_faith_attack(Player pl, final Plugin plugin,EntityDamageByEntityEvent event,int boost, FileConfiguration conf) 
	{
		if (boost > 0 && boost < 15)
		{
			if (event.getDamage() > 0.0D && event.getDamage() <= 4.0D)
		    {
		      event.setDamage(event.getDamage() + 1.0D);
		    }
			else if (event.getDamage() > 4.0D && event.getDamage() <= 8.0D)
			{
			      event.setDamage(event.getDamage() + 2.0D);
			}
			else if (event.getDamage() > 8.0D && event.getDamage() <= 12.0D)
			{
			      event.setDamage(event.getDamage() + 3.0D);
			}
			else if (event.getDamage() > 12.0D)
			{
			      event.setDamage(event.getDamage() + 4.0D);
			}
		}
		else if (boost >= 15)
		{
		      event.setDamage(event.getDamage() * 1.5D);
		      conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - 4);
		}
	}
	//�h��n
	public static void kami_faith_defence(Player pl, final Plugin plugin,EntityDamageByEntityEvent event,int boost, FileConfiguration conf) 
	{
		if (boost > 0 && boost < 15)
		{
			if (event.getDamage() > 2.0D && event.getDamage() <= 6.0D)
		    {
		      event.setDamage(event.getDamage() - 1.0D);
		    }
			else if (event.getDamage() > 6.0D && event.getDamage() <= 10.0D)
			{
			      event.setDamage(event.getDamage() - 2.0D);
			}
			else if (event.getDamage() > 10.0D && event.getDamage() <= 14.0D)
			{
			      event.setDamage(event.getDamage() - 3.0D);
			}
			else if (event.getDamage() > 14.0D)
			{
			      event.setDamage(event.getDamage() - 4.0D);
			}
		}
		else if (boost >= 15)
		{
		      event.setDamage(event.getDamage() / 1.5D);
		      conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - 2);
		}
	}
	public static void houzyousin_feed(Player pl, final Plugin plugin,EntityDamageByBlockEvent event) 
	{
		if (event.getCause() == EntityDamageEvent.DamageCause.STARVATION) event.setCancelled(true);
	}
	public static void houzyousin_potato(Player pl, final Plugin plugin,final String pluginpre,EntityDamageByEntityEvent event,int boost) 
	{
		if ((Math.random() >= 0.8D) && ((event.getEntity() instanceof Player)) && boost > 0.0D)
	    {
	      ((Player)event.getEntity()).setFoodLevel(((Player)event.getEntity()).getFoodLevel() - 1);
	      event.getEntity().sendMessage(pluginpre + ChatColor.GOLD + pl.getName() + "�͂������������������Ă����I�I");
	    }
	}
	public static void yakusin_darkside(Player pl, final Plugin plugin,final String pluginpre ,EntityDamageByEntityEvent event) 
	{
		if (event.getDamager() instanceof Player && event.getDamage() >= pl.getHealth())
		{
			Player killplayer = (Player) event.getDamager();
		    if (!killplayer.isDead())
		    {
		      killplayer.sendMessage(pluginpre + ChatColor.DARK_RED + "���Ȃ���_���M����󂯂��I�I�I");
		      killplayer.damage(50.0D);
		    }
		}
	}
}
