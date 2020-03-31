package ktbyte.assistant.app.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;


public class ShowTodolistAction extends Action{
	String[] keywords = {"show", "all"};
	double[] scores = {5, 1};
	
	@Override
	public void doCommand(String command) {
		
		List<String> words = Arrays.asList(command.split(" "));

		boolean showStatus = false;
		if (words.contains("finished") || words.contains("completed")) showStatus = true;
		//TODO add ittorator
		//Iterator keyIt = AddTodolistAction.events.entrySet.iterator();
		try {
			Assistant assistant = Assistant.getInstance();

			System.out.println("Printing " + showStatus + " events");
			if (showStatus) {
				assistant.displayItem(new Response("The completed events are: "));
			}else {
				assistant.displayItem(new Response("These events are yet to be done: "));

			}


			for(String event : AddTodolistAction.events.keySet()) {
				if(AddTodolistAction.events.get(event) == showStatus) {
					System.out.println("Found event " + event);
					assistant.displayItem(new Response(event));
				}
			}
		}catch(Exception e) {
			System.out.println("Error");
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
