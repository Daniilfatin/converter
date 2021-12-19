package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text AED;

    @FXML
    private Text BTC;

    @FXML
    private Text BYN;

    @FXML
    private Text CHF;

    @FXML
    private Text CNY;

    @FXML
    private Text GBP;

    @FXML
    private Text JPY;

    @FXML
    private Text KZT;

    @FXML
    private Text RUB;

    @FXML
    private Text THB;

    @FXML
    private Text TRY;

    @FXML
    private Text USD;

    @FXML
    private TextField amount;

    @FXML
    private Button getData;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String amountInput = amount.getText().trim().replace(",",".").replaceAll("[^0-9&^\\.]","");

            if(!amountInput.equals("")){
                Double getAmount = Double.valueOf(amountInput);
                String output = getUrlContent("http://data.fixer.io/api/latest?access_key=83117de4f4cd0e15492a6c9ba5eba0f1");

                if(!output.isEmpty()){
                    JSONObject obj = new JSONObject(output);
                    AED.setText("AED     " + obj.getJSONObject("rates").getDouble("AED") * getAmount);
                    BTC.setText("BTC     " + obj.getJSONObject("rates").getDouble("BTC") * getAmount);
                    BYN.setText("BYN     " + obj.getJSONObject("rates").getDouble("BYN") * getAmount);
                    CHF.setText("CHF     " + obj.getJSONObject("rates").getDouble("CHF") * getAmount);
                    CNY.setText("CNY     " + obj.getJSONObject("rates").getDouble("CNY") * getAmount);
                    GBP.setText("GBP     " + obj.getJSONObject("rates").getDouble("GBP") * getAmount);
                    JPY.setText("JPY     " + obj.getJSONObject("rates").getDouble("JPY") * getAmount);
                    KZT.setText("KZT     " + obj.getJSONObject("rates").getDouble("KZT") * getAmount);
                    RUB.setText("RUB     " + obj.getJSONObject("rates").getDouble("RUB") * getAmount);
                    THB.setText("THB     " + obj.getJSONObject("rates").getDouble("THB") * getAmount);
                    TRY.setText("TRY     " + obj.getJSONObject("rates").getDouble("TRY") * getAmount);
                    USD.setText("USD     " + obj.getJSONObject("rates").getDouble("USD") * getAmount);
                }
            } else {
                amount.clear();
            }
        });
    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println("Произошла ошибка");
        }
        return content.toString();
    }
}
