package sample;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.*;
import java.util.*;
import java.text.*;
public class Controller implements Initializable {
    @FXML
    private Button ok,start,reset;
    @FXML
    private Label atm,rslt,attempts,word;
    @FXML
    private TextField letter;
    @FXML
    private ProgressIndicator prgrs;

    private String[] words = {"chair","water","sky","rain","flower","loop","mountain","car","television","football",
            "computer","friend","bag","spoon","fruit","leg","eye","rainbow","cloud","love","shit","fuck","dick"};
    private static String chosen_word = null;
    private static double wordln = 0;
    private static final ArrayList<String> dashes = new ArrayList();
    private static int trueAtm = 0;
    private static int atmp = 10;
    private static final String validLetters = "abcdefghijklmnopqrstuvwxyz";
    private DecimalFormat df = new DecimalFormat("####.######");
    @FXML
    private void startButton(){
        ok.setVisible(true);
        start.setVisible(false);
        reset.setVisible(true);
        rslt.setVisible(true);
        attempts.setVisible(true);
        word.setVisible(true);
        letter.setVisible(true);
        rslt.setText("the word has " + df.format(wordln) + " letters");
        prgrs.setVisible(true);
        atm.setVisible(true);
    }
    @FXML
    private void okButton(){
        double prg = 0;
        if(atmp != 0) {
            String lt = letter.getText().toLowerCase();
            if (lt.length() == 1) {
                if (validLetters.contains(lt)) {
                    TreeSet<Integer> indexes = getIndex(lt);
                    if (!indexes.isEmpty()) {
                        rslt.setTextFill(Color.GREEN);
                        rslt.setText("there is a " + lt + " in the word!");
                        trueAtm++;
                        for (int i : indexes) {
                            dashes.set(i, lt + " ");
                        }
                        setWord();
                    } else {
                        atmp--;
                        rslt.setTextFill(Color.RED);
                        rslt.setText("nope! there is no " + lt + " in the word");
                        attempts.setText(String.valueOf(atmp));

                    }
                } else {
                    rslt.setTextFill(Color.RED);
                    rslt.setText("please enter a valid letter!");
                }
            } else {
                rslt.setTextFill(Color.RED);
                rslt.setText("please enter one letter!");

            }
        }else{
            ok.setDisable(true);
            rslt.setText("You have lost :(");
            word.setText(chosen_word);
        }
        if (trueAtm == wordln) {
            word.setTextFill(Color.GREEN);
            word.setText("You did it!");
            letter.setDisable(true);
            ok.setDisable(true);
            rslt.setTextFill(Color.BLUE);
            rslt.setText("the word was  : " + chosen_word);
        }
        for (String a : dashes) {
            if (!a.equals("_ ")) prg++;
        }
        prgrs.setProgress(prg / wordln);
        letter.clear();
    }
    @FXML
    private void resetButton(){
        Random rnd = new Random();
        ok.setDisable(false);
        letter.setDisable(false);
        ok.setVisible(false);
        letter.setVisible(false);
        atm.setVisible(false);
        attempts.setVisible(false);
        word.setVisible(false);
        reset.setVisible(false);
        prgrs.setVisible(false);
        start.setVisible(true);
        rslt.setVisible(false);
        atmp = 10;
        chosen_word = words[rnd.nextInt()];
        wordln = chosen_word.length();
    }
    private TreeSet<Integer> getIndex(String let){
        TreeSet<Integer> temp = new TreeSet();
        for (int i = 0; i<chosen_word.length();i++){
            StringBuilder bd = new StringBuilder();
            bd.append(chosen_word.charAt(i));
            if (bd.toString().equals(let)){
                temp.add(i);
            }
        }
        return temp;
    }
    private void setWord(){
        StringBuilder bd = new StringBuilder();
        for (String a : dashes){
            bd.append(a);
        }
        word.setText(bd.toString());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Random rnd = new Random();
        chosen_word = words[rnd.nextInt(words.length)];
        wordln = chosen_word.length();
        attempts.setText(String.valueOf(atmp));
        for (int i = 0; i<wordln ; i++ ){
            dashes.add("_ ");
        }
        setWord();
    }
}
