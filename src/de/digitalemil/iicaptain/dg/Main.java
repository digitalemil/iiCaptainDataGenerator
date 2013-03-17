package de.digitalemil.iicaptain.dg;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main implements Runnable {
	Map<Integer, Airport> airports = new HashMap<Integer, Airport>(10000);
	String url, url2;
	static int delay;

	public Main(String u1, String u2) {
		url = u1;
		url2= u2;
	}

	public Main() {
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException, InterruptedException {
		new Main().run(args[0], Integer.parseInt(args[1]),
				Integer.parseInt(args[2]));

	}

	private void run(String baseurl, int numthreads, int d) throws IOException,
			InterruptedException {
		delay =d;
		InputStream in = getClass().getResourceAsStream("/airports.dat.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String line = null;
		StringTokenizer stk;
		int a = 0;
		do {
			line = reader.readLine();
			if (line != null) {
				line = line.replaceAll("\"", "");
				line = line.replaceAll(",,", ",na,");
				
				stk = new StringTokenizer(line, ",");
				Airport airport = new Airport();
				try {
					airport.setId(new Integer(stk.nextToken()));
					airport.setName(stk.nextToken());
					airport.setCity(stk.nextToken());
					airport.setCountry(stk.nextToken());
					airport.setIata(stk.nextToken());
					airport.setIcao(stk.nextToken());
					airport.setLatitude(stk.nextToken());
					airport.setLongitude(stk.nextToken());
					airport.setAltitude(stk.nextToken());
					airport.setTimezone(stk.nextToken());
					airport.setDst(stk.nextToken());
					airport.setId(a);
				} catch (Exception e) {
					System.err.println("Can't parse: " + line);
					continue;
				}
				airports.put(a, airport);
				// System.out.println(airport);
				a++;
			}

		} while (line != null);
		System.out.println("Starting " + numthreads + " threads.");

		Thread thread = null;
		for (int i = 0; i < numthreads; i++) {
			a = (int) (Math.random() * airports.size());
			Airport airport = airports.get(a);
			url = baseurl + "/world/location?longitude="
					+ airport.getLongitude() + "&latitude="
					+ airport.getLatitude() + "&altitude="
					+ airport.getAltitude() + "&iata=" + airport.getIata();
			
			url2 = baseurl + "/world/create?width=256&height=256&type=java";
			thread = new Thread(new Main(url, url2));
			thread.start();
			Thread.currentThread().sleep(d/numthreads);
		}
		thread.join();
	}

	public void run() {
		
		while (true) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request= new HttpGet(url);
				HttpResponse response = client.execute(request);
				System.out.println(response.getStatusLine()+" "+url);
				request.releaseConnection();
				request= new HttpGet(url2);
				response = client.execute(request);
				request.releaseConnection();
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
