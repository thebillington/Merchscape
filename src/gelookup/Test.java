package gelookup;

import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class Test {
	
	//Create the scanner object
	private static Scanner sc;
	
	public static void main(String[] args) {
		
		//Create the scanner object
		sc = new Scanner(System.in);
		
		JSONObject response = null;
		JSONArray searchResults = null;
		int noItems = 0;
		
		//Create the search loop
		while (true) {
			
			//Get input
			System.out.print("Search term: ");
			String term = sc.nextLine();
			
			//Encode spaces in the search term to '%20'
			term = RequestManager.encodeSearchTerm(term);
			
			//Try and cast the response to the search to a json object and get the number of items
			try {
				response = new JSONObject(search(term, 1));
				noItems = Integer.parseInt(response.getString(Tags.TOTAL));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			if(noItems == 0) {
				System.out.println("No search results");
			}
			else {
				try {
					searchResults = new JSONArray(response.getString(Tags.ITEMS));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			for(int i = 0; i < searchResults.length(); i++) {
				try {
					JSONObject item = (JSONObject)searchResults.get(i);
					
					System.out.print("Name: " + item.get(Tags.NAME) + ", ");
					System.out.print("ID: " + item.get(Tags.ID) + ", ");
					
					JSONObject current = item.getJSONObject(Tags.CURRENT);
					System.out.println("Current Price: " + current.get(Tags.PRICE));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String getDetails(int i) {
		return GELookup.getDetails(Integer.toString(i));
	}
	
	private static String search(String term, int pages) {
		return GELookup.search(term, pages);
	}

}
