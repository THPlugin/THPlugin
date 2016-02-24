package org.noteusoft.mireiyu.thplugin.race.skill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class THSkillYUZ  implements Listener  {
	////�A�N�u�X�L���n
	public static void ninngyo_swimming(Player pl, final Plugin plugin, final PlayerMoveEvent event, int boost)
	{
		///�ړ��X�L���n
		if (pl.getLocation().getBlock().getType() == Material.WATER || pl.getLocation().getBlock().getType() == Material.STATIONARY_WATER)
		{
			if (boost == 1)
			{
				pl.setVelocity(pl.getLocation().getDirection().multiply(2.0D));
			}
			else
			{
				pl.setVelocity(pl.getLocation().getDirection().multiply(0.4D));
			}
		}
	}
	///�����X�L���n
	//�T��(�d�b�n)
	public static void youzyuu_summon_wolf(final Player pl, final Plugin plugin, final String pluginpre,final PlayerInteractEvent event){
	    MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	    pl.setMetadata("using-magic", usingmagic);
		pl.sendMessage(pluginpre + ChatColor.GRAY + "�T�̌Q����Ăяo���E�I�I�I�[��");
		pl.getWorld().playSound(pl.getLocation(), Sound.WOLF_WHINE, 4.0F, -1.0F);
		pl.getWorld().playEffect(pl.getLocation(), Effect.BLAZE_SHOOT, 1, 1);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
			    MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(false));
			    pl.setMetadata("using-magic", usingmagic);
       		  int n = 0;
       		  while (n < 3){
       			  Entity wolf = pl.getWorld().spawnEntity(pl.getLocation(), EntityType.WOLF);
       			  MetadataValue tamedwolf = new FixedMetadataValue(plugin, Boolean.valueOf(true));
       			  wolf.setMetadata("tamedwolf", tamedwolf);
       			  MetadataValue wolfowner = new FixedMetadataValue(plugin, pl.getUniqueId());
       			  wolf.setMetadata("wolfowner", wolfowner);
       			  n++;
       		  }
       		  pl.getWorld().playSound(pl.getLocation(), Sound.WOLF_BARK, 1.0F, 1.0F);
       		  pl.sendMessage(pluginpre + ChatColor.GOLD + "�u�E�I���v�u�E�I���v�u�E�I���v");
       	  }
         }, 40L);
	}
	//�L��()
	public static void siki_summon_ocerot(final Player pl, final Plugin plugin, final String pluginpre,final PlayerInteractEvent event){
	    MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	    pl.setMetadata("using-magic", usingmagic);
		  pl.sendMessage(pluginpre + ChatColor.GRAY + "�����邵���L���Ăяo���j���A");
		  pl.getWorld().playSound(pl.getLocation(), Sound.CAT_MEOW, 4.0F, -1.0F);
		  Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			  public void run(){
				    MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(false));
				    pl.setMetadata("using-magic", usingmagic);
				  int n = 0;
				  while (n < 3){
					  Entity cat = pl.getWorld().spawnEntity(pl.getLocation(), EntityType.OCELOT);
					  MetadataValue tamedcat = new FixedMetadataValue(plugin, Boolean.valueOf(true));
					  cat.setMetadata("tamedcat", tamedcat);
					  MetadataValue catowner = new FixedMetadataValue(plugin, pl.getUniqueId());
					  cat.setMetadata("catowner", catowner);
					  n++;
				  }
				  pl.getWorld().playSound(pl.getLocation(), Sound.CAT_PURREOW, 1.0F, 1.0F);
				  pl.sendMessage(pluginpre + ChatColor.GOLD + "�u�j���A�v�u�j���A�v�u�j���A�v");
			  }
		  }, 40L);
	}
	///�����X�L���n
	//��͏���ŋ�
	public static void youzyu_gainenergy(Player pl, final Plugin plugin, final String pluginpre,final PlayerInteractEvent event){
		pl.sendMessage(pluginpre + ChatColor.GREEN + "��͂Ŏ��g�̔\�͑�����}�����I");
		pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1.0F, 1.0F);
		pl.getWorld().playEffect(pl.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 1);
		double ram = Math.random();
		pl.sendMessage(pluginpre + ChatColor.GREEN + ram);
		if (ram < 0.1D){
			pl.sendMessage(pluginpre + ChatColor.GRAY + "���s�I");
		}else if (ram < 0.2D){
			pl.sendMessage(pluginpre + ChatColor.GREEN + "�ړ��̑��x������ɏオ�����I");
			pl.removePotionEffect(PotionEffectType.SPEED);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
		}else if (ram < 0.3D){
			pl.sendMessage(pluginpre + ChatColor.GREEN + "�����͂��オ�����I");
			pl.removePotionEffect(PotionEffectType.JUMP);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 0));
		}else if (ram < 0.4D){
			pl.sendMessage(pluginpre + ChatColor.GOLD + "�U��̑������オ�����I");
			pl.removePotionEffect(PotionEffectType.FAST_DIGGING);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 250, 0));
		}else if (ram < 0.5D){
			pl.sendMessage(pluginpre + ChatColor.LIGHT_PURPLE + "�y���Đ��\�͂𓾂��I");
			pl.removePotionEffect(PotionEffectType.REGENERATION);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 0));
		}else if (ram < 0.6D){
			pl.sendMessage(pluginpre + ChatColor.RED + "�}�]�Ȑ��_�𓾂�?��?");
			pl.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 250, 1));
		}else if (ram < 0.7D){
			pl.sendMessage(pluginpre + ChatColor.RED + "�T�]�Ȑ��_�𓾂��I");
			pl.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			pl.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 250, 0));
		}else if (ram < 0.8D){
			pl.sendMessage(pluginpre + ChatColor.AQUA + "�V��̗͂𓾂��I");
			if (pl.getWorld().isThundering()){
				pl.sendMessage(pluginpre + ChatColor.YELLOW + "�V�͗����̔@���͂������ĉ��������I");
				pl.removePotionEffect(PotionEffectType.SPEED);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
				pl.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
				pl.removePotionEffect(PotionEffectType.FAST_DIGGING);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1));
			}else if (pl.getWorld().hasStorm()){
				pl.sendMessage(pluginpre + ChatColor.DARK_BLUE + "�V�͉J�̑����b�݂������ĉ��������I");
				pl.removePotionEffect(PotionEffectType.JUMP);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
				pl.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
				pl.removePotionEffect(PotionEffectType.REGENERATION);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0));
			}else{
				pl.sendMessage(pluginpre + ChatColor.GOLD + "�V�͐��V�̋P�������������ĉ��������I");
				pl.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 400, 0));
				pl.removePotionEffect(PotionEffectType.HEAL);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 10));
				pl.removePotionEffect(PotionEffectType.NIGHT_VISION);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0));
			}
		}else if (ram < 0.9D){
			pl.sendMessage(pluginpre + ChatColor.UNDERLINE + "���̗͂𓾂��I");
			if (pl.getWorld().getTime() < 14000L){
				pl.sendMessage(pluginpre + ChatColor.GOLD + "���z�̃G�i�W�[�͋M���ɂ����܂����Đ��͂�^����I");
				pl.removePotionEffect(PotionEffectType.REGENERATION);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
			}else{
				pl.sendMessage(pluginpre + ChatColor.YELLOW + "���������͂��Ȃ��̐S�����킷�ł��낤");
				pl.removePotionEffect(PotionEffectType.CONFUSION);
				pl.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
			}
		}else if (ram < 1.0D){
			pl.sendMessage(pluginpre + ChatColor.GRAY + "���s�I");
		}else{
			pl.sendMessage(pluginpre + ChatColor.GRAY + "���s�I");
		}
		Object casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
		pl.setMetadata("casting", (MetadataValue)casting);
		MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(true));
		pl.setMetadata("using-magic", usingmagic);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
				Player pl = event.getPlayer();
				MetadataValue usingmagic = new FixedMetadataValue(plugin, Boolean.valueOf(false));
				pl.setMetadata("using-magic", usingmagic);
				pl.sendMessage(pluginpre + ChatColor.RED + "�r���̃N�[���_�E���������܂���");
			}
		}, 300L);
	}

}
