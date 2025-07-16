package me.guss.curso.utils;

import me.guss.curso.Curso;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    
    private final Curso plugin;
    private FileConfiguration config;
    
    public ConfigManager(Curso plugin) {
        this.plugin = plugin;
        carregarConfig();
    }
    
    public void carregarConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        
        // Configurações padrão
        config.addDefault("poderes.voar.habilitado", true);
        config.addDefault("poderes.voar.cooldown", 5);
        config.addDefault("poderes.invisibilidade.habilitado", true);
        config.addDefault("poderes.invisibilidade.duracao", 300);
        config.addDefault("poderes.velocidade.max_nivel", 10);
        config.addDefault("poderes.raio.dano", 5.0);
        config.addDefault("poderes.explosao.max_forca", 5.0);
        config.addDefault("poderes.superforca.multiplicador", 2.0);
        config.addDefault("poderes.escudo.reducao_dano", 0.5);
        config.addDefault("poderes.telecinese.alcance", 10);
        config.addDefault("poderes.clone.duracao", 30);
        config.addDefault("poderes.meteorito.dano", 10.0);
        config.addDefault("poderes.tempestade.duracao", 60);
        config.addDefault("poderes.portal.duracao", 120);
        config.addDefault("poderes.resurreicao.cooldown", 300);
        config.addDefault("poderes.superjump.multiplicador", 5.0);
        
        // Configurações de eventos
        config.addDefault("eventos.drop_diamante_chance", 0.15);
        config.addDefault("eventos.drop_esmeralda_chance", 0.20);
        config.addDefault("eventos.mob_especial_chance", 0.05);
        config.addDefault("eventos.tempestade_magica_chance", 0.30);
        
        // Configurações de mensagens
        config.addDefault("mensagens.prefixo", "§6[SuperPoderes] ");
        config.addDefault("mensagens.boas_vindas", "§aBem-vindo ao servidor com Super Poderes!");
        config.addDefault("mensagens.poder_ativado", "§aPoder ativado com sucesso!");
        config.addDefault("mensagens.poder_desativado", "§cPoder desativado!");
        config.addDefault("mensagens.sem_permissao", "§cVocê não tem permissão para usar este poder!");
        config.addDefault("mensagens.jogador_nao_encontrado", "§cJogador não encontrado!");
        config.addDefault("mensagens.cooldown", "§cAguarde {tempo} segundos para usar novamente!");
        
        // Configurações de efeitos
        config.addDefault("efeitos.particulas.habilitado", true);
        config.addDefault("efeitos.sons.habilitado", true);
        config.addDefault("efeitos.raio.particulas", 50);
        config.addDefault("efeitos.explosao.particulas", 100);
        config.addDefault("efeitos.portal.particulas", 200);
        
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }
    
    public void recarregarConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }
    
    // Getters para configurações de poderes
    public boolean isPoderHabilitado(String poder) {
        return config.getBoolean("poderes." + poder + ".habilitado", true);
    }
    
    public int getCooldown(String poder) {
        return config.getInt("poderes." + poder + ".cooldown", 5);
    }
    
    public int getDuracao(String poder) {
        return config.getInt("poderes." + poder + ".duracao", 300);
    }
    
    public int getMaxNivel(String poder) {
        return config.getInt("poderes." + poder + ".max_nivel", 10);
    }
    
    public double getDano(String poder) {
        return config.getDouble("poderes." + poder + ".dano", 5.0);
    }
    
    public double getMaxForca(String poder) {
        return config.getDouble("poderes." + poder + ".max_forca", 5.0);
    }
    
    public double getMultiplicador(String poder) {
        return config.getDouble("poderes." + poder + ".multiplicador", 2.0);
    }
    
    public double getReducaoDano(String poder) {
        return config.getDouble("poderes." + poder + ".reducao_dano", 0.5);
    }
    
    public int getAlcance(String poder) {
        return config.getInt("poderes." + poder + ".alcance", 10);
    }
    
    // Getters para configurações de eventos
    public double getChanceDrop(String item) {
        return config.getDouble("eventos.drop_" + item + "_chance", 0.15);
    }
    
    public double getChanceMobEspecial() {
        return config.getDouble("eventos.mob_especial_chance", 0.05);
    }
    
    public double getChanceTempestadeMagica() {
        return config.getDouble("eventos.tempestade_magica_chance", 0.30);
    }
    
    // Getters para configurações de mensagens
    public String getPrefixo() {
        return config.getString("mensagens.prefixo", "§6[SuperPoderes] ");
    }
    
    public String getMensagem(String tipo) {
        return config.getString("mensagens." + tipo, "Mensagem não configurada");
    }
    
    // Getters para configurações de efeitos
    public boolean isEfeitosHabilitados(String tipo) {
        return config.getBoolean("efeitos." + tipo + ".habilitado", true);
    }
    
    public int getParticulas(String poder) {
        return config.getInt("efeitos." + poder + ".particulas", 50);
    }
    
    // Métodos para salvar configurações
    public void setPoderHabilitado(String poder, boolean habilitado) {
        config.set("poderes." + poder + ".habilitado", habilitado);
        plugin.saveConfig();
    }
    
    public void setCooldown(String poder, int cooldown) {
        config.set("poderes." + poder + ".cooldown", cooldown);
        plugin.saveConfig();
    }
    
    public void setChanceDrop(String item, double chance) {
        config.set("eventos.drop_" + item + "_chance", chance);
        plugin.saveConfig();
    }
} 