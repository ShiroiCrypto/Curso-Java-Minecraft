package me.guss.curso.commands;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExplosaoCommand implements CommandExecutor {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    
    public ExplosaoCommand(Curso plugin, PoderManager poderManager) {
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
        float forca = args.length > 0 ? Float.parseFloat(args[0]) : 2.0f;

        if (!player.hasPermission("superpoderes.explosao")) {
            player.sendMessage("§cVocê não tem permissão para criar explosões!");
            return true;
        }

        return poderManager.criarExplosao(player, forca);
    }
} 