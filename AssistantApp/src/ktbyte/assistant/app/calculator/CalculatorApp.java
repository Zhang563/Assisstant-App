package ktbyte.assistant.app.calculator;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.mashape.unirest.http.JsonNode;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;

public class CalculatorApp extends App{

  @Override
  public List<Action> getActions() {
    return Arrays.asList(new GetCalculatorAction());
  }
}