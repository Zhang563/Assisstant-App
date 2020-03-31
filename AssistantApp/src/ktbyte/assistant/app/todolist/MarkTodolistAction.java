package ktbyte.assistant.app.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class MarkTodolistAction extends Action{
	String[] keywords = {"set"};
	double[] scores = {5};
	
	@Override
	public void doCommand(String command) {
		
		List<String> words = Arrays.asList(command.split(" "));
		
		Boolean contains = false;
		String event = null;
		try {
			Assistant assistant = Assistant.getInstance();

			for(int i = 0; i < words.size(); i++) {
				event = words.get(i);
				if(AddTodolistAction.events.containsKey(event)) {
					contains = true;
					break;
				}
			}
			
			if(contains) {
			AddTodolistAction.events.replace(event,true);
			assistant.displayItem(new Response(event + " is marked as complete"));
			}else {
				System.out.println("event not fount");
				assistant.displayItem(new Response("Event is not found in your list"));
			}
		}catch (Exception e){
			System.out.println("Error occured:" + e);
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
