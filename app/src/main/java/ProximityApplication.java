package com.yahoo.myapp.proximity;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mona on 3/11/15.
 */
public class ProximityApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "8qVrolqigtbUZb8ZLz5ZB5DOOxwxDCGu7CKIb0oz",
                "kLgkc5nmvkHTqyE64JSnPDXGNFgKeZdAg4l6wFpj");
    }

}
