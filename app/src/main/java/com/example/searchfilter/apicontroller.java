package com.example.searchfilter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {

    // 3.0 create apicontroller (API CONTROLLER)

    // 3.1 mention url path (PATH [3.0])
    // changed http://192.168.1.11/api/ with your live domain name

    //    TODO: 3. change http://192.168.1.11/api/ with your online domain name
    private static final String url = "https://jsonplaceholder.typicode.com/";
    // 3.2 create blank object of this class
    // this object will be called in the main
    private static apicontroller clientobject;
    // 3.3 create object of retrofit
    private static Retrofit retrofit;

    // 3.4 create api constructor (API CONSTRUCTOR)
    apicontroller(){
        // 3.5 initialize retrofit (RETROFIT OBJECT [3.1])
        // and pass url
        // and convert into json file
        // and build
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // 3.6 create method of apicontroller (call the objects at runtime  )
    public static synchronized apicontroller getInstance(){
        // 3.7 check if clientobject is already initialized
        if (clientobject==null) {
            // if not then initialized
            // calling apicontroller will initialize retrofit 3.5
            clientobject = new apicontroller();
        }
        return clientobject;
    }

    // 3.8 method apiset that return apiset list (INTERFACE)
    // convert jsondata to model class object [4]
    apiset getapi(){
        return retrofit.create(apiset.class);
    }

    // 3.9 go to MainActivity
}
