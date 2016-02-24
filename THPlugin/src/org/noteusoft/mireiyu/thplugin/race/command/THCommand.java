package org.noteusoft.mireiyu.thplugin.race.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.noteusoft.mireiyu.thplugin.THPlugin;
public class THCommand implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command mcd, String commandLabel, String[] args){
		  if ((commandLabel.equalsIgnoreCase("touhouraces")) && ((sender instanceof Player)) && (args.length == 0)){
			  Player pl = (Player)sender;
			  pl.sendMessage(THPlugin.plugin + "Version " + THPlugin.pdfFile.getVersion() + ". Made by:" + THPlugin.pdfFile.getAuthors().toString());
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && ((sender instanceof Player)) && (args.length == 0)){
			  Player pl = (Player)sender;
			  pl.sendMessage(THPlugin.plugin + "Version " + THPlugin.pdfFile.getVersion() + ". Made by:" + THPlugin.pdfFile.getAuthors().toString());
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("reload")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.reload")) || (pl.isOp())){
					  THPlugin.plugin.saveConfig();
					  THPlugin.plugin.reloadConfig();
					  sender.sendMessage(THPlugin.plugin + "��6 �����[�h���܂���.");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("help")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.help")) || (pl.hasPermission("thr.user"))){
					  THPlugin.plugin.reloadConfig();
					  sender.sendMessage(THPlugin.plugin + "��6�\�ȃv���O�C���ꗗ");
					  sender.sendMessage("��btouhouraces/thr : �o�[�W��������");
					  sender.sendMessage("thr reload : �����[�h");
					  sender.sendMessage("thr mana : ���݃}�i�m�F");
					  sender.sendMessage("thr heal-mana [num] : �}�i��num���񕜂���");
					  sender.sendMessage("thr racelist : �I�����C���ł���v���C���[�̎푰�̓��v���Ƃ�");
					  sender.sendMessage("thr toggleskill : �s���n�̃X�L���̔������g�O������");
					  sender.sendMessage("thr setpoint [num] : �����̃|�C���g�i�g�����͔C�Ӂj��ݒ肷��");
					  sender.sendMessage("thr addpoint [num] : �����̃|�C���g�i�g�����͔C�Ӂj��ǉ�����");
					  sender.sendMessage("thr setpoint [playername] [num] : playername�̃|�C���g�i�g�����͔C�Ӂj��ݒ肷��");
					  sender.sendMessage("thr addpoint [playername] [num] : playername�̃|�C���g�i�g�����͔C�Ӂj��ǉ�����");
					  sender.sendMessage("thr steppoint [max] : �|�C���g�i�g�����͔C�Ӂj��max������Ƃ���1�㏸����");
					  sender.sendMessage("thr steppoint [playername] [max] : playername�̃|�C���g�i�g�����͔C�Ӂj��max������Ƃ���1�㏸����");
					  sender.sendMessage("thr setrace  [�����푰��] : �����̎푰���푰���i�������j�ɕύX����");
					  sender.sendMessage("thr setrace [playername] [�����푰��] : playername�̎푰���푰���i�������j�ɕύX����");
					  sender.sendMessage("thr setrace [playername] [�����푰��] : playername�̎푰���푰���i�������j�ɕύX����");
					  sender.sendMessage("thr setrace [playername] [�����푰��] : playername�̎푰���푰���i�������j�ɕύX����");
					  sender.sendMessage("thr setrace [playername] [�����푰��] : playername�̎푰���푰���i�������j�ɕύX����");
					  sender.sendMessage("thr info : playername�̎푰�̏���\������");
					  sender.sendMessage("thr evolinfo [�����푰��] : �푰�̏���\������");
					  sender.sendMessage("thr evollist : playername�̐i���ł���푰�̃��X�g��\������");
					  sender.sendMessage("thr evolchange [�����푰��] : �푰�̐i�������݂�");
				  }else{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("mana")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.checkmana")) || (pl.hasPermission("thr.user"))) {
					  pl.sendMessage(THPlugin.thrpre + ChatColor.GREEN + "��́F" + ChatColor.LIGHT_PURPLE + THPlugin.plugin.getConfig().getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
				  } else {
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("heal-mana")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if (pl.hasPermission("thr.healmana")){
					  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".spilit", Double.valueOf(THPlugin.plugin.getConfig().getDouble("user." + pl.getUniqueId() + ".spilit") + Integer.parseInt(args[1])));
					  pl.sendMessage(THPlugin.thrpre + ChatColor.GREEN + "��́F" + ChatColor.LIGHT_PURPLE + THPlugin.plugin.getConfig().getDouble(new StringBuilder("user.").append(pl.getUniqueId()).append(".spilit").toString()));
				  }else{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("racelist")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.racelist")) || (pl.hasPermission("thr.user"))){
					  OfflinePlayer[] ppl = Bukkit.getOfflinePlayers();
					  pl.sendMessage(THPlugin.plugin + "��a �I�����C�����̎푰���X�g.");
					  int p = 0;
					  while (p < ppl.length){
						  if (ppl[p].isOnline()) {
							  pl.sendMessage(ChatColor.GREEN + "+" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(ppl[p].getUniqueId()).append(".name").toString()) + ":" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(ppl[p].getUniqueId()).append(".race").toString()) + "(" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(ppl[p].getUniqueId()).append(".point").toString()) + ")");
						  }
						  p++;
					  }
				  }else{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("toggleskill")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.toggleskill")) || (pl.hasPermission("thr.user"))){
					  if (pl.hasMetadata("ignoreskill")){
						  pl.removeMetadata("ignoreskill", THPlugin.plugin);
						  pl.sendMessage(THPlugin.thrpre + ChatColor.DARK_AQUA + "�s���X�L���͍Ăє������܂�");
					  }else{
						  MetadataValue ignoreskill = new FixedMetadataValue(THPlugin.plugin, Boolean.valueOf(true));
						  pl.setMetadata("ignoreskill", ignoreskill);
						  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "�s���X�L���𕕈󂵂܂���");
					  }
				  }else {
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("setrace")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setrace")){
					  THPlugin.plugin.getConfig().set("user." + commander.getUniqueId() + ".race", args[1].toString());
					  THPlugin.plugin.saveConfig();
					  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��͎푰��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(commander.getUniqueId()).append(".race").toString()) + "�ɂȂ�܂����B");
				  }else{
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("setrace")) && (args.length == 3)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setrace")){
					  if (Bukkit.getPlayer(args[1]) != null){
						  Player pl = Bukkit.getPlayer(args[1]);
						  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".race", args[2].toString());
						  THPlugin.plugin.saveConfig();
						  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + pl.getName() + "�̎푰��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".race").toString()) + "�ɂ��܂����B");
						  pl.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��͎푰��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".race").toString()) + "�ɂȂ�܂����B");
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("setpoint")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setpoint")){
					  if (Bukkit.getPlayer(args[1]) != null){
						  int point = Integer.parseInt(args[1]);
						  THPlugin.plugin.getConfig().set("user." + commander.getUniqueId() + ".point", Integer.valueOf(point));
						  THPlugin.plugin.saveConfig();
						  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(commander.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("setpoint")) && (args.length == 3)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setpoint")){
					  if (Bukkit.getPlayer(args[1]) != null){
						  Player pl = Bukkit.getPlayer(args[1]);
						  int point = Integer.parseInt(args[2]);
						  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".point", Integer.valueOf(point));
						  THPlugin.plugin.saveConfig();
						  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + pl.getName() + "�̃|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂ��܂����B");
						  pl.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("addpoint")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setpoint")){
					  int point = Integer.parseInt(args[1]);
					  THPlugin.plugin.getConfig().set("user." + commander.getUniqueId() + ".point", Integer.valueOf(THPlugin.plugin.getConfig().getInt("user." + commander.getUniqueId() + ".point") + point));
					  THPlugin.plugin.saveConfig();
					  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(commander.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("addpoint")) && (args.length == 3)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.setpoint")){
					  if (Bukkit.getPlayer(args[1]) != null){
						  Player pl = Bukkit.getPlayer(args[1]);
						  int point = Integer.parseInt(args[2]);
						  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".point", Integer.valueOf(THPlugin.plugin.getConfig().getInt("user." + pl.getUniqueId() + ".point") + point));
						  THPlugin.plugin.saveConfig();
						  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + pl.getName() + "�̃|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂ��܂����B");
						  pl.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("steppoint")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.steppoint")){
					  if (THPlugin.plugin.getConfig().getInt("user." + commander.getUniqueId() + ".point") < Integer.parseInt(args[1])){
						  THPlugin.plugin.getConfig().set("user." + commander.getUniqueId() + ".point", Integer.valueOf(THPlugin.plugin.getConfig().getInt("user." + commander.getUniqueId() + ".point") + 1));
						  THPlugin.plugin.saveConfig();
						  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(commander.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("steppoint")) && (args.length == 3)){
			  if ((sender instanceof Player)){
				  Player commander = ((Player)sender).getPlayer();
				  if (commander.hasPermission("thr.steppoint")){
					  if (Bukkit.getPlayer(args[1]) != null){
						  Player pl = Bukkit.getPlayer(args[1]);
						  if (THPlugin.plugin.getConfig().getInt("user." + commander.getUniqueId() + ".point") < Integer.parseInt(args[2])){
							  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".point", Integer.valueOf(THPlugin.plugin.getConfig().getInt("user." + pl.getUniqueId() + ".point") + 1));
							  THPlugin.plugin.saveConfig();
							  commander.sendMessage(THPlugin.thrpre + ChatColor.AQUA + pl.getName() + "�̃|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂ��܂����B");
							  pl.sendMessage(THPlugin.thrpre + ChatColor.AQUA + "���Ȃ��̓|�C���g��" + THPlugin.plugin.getConfig().getString(new StringBuilder("user.").append(pl.getUniqueId()).append(".point").toString()) + "�ɂȂ�܂����B");
						  }
					  }
				  }else {
					  commander.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���I");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("evollist")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.evol.user.list")) || (pl.hasPermission("thr.user"))){
					  pl.sendMessage(THPlugin.thrpre + ChatColor.AQUA + pl.getName() + "�̐i���ł���惊�X�g");
					  List<String> evolraces = new ArrayList<String>();
					  for (String race : THPlugin.plugin.getConfig().getConfigurationSection("race").getKeys(false)) {
						  if (THPlugin.plugin.getConfig().getString("race." + race + ".racetype.root").contains(THPlugin.plugin.getConfig().getString("user." + pl.getUniqueId() + ".race"))) {
							  evolraces.add(race);
						  }
					  }
					  for (String evolrace : evolraces) {
						  pl.sendMessage(THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(evolrace).append(".display.real").toString()) + "�F����name��" + evolrace);
					  }
				  }else	{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���B");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("evolinfo")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.evol.user.info")) || (pl.hasPermission("thr.user"))){
					  boolean existrace = false;
					  String inforace = "";
					  for (String race : THPlugin.plugin.getConfig().getConfigurationSection("race").getKeys(false)) {
						  if (race.toLowerCase().contains(args[1].toLowerCase())){
							  existrace = true;
							  inforace = race;
							  break;
						  }
					  }
					  if (existrace){
						  pl.sendMessage(THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".display.real").toString()) + "�F����name��" + inforace + "�i" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".display.tag").toString()) + "�j�̏��");
						  pl.sendMessage("���푰�F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".racetype.root").toString()));
						  pl.sendMessage("�����N�F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".racetype.rank").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȑi���̌��ЁF" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".evol.evolpoint.shard").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȑi���̕�΁F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".evol.evolpoint.crystal").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȏ푰�f�ށF" + THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.amount").toString()) + "��" + Material.getMaterial(THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.typeid").toString())) + "(���^" + THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.meta").toString()) + "�j");
						  pl.sendMessage(THPlugin.plugin.getConfig().getString("race." + inforace + ".intro.story"));
						  pl.sendMessage(THPlugin.plugin.getConfig().getString("race." + inforace + ".intro.skills"));
					  }else	{
						  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̎푰����name�͑��݂��܂���B");
					  }
				  }else	{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���B");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("info")) && (args.length == 1)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.info")) || (pl.hasPermission("thr.user"))){
					  boolean existrace = false;
					  String inforace = "";
					  for (String race : THPlugin.plugin.getConfig().getConfigurationSection("race").getKeys(false)) {
						  if (race.toLowerCase().contains(THPlugin.plugin.getConfig().getString("user." + pl.getUniqueId() + ".race").toLowerCase())){
							  existrace = true;
							  inforace = race;
							  break;
						  }
					  }
					  if (existrace){
						  pl.sendMessage(THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".display.real").toString()) + "�F����name��" + inforace + "�i" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".display.tag").toString()) + "�j�̏��");
						  pl.sendMessage("���푰�F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".racetype.root").toString()));
						  pl.sendMessage("�����N�F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".racetype.rank").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȑi���̌��ЁF" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".evol.evolpoint.shard").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȑi���̕�΁F" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".evol.evolpoint.crystal").toString()));
						  pl.sendMessage("�i���ɕK�v�Ȏ푰�f�ށF" + THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.amount").toString()) + "��" + Material.getMaterial(THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.typeid").toString())) + "(���^" + THPlugin.plugin.getConfig().getInt(new StringBuilder("race.").append(inforace).append(".evol.raceitem.meta").toString()) + "�j");
						  pl.sendMessage(THPlugin.plugin.getConfig().getString("race." + inforace + ".intro.story"));
						  pl.sendMessage(THPlugin.plugin.getConfig().getString("race." + inforace + ".intro.skills"));
					  }else {
						  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̎푰����name�͑��݂��܂���B");
					  }
				  }else	{
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���B");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args[0].equalsIgnoreCase("evolchange")) && (args.length == 2)){
			  if ((sender instanceof Player)){
				  Player pl = ((Player)sender).getPlayer();
				  if ((pl.hasPermission("thr.evol.user.change")) || (pl.hasPermission("thr.user"))){
					  boolean existrace = false;
					  String inforace = "";
					  for (String race : THPlugin.plugin.getConfig().getConfigurationSection("race").getKeys(false)) {
						  if (race.toLowerCase().contains(args[1].toLowerCase())){
							  existrace = true;
							  inforace = race;
							  break;
						  }
					  }
					  if (existrace){
						  if (THPlugin.plugin.getConfig().getString("race." + inforace + ".racetype.root").contains(THPlugin.plugin.getConfig().getString("user." + pl.getUniqueId() + ".race"))){
							  PlayerInventory inventory = pl.getInventory();
							  int ok_shard = 0;
							  int ok_crystal = 0;
							  int ok_raceitem = 0;
							  ItemStack shard = null;
							  ItemStack crystal = null;
							  ItemStack raceitem = null;
							  if (THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.evolpoint.shard") != 0){
								  shard = new ItemStack(Material.PRISMARINE_SHARD, THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.evolpoint.shard"));
								  if (inventory.contains(shard)) {
									  ok_shard = 1;
								  } else {
									  ok_shard = 2;
								  }
							  }
							  if (THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.evolpoint.crystal") != 0){
								  crystal = new ItemStack(Material.PRISMARINE_CRYSTALS, THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.evolpoint.crystal"));
								  if (inventory.contains(crystal)) {
									  ok_crystal = 1;
								  } else {
									  ok_shard = 2;
								  }
							  }
							  if (THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.raceitem.amount") != 0){
								  raceitem = new ItemStack(Material.getMaterial(THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.raceitem.typeid")), THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.raceitem.amount"));
								  int raceitemmeta = THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.raceitem.meta");
								  raceitem.setDurability((short)raceitemmeta);
								  if (inventory.contains(raceitem)) {
									  ok_raceitem = 1;
								  } else {
									  ok_raceitem = 2;
								  }
							  }
							  if ((ok_shard == 2) || (ok_crystal == 2) || (ok_raceitem == 2)){
								  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̎푰�ɐi������ׂ̃A�C�e��������܂���I");
							  }else {
								  pl.playSound(pl.getLocation(), Sound.PORTAL_TRAVEL, 1.0F, 1.0F);
								  if (ok_shard == 1) {
									  pl.getInventory().remove(shard);
								  }
								  if (ok_crystal == 1) {
									  pl.getInventory().remove(crystal);
								  }
								  if (ok_raceitem == 1) {
									  pl.getInventory().remove(raceitem);
								  }
								  THPlugin.plugin.getConfig().set("user." + pl.getUniqueId() + ".race", inforace);
								  THPlugin.plugin.saveConfig();
								  Bukkit.broadcastMessage(THPlugin.thrpre + ChatColor.BLUE + pl.getName() + "��" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".racetype.root").toString()) + "����" + THPlugin.plugin.getConfig().getString(new StringBuilder("race.").append(inforace).append(".display.real").toString()) + "�ɐi�������I�I");

								  ItemStack rewarditem = null;
								  if (THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.rewarditem.amount") != 0){
									  rewarditem = new ItemStack(Material.getMaterial(THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.rewarditem.typeid")), THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.rewarditem.amount"));
									  int rewarditemmeta = THPlugin.plugin.getConfig().getInt("race." + inforace + ".evol.rewarditem.meta");
									  rewarditem.setDurability((short)rewarditemmeta);
									  pl.getInventory().addItem(new ItemStack[] { rewarditem });
								  }
							  }
						  }else {
							  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "�i���ł���푰�ł͂���܂���I");
						  }
					  }else {
						  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "���̎푰����name�͑��݂��܂���B");
					  }
				  }else {
					  pl.sendMessage(THPlugin.thrpre + ChatColor.RED + "����������܂���B");
				  }
			  }
		  }else if ((commandLabel.equalsIgnoreCase("thr")) && (args.length >= 1)){
			  sender.sendMessage(THPlugin.plugin + "��c �R�}���h���Ԉ���Ă����.");
		  }
		  return true;
	  }
}