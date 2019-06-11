package blockchain;

import de.flexiprovider.common.util.ByteUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public final static String DIFFICULTY = "00000";
    public final static int COUNT = 5;
    public final static String VALID = "#ceffb5";
    public final static String INVALID = "#ffb7b7";

    @FXML private TextField block1, block2, block3, block4, block5;
    @FXML private TextField nonce1, nonce2, nonce3, nonce4, nonce5;
    @FXML private TextArea data1, data2, data3, data4, data5;
    @FXML private TextField prev1, prev2, prev3, prev4, prev5;
    @FXML private TextField hash1, hash2, hash3, hash4, hash5;
    @FXML private Button mine1, mine2, mine3, mine4, mine5;
    @FXML private Pane pane1, pane11, pane2, pane22, pane3, pane33, pane4, pane44, pane5, pane55;

    private ArrayList<TextField> blocks, nonces, previs, hashes;
    private ArrayList<TextArea> data;
    private ArrayList<Button> mine;
    private ArrayList<Pane> pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        blocks = new ArrayList<>(Arrays.asList(block1, block2, block3, block4, block5));
        nonces = new ArrayList<>(Arrays.asList(nonce1, nonce2, nonce3, nonce4, nonce5));
        previs = new ArrayList<>(Arrays.asList(prev1, prev2, prev3, prev4, prev5));
        hashes = new ArrayList<>(Arrays.asList(hash1, hash2, hash3, hash4, hash5));
        data = new ArrayList<>(Arrays.asList(data1, data2, data3, data4, data5));
        mine = new ArrayList<>(Arrays.asList(mine1, mine2, mine3, mine4, mine5));
        pane = new ArrayList<>(Arrays.asList(pane1, pane11, pane2, pane22, pane3, pane33,
                pane4, pane44, pane5, pane55));

        generateBlockChain();

        for(TextField nonce : nonces) {
            nonce.setOnKeyReleased(e -> {
                int index = nonces.indexOf(nonce);
                checkValidity(index);
            });
        }

        for(TextArea area : data) {
            area.setOnKeyReleased(e -> {
                int index = data.indexOf(area);
                checkValidity(index);
            });
        }

        for(Button button : mine) {
            button.setOnAction(e -> {
                mineBlock(mine.indexOf(button));
            });
        }
    }

    public void mineBlock(int index)
    {
        String prev = (index == 0) ? previs.get(0).getText() : hashes.get(index-1).getText();

        long nonce = 0;
        String hash, content = data.get(index).getText();
        while(true) {
            hash = getHash(nonce + prev + content);

            if(hash.startsWith(DIFFICULTY)) break;
            nonce++;
        }

        mine.get(index).setStyle("-fx-background-color:#4271f4");

        pane.get(2*index+1).setStyle("-fx-background-color:#ceffb5");
        pane.get(2*index).setStyle("-fx-background-color:#ceffb5");
        nonces.get(index).setText(Long.toString(nonce));
        hashes.get(index).setText(hash);

        checkValidity(index + 1);
    }

    public void checkValidity(int startIndex) {

        for(int i = startIndex; i < COUNT; i++) {
            String prev;
            if(i == 0) {
                prev = previs.get(i).getText();
            }
            else {
                prev = hashes.get(i-1).getText();
                previs.get(i).setText(prev);
            }

            String hash = getHash(nonces.get(i).getText() + prev + data.get(i).getText());

            if(hash.startsWith(DIFFICULTY)) {
                pane.get(2*i+1).setStyle("-fx-background-color:" + VALID);
                pane.get(2*i).setStyle("-fx-background-color:" + VALID);
            }
            else {
                pane.get(2*i+1).setStyle("-fx-background-color:" + INVALID);
                pane.get(2*i).setStyle("-fx-background-color:" + INVALID);
            }
            hashes.get(i).setText(hash);
        }
    }

    public void generateBlockChain() {

        for(int i = 0; i < COUNT; i++) {
            blocks.get(i).setText(Integer.toString(i+1));

            String prev;
            if(i == 0) { // Genesis Block
                prev = previs.get(0).getText();
            }
            else {
                prev = hashes.get(i-1).getText();
                previs.get(i).setText(prev);
            }

            long nonce = 0;

            String hash, content = data.get(i).getText();
            while(true) {
                hash = getHash(nonce + prev + content);

                if(hash.startsWith(DIFFICULTY)) break;
                nonce++;
            }

            pane.get(i).setStyle("-fx-background-color:#ceffb5");
            nonces.get(i).setText(Long.toString(nonce));
            hashes.get(i).setText(hash);
        }
    }

    public String getHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(text.getBytes());

            return ByteUtils.toHexString(messageDigest);

        } catch (Exception e) { }

        return "";
    }
}
