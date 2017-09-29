package com.nicoqueijo.cityskylinequiz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.helpers.CornerRounder;
import com.nicoqueijo.cityskylinequiz.helpers.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.models.QuestionReport;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuizReportRecyclerViewAdapter extends RecyclerView.Adapter<QuizReportRecyclerViewAdapter
        .ViewHolder> {

    private Context mContext;
    private ArrayList<QuestionReport> mQuestionReports;
    private LayoutInflater mInflater;


    public QuizReportRecyclerViewAdapter(Context context, List<QuestionReport> questionReports) {
        mContext = context;
        mQuestionReports = (ArrayList<QuestionReport>) questionReports;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_report_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the ViewHolder to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // clear the marks in each bind to assure correctness
        holder.mMarkChoice1.setImageDrawable(null);
        holder.mMarkChoice2.setImageDrawable(null);
        holder.mMarkChoice3.setImageDrawable(null);
        holder.mMarkChoice4.setImageDrawable(null);

        Picasso.with(mContext).load(mQuestionReports.get(position).getQuestion().getCorrectChoice()
                .getImageUrl()).into(holder.mCityImage);
        holder.mQuestionNumber.setText(" " + mQuestionReports.get(position).getQuestionNumber() + " ");

        holder.mFlagChoice1.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice1().getCountryName(), mContext));
        holder.mFlagChoice2.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice2().getCountryName(), mContext));
        holder.mFlagChoice3.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice3().getCountryName(), mContext));
        holder.mFlagChoice4.setImageResource(ResourceByNameRetriever.getDrawableResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice4().getCountryName(), mContext));

        holder.mCityNameChoice1.setText((ResourceByNameRetriever.getStringResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice1().getCityName(), mContext)));
        holder.mCityNameChoice2.setText((ResourceByNameRetriever.getStringResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice2().getCityName(), mContext)));
        holder.mCityNameChoice3.setText((ResourceByNameRetriever.getStringResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice3().getCityName(), mContext)));
        holder.mCityNameChoice4.setText((ResourceByNameRetriever.getStringResourceByName
                (mQuestionReports.get(position).getQuestion().getChoice4().getCityName(), mContext)));

        if (mQuestionReports.get(position).getChoice1mark() == QuestionReport.Mark.CORRECT) {
            holder.mMarkChoice1.setImageResource(R.drawable.ic_correct);
        } else if (mQuestionReports.get(position).getChoice1mark() == QuestionReport.Mark.INCORRECT) {
            holder.mMarkChoice1.setImageResource(R.drawable.ic_incorrect);
        }

        if (mQuestionReports.get(position).getChoice2mark() == QuestionReport.Mark.CORRECT) {
            holder.mMarkChoice2.setImageResource(R.drawable.ic_correct);
        } else if (mQuestionReports.get(position).getChoice2mark() == QuestionReport.Mark.INCORRECT) {
            holder.mMarkChoice2.setImageResource(R.drawable.ic_incorrect);
        }

        if (mQuestionReports.get(position).getChoice3mark() == QuestionReport.Mark.CORRECT) {
            holder.mMarkChoice3.setImageResource(R.drawable.ic_correct);
        } else if (mQuestionReports.get(position).getChoice3mark() == QuestionReport.Mark.INCORRECT) {
            holder.mMarkChoice3.setImageResource(R.drawable.ic_incorrect);
        }

        if (mQuestionReports.get(position).getChoice4mark() == QuestionReport.Mark.CORRECT) {
            holder.mMarkChoice4.setImageResource(R.drawable.ic_correct);
        } else if (mQuestionReports.get(position).getChoice4mark() == QuestionReport.Mark.INCORRECT) {
            holder.mMarkChoice4.setImageResource(R.drawable.ic_incorrect);
        }

        CornerRounder.roundImageCorners(holder.mCityImage, holder.mFlagChoice1, holder.mFlagChoice2,
                holder.mFlagChoice2, holder.mFlagChoice3, holder.mFlagChoice4);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mQuestionReports.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mQuestionNumber;
        ImageView mCityImage;

        ImageView mFlagChoice1;
        ImageView mFlagChoice2;
        ImageView mFlagChoice3;
        ImageView mFlagChoice4;

        TextView mCityNameChoice1;
        TextView mCityNameChoice2;
        TextView mCityNameChoice3;
        TextView mCityNameChoice4;

        ImageView mMarkChoice1;
        ImageView mMarkChoice2;
        ImageView mMarkChoice3;
        ImageView mMarkChoice4;

        public ViewHolder(View itemView) {
            super(itemView);

            mQuestionNumber = (TextView) itemView.findViewById(R.id.question_number);
            mCityImage = (ImageView) itemView.findViewById(R.id.city_image);

            mFlagChoice1 = (ImageView) itemView.findViewById(R.id.flag_choice_one);
            mFlagChoice2 = (ImageView) itemView.findViewById(R.id.flag_choice_two);
            mFlagChoice3 = (ImageView) itemView.findViewById(R.id.flag_choice_three);
            mFlagChoice4 = (ImageView) itemView.findViewById(R.id.flag_choice_four);

            mCityNameChoice1 = (TextView) itemView.findViewById(R.id.city_name_choice_one);
            mCityNameChoice2 = (TextView) itemView.findViewById(R.id.city_name_choice_two);
            mCityNameChoice3 = (TextView) itemView.findViewById(R.id.city_name_choice_three);
            mCityNameChoice4 = (TextView) itemView.findViewById(R.id.city_name_choice_four);

            mMarkChoice1 = (ImageView) itemView.findViewById(R.id.mark_choice_one);
            mMarkChoice2 = (ImageView) itemView.findViewById(R.id.mark_choice_two);
            mMarkChoice3 = (ImageView) itemView.findViewById(R.id.mark_choice_three);
            mMarkChoice4 = (ImageView) itemView.findViewById(R.id.mark_choice_four);

        }
    }

}
