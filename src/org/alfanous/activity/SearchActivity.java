 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alfanous.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.alfanous.android.app.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Ahmed Salem
 */
public class SearchActivity extends Activity implements
		AdapterView.OnItemClickListener {

	// private static final String rssFeed =
	// "https://www.dropbox.com/s/rhk01nqlyj5gixl/jsonparsing.txt?dl=1";
	 //private static final String searchText =
	 //"http://www.alfanous.org/json?search=%D9%81%D8%B3%D9%8A%D9%83%D9%81%D9%8A%D9%83%D9%87%D9%85";
	//private static final String searchText = "http://www.alfanous.org/json?search=%D8%A7%D9%84%D9%84%D9%87";
	private static final String searchText = "http://www.alfanous.org/json?search=%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D9%86";
	private static final String searchText2 = "http://www.alfanous.org/json?search=%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D9%86&page=2";
	private static final String searchText3 = "http://www.alfanous.org/json?search=%D8%A7%D9%84%D8%B1%D8%AD%D9%85%D9%86&page=3";
	
	private String searchTextWithPageNumber;

	List<Item> arrayOfList;
	List<QuranItem> quranItems;
	ListView listView;
	NewsRowAdapter objAdapter;
	int numberOfPages = 0;
	int incrementPageNumber = 1;
	JSONObject mainJSON;
	String result;
	int incremintalAyas=1;
	int x = 0;

	private boolean isResultMoreThanOnePage = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);

		quranItems = new ArrayList<QuranItem>();
		
		if (Util.isNetworkAvailable(SearchActivity.this)) {
			new SearchActivity.MyTask().execute(searchText, searchText2,searchText3);
		} else {
			showToast("No Network Connection!!!");
		}

	}

	// My AsyncTask start...

	class MyTask extends AsyncTask<String, Void, String> {

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(SearchActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
			
			/*String result = Util.getJSONString(searchText);
			try {
				mainJSON = new JSONObject(result);
				JSONObject intervalObject = mainJSON
						.getJSONObject("interval");
				int total = intervalObject.getInt("total");
				if (total > 10) {
					numberOfPages = (total / 10) + 1;
					isResultMoreThanOnePage = true;
				}
				readResultInPage();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pDialog.dismiss();
			Log.v("dialogue dismiss", "Dialogue dismiss");
			if (x==0){
				x++;
			setAdapterToListview();	
			}else {
				objAdapter.notifyDataSetChanged();
			}
			
		}



		@Override
		protected String doInBackground(String... params) {
			for (int i = 0; i<3;i++){
				result = Util.getJSONString(params[i]);
				readResultInPage();
				Log.v("read done", "read Done");
				publishProgress();
				
			}
				
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
/*
			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == result || result.length() == 0) {
				showToast("No data found from web!!!");
				SearchActivity.this.finish();
			} else {
				// check data...
				

				setAdapterToListview();
				readTheRestOfThePages();


			}*/

		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showDeleteDialog(position);
	}

	private void showDeleteDialog(final int position) {
		AlertDialog alertDialog = new AlertDialog.Builder(SearchActivity.this)
				.create();
		alertDialog.setTitle("Delete ??");
		alertDialog.setMessage("Are you sure want to Delete it??");
		alertDialog.setButton("No", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				arrayOfList.remove(position);
				objAdapter.notifyDataSetChanged();

			}
		});
		alertDialog.show();

	}

	public void setAdapterToListview() {
		objAdapter = null;
		objAdapter = new NewsRowAdapter(SearchActivity.this, R.layout.row,
				quranItems);
		listView.setAdapter(objAdapter);
		
	}

	public void showToast(String msg) {
		Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	public void getNumberOfResultPages() {
		try {
			String result = Util.getJSONString(searchText);
			JSONObject firstJSON = new JSONObject(result);
			JSONObject intervalObject = firstJSON.getJSONObject("interval");
			int total = intervalObject.getInt("total");
			if (total > 10) {
				numberOfPages = (total / 10) + 1;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readResultInPage() {

		try {
			
			mainJSON = new JSONObject(result);
			JSONObject ayasObject = mainJSON.getJSONObject("ayas");

			for (int i = incremintalAyas; i < ayasObject.length() + 1; i++) {

				QuranItem quranItem = new QuranItem();

				JSONObject ayasElement = ayasObject.getJSONObject("" + i);

				JSONObject sajdaObject = ayasElement.getJSONObject("sajda");

				JSONObject suraObject = ayasElement.getJSONObject("sura");
				JSONObject suraStatObject = suraObject.getJSONObject("stat");

				JSONObject ayaObject = ayasElement.getJSONObject("aya");
				JSONObject statObject = ayasElement.getJSONObject("stat");
				JSONObject themeObject = ayasElement.getJSONObject("theme");
				JSONObject positionObject = ayasElement
						.getJSONObject("position");

				quranItem.setSajdaExists(sajdaObject.getBoolean("exist"));

				if (quranItem.isSajdaExists()) {
					quranItem.setSajda_type(sajdaObject.getString("type"));
					quranItem.setSajda_id(sajdaObject.getInt("id"));
				}

				quranItem.setSuraStatAyas(suraStatObject.getInt("ayas"));
				quranItem.setSuraStatLetters(suraStatObject.getInt("letters"));
				quranItem.setSuraStatGODNames(suraStatObject.getInt("godnames"));
				quranItem.setSuraStatWords(suraStatObject.getInt("words"));

				quranItem.setSura_order(suraObject.getInt("order"));
				quranItem.setSuraType(suraObject.getString("type"));
				quranItem.setSuraName(suraObject.getString("name"));
				quranItem.setSuraId(suraObject.getInt("id"));

				quranItem.setAyaId(ayaObject.getInt("id"));
				quranItem.setAyaText(ayaObject.getString("text"));
				quranItem
						.setAyaTextUthmani(ayaObject.getString("text_uthmani"));
				quranItem.setAyaRecitation(ayaObject.getString("recitation"));

				quranItem.setStatLetters(statObject.getInt("letters"));
				quranItem.setStatGodNames(statObject.getInt("godnames"));
				quranItem.setStatWords(statObject.getInt("words"));

				quranItem.setThemeChapter(themeObject.getString("chapter"));
				quranItem.setThemeTopic(themeObject.getString("topic"));
				quranItem.setThemeSubTopic(themeObject.getString("subtopic"));

				quranItem.setPositionRubu(positionObject.getInt("rubu"));
				quranItem.setPositionManzil(positionObject.getInt("manzil"));
				quranItem.setPositionHizb(positionObject.getInt("hizb"));
				quranItem.setPositionPage(positionObject.getInt("page"));

				quranItems.add(quranItem);
				incremintalAyas++;
			}
			//checkIfReadMorePagesNeeded();
				

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void getNextResultPage() {
		
		searchTextWithPageNumber = searchText + "&page=" + incrementPageNumber++;
		Log.v(searchTextWithPageNumber,searchTextWithPageNumber);
		result = Util.getJSONString(searchTextWithPageNumber);  

	}
	
	public void checkIfReadMorePagesNeeded(){
		if (isResultMoreThanOnePage){
			Log.v("I came here", "yes");
			if (incrementPageNumber <=2){
				getNextResultPage();
				readResultInPage();
			}else{
				readTheRestOfThePages();
			}
		}
		
	}
	
	public void readTheRestOfThePages(){
		if (incrementPageNumber <= numberOfPages){
			getNextResultPage();
			readResultInPage();
		}
	}

}
