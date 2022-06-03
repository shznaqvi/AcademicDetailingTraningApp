package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

public class VideoItemsAdapter2 {
/*

    private static final String TAG = "VideoItemsAdapter";
    private final DownloadVideoActivity downloadVideoActivity;
    private final String[] videosList;
    VideoItemsViewHolder holder;
    List<String> existVideos;

    public VideoItemsAdapter(DownloadVideoActivity downloadVideoActivity, String[] videosList) {
        this.downloadVideoActivity = downloadVideoActivity;
        this.videosList = videosList;
    }

    @Override
    public VideoItemsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        return new VideoItemsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.video_item_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(VideoItemsViewHolder holder, int position) {
        int pos = holder.getAbsoluteAdapterPosition();
        this.holder = holder;
        this.holder.bindUser(this.videosList[position], pos);
        Log.d(TAG, "onBindViewHolder(mName): " + this.videosList[position]);
    }

    @Override
    public int getItemCount() {
        return videosList.length;
    }

    public class VideoItemsViewHolder extends RecyclerView.ViewHolder {

        VideoItemLayoutBinding videoItemBinding;

        public VideoItemsViewHolder(View itemView) {
            super(itemView);
            videoItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(String mname, int pos) {
            videoItemBinding.movieName.setText(getVideoItemName(mname));
            if (MainApp.checkVideoExist(pos, mname)) {
                videoItemBinding.downloadImage.setImageResource(R.drawable.ic_check);
                videoItemBinding.vdoStatus.setVisibility(View.VISIBLE);

            //    Bitmap bMap = ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);

                videoItemBinding.vdoThumbnail.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    videoItemBinding.movieName.setTextColor(downloadVideoActivity.getColor(R.color.colorPrimary));
                else
                    videoItemBinding.movieName.setTextColor(downloadVideoActivity.getResources().getColor(R.color.colorPrimary));

               existVideos.add(mname);
            }
        }
    }

    private String getVideoItemName(String moduleName) {
        String[] nameSplit = moduleName.split("_");
        if (nameSplit.length != 3) return moduleName;
        return getSubModuleName(nameSplit[0]) + " " + nameSplit[2];
    }


    private String getSubModuleName(String startChar) {
        switch (startChar) {
            case "dia":
                return "Diarrhoea";
            case "gds":
                return "General Danger Sign";
            case "psbi":
                return "PSBI";
            case "cdb":
                return "Cough & Difficult Breathing";
            case "eceb":
                return "Newborn";
            default:
                return startChar;
        }
    }
*/
}
