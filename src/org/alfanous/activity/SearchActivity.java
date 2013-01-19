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
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(ARRAY_NAME);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject objJson = jsonArray.getJSONObject(i);

						Item objItem = new Item();

						objItem.setId(objJson.getInt(ID));
						objItem.setName(objJson.getString(NAME));
						objItem.setCity(objJson.getString(CITY));
						objItem.setGender(objJson.getString(GENDER));
						objItem.setAge(objJson.getInt(AGE));
						objItem.setBirthdate(objJson.getString(BIRTH_DATE));

						arrayOfList.add(objItem);

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
