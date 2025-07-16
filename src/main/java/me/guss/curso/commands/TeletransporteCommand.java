package me.guss.curso.commands;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeletransporteCommand implements CommandExecutor {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    
    public TeletransporteCommand(Curso plugin, PoderManager poderManager) {
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
        
        if (args.length == 0) {
            player.sendMessage("§cUso: /teletransporte <jogador>");
            return true;
        }

        Player alvo = Bukkit.getPlayer(args[0]);
        if (alvo == null) {
            player.sendMessage("§cJogador não encontrado!");
            return true;
        }

        if (!player.hasPermission("superpoderes.teletransporte")) {
            player.sendMessage("§cVocê não tem permissão para teletransportar!");
            return true;
        }

        return poderManager.teletransportar(player, alvo);
    }
} 