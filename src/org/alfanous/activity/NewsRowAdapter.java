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

public class NewsRowAdapter extends ArrayAdapter<Item> {

	private Activity activity;
	private List<Item> items;
	private Item objBean;
	private int row;

	public NewsRowAdapter(Activity act, int resource, List<Item> arrayList) {
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

		holder.tvName = (TextView) view.findViewById(R.id.tvname);
		holder.tvCity = (TextView) view.findViewById(R.id.tvcity);
		holder.tvBDate = (TextView) view.findViewById(R.id.tvbdate);
		holder.tvGender = (TextView) view.findViewById(R.id.tvgender);
		holder.tvAge = (TextView) view.findViewById(R.id.tvage);

		if (holder.tvName != null && null != objBean.getName()
				&& objBean.getName().trim().length() > 0) {
			holder.tvName.setText(Html.fromHtml(objBean.getName()));
		}
		if (holder.tvCity != null && null != objBean.getCity()
				&& objBean.getCity().trim().length() > 0) {
			holder.tvCity.setText(Html.fromHtml(objBean.getCity()));
		}
		if (holder.tvBDate != null && null != objBean.getBirthdate()
				&& objBean.getBirthdate().trim().length() > 0) {
			holder.tvBDate.setText(Html.fromHtml(objBean.getBirthdate()));
		}
		if (holder.tvGender != null && null != objBean.getGender()
				&& objBean.getGender().trim().length() > 0) {
			holder.tvGender.setText(Html.fromHtml(objBean.getGender()));
		}
		if (holder.tvAge != null && objBean.getAge() > 0) {
			holder.tvAge.setText(Html.fromHtml("" + objBean.getAge()));
		}

		return view;
	}

	public class ViewHolder {
		public TextView tvName, tvCity, tvBDate, tvGender, tvAge;
	}
}