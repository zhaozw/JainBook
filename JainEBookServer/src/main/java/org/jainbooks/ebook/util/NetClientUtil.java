package org.jainbooks.ebook.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

public class NetClientUtil {
	private static final Logger logger = Logger.getLogger(NetClientUtil.class);

	public static String httpGetClient(String connectionUrl) {
		String output = "";
		try {
			URL url = new URL(connectionUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				logger.fatal("Failed : HTTP error code : "
						+ conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn
					.getInputStream())));

			while ((output = br.readLine()) != null) {
				logger.info(output);
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
		}
		return output;
	}

	public static void httpGcmPostClient(String jsonString, String serviceType) {
		try {

			String finalInputJsonString = "{\"data\":"
					+ jsonString.replace(",\"registration_ids\"",
							",\"date\":\"" + new Date()
									+ "\",\"pushServiceName\":\"" + serviceType
									+ "\"},\"registration_ids\"");
			System.out.println(finalInputJsonString);
			URL url = new URL("https://android.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", PropertiesFileReaderUtil
					.getPropertyValue("authorization.key"));

			OutputStream os = conn.getOutputStream();
			os.write(finalInputJsonString.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {

				logger.fatal("Failed : HTTP error code : "
						+ conn.getResponseCode());

				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn
					.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				logger.info("Output from Server ....**" + output);

			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			e.printStackTrace();
		}

	}

}
