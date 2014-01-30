package behave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class HelloSteps {
	
	@Given("step represents a precondition to an event")
	public void a1() {
		
	}
	
	@When("step represents the occurrence of the event")
	public void a2() {
		
	}

	@Then("step represents the outcome of the event")
	public void a3() {
		
	}
	
	@When("I call the web service to get the weather forcast using $inputFileName")
	public void placeOrder(String inputFileName) {
		try {
		    
			String parameters = readFile(inputFileName);
		    
		    java.net.URL url = new java.net.URL("http://wsf.cdyne.com/WeatherWS/Weather.asmx");
		    java.net.HttpURLConnection connjava = (java.net.HttpURLConnection)url.openConnection();
		    connjava.setRequestMethod("POST");
		    connjava.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
		    connjava.setRequestProperty("Content-Language", "en-US"); 
		    connjava.setRequestProperty("Content-Type", "text/xml");
		    connjava.setRequestProperty("SOAPAction", "http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP");
		    connjava.setDoInput(true); 
		    connjava.setDoOutput(true); 
		    connjava.setUseCaches(false); 
		    connjava.setAllowUserInteraction(true);
		    java.io.DataOutputStream printout = new java.io.DataOutputStream (connjava.getOutputStream());
		    printout.writeBytes(parameters);
		    printout.flush();
		    printout.close();
		    java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(connjava.getInputStream()));
		    String line;            
		    while ((line = in.readLine()) != null) {
		        System.out.println(line);
		    }
		    in.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private String readFile(String inputFileName)
			throws UnsupportedEncodingException, IOException {
		InputStream is = getClass().getResourceAsStream("/input/" + inputFileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuilder builder = new StringBuilder();
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        builder.append(line);
		        line = br.readLine();
		    }
		    
		} finally {
		    br.close();
		}
		
		return builder.toString();
	}
	
}
