package cz.muni.fi.pv254;

import cz.muni.fi.pv254.entity.Game;
import cz.muni.fi.pv254.entity.Recommendation;
import cz.muni.fi.pv254.entity.User;
import cz.muni.fi.pv254.parsing.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User admin = user(123L,"password", "Admin", "admin@google.com", "766766766", "Praha", Boolean.TRUE);
        User user = user(124L,"password", "User", "user@google.com", "755755755", "Olomouc", Boolean.FALSE);
//        Game game = game(123, "game1");
        App app = new App();
        app.setDebug(0);
        app.setOffsetDiff(100);
//        int[] games = {892760, 911520, 964030,717690,949970,893330,396900,396900,582010,292030};
        long[] games = {892760L};
        for (long id : games) {
            Game game = game(id, app.downloadGameName(id));
            Set<List<Object>> ret = app.inteligentParse(id);
            for (List<Object> rec : ret) {

                Long userIdInt = (Long) rec.get(1);
//                String userIdString = Long.toString((Long)rec.get(1));
                String userIdString = app.downloadUserName(userIdInt);
                String userIdEmail = userIdString + "@" + Long.toString(userIdInt) + ".com";
                System.out.println(userIdString);
                User author = user(userIdInt,userIdString, userIdString, userIdEmail, userIdString, userIdString, Boolean.FALSE);
                Long recId = (Long) rec.get(0);
                Boolean recVotedUp = (Boolean) rec.get(2);
                Recommendation recommendation = recommendation(recId, recVotedUp, game, author);
            }

        }
    }

    private Recommendation recommendation(Long steamId, boolean votedUp, Game game, User user) {
        Recommendation r = new Recommendation();
        r.setSteamId(steamId);
        r.setVotedUp(votedUp);
        r.setAuthor(user);
        r.setGame(game);
        recommendationService.add(r);
        return r;
    }

    private User user(long steamId, String password, String name, String email, String phone, String address, Boolean isAdmin) {
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

    private Game game(Long steamId, String name){
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