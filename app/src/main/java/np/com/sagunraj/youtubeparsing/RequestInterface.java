package np.com.sagunraj.youtubeparsing;

import np.com.sagunraj.youtubeparsing.entities.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {
    @GET("youtube/v3/playlistItems")
    Call<Example> getData(@Query("part") String part, @Query("fields") String field, @Query("maxResults") String maxResults, @Query("playlistId") String playlistId, @Query("key") String key);
}
