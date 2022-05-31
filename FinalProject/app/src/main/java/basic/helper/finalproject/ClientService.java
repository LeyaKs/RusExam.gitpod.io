package basic.helper.finalproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientService {
    @GET("resources.json")
    Call<ArrayBasic> getres();
}
