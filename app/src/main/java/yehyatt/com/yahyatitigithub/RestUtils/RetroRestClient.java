package yehyatt.com.yahyatitigithub.RestUtils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import yehyatt.com.yahyatitigithub.models.MainListModel;

/**
 * Created by yehyatt on 2/24/18.
 */

public class RetroRestClient
{
    private static String BASE_URL = "https://api.github.com";
    private static GitApiInterface gitApiInterface;

    public static GitApiInterface getClient()
    {
        if (gitApiInterface == null)
        {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor()
            {
                @Override
                public Response intercept(Chain chain) throws IOException
                {
                    return chain.proceed(chain.request());
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface;
    }

    public interface GitApiInterface
    {

        @Headers("User-Agent: Retrofit2.0Tutorial-App")
        @GET("/search/repositories")
        //https://api.github.com/search/repositories?q=android&sort=popularity&order=desc
        Call<MainListModel> getAndroidPopular(@Query("q") String Query,@Query("sort")String Sort,@Query("order")String OrderBy);

    }
}
