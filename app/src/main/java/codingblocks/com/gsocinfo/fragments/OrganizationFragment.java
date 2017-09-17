package codingblocks.com.gsocinfo.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codingblocks.com.gsocinfo.R;
import codingblocks.com.gsocinfo.adapters.OrgAdapter;
import codingblocks.com.gsocinfo.data.model.Organizations;
import codingblocks.com.gsocinfo.data.viewmodel.OrganizationViewModel;

/**
 * Created by harshit on 25/08/17.
 */

public class OrganizationFragment extends Fragment {

    private OrgAdapter orgAdapter;

    public static OrganizationFragment newInstance() {
        Bundle args = new Bundle();
        OrganizationFragment fragment = new OrganizationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_org, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_org);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        if (savedInstanceState != null && savedInstanceState.containsKey("ORG"))
//            setData(savedInstanceState.<Organizations.Organization>getParcelableArrayList("ORG"));
        orgAdapter = new OrgAdapter();
        recyclerView.setAdapter(orgAdapter);

        OrganizationViewModel organizationViewModel = ViewModelProviders.of(this).get(OrganizationViewModel.class);

        organizationViewModel.getOrganizations().observe(this, new Observer<List<Organizations.Organization>>() {
            @Override
            public void onChanged(@Nullable List<Organizations.Organization> organizations) {
                orgAdapter.setData(organizations);
            }
        });

        return view;
    }

//    public void setData(List<Organizations.Organization> data) {
//        orgAdapter = new OrgAdapter();
//        orgAdapter.setData(data);
//        this.organizations = data;
//    }
}
