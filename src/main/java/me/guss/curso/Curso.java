package me.guss.curso;

import me.guss.curso.commands.*;
import me.guss.curso.events.PlayerEvents;
import me.guss.curso.events.WorldEvents;
import me.guss.curso.utils.PoderManager;
import me.guss.curso.utils.ConfigManager;
import me.guss.curso.utils.CooldownManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Curso extends JavaPlugin {

    private PoderManager poderManager;
    private ConfigManager configManager;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        // Inicializa o ConfigManager primeiro
        configManager = new ConfigManager(this);
        
        // Inicializa o CooldownManager
        cooldownManager = new CooldownManager(this, configManager);
        
        // Inicializa o PoderManager
        poderManager = new PoderManager(this);
        
        // Registra comandos
        registrarComandos();
        
        // Registra eventos
        registrarEventos();
        
        // Mensagem de inicialização
        getLogger().info("§a[SuperPoderes] Plugin iniciado com sucesso!");
        getLogger().info("§a[SuperPoderes] Comandos disponíveis: /superpoder, /voar, /teletransporte, /raio, /explosao, /cura, /velocidade, /invisibilidade");
        getLogger().info("§a[SuperPoderes] Novos poderes: superforca, escudo, telecinese, clone, meteorito, tempestade, portal, resurreicao, superjump");
        getLogger().info("§a[SuperPoderes] Sistema de cooldown e configuração ativados!");
    }

    @Override
    public void onDisable() {
        // Restaura todos os jogadores ao estado normal
        for (Player player : Bukkit.getOnlinePlayers()) {
            poderManager.limparDadosJogador(player.getUniqueId());
            cooldownManager.clearAllCooldowns(player);
            
            // Remove efeitos de poção
            player.removePotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY);
            player.removePotionEffect(org.bukkit.potion.PotionEffectType.INCREASE_DAMAGE);
            player.removePotionEffect(org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE);
            player.removePotionEffect(org.bukkit.potion.PotionEffectType.JUMP);
            
            // Restaura velocidade
            player.setWalkSpeed(0.2f);
            
            // Desativa voo
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        
        getLogger().info("§c[SuperPoderes] Plugin desligado!");
    }
    
    private void registrarComandos() {
        // Comando principal
        getCommand("superpoder").setExecutor(new SuperPoderCommand(this, poderManager));
        
        // Comandos individuais
        getCommand("voar").setExecutor(new VoarCommand(this, poderManager));
        getCommand("teletransporte").setExecutor(new TeletransporteCommand(this, poderManager));
        getCommand("raio").setExecutor(new RaioCommand(this, poderManager));
        getCommand("explosao").setExecutor(new ExplosaoCommand(this, poderManager));
        getCommand("cura").setExecutor(new CuraCommand(this, poderManager));
        getCommand("velocidade").setExecutor(new VelocidadeCommand(this, poderManager));
        getCommand("invisibilidade").setExecutor(new InvisibilidadeCommand(this, poderManager));
    }
    
    private void registrarEventos() {
        // Registra eventos de jogadores
        getServer().getPluginManager().registerEvents(new PlayerEvents(this, poderManager), this);
        
        // Registra eventos do mundo
        getServer().getPluginManager().registerEvents(new WorldEvents(this, poderManager), this);
    }
    
    public PoderManager getPoderManager() {
        return poderManager;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}
