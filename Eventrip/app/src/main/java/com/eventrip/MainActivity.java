package com.eventrip;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("facebook login","success");
                String accessToken = loginResult.getAccessToken().getToken();
                String url = "https://graph.facebook.com/v2.8/search?q=telaviv&type=event&access_token="+accessToken;
                new DownloadTask().execute(url);
            }

            @Override
            public void onCancel() {
                Log.d("facebook login","cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook login","error");
            }
        });


        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.eventrip", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

        LinearLayout searchButton = (LinearLayout) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

        /*Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);*/
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            Toast.makeText(this, token.toString(), Toast.LENGTH_SHORT).show();
            //Log.e(TAG, "Token: " + token.getToken());
            //Log.e(TAG, "UserID: " + token.getUserId());
        }
        //AccessToken.getCurrentAccessToken().getToken();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchButton:
                //Intent intent = new Intent(this, new MainActivity());
                break;
        }
    }
//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(
//                AccessToken oldAccessToken,
//                AccessToken currentAccessToken) {
//
//        }
//    };
}
