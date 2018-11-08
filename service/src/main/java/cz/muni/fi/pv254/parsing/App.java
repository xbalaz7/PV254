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
    steamcommunity.com/profiles/USERID/recommended/RECOMENDATIONID
    store.steampowered.com/app/APPID
     */

    public int getOffsetDiff() {
        return offsetDiff;
    }

    public void setOffsetDiff(int offsetDiff) {
        this.offsetDiff = offsetDiff;
    }

    public int getDebug() {
        return debug;
    }

    public void setDebug(int debug) {
        this.debug = debug;
    }

    /**
     * Level 0 - no print
     * Level 1 - expected, received
     * Level 2 - retrieved items by offset
     * Level 3 - write all
     */
    private int debug = 0;

    private int offsetDiff = 20;


    private List<Integer> gameIds;

    public void addGameId(Integer id) {
        if (id == null) {
            throw new NullPointerException("Tried to add game with null id");
        }
        gameIds.add(id);
    }

    public void removeGameId(Integer id) {
        if (id == null) {
            throw new NullPointerException("Tried to removen game with id null");
        }
        gameIds.remove(id);
    }
    public List<Integer> getGameIds() {
        return Collections.unmodifiableList(gameIds);
    }

    public App() {

        gameIds = new ArrayList<Integer>();
    }

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
        String url = "https://store.steampowered.com/appreviews/" + Integer.toString(gameID) + "?json=1&language=all&filter=recent&start_offset=0";
        JSONObject obj = new JSONObject(getJsonFromUrl(url).toString());
        JSONObject summary = obj.getJSONObject("query_summary");
        return summary.getInt("total_reviews");
    }


    public Set<List<Object>> inteligentParse(int gameID) {
        Set<List<Object>> recIds = new HashSet<>();
        try {
            String url = "https://store.steampowered.com/appreviews/"
                    + Integer.toString(gameID) +
                    "?json=1&language=all&num_per_page="
                    +Integer.toString(getOffsetDiff())+
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
                    JSONObject author = review.getJSONObject("author");
                    String userid = author.getString("steamid");
//                    System.out.println(userid);
                    userid = userid.replaceAll("\"","");
                    Long userId = Long.parseLong(userid);
                    Boolean votedUp = review.getBoolean("voted_up");
                    List<Object> list = new ArrayList();
                    list.add(id);
                    list.add(userId);
                    list.add(votedUp);
                    recIds.add(list);
                }
                if (debug >= 2)
                    System.out.println("Retrieved new items with offset "+Integer.toString(offset)+": "+Integer.toString(recIds.size()-oldSize));
                offset+=getOffsetDiff();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (debug >=1) {

            System.out.println("Received size: "+Integer.toString(recIds.size()));
            System.out.println("Expected size: "+ Integer.toString(getTotalNumberOfReviews(gameID)));
        }
//        if (debug >=3) {
//            ArrayList<Integer> sorted = new ArrayList<>(recIds);
//            Collections.sort(sorted);
//            for (Integer id : sorted) {
//                System.out.println(id.toString());
//            }
//        }
        return recIds;

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
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return name;
    }


    public void inteligentParseAllGanes()  {
        for (Integer id : gameIds) {
            inteligentParse(id);
        }
    }

    @Deprecated
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
                URL url = new URL("https://store.steampowered.com/appreviews/"+10+"?json=1&language=all&filter=recent&start_offset="+Integer.toString(i));
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
//        int[] games = {892760, 911520, 964030,717690,949970,893330,396900};
//        int[] games = {396900,582010,292030};
        int index = 6;
        int[] games = {911520};
        App app = new App();
        app.setOffsetDiff(100);
        app.setDebug(2);
        for (int id : games) {
            System.out.println(app.downloadGameName(id));
            System.out.println(app.getTotalNumberOfReviews(id));
            for (int i = 0 ; i< 1 ; i++) { // DO it more times
//                Thread.sleep(10000); // wait for 10 seconds, so steam wont block us
                app.inteligentParse(id);

            }
        }

    }


}
