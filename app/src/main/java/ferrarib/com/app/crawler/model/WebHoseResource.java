package ferrarib.com.app.crawler.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bruno on 7/11/16.
 */
public interface WebHoseResource {

    @GET("/search")
    Call<Response> simpleQuery(@Query("q") String query,
                               @Query("token") String token,
                               @Query("format") String format,
                               @Query("size") int size);

    @GET("/search")
    Call<Response> queryByPerformanceMeasure(@Query("q") String query,
                                             @Query("token") String token,
                                             @Query("format") String format,
                                             @Query("size") int size);

    @GET("/search")
    Call<Response> queryByCountry(@Query("q") String query,
                                  @Query("token") String token,
                                  @Query("format") String format,
                                  @Query("size") int size);
}
