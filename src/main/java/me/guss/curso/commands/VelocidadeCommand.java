package me.guss.curso.commands;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VelocidadeCommand implements CommandExecutor {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    
    public VelocidadeCommand(Curso plugin, PoderManager poderManager) {
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
            player.sendMessage("§cUso: /velocidade <nível> [jogador]");
            return true;
        }

        int nivel;
        try {
            nivel = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("§cNível deve ser um número!");
            return true;
        }

        Player alvo = args.length > 1 ? Bukkit.getPlayer(args[1]) : player;
        if (args.length > 1 && alvo == null) {
            player.sendMessage("§cJogador não encontrado!");
            return true;
        }

        if (!player.hasPermission("superpoderes.velocidade")) {
            player.sendMessage("§cVocê não tem permissão para alterar velocidade!");
            return true;
        }

        return poderManager.definirVelocidade(alvo, nivel);
    }
} 