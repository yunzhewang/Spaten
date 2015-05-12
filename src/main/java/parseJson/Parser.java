package parseJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pois.Poi;


public class Parser {

	public Poi returnPoi(int id, JSONObject obj) throws JSONException {
		
		JSONArray array;
		String titleJ   = "";
	    String addressJ = "";
	    String ratingJ  = "";
	    String rtitleJ  = "";
	    String reviewJ  = "";
		String longitude = "";
		String latitude  = "";
		
		/*get title*/				
		if (obj.has("title")) {
			array= obj.getJSONArray("title");
			for (int j = 0; j < array.length(); j++) {
				titleJ = titleJ + array.getString(j);
			}
			titleJ = titleJ.replaceAll("\n", "");
		}
		
		/*get address*/
		if (obj.has("address")) {
			array = obj.getJSONArray("address");
			for (int j = 0; j < array.length(); j++) {
				addressJ = addressJ + array.getString(j);
			}
			addressJ = addressJ.replaceAll("\n", "");
		}
	
		/*get rating*/
		if (obj.has("rating")) {
			array = obj.getJSONArray("rating");
			for (int j = 0; j < array.length(); j++) {
				ratingJ = ratingJ + array.getString(j);
			}
			ratingJ = ratingJ.replaceAll("\n", "");
		}
	
		/*get review title*/
		if (obj.has("rtitle")) {
			array = obj.getJSONArray("rtitle");
			for (int j = 0; j < array.length(); j++) {
				rtitleJ = rtitleJ + array.getString(j);
			}
			rtitleJ = rtitleJ.replaceAll("\n", "");
		}
	
		/*get review*/
		if (obj.has("review")) {
			array = obj.getJSONArray("review");
			for (int j = 0; j < array.length(); j++) {
				reviewJ = reviewJ + array.getString(j);
			}
			reviewJ = reviewJ.replaceAll("\n", "");
		}
	
		/*get longitude*/
		if (obj.has("longitude")) {
			longitude = obj.getString("longitude");
		}
		
		/*get latitude*/
		if (obj.has("latitude")) {
			latitude = obj.getString("latitude");
		}
		
		/*create restaurant object*/
		Poi p = new Poi(id, titleJ, addressJ, ratingJ, rtitleJ, reviewJ, longitude, latitude);
		
		return p;
		
	}
	
	public HashMap<Integer, Poi> createPois(String path) throws IOException, JSONException {
		
		HashMap<String, Poi> restaurantsMap = new HashMap<String, Poi>();
		HashMap<Integer, Poi> restRetMap = new HashMap<Integer, Poi>();

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
        line = br.readLine();
        int i = 1;
        while (line != null){
			JSONObject obj = new JSONObject(line);
			Poi p = returnPoi(i, obj);
			//Add only those that are an actual poi
			if ((p.getLatitude() != "") && (p.getLongitude() != "")) {
				//If the restaurant already exists on the HashMap, merge their reviews
				if (restaurantsMap.containsKey(p.getTitle())) {
					Poi pp = restaurantsMap.get(p.getTitle());
					pp.addReview(p.getReviews().get(0));
				} else {
					restaurantsMap.put(p.getTitle(), p);
					restRetMap.put(i, p);
					i++;
				}
			}
        	line=br.readLine();
        }
        br.close();
        return restRetMap;
	}

}