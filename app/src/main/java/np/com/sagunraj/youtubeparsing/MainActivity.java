package np.com.sagunraj.youtubeparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.com.sagunraj.youtubeparsing.entities.Example;
import np.com.sagunraj.youtubeparsing.entities.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String part = "snippet,status";
    String field = "nextPageToken,items(snippet(publishedAt,title,resourceId,thumbnails),status)";
    String playlistId = "PLrEnWoR732-BHrPp_Pm8_VleD68f9s14-";
    String maxResults = "50";
    String key = "AIzaSyAJ7XL6febb9L2PNpiiIrmrG-dJNOGfZ9g";

    List<Item> mydata = new ArrayList<>();

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RequestInterface requestInterface = ApiClient.getClient().create(RequestInterface.class);
        Call<Example> call = requestInterface.getData(part, field, maxResults, playlistId, key);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                    mydata = response.body().getItems();
                    recyclerView.setAdapter(new CustomAdapter(MainActivity.this, mydata));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No data was received.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
