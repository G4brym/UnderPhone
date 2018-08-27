package pt.technic.underphone.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.technic.underphone.DrawerActivity;
import pt.technic.underphone.R;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {
    public List<AppInfo> appsList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView img;


        //This is the subclass ViewHolder which simply
        //'holds the views' for us to show on each row
        public ViewHolder(View itemView) {
            super(itemView);

            //Finds the views from our row.xml
            textView = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View v) {
            int pos = getAdapterPosition();
            DrawerActivity context = (DrawerActivity) v.getContext();

            if (context.action == DrawerActivity.OPEN_APP){
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(pos).packageName.toString());
                context.startActivity(launchIntent);
                Toast.makeText(v.getContext(), appsList.get(pos).label.toString(), Toast.LENGTH_LONG).show();
                return;
            }

            // put the String to pass back into an Intent and close this activity
            Intent intent = new Intent();
            intent.putExtra("name", appsList.get(pos).packageName.toString());
            intent.putExtra("id", context.idLauncher);
            context.setResult(DrawerActivity.RESULT_OK, intent);
            context.finish();
        }
    }

    public RAdapter(Context c) {
        appsList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(RAdapter.ViewHolder viewHolder, int i) {

        //Here we use the information in the list we created to define the views

        String appLabel = appsList.get(i).label.toString();
        String appPackage = appsList.get(i).packageName.toString();
        Drawable appIcon = appsList.get(i).icon;

        TextView textView = viewHolder.textView;
        textView.setText(appLabel);
        ImageView imageView = viewHolder.img;
        imageView.setImageDrawable(appIcon);
    }


    @Override
    public int getItemCount() {

        //This method needs to be overridden so that Androids knows how many items
        //will be making it into the list

        return appsList.size();
    }


    @Override
    public RAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //This is what adds the code we've written in here to our target view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
}