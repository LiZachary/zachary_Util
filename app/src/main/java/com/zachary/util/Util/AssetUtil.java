package com.zachary.util.Util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zachary on 2018-05-02.
 */

    public class AssetUtil {
    /**
     * Created by Zachary on 2018-05-02.
     */

        public String getJson(Context context, String fileName) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                AssetManager assetManager = context.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open(fileName)));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
}