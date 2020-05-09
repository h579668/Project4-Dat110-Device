package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class RestClient {
	
	private OkHttpClient client;
	private Gson gson;
	public static final MediaType JSON
			= MediaType.parse("application/json; charset=utf-8");

	public RestClient() {
		// TODO Auto-generated constructor stub
		gson = new Gson();
		client = new OkHttpClient();
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message){

		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		AccessMessage msg = new AccessMessage(message);
		RequestBody body = RequestBody.create(JSON, gson.toJson(msg));

		Request request = new Request.Builder()
				.url("http://localhost:8080/accessdevice/log/")
				.post(body)
				.build();

		System.out.println(request);

		Response response;
		try {
			response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: implement a HTTP POST on the service to post the message
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();

		Request request = new Request.Builder()
				.url("http://localhost:8080/accessdevice/code")
				.get()
				.build();

		System.out.println(request);

		Response respons;
		
		try {
			respons = client.newCall(request).execute();
			String body = respons.body().string();
			System.out.println(body);
			code = gson.fromJson(body, AccessCode.class);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;
	}
}
