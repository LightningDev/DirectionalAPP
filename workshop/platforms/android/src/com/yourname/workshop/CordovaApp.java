/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.yourname.workshop;

import android.os.Bundle;
import org.apache.cordova.*;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.os.Build;
import android.webkit.WebView;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class CordovaApp extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        // Set by <content src="index.html" /> in config.xml
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
        loadUrl(launchUrl);
    }
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
        Log.e("onActivityResult", requestCode +" "+"hello ");
        if(requestCode == 1){
             LocationManager manager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
             boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
             if(!statusOfGPS){
                 new AlertDialog.Builder(this)
                    .setTitle("GPS Service")
                    .setMessage("Are you sure you don't want to enable GPS?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                           Log.e("GPS SERVICE", "No GPS Service..."); 
                        }
                     })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);   // do nothing
                        }
                     })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                     .show();
             }
        }
    }
}
