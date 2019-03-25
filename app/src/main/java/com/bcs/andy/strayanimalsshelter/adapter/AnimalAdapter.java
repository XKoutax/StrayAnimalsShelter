package com.bcs.andy.strayanimalsshelter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcs.andy.strayanimalsshelter.R;
import com.bcs.andy.strayanimalsshelter.model.Animal;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private List<Animal> listAnimals;
    private Context context;
    private AnimalAdapterListener animalAdapterListener;

    public AnimalAdapter(List<Animal> listAnimals, Context context, AnimalAdapterListener animalAdapterListener) {
        this.listAnimals = listAnimals;
        this.context = context;
        this.animalAdapterListener = animalAdapterListener;
    }

    // whenever a ViewHolder is created
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_my_animals, parent, false);
        return new ViewHolder(view, animalAdapterListener);
    }

    // called right after onCreateViewHolder method
    // binds the data to the ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = listAnimals.get(position);
        String age = String.valueOf(animal.getAge()) + " yrs";
        holder.textViewAge.setText(age);
        holder.textViewName.setText(animal.getAnimalName());

        if(animal.getObservations().length() > 110) {
            String obs = animal.getObservations().substring(0, 107).concat("...");
            holder.textViewObs.setText(obs);
        }
        else {
            holder.textViewObs.setText(animal.getObservations());
        }


        switch (animal.getSpecies()) {
            case "Dog":
                holder.imageViewSpecies.setImageResource(R.drawable.dog_icon);
                break;
            case "Cat":
                holder.imageViewSpecies.setImageResource(R.drawable.cat_icon);
                break;
            default:
                holder.imageViewSpecies.setImageResource(R.drawable.dog_icon);
        }




    }

    @Override
    public int getItemCount() {
        return listAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        TextView textViewName, textViewObs, textViewAge;
        ImageView imageViewSpecies;

        AnimalAdapterListener animalAdapterListener;

        public ViewHolder(@NonNull View itemView, AnimalAdapterListener animalAdapterListener) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.animalNameTV);
            textViewObs = (TextView) itemView.findViewById(R.id.animalObsTV);
            textViewAge = (TextView) itemView.findViewById(R.id.animalAgeTV);
            imageViewSpecies = (ImageView) itemView.findViewById(R.id.imageViewMarker);
            cardView = (CardView) itemView.findViewById(R.id.list_item_animals_CardView);

            this.animalAdapterListener = animalAdapterListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            animalAdapterListener.onAnimalClick(getAdapterPosition(), listAnimals.get(getAdapterPosition()));
        }
    }

    public interface AnimalAdapterListener {
        void onAnimalClick(int position, Animal animal);
    }


}