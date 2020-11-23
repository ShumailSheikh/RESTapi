package com.developer.RestAPI.controllers;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MainController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView get(@RequestParam("id") String id) {
        ModelAndView mv = new ModelAndView("redirect:/");
        String superhero = getSuperheroById(id);
        try {
            JSONObject json = new JSONObject(superhero);
            mv.addObject("image", json.getJSONObject("image").getString("url"));
            mv.addObject("name", json.getString("name"));
            mv.addObject("place-of-birth", json.getJSONObject("biography").get("place-of-birth").toString());
            mv.addObject("relatives", json.getJSONObject("connections").get("relatives").toString());
            mv.addObject("real-name", json.getJSONObject("biography").get("full-name").toString());
            mv.addObject("alter-egos", json.getJSONObject("biography").get("alter-egos").toString());
            mv.addObject("race", json.getJSONObject("appearance").get("race").toString());
            mv.addObject("gender", json.getJSONObject("appearance").get("gender").toString());
            mv.addObject("height", json.getJSONObject("appearance").get("height").toString());
            mv.addObject("weight", json.getJSONObject("appearance").get("weight").toString());
            mv.addObject("work", json.getJSONObject("work").get("occupation").toString());
            mv.addObject("power", json.getJSONObject("powerstats").get("intelligence").toString());
            mv.addObject("power1", json.getJSONObject("powerstats").get("strength").toString());
            mv.addObject("power2", json.getJSONObject("powerstats").get("speed").toString());
            mv.addObject("power3", json.getJSONObject("powerstats").get("durability").toString());
            mv.addObject("power4", json.getJSONObject("powerstats").get("power").toString());
            mv.addObject("power5", json.getJSONObject("powerstats").get("combat").toString());

        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return mv;
    }

    private String getSuperheroById(String id) {
        try {
            String apiKey = "10221405381743383";
            URL urlForGetRequest = new URL("https://superheroapi.com/api/" + apiKey + "/" + id);

            //COVID19 SAMPLE CALL
            //urlForGetRequest = new URL("https://api.covid19api.com/summary");

            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            } else {
                return "Unexpected HTTP response";
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }
}

class Hero
{
    private String name;
    public String getName()
    {
        return name;
    }
    public Hero()
    {
        name = "DEFAULT";
    }
    public Hero(String n)
    {
        name = n;
    }
}
