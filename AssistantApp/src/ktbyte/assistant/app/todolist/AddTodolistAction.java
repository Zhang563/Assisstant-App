package ktbyte.assistant.app.todolist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class AddTodolistAction extends Action{
	String[] keywords = {"add"};
	double[] scores = {5};
	public static HashMap<String, Boolean> events = new HashMap<String, Boolean>();
	
	@Override
	public void doCommand(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        
        String event = words.get(words.size() - 1);
        

        try {
        	Assistant assistant = Assistant.getInstance();

        	if (!events.containsKey(event)) {
        	events.put(event, false);
        	System.out.println("TodolistADD " + event);
        	assistant.displayItem(new Response(event + " is added to your to do list"));
        	}else {
        	System.out.println("Error: event already exist");
        	assistant.displayItem(new Response(event + " already exist in your event, cannot add again"));
        	}
        }catch(Exception e) {
        	System.out.println("Error occured" + e);
        }
	}
	
	@Override
    public double getLikelihood(String command) {
        double score = 0;
        for (int i = 0; i < keywords.length; i++) {
            String keyword = keywords[i];
            if (command.contains(keyword)) {
                score += scores[i];
            }
        }
        return score;
    }
}

