package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.example.javajoke.MyClass;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that retrieves jokes from the JavaLibrary
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyClass joke = new MyClass();
        String Joke = joke.getJoke();
        MyBean response = new MyBean();
        response.setData(Joke);

        return response;
    }

}
