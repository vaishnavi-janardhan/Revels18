package mitrev.in.mitrev18.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import mitrev.in.mitrev18.R;
import mitrev.in.mitrev18.models.events.ScheduleModel;
import mitrev.in.mitrev18.models.workshops.WorkshopModel;
import mitrev.in.mitrev18.utilities.IconCollection;

/**
 * Created by skvrahul on 9/12/17.
 */

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.EventViewHolder>{
    String TAG = "WorkshopsAdapter";
    //TODO: Change EVENT_DAY_ZERO and EVENT_MONTH
    private final int EVENT_DAY_ZERO = 03;
    private final int EVENT_MONTH = Calendar.OCTOBER;
    private PendingIntent pendingIntent1 = null;
    private PendingIntent pendingIntent2 = null;
    private Activity activity;
    private List<WorkshopModel> eventScheduleList;
    private final EventClickListener eventListener;
    public interface EventClickListener {
        void onItemClick(ScheduleModel event, View view);
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workshops, parent, false);
        return new EventViewHolder(itemView);
    }
    public void updateList(List<WorkshopModel> workshopModelList){
        this.eventScheduleList.clear();
        this.eventScheduleList.addAll(eventScheduleList);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        WorkshopModel event = eventScheduleList.get(position);
        holder.onBind(event,eventListener);
    }

    @Override
    public int getItemCount() {
        return eventScheduleList.size();
    }
    public WorkshopsAdapter(Activity activity, List<WorkshopModel> events, EventClickListener eventListener){
        this.eventScheduleList = events;
        this.eventListener = eventListener;
        this.activity = activity;
    }
    private void displayEventDialog(final WorkshopModel event, Context context){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View workshop_view = inflater.inflate(R.layout.dialog_workshop, null, false);
        TextView name =(TextView) workshop_view.findViewById(R.id.workshop_name);
        TextView date = (TextView) workshop_view.findViewById(R.id.workshop_date);
        TextView time = (TextView) workshop_view.findViewById(R.id.workshop_time);
        TextView venue = (TextView) workshop_view.findViewById(R.id.workshop_venue);
        TextView contact_name1 = (TextView) workshop_view.findViewById(R.id.workshop_contact_name);
        TextView contact_number1 = (TextView) workshop_view.findViewById(R.id.workshop_contact);
        TextView contact_name2 = (TextView) workshop_view.findViewById(R.id.workshop_contact_name2);
        TextView contact_number2 = (TextView) workshop_view.findViewById(R.id.workshop_contact2);
        TextView description = (TextView) workshop_view.findViewById(R.id.workshop_description);
        name.setText(event.getName());

        contact_name1.setText(R.string.cnt_wksp_1);
        contact_number1.setText("(".concat(workshop_view.getResources().getString(R.string.cntno_wksp_1)).concat(")"));
        contact_name2.setText(R.string.cnt_wksp_2);
        contact_number2.setText("(".concat(workshop_view.getResources().getString(R.string.cntno_wksp_2)).concat(")"));

        venue.setText(event.getVenue());
        time.setText(event.getStartTime()+" - "+event.getEndTime());
        date.setText(event.getDate());
        description.setText(event.getDesc());
        dialog.setContentView(workshop_view);
        dialog.show();
    }
    public class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName, eventVenue, eventTime, eventRound;
        public ImageView eventIcon, favIcon;
        public View container;
        public void onBind(final WorkshopModel event, final EventClickListener eventClickListener){
            favIcon.setVisibility(View.GONE);
            eventName.setText(event.getName());
            eventTime.setText(event.getStartTime() + " - " + event.getEndTime());
            eventVenue.setText(event.getVenue());
            IconCollection icons = new IconCollection();
            eventRound.setText(event.getDate());
            if(event.getCatName()!=null)
                eventIcon.setImageResource(icons.getIconResource(activity, event.getCatName()));

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: Event clicked"+event.getName());
                    displayEventDialog(event, view.getContext());

                }
            });
        }
        public EventViewHolder(View view){
            super(view);
            eventIcon = (ImageView)view.findViewById(R.id.event_logo_image_view);
            favIcon = (ImageView)view.findViewById(R.id.event_fav_ico);
            eventName = (TextView)view.findViewById(R.id.event_name_text_view);
            eventVenue = (TextView)view.findViewById(R.id.event_venue_text_view);
            eventTime = (TextView)view.findViewById(R.id.event_time_text_view);
            eventRound = (TextView)view.findViewById(R.id.event_round_text_view);
            container = view.findViewById(R.id.event_item_relative_layout);
        }
    }

}

