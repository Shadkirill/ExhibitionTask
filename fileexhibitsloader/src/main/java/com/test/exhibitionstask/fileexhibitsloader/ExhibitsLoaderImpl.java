package com.test.exhibitionstask.fileexhibitsloader;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import com.test.exhibitionstask.model.Exhibit;
import com.test.exhibitionstask.model.ExhibitsLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class ExhibitsLoaderImpl implements ExhibitsLoader {
    private Context mContext;

    public ExhibitsLoaderImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    @Nullable
    public List<Exhibit> getExhibitList() {
        List<Exhibit> result = null;
        String jsonString = getFileContents();
        try {
            result = parseExhibitJson(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Nullable
    private List<Exhibit> parseExhibitJson(String jsonString) throws JSONException {
        List<Exhibit> result = null;
        if (jsonString != null && jsonString.length() > 0) {
            JSONObject mainJsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = mainJsonObject.getJSONArray("list");
            result = new LinkedList<>();
            for (int i = 0; i < jsonArray.length() ; ++i) {
                JSONObject exhibitJsonObject = jsonArray.getJSONObject(i);
                try {
                    Exhibit exhibit = parseExhibit(exhibitJsonObject);
                    result.add(exhibit);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    private Exhibit parseExhibit(JSONObject exhibitJsonObject) throws JSONException {
        Exhibit result = new Exhibit();
        result.setTitle(exhibitJsonObject.getString("title"));
        result.setImages(parseImages(exhibitJsonObject.getJSONArray("images")));
        return result;
    }

    private List<String> parseImages(JSONArray imagesJsonArray) throws JSONException {
        List<String> result = new LinkedList<>();
        for (int i = 0 ; i < imagesJsonArray.length() ; ++i) {
            result.add(imagesJsonArray.getString(i));
        }
        return result;
    }

    @Nullable
    private String getFileContents() {
        String result = null;
        InputStream inputStream = null;
        try {
            inputStream = mContext.getAssets().open("model.json");
            byte[] data = new byte[(int) inputStream.available()];
            inputStream.read(data);
            result = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
