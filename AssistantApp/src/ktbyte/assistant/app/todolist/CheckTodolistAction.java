package ktbyte.assistant.app.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class CheckTodolistAction extends Action{
	String[] keywords = {"status", "check", "did"};
	double[] scores = {5, 1, 1};

	
	@Override
	public void doCommand(String command) {
        List<String> words = Arrays.asList(command.split(" "));
        
        String event = words.get(words.size() - 1);

        for(int i = 0; i < words.size(); i++) {
			event = words.get(i);
			if(AddTodolistAction.events.containsKey(event)) {

				break;
			}
		}
        //HashMap<String, Boolean> events = new HashMap<String, Boolean>();
        
        try {
        	Assistant assistant = Assistant.getInstance();
        	System.out.println("containevent: " + AddTodolistAction.events.containsKey(event));
        	if(AddTodolistAction.events.containsKey(event)) {
        		
        		System.out.println(event + "status: " + AddTodolistAction.events.get(event));
        		if(AddTodolistAction.events.get(event)) {
                	assistant.displayItem(new Response(event + " was marked as complete"));
        		}else {
        			assistant.displayItem(new Response(event + " still needs to be done"));
        		}
        		
        	}else{
	        	System.out.println("Event not found:" + event);
	        	assistant.displayItem(new Response(event + " is not in your to do list"));
        	}
        
        }catch(Exception e) {
        	System.out.println("Error occured: " + e);
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


