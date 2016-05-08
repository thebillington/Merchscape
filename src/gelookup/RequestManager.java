package gelookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RequestManager {

	CloseableHttpClient client;

	public RequestManager() {
		client = HttpClients.createDefault();
	}

	public String GET(String url) {

		// Create a new HTTP get object
		HttpGet request = new HttpGet(url);

		// Add a header
		request.addHeader("User-agent", "Mozilla");

		HttpResponse response = null;

		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();

		if (response != null) {
			
			BufferedReader rd = null;

			try {
				rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.print("Unable to complete request");
		}

		return result.toString();

	}
	
	//Method to return an encoded HTML string
	public static String encodeSearchTerm(String term) {
		
		char[] chars = term.toCharArray();
		int spaceLoc = -1;
		boolean space = false;
		char[] temp = new char[chars.length + 2];
		for(int i = 0; i < chars.length; i++) {
			if(chars[i] == ' ') {
				space = true;
				spaceLoc = i;
				for(int j = 0; j < i ; j++) {
					temp[j] = chars[j];
				}
				for(int j = i + 3; j < temp.length; j++) {
					temp[j] = chars[j - 2];
				}
				temp[spaceLoc] = '%';
				temp[spaceLoc + 1] = '2';
				temp[spaceLoc + 2] = '0';
			}
		}
		
		if(space) {
			return String.copyValueOf(temp);
		}
		return term;
	}

}
