/*
 * Skill India
 * Copyright (C) 2017  e-LEMON-ators
 *
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.ssn.skillindia.model;

import android.content.Context;

import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.utils.LogHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

public class LocalJSONSource {
    private static final String TAG = LogHelper.makeLogTag(LocalJSONSource.class);
    private Context context;

    public LocalJSONSource(Context context) {
        this.context = context;
        fetchJSONFromAssets();
    }

    private void fetchJSONFromAssets() {
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = context.getAssets().open("training-centers.json");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray eventsJsonArray = jsonObject.getJSONArray("centers");

            // TODO: Algolia Search
            /*Client client = new Client(context.getString(R.string.algolia_app_id),
                    context.getString(R.string.algolia_api_key));

            Index index = client.initIndex("centers");
            for (int i = 0; i < eventsJsonArray.length(); i++) {
                index.addObjectAsync((JSONObject) eventsJsonArray.get(i), null);
            }

            CompletionHandler completionHandler = new CompletionHandler() {
                @Override
                public void requestCompleted(JSONObject content, AlgoliaException error) {
                    try {
                        LogHelper.e(TAG, content.toString());
                    } catch (Exception e) {
                        LogHelper.e(TAG, error.toString());
                    }
                }
            };
            index.searchAsync(new Query("delhi"), completionHandler);
            index.searchAsync(new Query("delih"), completionHandler);
            index.searchAsync(new Query("in"), completionHandler);
            index.searchAsync(new Query("ni"), completionHandler);*/

            SkillIndiaApplication skillIndiaApplication = (SkillIndiaApplication) context.getApplicationContext();
            Realm realm = skillIndiaApplication.getRealmHelper().getRealmInstance();
            realm.beginTransaction();
            try {
                realm.createOrUpdateAllFromJson(TrainingCenter.class, eventsJsonArray);
                realm.commitTransaction();
                LogHelper.e(TAG, "TRY");
            } catch (Exception e) {
                LogHelper.e(TAG, "IO : ", e);
                realm.cancelTransaction();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (JSONException e) {
            LogHelper.e(TAG, e.toString());
        } catch (Exception e) {
            LogHelper.e(TAG, e.toString());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
