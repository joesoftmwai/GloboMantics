package com.joesoft.globomantics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.joesoft.globomantics.helpers.SampleContent;
import com.joesoft.globomantics.models.Idea;
import com.joesoft.globomantics.services.IdeaService;
import com.joesoft.globomantics.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdeaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Idea mItem;

    public IdeaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.idea_detail, container, false);

        final Context context = getContext();

        final Button updateIdea = (Button) rootView.findViewById(R.id.idea_update);
        Button deleteIdea = (Button) rootView.findViewById(R.id.idea_delete);

        final EditText ideaName = (EditText) rootView.findViewById(R.id.idea_name);
        final EditText ideaDescription = (EditText) rootView.findViewById(R.id.idea_description);
        final EditText ideaStatus = (EditText) rootView.findViewById(R.id.idea_status);
        final EditText ideaOwner = (EditText) rootView.findViewById(R.id.idea_owner);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Activity activity = this.getActivity();
//            final CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

//            mItem = SampleContent.getIdeaById(getArguments().getInt(ARG_ITEM_ID));

//            ideaName.setText(mItem.getName());
//            ideaDescription.setText(mItem.getDescription());
//            ideaOwner.setText(mItem.getOwner());
//            ideaStatus.setText(mItem.getStatus());

//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.getName());
//            }

            IdeaService ideaService = ServiceBuilder.buildService(IdeaService.class);
            final Call<Idea> request = ideaService.getIdea(getArguments().getInt(ARG_ITEM_ID));
            request.enqueue(new Callback<Idea>() {
                @Override
                public void onResponse(Call<Idea> call, Response<Idea> response) {
                    mItem = response.body();

                    ideaName.setText(mItem.getName());
                    ideaDescription.setText(mItem.getDescription());
                    ideaStatus.setText(mItem.getStatus());
                    ideaOwner.setText(mItem.getOwner());
                }

                @Override
                public void onFailure(Call<Idea> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        updateIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Idea newIdea = new Idea();
//                newIdea.setId(getArguments().getInt(ARG_ITEM_ID));
//                newIdea.setName(ideaName.getText().toString());
//                newIdea.setDescription(ideaDescription.getText().toString());
//                newIdea.setStatus(ideaStatus.getText().toString());
//                newIdea.setOwner(ideaOwner.getText().toString());

//                SampleContent.updateIdea(newIdea);
//                Intent intent = new Intent(getContext(), IdeaListActivity.class);
//                startActivity(intent);

                IdeaService ideaService = ServiceBuilder.buildService(IdeaService.class);
                Call<Idea> updateRequest = ideaService.updateIdea(
                        getArguments().getInt(ARG_ITEM_ID),
                        ideaName.getText().toString(),
                        ideaDescription.getText().toString(),
                        ideaStatus.getText().toString(),
                        ideaOwner.getText().toString()

                );

                updateRequest.enqueue(new Callback<Idea>() {
                    @Override
                    public void onResponse(Call<Idea> call, Response<Idea> response) {
                        Intent intent = new Intent(getContext(), IdeaListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Idea> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed to update Idea; " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SampleContent.deleteIdea(getArguments().getInt(ARG_ITEM_ID));
//                Intent intent = new Intent(getContext(), IdeaListActivity.class);
//                startActivity(intent);

                IdeaService ideaService = ServiceBuilder.buildService(IdeaService.class);
                Call<Void> deleteRequest = ideaService.deleteIdea(getArguments().getInt(ARG_ITEM_ID));

                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(getContext(), IdeaListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed to delete Idea; " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return rootView;
    }
}
