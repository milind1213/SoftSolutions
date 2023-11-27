package com.sos.CommonFactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.output.WriterOutputStream;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class CommonREST {
	protected RequestSpecification req;
	protected Response res;
	StringWriter sw;
	PrintStream ps;

	public Response post(RequestSpecification request, String resourceURL) {
		res = request.post(resourceURL);
		return res;
	}

	@SuppressWarnings("deprecation")
	protected Response get(RequestSpecification spec, String resourceURL) {
		sw = new StringWriter();
		ps = new PrintStream(new WriterOutputStream(sw), true);
		res = given().spec(spec).filter(new RequestLoggingFilter(ps)).log().all().when().get(resourceURL);
		sw.toString();
		res.prettyPrint();
		return res;
	}

	protected Response postNew(RequestSpecification spec, String resourceURL) {
		sw = new StringWriter();
		ps = new PrintStream(new WriterOutputStream(sw), true);
		res = given().spec(spec).filter(new RequestLoggingFilter(ps)).log().all().when().post(resourceURL);
		sw.toString();
		res.prettyPrint();
		return res;
	}

	protected Response put(RequestSpecification spec, String resourceURL) {
		sw = new StringWriter();
		ps = new PrintStream(new WriterOutputStream(sw), true);
		res = given().spec(spec).filter(new RequestLoggingFilter(ps)).log().all().when().put(resourceURL);
		return res;
	}

	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public Response getCurrentResponse() {
		return res;
	}

	public void waitFor(int i) {
		try {
			Thread.sleep(1000 * i);
		} catch (Exception e) {
		}
	}

	public Object parseJson(Response response, String path) {
		return response.jsonPath().get(path);
	}

	public String decrypt(int xdLength, String splitterString, String encryptedText) {
		String decoded = null;
		try {
			decoded = new String(org.apache.commons.codec.binary.Base64.decodeBase64(splitterString));
			System.out.println(decoded);
			String one = encryptedText.split(decoded)[0];
			String two = encryptedText.replace(one, "").replace(decoded, "");

			String encryptionString = new String(
					org.apache.commons.codec.binary.Base64.decodeBase64(two.substring(0, xdLength)),
					StandardCharsets.UTF_8).replace("new Function('return \"", "").replace("\"')", "");
			String third = two.substring(xdLength, two.length());
			return getDecryptedString(one + third, new String(
					org.apache.commons.codec.binary.Base64.decodeBase64(encryptionString), StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDecryptedString(String inputText, String secret) {

		SecretKey key = new SecretKeySpec(secret.getBytes(), "AES");
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
		} catch (NoSuchPaddingException e) {
			e.getMessage();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			e.getMessage();
		}
		try {
			Thread.sleep(1000);
			return new String(cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(inputText)));
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRedirectedURL(String link) throws IOException {
		HttpURLConnection con = (HttpURLConnection) (new URL(link).openConnection());
		con.setInstanceFollowRedirects(false);
		con.connect();
		int responseCode = con.getResponseCode();
		System.out.println(responseCode);
		String location = con.getHeaderField("Location");
		System.out.println(location);
		return location;
	}

	public String getJsonPathValue(String res, String key) {
		JsonPath json = new JsonPath(res.toString());
		Object value = json.get(key);
		return String.valueOf(value);
	}
	
	
}
