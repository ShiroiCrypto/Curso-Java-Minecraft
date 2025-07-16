package me.guss.curso.commands;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoarCommand implements CommandExecutor {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    
    public VoarCommand(Curso plugin, PoderManager poderManager) {
        this.plugin = plugin;
        this.poderManager = poderManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando só pode ser usado por jogadores!");
            return true;
        }

        Player player = (Player) sender;
        Player alvo = args.length > 0 ? Bukkit.getPlayer(args[0]) : player;

        if (args.length > 0 && alvo == null) {
            player.sendMessage("§cJogador não encontrado!");
            return true;
        }

        if (!player.hasPermission("superpoderes.voar") && alvo != player) {
            player.sendMessage("§cVocê não tem permissão para fazer outros voarem!");
            return true;
        }

        return poderManager.alternarVoo(alvo);
    }
} 