package be.marbleous.wml2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import be.marbleous.wml2.Models.PickSlipLine;

/**
 * Created by jonasvermeulen on 05/04/15.
 */
public class CustomListAdapter extends BaseAdapter
{


    Context context;
    PickSlipLine[] data;
    private static LayoutInflater inflater = null;

    public CustomListAdapter(Context context, int layoutResourceId, PickSlipLine[] data)
    {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.listview_item_row, null);
        TextView text = (TextView) vi.findViewById(R.id.rowTextView);
     //   text.setText(data[position].TempPickLocation);
        return vi;
    }
}
