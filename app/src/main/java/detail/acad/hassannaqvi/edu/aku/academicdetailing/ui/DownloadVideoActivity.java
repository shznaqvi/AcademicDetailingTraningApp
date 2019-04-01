package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.Arrays;
import java.util.LinkedList;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityDownloadVideoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.VideoItemLayoutBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;

public class DownloadVideoActivity extends AppCompatActivity {

    ActivityDownloadVideoBinding bi;
    VideoItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_download_video);
        bi.setCallback(this);

        bi.modNSpinner.attachDataSource(new LinkedList<>(Arrays.asList("NEWBORN MODULE", "MATERNAL MODULE", "CHILD MODULE")));
        bi.modNSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // populate recycler_view
                new populateRecyclerView(DownloadVideoActivity.this, i).execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // populate recycler_view
        new populateRecyclerView(DownloadVideoActivity.this, 0).execute();

    }

    //    Recycler classes
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;
        private OnItemClickListener mListener;
        private RecyclerView viewRecycle;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;

            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = viewRecycle.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onItemLongClick(child, viewRecycle.getChildAdapterPosition(child));
                    }

                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            viewRecycle = view;
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }


    }

    private String[] getStringArray(int position) {

        switch (position) {
            case 0:
                return Data.newbornVideos;
            default:
                return new String[]{};
        }
    }

    private String getVideoItemName(String moduleName) {
        String[] nameSplit = moduleName.split("_");
        if (nameSplit.length != 3) return moduleName;
        return getModuleName(nameSplit[0]) + " " + nameSplit[2];
    }

    private String getModuleName(String startChar) {
        switch (startChar) {
            case "dia":
                return "Diarrhoea";
            case "gds":
                return "General Danger Sign";
            case "psbi":
                return "PSBI";
            case "cdb":
                return "Cough & Difficult Breathing";
            default:
                return startChar;
        }
    }

    public class VideoItemsAdapter extends RecyclerView.Adapter<VideoItemsAdapter.MyViewHolder> {

        MyViewHolder holder;
        private String[] videosList;

        public VideoItemsAdapter(String[] videosList) {
            this.videosList = videosList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_item_layout, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            this.holder = holder;
            this.holder.bindUser(this.videosList[position]);
        }

        @Override
        public int getItemCount() {
            return videosList.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            VideoItemLayoutBinding videoItemBinding;

            public MyViewHolder(View itemView) {
                super(itemView);
                videoItemBinding = DataBindingUtil.bind(itemView);

            }

            public void bindUser(String mname) {
                videoItemBinding.movieName.setText(getVideoItemName(mname));
            }
        }
    }

    public class populateRecyclerView extends AsyncTask<String, String, String> {
        private Context mContext;
        private int position;

        public populateRecyclerView(Context mContext, int position) {
            this.mContext = mContext;
            this.position = position;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
//              Set Recycler View
                    mAdapter = new VideoItemsAdapter(getStringArray(position));
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
                    bi.modVidRecycler.setLayoutManager(mLayoutManager);
                    bi.modVidRecycler.setItemAnimator(new DefaultItemAnimator());
                    bi.modVidRecycler.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                   Background black for those that's data filled
                /*    for (int item : MainApp.hhClicked) {

                        JSONModelClass json = JSONUtilClass.getModelFromJSON(MainApp.familyMembersClicked.get(item).getsA2(), JSONModelClass.class);
                        int age = Integer.valueOf(json.getAge());

                        if (age >= 10) {
                            if (json.getGender().equals("1")) {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(getResources().getColor(R.color.darkBlue));
                            } else if (json.getGender().equals("2")) {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(getResources().getColor(R.color.darkPink));
                            } else {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(Color.BLACK);
                            }
                        } else {
                            if (json.getGender().equals("1")) {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(getResources().getColor(R.color.lightBlue));
                            } else if (json.getGender().equals("2")) {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(getResources().getColor(R.color.lightPink));
                            } else {
                                binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(Color.BLACK);
                            }
                        }

                    }

                    for (int item : MainApp.flagClicked) {
                        binding.recyclerNoMembers.getChildAt(item).setBackgroundColor(getResources().getColor(R.color.brown));
                    }
*/
                }
            }, 800);
        }
    }

}
