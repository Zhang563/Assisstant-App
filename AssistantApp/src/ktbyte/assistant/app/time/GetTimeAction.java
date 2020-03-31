package ktbyte.assistant.app.time;

import java.time.LocalTime;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class GetTimeAction extends Action {

  @Override
  public void doCommand(String command) {
    Assistant assistant = Assistant.getInstance();

    String result = LocalTime.now().toString();

    assistant.displayItem( new Response("Local time: " + result) );
  }


  @Override
  public double getLikelihood(String command) {
    if (command.equals("time")) return 9.0;
    if (command.contains("time")) return 3.0;
    return 0;
  }

}