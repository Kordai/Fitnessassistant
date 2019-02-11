package kz.alexpro.fitnessassistant;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView vessnaryada;
        TextView kolPodhodov;
        ImageView logoUprojneniya;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            vessnaryada = (TextView)itemView.findViewById(R.id.ves);
            kolPodhodov = (TextView)itemView.findViewById(R.id.podhody);
            logoUprojneniya = (ImageView)itemView.findViewById(R.id.logo);
        }
    }

    List<Uprojnenie> uprojneniya;

    RVAdapter(List<Uprojnenie> uprojneniya){
        this.uprojneniya = uprojneniya;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.vessnaryada.setText(uprojneniya.get(i).ves);
        personViewHolder.kolPodhodov.setText(uprojneniya.get(i).podhodi);
        personViewHolder.logoUprojneniya.setImageResource(uprojneniya.get(i).logo);
    }

    @Override
    public int getItemCount() {
        return uprojneniya.size();
    }
}
