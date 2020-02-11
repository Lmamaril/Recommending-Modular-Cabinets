package assignment2_refactored;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Handles File reading a customer request and writing a recommendation.
 * It does not make sense to add an equals() toString, and hashcode() function when there are no
 * fields of data being stored for this class.
 */
public class FileIO {

  /**
   * Read the customer request from a JSON file and returns a Customer object.
   * JSON is required to have the following keys:
   * - "maxHeight"
   * - "maxWidth"
   * - "drawerCount"
   * - "shelfCount"
   * - "color"
   * - "mount"
   * @param filename Name of the json file representing the customerRequest.
   * @return customerRequest CustomerRequest object with details of the customer request.
   * @throws ParseException Exception thrown while an error may occur when parsing the JSON.
   * @throws IOException Exception thrown when there are issues with Reading the file.
   */
  public CustomerRequest readRequest(String filename) throws ParseException, IOException{

    CustomerRequest customerRequest;
    Object customer = new JSONParser().parse(new FileReader(filename));
    JSONObject custData = (JSONObject) customer;
    int maxHeight = Integer.parseInt((String)custData.get("maxHeight"));
    int maxWidth = Integer.parseInt((String)custData.get("maxWidth"));
    int drawerCnt = Integer.parseInt((String)custData.get("drawerCount"));
    int shelfCnt =  Integer.parseInt((String)custData.get("shelfCount"));
    String color = (String)custData.get("color");
    String mount =  (String)custData.get("mount");
    Double budget = Double.parseDouble((String)custData.get("budget"));

    customerRequest = new CustomerRequest(maxHeight, maxWidth, shelfCnt,
        drawerCnt, color, mount, budget);

    return customerRequest;
  }

  /**
   * Prints the recommendation into a new file.
   * @param recommendationList RecommendationList object containing recommendations to be printed.
   * @return String of information that was printed.
   */
  public String printRecommendation(List<Recommendation> recommendationList) {
    Gson gsonObj = new Gson();
    StringBuilder json = new StringBuilder();
    for (Recommendation recommendation : recommendationList) {
      String output = gsonObj.toJson(recommendation);
      json.append(output);
    }
    try {
      FileWriter fileWriter = new FileWriter("FurnitureRecommendation.json");
      PrintWriter printer = new PrintWriter(fileWriter);
      printer.print(json);
      printer.close();
    }
    catch (IOException error) {
      System.out.println(error);
    }
    return json.toString();
  }



}
