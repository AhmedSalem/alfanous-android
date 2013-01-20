/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alfanous.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
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
public class SearchActivity extends Activity implements AdapterView.OnItemClickListener {
	// http://www.alfanous.org/json?search=%D9%81%D8%B3%D9%8A%D9%83%D9%81%D9%8A%D9%83%D9%87%D9%85&highlight=bbcode&sortedby=tanzil&page=1
	//
	private static final String rssFeed = "https://www.dropbox.com/s/rhk01nqlyj5gixl/jsonparsing.txt?dl=1";

	private static final String ARRAY_NAME = "student";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String CITY = "city";
	private static final String GENDER = "Gender";
	private static final String AGE = "age";
	private static final String BIRTH_DATE = "birthdate";

	List<Item> arrayOfList;
	ListView listView;
	NewsRowAdapter objAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);

		arrayOfList = new ArrayList<Item>();

		if (Util.isNetworkAvailable(SearchActivity.this)) {
			new SearchActivity.MyTask().execute(rssFeed);
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
		}

		@Override
		protected String doInBackground(String... params) {
			return Util.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == result || result.length() == 0) {
				showToast("No data found from web!!!");
				SearchActivity.this.finish();
			} else {

				try {

					ArrayList<QuranItem> quranItems = new ArrayList<QuranItem>();
					
					JSONObject mainJson = new JSONObject(result);
					JSONObject ayasObject = mainJson.getJSONObject("ayas");
					
					for (int i = 1; i < ayasObject.length()+1; i++) {
						QuranItem quranItem = new QuranItem();
						
						JSONObject ayasElement = ayasObject.getJSONObject(""+i);
						
						JSONObject sajdaObject =  ayasElement.getJSONObject("sajda");
						
						JSONObject suraObject =  ayasElement.getJSONObject("sura");
						JSONObject suraStatObject =  suraObject.getJSONObject("stat");
						
						JSONObject ayaObject =  ayasElement.getJSONObject("aya");
						JSONObject statObject =  ayasElement.getJSONObject("stat");
						JSONObject themeObject =  ayasElement.getJSONObject("theme");
						JSONObject positionObject =  ayasElement.getJSONObject("position");
						
						
						
						quranItem.setSajdaExists(sajdaObject.getBoolean("exist")); 
						
						if (quranItem.isSajdaExists()){
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
						quranItem.setAyaTextUthmani(ayaObject.getString("text_uthmani"));
						quranItem.setAyaRecitation(ayaObject.getString("recitation"));
						
						

						
						
						/*sajda
						sura
						aya
						stat
						theme
						position
*/
						quranItems.add(quranItem);

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// check data...

				/*
				 * for (int i = 0; i < arrayOfList.size(); i++) { Item item = arrayOfList.get(i);
				 * System.out.println(item.getId());
				 * 
				 * System.out.println(item.getId()); System.out.println(item.getName());
				 * System.out.println(item.getCity()); System.out.println(item.getGender());
				 * System.out.println(item.getAge()); System.out.println(item.getBirthdate()); }
				 */

				Collections.sort(arrayOfList, new Comparator<Item>() {

					public int compare(Item lhs, Item rhs) {
						return (lhs.getAge() - rhs.getAge());
					}
				});
				setAdapterToListview();

			}

		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		showDeleteDialog(position);
	}

	private void showDeleteDialog(final int position) {
		AlertDialog alertDialog = new AlertDialog.Builder(SearchActivity.this).create();
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
		objAdapter = new NewsRowAdapter(SearchActivity.this, R.layout.row, arrayOfList);
		listView.setAdapter(objAdapter);
	}

	public void showToast(String msg) {
		Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_LONG).show();
	}
}
