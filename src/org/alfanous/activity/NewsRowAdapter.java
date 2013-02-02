package org.alfanous.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsRowAdapter extends ArrayAdapter<QuranItem> {

	private Activity activity;
	private List<QuranItem> items;
	private QuranItem objBean;
	private int row;

	public NewsRowAdapter(Activity act, int resource, List<QuranItem> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.items = arrayList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		objBean = items.get(position);

		holder.ayaText = (TextView) view.findViewById(R.id.ayaText);
		holder.ayaSura = (TextView) view.findViewById(R.id.ayaSura);
		holder.ayaNumber = (TextView) view.findViewById(R.id.ayaNumber);
//		holder.tvBDate = (TextView) view.findViewById(R.id.tvbdate);
//		holder.tvGender = (TextView) view.findViewById(R.id.tvgender);
//		holder.tvAge = (TextView) view.findViewById(R.id.tvage);

		
		if (holder.ayaText != null && null != objBean.getAyaText()
				&& objBean.getAyaText().trim().length() > 0) {
			holder.ayaText.setText(Html.fromHtml(objBean.getAyaText()));
		}
		if (holder.ayaSura != null && null != objBean.getSuraName()
				&& objBean.getSuraName().trim().length() > 0) {
			holder.ayaSura.setText(Html.fromHtml("سورة:"+objBean.getSuraName()));
		}
		if (holder.ayaNumber != null && objBean.getAyaId() > 0) {
			holder.ayaNumber.setText(Html.fromHtml("الآيه رقم:"+objBean.getAyaId()));
		}

		return view;
	}

	public class ViewHolder {
		public TextView ayaText, ayaSura, ayaNumber;
	}
}