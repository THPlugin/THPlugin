package org.noteusoft.mireiyu.thplugin.race;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.noteusoft.mireiyu.thplugin.THPlugin;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillAKM;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillGlobal;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillKAM;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillNNG;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillSIR;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillYUM;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillYUS;
import org.noteusoft.mireiyu.thplugin.race.skill.THSkillYUZ;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;

@SuppressWarnings("unused")
public class THRaces implements Listener 
{
    static Plugin plugin = THPlugin.plugin;
    static String pluginpre = THPlugin.thrpre;
    static FileConfiguration conf = THPlugin.conf;
    static File file = THPlugin.configfile;
    ////�A�N�V����
    ///��N���N�n
    public void chat(final AsyncPlayerChatEvent event) {
        //�O�u���Ɏ푰����������
        Player pl = event.getPlayer();
        THSkillGlobal.global_chat(pl, plugin, event);
    }

    public void quit(final PlayerQuitEvent event) {
        //���^�폜��
        Player pl = event.getPlayer();
        THSkillGlobal.global_quit(pl, plugin, event);
    }

    public void join(final PlayerJoinEvent event) {
        //�����[�^��
        Player pl = event.getPlayer();
        THSkillGlobal.global_join(pl, plugin, event);
    }

    public void respawn(final PlayerRespawnEvent event) {
        //���X�|�����g���K�[�Ƃ��đ�̗͒���
        Player pl = event.getPlayer();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
        if (race.equalsIgnoreCase("youma") || race.equalsIgnoreCase("kappa") || race.equalsIgnoreCase("tenngu"))
        {
        	THSkillYUM.youma_respawnhealth(pl, plugin, event);
        }
        else if(race.equalsIgnoreCase("kennyou"))
        {
        	THSkillYUM.kennyou_respawnhealth(pl, plugin, event);
        }
        else
        {
        	THSkillGlobal.global_respawnhealth(pl, plugin, event);
        }
    }

    public void move(final PlayerMoveEvent event) {

        Player pl = event.getPlayer();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
        int mana = 0;
        //�l�������j�������ݗL�j�i�u�[�X�^�[���L
        mana = 1;
        if (race.equalsIgnoreCase("ninngen") && conf.getDouble("user." + pl.getUniqueId() + ".spilit") > mana) {
            int boost = 0;
            if (pl.getMetadata("spilituse").get(0).asInt() > 0) boost = 1;
			THSkillYUZ.ninngyo_swimming(pl, plugin, event,boost);
			if (boost == 1)
			{
	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	            try {
	    			conf.save(file);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
			}
        }
    }
    
    @SuppressWarnings("deprecation")
    public void togglesneak(final PlayerToggleSneakEvent event) {
        Player pl = event.getPlayer();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
        int mana = 0;
	    //�d���H�΂���
	    mana = 5;
	    if (race.equalsIgnoreCase("yousei") || race.equalsIgnoreCase("kobito") || race.equalsIgnoreCase("kibito") || race.equalsIgnoreCase("satori")&& conf.getDouble("user." + pl.getUniqueId() + ".spilit") > mana)
	    {
	        if (!pl.isOnGround() && pl.isSneaking())
	        {
        		THSkillYUS.yousei_feather(pl, plugin, event);
	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	            try {
	    			conf.save(file);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	        }
	    }
        //��l�̕ǔ�
	    mana = 10;
        if (race.equalsIgnoreCase("sennnin")) {
            if ((!pl.isOnGround()) && (pl.isSneaking()) && conf.getDouble("user." + pl.getUniqueId() + ".spilit") >= mana) {
                THSkillNNG.sennnin_passthough(pl, plugin,event);
	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	            try {
	    			conf.save(file);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
            }
        }
    }
    
    ///�N���N�n
    public void interactentity(final PlayerInteractEntityEvent event) {
        //��l�ԑ��l�K���O�u���L
    	int mana = 0;
        final String pluginpre = THPlugin.thrpre;
        final Player pl = event.getPlayer();
        Material handitem = pl.getItemInHand().getType();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
        if (race.equalsIgnoreCase("ninngen") == false && race.equalsIgnoreCase("mazyo") == false && race.equalsIgnoreCase("houraizin") == false && race.equalsIgnoreCase("gennzinnsin") == false && race.equalsIgnoreCase("sibito") == false && race.equalsIgnoreCase("sennninn") == false) {
        	if (event.getRightClicked() instanceof Villager) THSkillGlobal.global_no_ninngen(pl, plugin, pluginpre, event);
        }
        //�S�̉��́i�������ݗL�j�i�O�u���L(�r���L)
    	mana = 30;
        if (race.equalsIgnoreCase("oni")) 
        {
        	if(magic_iscastable(pl , mana,"�����\�����I") && handitem == Material.IRON_PICKAXE && event.getRightClicked() instanceof LivingEntity)
        	{
        		final LivingEntity entity = (LivingEntity) event.getRightClicked();
                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                pl.setMetadata("casting", casting);
                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
                try {
        			conf.save(file);
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                pl.getWorld().playSound(pl.getLocation(), Sound.ANVIL_LAND, 1, 2);
        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
        		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
                    public void run() {
                        THSkillAKM.oni_kairiki(pl, plugin, pluginpre, event, entity);
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                        pl.setMetadata("casting", casting);
                    }
                }, 20L);
            }
        }
        //�z���S�̋z���i�������ݗL�j�i�O�u���L(�r���L)
    	mana = 45;
        if (race.equalsIgnoreCase("kyuuketuki")) 
        {
        	if(magic_iscastable(pl , mana,"����\�����I") && handitem == Material.WOOD_PICKAXE && event.getRightClicked() instanceof LivingEntity)
        	{
        		final LivingEntity entity = (LivingEntity) event.getRightClicked();
                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                pl.setMetadata("casting", casting);
                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
                try {
        			conf.save(file);
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                pl.getWorld().playSound(pl.getLocation(), Sound.ANVIL_LAND, 1, 2);
        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
        		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
                    public void run() {
                        THSkillAKM.kyuuketuki_drain(pl, plugin, pluginpre, event, entity);
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                        pl.setMetadata("casting", casting);
                    }
                }, 50L);
            }
        }
    }

    public void interact(final PlayerInteractEvent event) {
        final Player pl = event.getPlayer();
        Material handitem = pl.getItemInHand().getType();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
    	int mana = 0;
        ///�E�N��
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	///�O���[�o��
    		Material dust_is_ok = pl.getItemInHand().getType() ; 
    		if (dust_is_ok == Material.SUGAR || dust_is_ok == Material.SULPHUR || dust_is_ok == Material.GLOWSTONE_DUST)
    		{
    			THSkillGlobal.global_charge_mana(pl,plugin,pluginpre,event);
    		}
            //�l�Ԗ����̉񕜖��@�i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("mazyo")||race.equalsIgnoreCase("ninngen") ) {
            	mana = 25;
            	if(magic_iscastable(pl , mana,"�r���I") && handitem == Material.STICK)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillNNG.mazyo_heal(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 20L);
                }
                //�����̕����@�i�������ݗL�j�i�O�u���L(�r���L)
            	mana = 30;
            	if(magic_iscastable(pl , mana,"�r���I") && handitem == Material.WOOD_SWORD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    	                	
                            public void run() {
                                THSkillNNG.mazyo_wind(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 10L);
            	}
                //�����̓y���@�i�������ݗL�j�i�O�u���L(�r���L)
            	mana = 45;
                if (magic_iscastable(pl , mana,"�r���I") && handitem == Material.STONE_SWORD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                THSkillNNG.mazyo_dirt(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 60L);
                }
                //�����̉Ζ��@�i�������ݗL�j�i�O�u���L(�r���L)
            	mana = 30;
                if (magic_iscastable(pl , mana,"�r���I") && handitem == Material.IRON_SWORD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    	                	
                            public void run() {
                                THSkillNNG.mazyo_fire(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 20L);
                }
                //�����̐����@�i�������ݗL�j�i�O�u���L(�r���L)
            	mana = 60;
                if (magic_iscastable(pl , mana,"�r���I") && handitem == Material.DIAMOND_SWORD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    	                	
                            public void run() {
                                THSkillNNG.mazyo_water(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 50L);
                            
                }
                //�����̗����@�i�������ݗL�j�i�O�u���L(�r���L)
            	mana = 70;
                if (magic_iscastable(pl , mana,"�r���I") && handitem == Material.GOLD_SWORD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                THSkillNNG.mazyo_thunder(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 70L);
                }
            }
            ///�V��_���������ݗL�j�i�O�u���L�i�u�[�X�^�[���L
            mana = 40;
            if (race.equalsIgnoreCase("tenngu") && conf.getDouble("user." + pl.getUniqueId() + ".spilit") > mana) {
                int boost = 0;
                if (pl.getMetadata("spilituse").get(0).asInt() > 0 && handitem == Material.FEATHER)
                {
                	boost = pl.getMetadata("spilituse").get(0).asInt();
                	THSkillYUM.tenngu_kamikaze(pl, plugin,pluginpre, event , boost);
    	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	            try {
    	    			conf.save(file);
    	    		} catch (IOException e) {
    	    			e.printStackTrace();
    	    		}
            		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                }
            }
            ///�d���̃C�����[�W�����i�������ݗL�j�i�O�u���L(�r���L)
            mana = 20;
            if (race.equalsIgnoreCase("yousei") || race.equalsIgnoreCase("kobito") || race.equalsIgnoreCase("kibito") || race.equalsIgnoreCase("satori")) 
            {
            	if(magic_iscastable(pl , mana,"���̑����f�����I") && handitem == Material.GOLD_SPADE)
                {
    	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	            try {
    	    			conf.save(file);
    	    		} catch (IOException e) {
    	    			e.printStackTrace();
    	    		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
            		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
            		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                    	THSkillYUS.yousei_illusion(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 30L);
                }
            }
            ///���l�̓ŎU�z�i�������ݗL�j�i�O�u���L(�r���L)
            mana = 35;
            if (race.equalsIgnoreCase("kibito")) 
            {
            	if(magic_iscastable(pl , mana,"�Ԃ͊J������I") && handitem == Material.STONE_SPADE)
                {
    	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	            try {
    	    			conf.save(file);
    	    		} catch (IOException e) {
    	    			e.printStackTrace();
    	    		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.SILVERFISH_WALK, 1, -1);
            		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
            		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                    	THSkillYUS.kibito_venom(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 45L);
                }
            }
            ///�z���S�̃J���t���[�W���i�������ݗL�j�i�O�u���L(�r���L)
            mana = 25;
            if (race.equalsIgnoreCase("kyuuketuki")) 
            {
            	if(magic_iscastable(pl , mana,"�p��ς�����I") && handitem == Material.WOOD_PICKAXE)
                {
    	            conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	            try {
    	    			conf.save(file);
    	    		} catch (IOException e) {
    	    			e.printStackTrace();
    	    		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BAT_HURT, 1, 0);
            		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                    	THSkillAKM.kyuuketuki_vamp(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 5L);
                }
            }
        	mana = 40;
        	//�g���@�i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("akuma")||race.equalsIgnoreCase("oni")||race.equalsIgnoreCase("kyuuketuki") ) {
            	if(magic_iscastable(pl , mana,"�r���I") && handitem == Material.STONE_PICKAXE)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillAKM.akuma_red_magic(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 80L);
                }
            }
            mana = 40;
            //����̏����i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("seirei")||race.equalsIgnoreCase("hannrei")||race.equalsIgnoreCase("sourei")||race.equalsIgnoreCase("onnryou") ) {
            	if(magic_iscastable(pl , mana,"�����I") && handitem == Material.STONE_HOE)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillSIR.seirei_summon(pl, plugin,pluginpre, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 100L);
                }
            }
            mana = 5;
            //����̌��e�i�������ݗL�j(�r���L)
            if (race.equalsIgnoreCase("seirei")||race.equalsIgnoreCase("hannrei")||race.equalsIgnoreCase("sourei")||race.equalsIgnoreCase("onnryou") ) {
            	if(magic_iscastable(pl , mana,"�r���I") && handitem == Material.WOOD_HOE)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillSIR.seirei_lightball(pl, plugin,event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 30L);
                }
            }
            mana = 50;
            //����̏����i�������ݗL�j(�r���L)
            if (race.equalsIgnoreCase("hannrei")) {
            	if(magic_iscastable(pl , mana,"�s���I����I") && handitem == Material.GOLD_HOE)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.GHAST_CHARGE, 1, 1);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillSIR.hannrei_hannrei_ball(pl, plugin, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 5L);
                }
            }
            mana = 60;
            //����̃I�[�P�X�g���i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("sourei")) {
            	if(magic_iscastable(pl , mana,"���b�c�I�[�P�X�g���I�I") && handitem == Material.IRON_HOE)
            	{
	                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
	                pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.NOTE_SNARE_DRUM, 1, 0);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {                	
	                    public void run() {
	                        THSkillSIR.hannrei_hannrei_ball(pl, plugin, event);
	                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                        pl.setMetadata("casting", casting);
	                    }
	                }, 120L);
                }
            }
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            ///�d�b�l���b�l
            if (race.equalsIgnoreCase("youzuu") || race.equalsIgnoreCase("ninngyo") || race.equalsIgnoreCase("zyuuzin")) {
            	 //�d�b�̘T�����i�������ݗL�j�i�O�u���L(�r���L)
                mana = 15;
                if (magic_iscastable(pl , mana,"�����I�I") && handitem == Material.FISHING_ROD) {
                        MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                        pl.setMetadata("casting", casting);
    	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
    	                try {
    	        			conf.save(file);
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
    	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    	                	
                            public void run() {
                                THSkillYUZ.youzyuu_summon_wolf(pl, plugin,pluginpre, event);
                                MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
                                pl.setMetadata("casting", casting);
                            }
                        }, 30L);
                }
            }
          //���̃l�R�����i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("siki") && conf.getDouble("user." + pl.getUniqueId() + ".spilit") > 20.0 ) {
                mana = 15;
                if (magic_iscastable(pl , mana ,"�����I�I") && handitem == Material.FISHING_ROD) 
            		{
                    MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                    pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.BLAZE_BREATH, 1, 2);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
	                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		                	
	                        public void run() {
	                            THSkillYUZ.siki_summon_ocerot(pl, plugin,pluginpre, event);
	                            MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                            pl.setMetadata("casting", casting);
		                        }
	                        }, 40L);
            		}
 
            }
            //�d�b�̋����i�������ݗL�j�i�O�u���L(�r���L)
            if (race.equalsIgnoreCase("youzyuu") || race.equalsIgnoreCase("zyuuzin") || race.equalsIgnoreCase("ninngyo") || race.equalsIgnoreCase("siki")) {
                mana = 35;
                if (magic_iscastable(pl , mana ,"�������@�I") && handitem == Material.BOW) 
            		{
                    MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(true));
                    pl.setMetadata("casting", casting);
	                conf.set("user." + pl.getUniqueId() + ".spilit", conf.getDouble("user." + pl.getUniqueId() + ".spilit") - mana);
	                try {
	        			conf.save(file);
	        		} catch (IOException e) {
	        			e.printStackTrace();
	        		}
	                pl.getWorld().playSound(pl.getLocation(), Sound.DONKEY_ANGRY, 1, 1);
	        		pl.sendMessage(pluginpre + ChatColor.GREEN + "���" + ChatColor.LIGHT_PURPLE + conf.getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    	                	
                            public void run() {
                                THSkillYUZ.youzyu_gainenergy(pl, plugin,pluginpre, event);
	                            MetadataValue casting = new FixedMetadataValue(plugin, Boolean.valueOf(false));
	                            pl.setMetadata("casting", casting);
                            }
                        }, 15L);
            		}
            }
        }
    }
    //TODO �V�N�����v���O�C���҂�
    /*
	@EventHandler(priority=EventPriority.HIGH)
  	public void on_simpleclans_crackshot_hook(WeaponDamageEntityEvent event,Newclanplugin clans)
  	{
	    if (((event.getPlayer() instanceof Player)) && (event.getDamager() != null)) {
	      if ((event.getVictim() instanceof Player))
	      {
	        UUID shooter_owner_id = event.getPlayer().getUniqueId();
	        UUID victim_id = ((Player)event.getVictim()).getUniqueId();
	        if ((clans.getClanManager().getClanByPlayerUniqueId(shooter_owner_id) != null) && (clans.getClanManager().getClanByPlayerUniqueId(victim_id) != null)) {
	          if (clans.getClanManager().getClanByPlayerUniqueId(shooter_owner_id) == clans.getClanManager().getClanByPlayerUniqueId(victim_id)) {
	    		return ;
	          } else if (clans.getClanManager().getClanPlayer(shooter_owner_id).isAlly(clans.getClanManager().getClanPlayer(victim_id).toPlayer())) {
	    		return ;
	          }
	        }
	      }
	    }
	    EntityDamageByEntityEvent weaponattack = new EntityDamageByEntityEvent(event.getPlayer(), event.getVictim(), EntityDamageByEntityEvent.DamageCause.ENTITY_ATTACK, event.getDamage());
		damagebyentity(weaponattack);
		return ;
	}
	*/
    
    public void damagebyentity(final EntityDamageByEntityEvent event) {
        int mana = 30;
        if (event.getDamager() instanceof Player) {
            Player pl = (Player) event.getDamager();
            int boost = pl.getMetadata("spilituse").get(0).asInt();
            String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
            //���l
            if (race.equalsIgnoreCase("sibito") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillNNG.sibito_deadattack(pl, plugin, event);
            //���l�_
            if (race.equalsIgnoreCase("gennzinnsin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillNNG.gennzinnsin_luckyattack(pl, plugin,pluginpre, event);
            //����
            if (race.equalsIgnoreCase("akuma") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillAKM.akuma_dark_attack(pl, plugin, event);
            //�S
            if (race.equalsIgnoreCase("oni") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillAKM.oni_closed_attack(pl, plugin, event);
            //�z���S
            if (race.equalsIgnoreCase("kyuuketuki") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillAKM.kyuuketuki_shadow_attack(pl, plugin, event);
            //�_
            if (race.equalsIgnoreCase("kami") || race.equalsIgnoreCase("houzyousin") || race.equalsIgnoreCase("yakusin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillKAM.kami_faith_attack(pl, plugin, event, boost, conf);
            //�L���_
            if (race.equalsIgnoreCase("houzyousin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillKAM.houzyousin_potato(pl, plugin, race, event, boost);
            //�O���[�o��
            if (conf.getInt("user." + pl.getUniqueId() + ".split") <= mana)
                THSkillGlobal.global_no_mana_attack(pl, plugin,pluginpre, event);
        }
        mana = 20;
        if (event.getEntity() instanceof Player) {
            Player pl = (Player) event.getEntity();
            int boost = pl.getMetadata("spilituse").get(0).asInt();
            String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
            //�H���l
            if (race.equalsIgnoreCase("houraizin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana) THSkillNNG.houraizin_reverselife_Entity(pl, plugin,pluginpre, event);
            //�d�� (���l����)
            if (race.equalsIgnoreCase("yousei") || race.equalsIgnoreCase("satori") || race.equalsIgnoreCase("kibito") && conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillYUS.yousei_glaze(pl, plugin, event);
            //���l
            if (race.equalsIgnoreCase("kobito")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillYUS.kobito_glaze(pl, plugin, event);
            //�T�g��
            if (race.equalsIgnoreCase("satori")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= 50) THSkillYUS.satori_satori(pl, plugin,pluginpre, event);
            //����
            if (race.equalsIgnoreCase("seirei")|| race.equalsIgnoreCase("hannrei")|| race.equalsIgnoreCase("sourei")|| race.equalsIgnoreCase("onnryou")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillSIR.seirei_mighty_guard(pl, plugin, event, boost);
            //����
            if (race.equalsIgnoreCase("onnryou")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= 20) THSkillSIR.onnryou_never_vanish(pl, plugin, race, event, boost);
            //�_
            if (race.equalsIgnoreCase("kami") || race.equalsIgnoreCase("houzyousin") || race.equalsIgnoreCase("yakusin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillKAM.kami_faith_defence(pl, plugin, event, boost, conf);
            //��_
            if (race.equalsIgnoreCase("yakusin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana)
                THSkillKAM.yakusin_darkside(pl, plugin, race, event);
            //�O���[�o��
            if (conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillGlobal.global_no_mana_damaged(pl, plugin,pluginpre, event);
        }
    }

    public void damagebyblock(final EntityDamageByBlockEvent event) {
        int mana = 25;
        Player pl = (Player) event.getDamager();
        int boost = pl.getMetadata("spilituse").get(0).asInt();
        String race = conf.getString("user." + pl.getUniqueId() + ".race").toString();
        //�H���l
        if (race.equalsIgnoreCase("houraizin") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana) THSkillNNG.houraizin_reverselife_block(pl, plugin,pluginpre, event);
        //�͓�
        if (race.equalsIgnoreCase("kappa") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana) THSkillYUM.kappa_anti_drawn(pl, plugin,pluginpre, event);
        //�d��
        if (race.equalsIgnoreCase("yousei") || race.equalsIgnoreCase("satori") || race.equalsIgnoreCase("kibito") || race.equalsIgnoreCase("kobito") && conf.getInt("user." + pl.getUniqueId() + ".split") > mana) THSkillYUS.yousei_fall_protection(pl, plugin, event);
        //����
        if (race.equalsIgnoreCase("akuma")|| race.equalsIgnoreCase("oni")|| race.equalsIgnoreCase("kyuuketuki")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillAKM.akuma_antiheat_body(pl, plugin, event);
        //�z���S
        if (race.equalsIgnoreCase("kyuuketuki")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillAKM.kyuuketuki_antiallfire_body(pl, plugin, event);
        //����
        if (race.equalsIgnoreCase("seirei")|| race.equalsIgnoreCase("hannrei")|| race.equalsIgnoreCase("sourei")|| race.equalsIgnoreCase("onnryou")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillSIR.seirei_mighty_guard(pl, plugin, event, boost);
        //�L���_
        if (race.equalsIgnoreCase("houzyousin")&& conf.getInt("user." + pl.getUniqueId() + ".split") <= mana) THSkillKAM.houzyousin_feed(pl, plugin, event);
    }
    
    ////�璷�h�~
    boolean magic_iscastable(Player pl, int mana,String string)
    {
	        if (((MetadataValue) pl.getMetadata("casting").get(0)).asBoolean()) {
	            pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̖��@���r�����ł�");
	            return false;
	        } else if (((MetadataValue) pl.getMetadata("using-magic").get(0)).asBoolean()) {
	            pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̖��@���g�p���ł�");
	            return false;
	        } else {
	    	    if (conf.getDouble("user." + pl.getUniqueId() + ".spilit") > mana) 
	    	    {
	        		pl.sendMessage(pluginpre + ChatColor.LIGHT_PURPLE + string);
		        	return true;
	    	    }
	    	    else
	    	    {
		            pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "��͂��s�����Ă��܂�");
	    	    	return false;
	    	    }
	        }
    }
}
