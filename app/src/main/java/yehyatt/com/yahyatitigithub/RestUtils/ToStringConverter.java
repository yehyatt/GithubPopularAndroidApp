package yehyatt.com.yahyatitigithub.RestUtils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by yehyatt on 2/24/18.
 */


public final class ToStringConverter implements Converter<String>
{

    @Override
    public String fromBody(ResponseBody body) throws IOException
    {
        return body.string();
    }

    @Override
    public RequestBody toBody(String value)
    {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

}
