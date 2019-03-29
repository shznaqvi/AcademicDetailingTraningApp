package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityDownloadVideoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.VideoItemLayoutBinding;

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
                Toast.makeText(DownloadVideoActivity.this, "" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

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

    public class VideoItemsAdapter extends RecyclerView.Adapter<VideoItemsAdapter.MyViewHolder> {

        MyViewHolder holder;
        private List<String> videosList;

        public VideoItemsAdapter(List<String> videosList) {
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
            this.holder.bindUser(this.videosList.get(position));
        }

        @Override
        public int getItemCount() {
            return videosList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            VideoItemLayoutBinding videoItemBinding;

            public MyViewHolder(View itemView) {
                super(itemView);
                videoItemBinding = DataBindingUtil.bind(itemView);

            }

            public void bindUser(String mname) {
                videoItemBinding.movieName.setText(mname);
            }
        }
    }

    public class populateRecyclerView extends AsyncTask<String, String, String> {
        private Context mContext;

        public populateRecyclerView(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
//              Set Recycler View
                    mAdapter = new VideoItemsAdapter(new ArrayList<>());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
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
