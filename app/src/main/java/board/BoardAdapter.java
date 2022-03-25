package board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp41.R;
import com.geektech.newsapp41.databinding.PagerBoardBinding;

import java.util.ArrayList;

import models.Board;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Board> list;
    private PagerBoardBinding binding;


    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Welcome", "Here you can share photos", R.drawable.image_1));
        list.add(new Board(" Your feedback:", "Share with other users and friends", R.drawable.image_2));
        list.add(new Board("Let's start", "Click on the Start to continue", R.drawable.image_3));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
        if (position == 2) {
            binding.btnStart.setVisibility(View.VISIBLE);
        } else {
            binding.btnStart.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull PagerBoardBinding itemView) {
            super(itemView.getRoot());

        }

        public void bind(int position) {
            Board board = list.get(position);
            binding.textTitle.setText(board.getTitle());
            binding.boardDesc.setText(board.getDescription());
            binding.imageBoard.setImageResource(board.getImage());
            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                }
            });
        }
    }
}
