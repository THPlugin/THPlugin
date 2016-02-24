package org.noteusoft.mireiyu.thplugin;

/**
 * Created by noteumaple on 16/02/11.
 */

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.noteusoft.mireiyu.thplugin.race.THRaces;
import org.noteusoft.mireiyu.thplugin.race.command.THCommand;
import org.noteusoft.mireiyu.thplugin.race.schedule.THSchedule;

/*import com.shampaggon.crackshot.CSDirector; */
/*import com.shampaggon.crackshot.events.WeaponDamageEntityEvent; */

public class THPlugin extends JavaPlugin implements Listener
{
    public static Logger logger = Logger.getLogger("Minecraft");
    public static THPlugin plugin;
    public static String thrpre = ChatColor.WHITE + "[" + ChatColor.RED + "THR" + ChatColor.WHITE + "]";
    public static PluginDescriptionFile pdfFile = plugin.getDescription();
    private static File pluginDir = new File("plugins", "THRPlugin");
    public static File configfile = new File(pluginDir, "config.yml");
    public static FileConfiguration conf = YamlConfiguration.loadConfiguration(configfile);
	public static boolean crackshot_hook = false;
	public static boolean nametagedit_hook = false;
	public static boolean scoreboardapi_hook = false;
	public static boolean barapi_hook;
    
    public void onDisable(){
        logger.info("[THR] Plugin Successfully Disabled!");
        saveConfig();
    }

    public void onEnable()
    {
    	//��{�ݒ�
        logger.info(thrpre + pdfFile.getVersion() + "�͐������N�����܂���");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        saveDefaultConfig();
        registerTHRaces();
        registerTHCommand();
        THPlugin plugin0 = this;
        THSchedule schedule = new THSchedule();
        String thpre0 = THPlugin.thrpre;
        schedule.run1(plugin0,thpre0);
        schedule.run2(plugin0,thpre0);
        schedule.run3(plugin0,thpre0);
        
    	//�t�b�N�ݒ�
        if (this.getConfig().getBoolean("enable-CrackShot-shooter-hook"))
        {
        	if (Bukkit.getPluginManager().getPlugin("CrackShot") != null)
        	{
        		crackshot_hook = true;
        		logger.info(thrpre + pdfFile.getVersion() + "�͐�����CrackShot�ƘA�g���܂���");

        	}
        }
        if (this.getConfig().getBoolean("enable-NametagEdit-tab-hook"))
        {
        	if (Bukkit.getPluginManager().getPlugin("NametagEdit") != null)
        	{
        		nametagedit_hook = true;
        		logger.info(thrpre + pdfFile.getVersion() + "�͐�����NametagEdit�ƘA�g���܂���");

        	}
        }
        if (this.getConfig().getBoolean("enable-ScoreboardAPI-listboard-hook"))
        {
        	if (Bukkit.getPluginManager().getPlugin("ScoreboardAPI") != null)
        	{
        		scoreboardapi_hook = true;
        		logger.info(thrpre + pdfFile.getVersion() + "�͐�����ScoreboardAPI�ƘA�g���܂���");

        	}
        }
        if (this.getConfig().getBoolean("enable-BarAPI-manabar-hook"))
        {
        	if (Bukkit.getPluginManager().getPlugin("BarAPI") != null)
        	{
        		barapi_hook = true;
        		logger.info(thrpre + pdfFile.getVersion() + "�͐�����BarAPI�ƘA�g���܂���");

        	}
        }
    }

    public void registerTHRaces()
    {
        getServer().getPluginManager().registerEvents(new THRaces(), plugin);
    }
    public void registerTHCommand()
    {
        getCommand("touhouraces").setExecutor(new THCommand());
        getCommand("thr").setExecutor(new THCommand());
    }

}
