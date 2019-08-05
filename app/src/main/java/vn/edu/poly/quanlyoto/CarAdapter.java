package vn.edu.poly.quanlyoto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CarAdapter extends BaseAdapter {
    private Context context;
    private List<Car> carList;
    private CarDao carDao;

    public CarAdapter(Context context, List<Car> carList, CarDao carDao) {
        this.context = context;
        this.carList = carList;
        this.carDao = carDao;
    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.cer,parent,false);
        TextView tvIds=convertView.findViewById(R.id.tvIds);
        TextView tvNames=convertView.findViewById(R.id.tvNames);
        TextView tvPrices=convertView.findViewById(R.id.tvPrices);
        TextView tvYears=convertView.findViewById(R.id.tvYears);
        TextView tvStts=convertView.findViewById(R.id.tvStts);


        final Car car= (Car) getItem(position);
        tvStts.setText(position+1+"");
        tvIds.setText("ID: "+car.id);
        tvNames.setText("Name: "+car.name);
        tvPrices.setText("Price: "+car.price +" $" );
        tvYears.setText("Year: "+car.year );





        return convertView;
    }
}
