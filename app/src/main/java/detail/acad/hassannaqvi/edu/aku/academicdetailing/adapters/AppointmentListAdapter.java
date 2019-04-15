package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;

public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.ViewHolder> {

    Context context;
    List<NextMeetingContract> collection;

    public AppointmentListAdapter(Context context, List<NextMeetingContract> contracts) {

        this.collection = contracts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.appointment_list_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {


        holder.providerName.setText(collection.get(i).getHp_name());
        holder.hfName.setText("(" + collection.get(i).getHf_name()+")");
        holder.date.setText("Date: " + collection.get(i).getBook_date());
        holder.time.setText("Time: " + collection.get(i).getBook_time());
        holder.sessionName.setText(Data.allSessionsMap.get(collection.get(i).session));
        holder.bottomText.setText("Booked By: " + collection.get(i).getBookBy() + " - " + "Booked on: " + collection.get(i).getFormdate() + " - ");


    }

    @Override
    public int getItemCount() {
        return collection.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView providerName, hfName, sessionName, date, time, bottomText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            providerName = itemView.findViewById(R.id.pName);
            hfName = itemView.findViewById(R.id.hfName);
            sessionName = itemView.findViewById(R.id.sessionName);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            bottomText = itemView.findViewById(R.id.bottomText);


        }
    }


}
