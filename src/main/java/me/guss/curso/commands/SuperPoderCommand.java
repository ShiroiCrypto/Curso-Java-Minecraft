package me.guss.curso.commands;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuperPoderCommand implements CommandExecutor {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    
    public SuperPoderCommand(Curso plugin, PoderManager poderManager) {
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
            mostrarMenu(player);
            return true;
        }

        String poder = args[0].toLowerCase();
        Player alvo = args.length > 1 ? Bukkit.getPlayer(args[1]) : player;

        if (args.length > 1 && alvo == null) {
            player.sendMessage("§cJogador não encontrado!");
            return true;
        }

        switch (poder) {
            case "voar":
                return poderManager.alternarVoo(alvo);
            case "velocidade":
                return poderManager.definirVelocidade(alvo, 3);
            case "invisibilidade":
                return poderManager.alternarInvisibilidade(alvo);
            case "cura":
                return poderManager.curarJogador(alvo);
            case "raio":
                return poderManager.invocarRaio(alvo);
            case "explosao":
                return poderManager.criarExplosao(alvo);
            case "superforca":
                return poderManager.ativarSuperForca(alvo);
            case "escudo":
                return poderManager.ativarEscudo(alvo);
            case "telecinese":
                return poderManager.ativarTelecinese(alvo);
            case "clone":
                return poderManager.criarClone(alvo);
            case "meteorito":
                return poderManager.invocarMeteorito(alvo);
            case "tempestade":
                return poderManager.invocarTempestade(alvo);
            case "portal":
                return poderManager.criarPortal(alvo);
            case "resurreicao":
                return poderManager.resurreicao(alvo);
            case "superjump":
                return poderManager.ativarSuperJump(alvo);
            default:
                player.sendMessage("§cPoder não reconhecido! Use /superpoder para ver a lista.");
                return true;
        }
    }
    
    private void mostrarMenu(Player player) {
        player.sendMessage("§6╔══════════════════════════════════════════════════════════════╗");
        player.sendMessage("§6║                    §e§lSUPER PODERES §6                    ║");
        player.sendMessage("§6╠══════════════════════════════════════════════════════════════╣");
        player.sendMessage("§6║ §a/superpoder voar §7- Ativa o modo voo                    ║");
        player.sendMessage("§6║ §a/superpoder velocidade §7- Aumenta velocidade            ║");
        player.sendMessage("§6║ §a/superpoder invisibilidade §7- Fica invisível            ║");
        player.sendMessage("§6║ §a/superpoder cura §7- Cura completamente                  ║");
        player.sendMessage("§6║ §a/superpoder raio §7- Invoca um raio                      ║");
        player.sendMessage("§6║ §a/superpoder explosao §7- Cria explosão controlada        ║");
        player.sendMessage("§6║ §a/superpoder superforca §7- Força sobre-humana            ║");
        player.sendMessage("§6║ §a/superpoder escudo §7- Escudo protetor                   ║");
        player.sendMessage("§6║ §a/superpoder telecinese §7- Mover objetos com a mente     ║");
        player.sendMessage("§6║ §a/superpoder clone §7- Cria um clone seu                  ║");
        player.sendMessage("§6║ §a/superpoder meteorito §7- Invoca um meteorito            ║");
        player.sendMessage("§6║ §a/superpoder tempestade §7- Invoca uma tempestade         ║");
        player.sendMessage("§6║ §a/superpoder portal §7- Cria um portal dimensional        ║");
        player.sendMessage("§6║ §a/superpoder resurreicao §7- Ressuscita jogadores mortos  ║");
        player.sendMessage("§6║ §a/superpoder superjump §7- Pulo sobre-humano               ║");
        player.sendMessage("§6╚══════════════════════════════════════════════════════════════╝");
    }
} 