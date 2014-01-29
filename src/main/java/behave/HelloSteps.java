package behave;

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
	
	@When("I call the web service to place an order")
	public void placeOrder() {
		try {
//		    String parameters = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
//		        "<soap:Body>"+
//		        " <HelloWorld xmlns=\"http://np-challenger\" />"+
//		        "</soap:Body>"+
//		        "</soap:Envelope>";
		    
		    String parameters = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
		    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
		    "<soap12:Body>" +
		      "<GetCityForecastByZIP xmlns=\"http://ws.cdyne.com/WeatherWS/\">" +
		        "<ZIP>60618</ZIP>" +
		      "</GetCityForecastByZIP>" +
		    "</soap12:Body>" +
		  "</soap12:Envelope>";
		    System.out.println(parameters);
		    
		    java.net.URL url = new java.net.URL("http://wsf.cdyne.com/WeatherWS/Weather.asmx");
		    java.net.HttpURLConnection connjava = (java.net.HttpURLConnection)url.openConnection();
		    connjava.setRequestMethod("POST");
		    connjava.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
		    connjava.setRequestProperty("Content-Language", "en-US"); 
		    connjava.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
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
	
}
