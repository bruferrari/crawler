package ferrarib.com.app.crawler.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bruno on 7/11/16.
 */
public interface WebHoseEndpoints {

    @GET("/search")
    Call<Response> simpleQuery(@Query("q") String query,
                               @Query("token") String token,
                               @Query("format") String format,
                               @Query("size") int size);

    @GET("/search%20performance_score={performance_score}")
    Call<Response> queryByPerformanceMeasure(@Path("performance_score") String performanceScore,
                                             @Query("q") String query,
                                             @Query("token") String token,
                                             @Query("format") String format,
                                             @Query("size") int size);
}
