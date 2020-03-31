package ktbyte.assistant.app.todolist;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;
import ktbyte.assistant.app.weather.GetWeatherAction;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.mashape.unirest.http.JsonNode;



public class TodolistApp extends App{

  @Override
  public List<Action> getActions() {
    return Arrays.asList(new AddTodolistAction(), new CheckTodolistAction(), new MarkTodolistAction(), new ShowTodolistAction());
  }
}
