package deint.jroldan.manageproductrecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import deint.jroldan.manageproductrecycler.ManageProductRecycler_Application;
import deint.jroldan.manageproductrecycler.R;
import deint.jroldan.manageproductrecycler.model.Product;

/**
 * Created by usuario on 18/11/16.
 */

/**
 * No es necesario llamar a este método notifyDataSetChanged() después de add(), insert(), remove(), clear(), sort()...
 * porque estos métodos los llaman automáticamente setNotifyOnChange=true y se utiliza la copia local.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public static boolean ASC = true;

    /**
     * Third param for super = ArrayList with Repository elements. A different local copy from the repository
     * @param context
     */
    private List<Product> list;
    public ProductAdapter(Context context) {
        super(context, R.layout.item_product, new ArrayList<Product>((
                (ManageProductRecycler_Application)context.getApplicationContext()).getProducts()));

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        ProductHolder productHolder;
        if(view==null) {
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_product, null);
            productHolder=new ProductHolder();
            productHolder.product_image=(ImageView)view.findViewById(R.id.imvPhotoItem);
            productHolder.txvName=(TextView)view.findViewById(R.id.txvName);
            productHolder.txvStock=(TextView)view.findViewById(R.id.txvStock);
            productHolder.txvPrice=(TextView)view.findViewById(R.id.txvPrice);

            view.setTag(productHolder);
        }
        else
            productHolder=(ProductHolder)view.getTag();

        productHolder.product_image.setImageResource(getItem(position).getmImage());
        productHolder.txvName.setText(getItem(position).getmName());
        productHolder.txvStock.setText(getItem(position).getFormattedUnitsInStock());
        productHolder.txvPrice.setText(getItem(position).getFormattedPrice());

        return view;
    }

    class ProductHolder{
        ImageView product_image;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;
    }

    public void sortAlphabetically() {

        if(ASC)
            sort(Product.NAME_COMPARATOR);
        else
            sort(Collections.reverseOrder());
        notifyDataSetChanged();
    }

    public void addProduct(Product product) {
        add(product);
    }
}
