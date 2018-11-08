package cz.muni.fi.pv254;

import cz.muni.fi.pv254.entity.Game;
import cz.muni.fi.pv254.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {


    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RecommendationService recommendationService;

    @Override
    public void loadData() throws IOException {
        User admin = user(123,"password", "Admin", "admin@google.com", "766766766", "Praha", Boolean.TRUE);
        User user = user(124,"password", "User", "user@google.com", "755755755", "Olomouc", Boolean.FALSE);
        Game game = game(123, "game1");
    }

    private User user(int steamId, String password, String name, String email, String phone, String address, Boolean isAdmin) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPhone(phone);
        u.setAddress(address);
        u.setIsAdmin(isAdmin);
        u.setSteamId(steamId);
        User existing = userService.findByEmail(email);
        if (existing == null)
            userService.registerUser(u, password);
        return u;
    }

    private Game game(int steamId, String name){
        Game g = new Game();
        g.setSteamId(steamId);
        g.setName(name);
        g.setRecommendations(new HashSet<>());

        Game existing = gameService.findByName(name);
        if (existing == null)
            gameService.add(g);
        return g;
    }
}