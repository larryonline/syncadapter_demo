package com.chehejia.demo.datasync.slave1.sync;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChehejiaGateway {

    @GET("/")
    Call<ResponseBody> getHomePage();

    @GET("/about.html")
    Call<ResponseBody> getAboutPage();

    @GET("/job.html")
    Call<ResponseBody> getJobsPage();

}
