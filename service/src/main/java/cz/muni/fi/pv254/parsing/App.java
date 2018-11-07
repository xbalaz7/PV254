package cz.muni.fi.pv254.parsing;

import com.mysql.cj.xdevapi.JsonArray;
import org.json.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    /*
    steamcommunity.com/profiles/USERID
    steamcommunity.com/profiles/USERID/recommended/APPID
    store.steampowered.com/app/APPID
     */
    /*
        892760 - 57 reviews

     */
    String appId = "892760"; // 440,10
    String myJson = "{\"success\":1,\"query_summary\":{\"num_reviews\":20,\"review_score\":9,\"review_score_desc\":\"Overwhelmingly Positive\",\"total_positive\":12843,\"total_negative\":572,\"total_reviews\":13415},\"reviews\":[{\"recommendationid\":\"45164196\",\"author\":{\"steamid\":\"76561197963476421\",\"num_games_owned\":9,\"num_reviews\":1,\"playtime_forever\":33736,\"playtime_last_two_weeks\":18693,\"last_played\":1541008571},\"language\":\"english\",\"review\":\"2018 and still the best CS.\",\"timestamp_created\":1538942067,\"timestamp_updated\":1538942067,\"voted_up\":true,\"votes_up\":20,\"votes_funny\":2,\"weighted_vote_score\":\"0.656377851963043213\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45232859\",\"author\":{\"steamid\":\"76561198289826573\",\"num_games_owned\":5,\"num_reviews\":4,\"playtime_forever\":32541,\"playtime_last_two_weeks\":1453,\"last_played\":1541062611},\"language\":\"english\",\"review\":\"Very good game. Better than CS:GO\",\"timestamp_created\":1539356863,\"timestamp_updated\":1539356863,\"voted_up\":true,\"votes_up\":4,\"votes_funny\":1,\"weighted_vote_score\":\"0.543396234512329102\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45544138\",\"author\":{\"steamid\":\"76561197962754351\",\"num_games_owned\":279,\"num_reviews\":1,\"playtime_forever\":32187,\"playtime_last_two_weeks\":0,\"last_played\":1532450776},\"language\":\"english\",\"review\":\"(g)old\",\"timestamp_created\":1541007010,\"timestamp_updated\":1541007010,\"voted_up\":true,\"votes_up\":2,\"votes_funny\":0,\"weighted_vote_score\":\"0.545454561710357666\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45160704\",\"author\":{\"steamid\":\"76561198216170987\",\"num_games_owned\":39,\"num_reviews\":1,\"playtime_forever\":74,\"playtime_last_two_weeks\":0,\"last_played\":1539274642},\"language\":\"english\",\"review\":\"sa\",\"timestamp_created\":1538928740,\"timestamp_updated\":1538928740,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45304333\",\"author\":{\"steamid\":\"76561198416198476\",\"num_games_owned\":35,\"num_reviews\":18,\"playtime_forever\":474,\"playtime_last_two_weeks\":0,\"last_played\":1539171876},\"language\":\"english\",\"review\":\"Counter-Strike is engaging, tense and great fun. A game of terrorists and counter-terrorists with a competitive level that is unmatched by most other games. The game may be old but it's certainly a worthy entry for someone looking to play a Counter-Strike game or understand the evolution of the series by starting here like myself.\",\"timestamp_created\":1539749740,\"timestamp_updated\":1539749776,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":0,\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45305763\",\"author\":{\"steamid\":\"76561198848641570\",\"num_games_owned\":4,\"num_reviews\":3,\"playtime_forever\":24126,\"playtime_last_two_weeks\":0,\"last_played\":1539608691},\"language\":\"english\",\"review\":\"Best game!! i know its old but still. there is a lot of players in the servers... And the game customization..custom skins custom playermodels etc.\",\"timestamp_created\":1539760325,\"timestamp_updated\":1539760325,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":0,\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":true,\"written_during_early_access\":false},{\"recommendationid\":\"45306832\",\"author\":{\"steamid\":\"76561198215239327\",\"num_games_owned\":146,\"num_reviews\":11,\"playtime_forever\":4420,\"playtime_last_two_weeks\":0,\"last_played\":1537076286},\"language\":\"english\",\"review\":\"I miss the days of this game's prime especially for competitive when you could shoot through a 7-8 foot thick concrete wall with an M4 and stop a B-Rush in like 5 seconds that way.\\nFun fact wall banging that significantly was originally an accident in the CS betas :V\",\"timestamp_created\":1539766983,\"timestamp_updated\":1539767098,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":0,\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45377065\",\"author\":{\"steamid\":\"76561198801986843\",\"num_games_owned\":11,\"num_reviews\":1,\"playtime_forever\":41831,\"playtime_last_two_weeks\":4837,\"last_played\":1541013418},\"language\":\"english\",\"review\":\"good game\",\"timestamp_created\":1540133688,\"timestamp_updated\":1540133688,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":true,\"written_during_early_access\":false},{\"recommendationid\":\"45255255\",\"author\":{\"steamid\":\"76561198057516296\",\"num_games_owned\":5,\"num_reviews\":1,\"playtime_forever\":20617,\"playtime_last_two_weeks\":905,\"last_played\":1541007278},\"language\":\"english\",\"review\":\"This is the only game that i've played since it appeared on the market. And i'm not bored to play it! Well done!\",\"timestamp_created\":1539465156,\"timestamp_updated\":1539465156,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45264497\",\"author\":{\"steamid\":\"76561198441026254\",\"num_games_owned\":4,\"num_reviews\":2,\"playtime_forever\":36,\"playtime_last_two_weeks\":0,\"last_played\":1518704265},\"language\":\"english\",\"review\":\"old but gold!!!!\",\"timestamp_created\":1539516608,\"timestamp_updated\":1539516608,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45245112\",\"author\":{\"steamid\":\"76561197975912053\",\"num_games_owned\":18,\"num_reviews\":2,\"playtime_forever\":23247,\"playtime_last_two_weeks\":1058,\"last_played\":1540992781},\"language\":\"english\",\"review\":\"i love gme\",\"timestamp_created\":1539423252,\"timestamp_updated\":1539423252,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45284498\",\"author\":{\"steamid\":\"76561198339425339\",\"num_games_owned\":7,\"num_reviews\":1,\"playtime_forever\":424,\"playtime_last_two_weeks\":0,\"last_played\":1539189402},\"language\":\"english\",\"review\":\"CS 1.6 has helped me sweep through my teenage. If I wouldn't have bought it on steam this late, I perhaps would have crossed a ten thousand hours mark already. I remember bunking classes to play clan matches.\\n\\nThis game is one of the classic FPSs. Gradually I started moving more unto CSGO for the graphics and ease of gameplay, but damn, those wall bangs and brush fires will be greately missed.\",\"timestamp_created\":1539625622,\"timestamp_updated\":1539625622,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45220584\",\"author\":{\"steamid\":\"76561198864230820\",\"num_games_owned\":20,\"num_reviews\":1,\"playtime_forever\":6381,\"playtime_last_two_weeks\":2471,\"last_played\":1541008853},\"language\":\"english\",\"review\":\"N1 <33\",\"timestamp_created\":1539277673,\"timestamp_updated\":1539277673,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":true,\"written_during_early_access\":false},{\"recommendationid\":\"45149142\",\"author\":{\"steamid\":\"76561198079239270\",\"num_games_owned\":4,\"num_reviews\":1,\"playtime_forever\":36265,\"playtime_last_two_weeks\":1040,\"last_played\":1541020044},\"language\":\"english\",\"review\":\"[code] The classic FPS game par excellence [\\/code]\",\"timestamp_created\":1538878307,\"timestamp_updated\":1538878307,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":1,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45169804\",\"author\":{\"steamid\":\"76561198155374051\",\"num_games_owned\":67,\"num_reviews\":43,\"playtime_forever\":24,\"playtime_last_two_weeks\":0,\"last_played\":1538629878},\"language\":\"english\",\"review\":\"Drink my sodas, they'll make you great, they'll getcha anything that you ever want, you get a girlfriend, you get some money, buy my soda, they'll make you healthy, they'll make your feet small and give you abs, my soda will make your breath smell nice!\",\"timestamp_created\":1538977547,\"timestamp_updated\":1538977547,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45173615\",\"author\":{\"steamid\":\"76561198823604083\",\"num_games_owned\":13,\"num_reviews\":1,\"playtime_forever\":16288,\"playtime_last_two_weeks\":1223,\"last_played\":1541014973},\"language\":\"english\",\"review\":\"Good\",\"timestamp_created\":1539002151,\"timestamp_updated\":1539002151,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45439355\",\"author\":{\"steamid\":\"76561198110132634\",\"num_games_owned\":7,\"num_reviews\":1,\"playtime_forever\":27691,\"playtime_last_two_weeks\":3670,\"last_played\":1541001547},\"language\":\"english\",\"review\":\"Very nostalgic and good game, I play it for 5 years and not yet tired. I like everything in this game. I recommend to everyone !!!\",\"timestamp_created\":1540503896,\"timestamp_updated\":1540503896,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45449825\",\"author\":{\"steamid\":\"76561198323640611\",\"num_games_owned\":7,\"num_reviews\":2,\"playtime_forever\":716,\"playtime_last_two_weeks\":618,\"last_played\":1541028898},\"language\":\"english\",\"review\":\"<3\",\"timestamp_created\":1540569038,\"timestamp_updated\":1540569038,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":\"0.523809552192687988\",\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45503778\",\"author\":{\"steamid\":\"76561197961884062\",\"num_games_owned\":26,\"num_reviews\":1,\"playtime_forever\":1341,\"playtime_last_two_weeks\":1341,\"last_played\":1541028960},\"language\":\"english\",\"review\":\"I played cs 1.6 from 1999 so i am a player!\",\"timestamp_created\":1540839272,\"timestamp_updated\":1540839272,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":0,\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false},{\"recommendationid\":\"45506117\",\"author\":{\"steamid\":\"76561198402047497\",\"num_games_owned\":34,\"num_reviews\":13,\"playtime_forever\":12,\"playtime_last_two_weeks\":0,\"last_played\":1521666591},\"language\":\"english\",\"review\":\"child hood :D\",\"timestamp_created\":1540848712,\"timestamp_updated\":1540848732,\"voted_up\":true,\"votes_up\":1,\"votes_funny\":0,\"weighted_vote_score\":0,\"comment_count\":0,\"steam_purchase\":true,\"received_for_free\":false,\"written_during_early_access\":false}]}";
    String url = "http://steamcommunity.com/profiles/76561198216170987/recommended";

    private StringBuffer getJsonFromUrl(String website) {

        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(website);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println(Integer.toString(status));
                throw new HTTPException(status);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
        }
        catch (MalformedURLException e) {
            System.out.println(e.toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return content;
    }

    private int getTotalNumberOfReviews(int gameID) {
//        try {
            String url = "https://store.steampowered.com/appreviews/" + Integer.toString(gameID) + "?json=1&language=all&filter=recent&start_offset=0";
            JSONObject obj = new JSONObject(getJsonFromUrl(url).toString());
            JSONObject summary = obj.getJSONObject("query_summary");
            return summary.getInt("total_reviews");
//        }
    }

    public void inteligentParse(int gameID) {
        inteligentParse(gameID,20,true);
    }

    public void inteligentParse(int gameID, boolean debug) {
        inteligentParse(gameID,20,debug);
    }

    public void inteligentParse(int gameID, int offsetDif) {
        inteligentParse(gameID,offsetDif,true);
    }

    public void inteligentParse(int gameID,int offsetDif, boolean debug) {
        Set<Integer> recIds = new HashSet<>();
        try {
            String url = "https://store.steampowered.com/appreviews/"
                    + Integer.toString(gameID) +
                    "?json=1&language=all&num_per_page="
                    +Integer.toString(offsetDif)+
                    "&filter=recent&start_offset=";

            boolean isEmpty = false;
            int offset = 0;
            while (true) {
                // TODO chcekc success code
//                Thread.sleep(1000);
                JSONObject obj = new JSONObject(getJsonFromUrl(url + Integer.toString(offset)).toString());
                JSONArray arr = obj.getJSONArray("reviews");
                int oldSize = recIds.size();
                if (arr.length() == 0) {
                    break;
                }
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject review = arr.getJSONObject(i);
                    Integer id = review.getInt("recommendationid");
                    recIds.add(id);
                }
                if (debug)
                    System.out.println("Retrieved new items with offset "+Integer.toString(offset)+": "+Integer.toString(recIds.size()-oldSize));

                offset+=offsetDif;

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Received size: "+Integer.toString(recIds.size()));
        System.out.println("Expected size: "+ Integer.toString(getTotalNumberOfReviews(gameID)));
        if (debug) {

            ArrayList<Integer> sorted = new ArrayList<>(recIds);
            Collections.sort(sorted);
            for (Integer id : sorted) {
                System.out.println(id.toString());
            }
        }

    }

    public String downloadGameName(int gameId) {
        String url = "https://store.steampowered.com/app/" + Integer.toString(gameId);
        String name = "";
        try {
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            Elements nieco = body.getElementsByAttributeValue("class","details_block");
            name = nieco.get(0).text();
            name = name.replaceFirst("Title: ", "");
            name = name.replaceFirst(" Genre(.*)","");
//            System.out.println(name);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return name;
    }

    public void parse() {
        // stiahnut stranko ako string
        int tresh = 61;
        Set<Integer> recIds = new HashSet<Integer>();
        boolean tresh_by_one = false;
        try {
            for (int i =0;i<tresh;i++) {
                if (!tresh_by_one && (i % 20 != 0)) {
//                    System.out.println("SKIP");
                    continue;
                }

                StringBuffer content = new StringBuffer();
                URL url = new URL("https://store.steampowered.com/appreviews/"+appId+"?json=1&language=all&filter=recent&start_offset="+Integer.toString(i));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                System.out.println(status);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                JSONObject obj = new JSONObject(content.toString());
                JSONArray arr = obj.getJSONArray("reviews");
//                System.out.println(Integer.toString(obj.getInt("total_reviews")));
                int oldSize = recIds.size();
                if (arr.length() == 0) {
                    System.out.println("Received empty list of recs");
                }
                for (int j = 0; j < arr.length(); j++) {
//                    System.out.println("HERE "+Integer.toString(i)+" "+Integer.toString(j));
                    JSONObject review = arr.getJSONObject(j);
                    Integer id = review.getInt("recommendationid");
//                    System.out.println(Integer.toString(id));
                    recIds.add(id);
                }
                System.out.println("Retrieved new items with offset "+Integer.toString(i)+": "+Integer.toString(recIds.size()-oldSize));


            }
            System.out.println("Received size: "+Integer.toString(recIds.size()));
            ArrayList<Integer> sorted = new ArrayList<>(recIds);
            Collections.sort(sorted);
            for (Integer id : sorted) {
                System.out.println(id.toString());
            }


        }
        catch (Exception e) {

        }
        return;
//        // aprsovanie json
//        JSONObject obj = new JSONObject(content.toString());
//        System.out.println(content.toString());
//
//        int pageName =  obj.getInt("success");
//        System.out.println(pageName);

        // stahovanie stranky a parsovanie html dokumentu
////        try {
////            Document doc = Jsoup.connect(url).get();
//////        log(doc.title());
////            Element body = doc.body();
////            Elements nieco = body.getElementsByAttributeValue("href","https://steamcommunity.com/id/BoUnD911/recommended/10/");
////            for (Element element : nieco) {
////
////                System.out.println(element.html());
////            }
//
//        }
//        catch (Exception e) {
//
//        }


    }

    public static void main( String[] args ) throws InterruptedException
    {
//        new App().parse();
//        System.out.println( "Hello World!" );
        // 60,11,0,889, 17, 27
//        int[] games = {892760, 911520, 964030,717690,949970,893330,396900};
//        int[] games = {396900,582010,292030};
        int index = 6;
        int[] games = {292030};
        App app = new App();

        for (int id : games) {

            System.out.println(app.downloadGameName(id));
            System.out.println(app.getTotalNumberOfReviews(id));
            for (int i = 0 ; i< 5 ; i++) {
                Thread.sleep(10000); // wait for 10 seconds
                app.inteligentParse(id,100,true);
            }
        }

    }


}
