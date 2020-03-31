package ktbyte.assistant.app.calculator;

import java.util.Arrays;
import java.util.List;

//import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;


public class GetCalculatorAction extends Action{
	String[] keywords = { "calculate", "of", "product", "sum" , "is"};
    double[] scores = { 5, 0.2, 1, 1, 0.1 };
    @Override
    public void doCommand(String command) {

    	List<String> words = Arrays.asList(command.split(" "));
    	
    	HttpRequest req = null;
    	
    	String equation = words.get(words.size() - 1);
    	
    	req = Unirest.get("https://api.mathjs.org/v4/").queryString("expr",equation);
    	
    	try {
    		Assistant assistant = Assistant.getInstance();
            //JsonNode node = req.getBody();
           // System.out.println(node);
           // handleResult(node);
    		String returned = req.asString().getBody();
    		System.out.println(returned);
    		
    		if (returned.contains("Error")) {
    			assistant.displayItem(
                        new Response(returned ) );
    		}
    		else {
	    		assistant.displayItem(
	                    new Response( "The calculation result is " + returned ) );
    		}
        
    		
        } catch (UnirestException e) {
            System.out.println("request error occurred");
        }

    }
    
    @Override
    public double getLikelihood(String command) {
    	
        double score = 0.0;
        if (Character.isDigit(command.charAt(0))) {
        	 
        	return score +=3;
        }
	        if(command.contains("what is")) {
	        	score += 5;
	        }else {
		        for (int i = 0; i < keywords.length; i++) {
		            String keyword = keywords[i];
		            
			        if (command.contains(keyword)) {
			            score += scores[i];
			        }
		            
		        }
	        }
        return score;
    }

}
