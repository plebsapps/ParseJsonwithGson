package ch.plebsapps.parsejsonwithgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_citys) TextView tvCitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        readJson();

        tvCitys.setText(writeCitys(readJson()));
    }

    private String writeCitys(City[] citys) {
        String strCity = "";

        for (City city : citys) {
            strCity += city.getName() + " " + city.getCountry() + " " +city.getCoord().getLat() + " " +city.getCoord().getLon() + "\n";
        }

        return strCity;
    }

    private City[] readJson() {
        String jsonString = getAssetsJSON("citys.json");

        Gson gson = new Gson();
        return gson.fromJson(jsonString, City[].class);
    }

    /* Get File in Assets Folder */
    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}