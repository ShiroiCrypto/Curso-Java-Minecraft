# 🚀 Plugin SuperPoderes - Minecraft

Um plugin inovador e complexo para Minecraft que adiciona super poderes aos jogadores, com sistema de comandos, eventos e configurações avançadas em português!

## 📋 Características

### 🎮 Poderes Disponíveis
- **Voo**: Permite voar pelo servidor
- **Invisibilidade**: Torna o jogador invisível
- **Velocidade**: Aumenta a velocidade de movimento
- **Cura**: Cura completamente o jogador
- **Raio**: Invoca um raio no local
- **Explosão**: Cria uma explosão controlada
- **Super Força**: Aumenta o dano causado
- **Escudo**: Reduz o dano recebido
- **Telecinese**: Move blocos com a mente
- **Clone**: Cria um clone temporário
- **Meteorito**: Invoca um meteorito
- **Tempestade**: Invoca uma tempestade
- **Portal**: Cria um portal dimensional
- **Ressurreição**: Ressuscita jogadores mortos
- **Super Jump**: Pulo sobre-humano

### 🎯 Comandos
- `/superpoder <poder> [jogador]` - Comando principal
- `/voar [jogador]` - Ativa/desativa voo
- `/teletransporte <jogador>` - Teleporta para um jogador
- `/raio [jogador]` - Invoca um raio
- `/explosao [força]` - Cria uma explosão
- `/cura [jogador]` - Cura um jogador
- `/velocidade <nível> [jogador]` - Altera velocidade
- `/invisibilidade [jogador]` - Torna invisível

### 🔧 Sistema de Configuração
- Arquivo `config.yml` totalmente configurável
- Sistema de cooldown para cada poder
- Configurações de eventos e drops
- Mensagens personalizáveis
- Efeitos visuais configuráveis

### 🎨 Eventos Especiais
- **Drops especiais**: Chance de encontrar diamantes e esmeraldas
- **Mobs especiais**: Mobs com efeitos visuais
- **Tempestades mágicas**: Eventos climáticos especiais
- **Efeitos visuais**: Partículas e sons para cada poder

## 📁 Estrutura do Projeto

```
src/main/java/me/guss/curso/
├── Curso.java                    # Classe principal
├── commands/                     # Comandos separados
│   ├── SuperPoderCommand.java
│   ├── VoarCommand.java
│   ├── TeletransporteCommand.java
│   ├── RaioCommand.java
│   ├── ExplosaoCommand.java
│   ├── CuraCommand.java
│   ├── VelocidadeCommand.java
│   └── InvisibilidadeCommand.java
├── events/                       # Eventos separados
│   ├── PlayerEvents.java
│   └── WorldEvents.java
└── utils/                        # Utilitários
    ├── PoderManager.java
    ├── ConfigManager.java
    └── CooldownManager.java
```

## 🛠️ Instalação

1. **Compile o projeto**:
   ```bash
   mvn clean package
   ```

2. **Copie o JAR** para a pasta `plugins/` do seu servidor

3. **Reinicie o servidor**

4. **Configure as permissões** no seu plugin de permissões:
   ```
   superpoderes.admin
   superpoderes.voar
   superpoderes.teletransporte
   superpoderes.raio
   superpoderes.explosao
   superpoderes.cura
   superpoderes.velocidade
   superpoderes.invisibilidade
   ```

## ⚙️ Configuração

Edite o arquivo `config.yml` para personalizar:

### Poderes
```yaml
poderes:
  voar:
    habilitado: true
    cooldown: 5
  invisibilidade:
    habilitado: true
    duracao: 300
```

### Eventos
```yaml
eventos:
  drop_diamante_chance: 0.15
  drop_esmeralda_chance: 0.20
  mob_especial_chance: 0.05
```

### Mensagens
```yaml
mensagens:
  prefixo: "§6[SuperPoderes] "
  boas_vindas: "§aBem-vindo ao servidor com Super Poderes!"
```

## 🎮 Como Usar

### Para Jogadores
1. Use `/superpoder` para ver todos os poderes disponíveis
2. Use `/superpoder <poder>` para ativar um poder
3. Use comandos individuais como `/voar`, `/raio`, etc.

### Para Administradores
1. Configure permissões no seu plugin de permissões
2. Edite `config.yml` para personalizar o plugin
3. Use `/superpoder admin` para comandos administrativos

## 🔧 Recursos Técnicos

### Sistema de Cooldown
- Cada poder tem seu próprio cooldown configurável
- Sistema de cooldown por jogador
- Mensagens de cooldown personalizáveis

### Sistema de Configuração
- Configurações carregadas dinamicamente
- Suporte a recarregamento sem reiniciar
- Valores padrão para todas as configurações

### Sistema de Eventos
- Eventos de jogador (join, quit, move, etc.)
- Eventos de mundo (mob death, weather, etc.)
- Efeitos visuais e sonoros

### Sistema de Poderes
- Gerenciamento centralizado de poderes
- Estados persistentes por jogador
- Limpeza automática ao sair

## 🐛 Solução de Problemas

### Plugin não carrega
- Verifique se o Java 17+ está instalado
- Verifique se o Spigot 1.18+ está sendo usado
- Verifique os logs do servidor

### Poderes não funcionam
- Verifique as permissões do jogador
- Verifique se o poder está habilitado no `config.yml`
- Verifique se o cooldown não está ativo

### Efeitos visuais não aparecem
- Verifique se as partículas estão habilitadas no `config.yml`
- Verifique se o jogador tem permissão para ver partículas

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests

## 📞 Suporte

Para suporte, entre em contato através do GitHub ou Discord.

---

**Desenvolvido com ❤️ para a comunidade Minecraft brasileira!**
