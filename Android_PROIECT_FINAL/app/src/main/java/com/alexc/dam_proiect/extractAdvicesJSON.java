package com.alexc.dam_proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class extractAdvicesJSON extends ListActivity {

    public static final String TAG_ADVICES = "advices";

    public static final String TAG_ID = "id";
    public static final String TAG_TITLE = "title";
    public static final String TAG_CONTENT = "content";
    public static final String TAG_IMPACT_LEVEL = "impact-level";

    private ProgressDialog pDialog;

    TextView source;
    JSONArray advices = null;

    ArrayList<HashMap<String, String>> advicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_extract_advices_j_s_o_n);

        source=findViewById(R.id.infoS);
        source.setMovementMethod(LinkMovementMethod.getInstance());
        advicesList = new ArrayList<>();

        URL url = null;
        try {
            url = new URL("https://pastebin.com/raw/fe6RDnw5");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        GetAdvices m = new GetAdvices();
        m.setOnTaskFinisedEvent(new OnTaskExecutionFinished() {
            @Override
            public void onTaskFinishedEvent(String result) {

                if (pDialog.isShowing())
                {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pDialog.dismiss();
                }

                ListAdapter adapter = new SimpleAdapter(extractAdvicesJSON.this,
                        advicesList,
                        R.layout.list_item,
                        new String[]{TAG_TITLE, TAG_CONTENT, TAG_IMPACT_LEVEL},
                        new int[] {R.id.title, R.id.content1, R.id.impactLevel}){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        HashMap<String, String> currentRow = advicesList.get(position);

                        TextView impactLevel = view.findViewById(R.id.impactLevel);
                        String impact = (currentRow.get(TAG_IMPACT_LEVEL));
                        if(impact.contains("10"))

                           impactLevel.setTextColor(Color.GREEN);
                        else
                            impactLevel.setTextColor(Color.BLUE);

                        return  view;
                    }
                };

                setListAdapter(adapter);


            }
        });
        m.execute(url);
    }

    public interface OnTaskExecutionFinished
    {
        void onTaskFinishedEvent(String result);
    }

    public class GetAdvices extends AsyncTask<URL, Void, String>
    {
        private OnTaskExecutionFinished event;

        public void setOnTaskFinisedEvent(OnTaskExecutionFinished event)
        {
            if (event!=null)
                this.event = event;
        }

        @Override
        protected String doInBackground(URL... urls) {

            HttpURLConnection conn = null;

            try {
                conn = (HttpURLConnection)urls[0].openConnection();
                conn.setRequestMethod("GET");
                InputStream ist = conn.getInputStream();

                InputStreamReader isr = new InputStreamReader(ist);
                BufferedReader br = new BufferedReader(isr);
                String linie = null;
                String sbuf = "";
                while ((linie=br.readLine())!=null)
                {
                    sbuf += linie;
                }

                //parsare JSON
                loadJSON(sbuf);

                return sbuf;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(this.event!=null)
                this.event.onTaskFinishedEvent(s);
            else
                Log.e("GETADVICES", "event is null");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(extractAdvicesJSON.this);
            pDialog.setMessage("Just a second...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        public void loadJSON(String jsonStr)
        {
            if(jsonStr!=null)
            {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    advices = jsonObject.getJSONArray(TAG_ADVICES);

                    for(int i=0; i<advices.length(); i++)
                    {
                        JSONObject c = advices.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String title = c.getString(TAG_TITLE);
                        String content = c.getString(TAG_CONTENT);
                        String impact = c.getString(TAG_IMPACT_LEVEL);

                        HashMap<String, String> advice = new HashMap<>();
                        advice.put(TAG_ID, id);
                        advice.put(TAG_TITLE, title);
                        advice.put(TAG_CONTENT, content);
                        advice.put(TAG_IMPACT_LEVEL,"Impact Level: "+impact);

                        advicesList.add(advice);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
                Log.e("loadJSON", "JSON object is null");
        }
    }
}