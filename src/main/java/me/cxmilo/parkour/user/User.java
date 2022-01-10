package me.cxmilo.parkour.user;

import me.cxmilo.parkour.find.Findable;
import me.cxmilo.parkour.game.impl.CompletedGame;
import me.cxmilo.parkour.game.impl.ParkourGame;
import me.cxmilo.parkour.message.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User
        implements Findable<Player> {

    private final UUID id;
    private Lang lang;
    private ParkourGame activeGame;
    private List<CompletedGame> completedGames;

    /**
     * Initialize a new {@link Player player} wrapper to store all active and completed games and their language
     *
     * @param id player's unique id
     */
    public User(UUID id) {
        this.id = id;
        this.lang = Lang.ES;
        this.activeGame = null;
        this.completedGames = new ArrayList<>();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(id);
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public ParkourGame getActiveGame() {
        return activeGame;
    }

    public void setActiveGame(ParkourGame activeGame) {
        this.activeGame = activeGame;
    }

    public List<CompletedGame> getCompletedGames() {
        return completedGames;
    }

    public void setCompletedGames(List<CompletedGame> completedGames) {
        this.completedGames = completedGames;
    }

    @Override
    public Player getFindKey() {
        return Bukkit.getPlayer(id);
    }
}
