package ch.turtlestack.dorscht;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import ch.turtlestack.dorscht.backend.myApi.model.CategoryGroup;
import ch.turtlestack.dorscht.classPackage.CategoryAsyncTask;
import ch.turtlestack.dorscht.classPackage.ExpandableListAdapter;
import ch.turtlestack.dorscht.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //VARIABELN VON ANDY
    //ExpandablesList variables
    ArrayList<String> mainCategories = new ArrayList<>();
    ArrayList<Object> subCategories = new ArrayList<>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<CategoryGroup> categoryList;
    HashMap<String, List<String>> listDataChild;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        //get the listview
     /*   expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        //preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(view.getContext(), listDataHeader, listDataChild);

        //setting list adapter
        expListView.setAdapter(listAdapter);*/

        //................

        ExpandableListView exList = (ExpandableListView) view.findViewById(ch.turtlestack.dorscht.R.id.lvExp);
        exList.setIndicatorBounds(5, 5);

        exList.setIndicatorBounds(0, 20);
        //exList.setAdapter(exAdpt);
        new CategoryAsyncTask(exList, view.getContext()).execute();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Sports");
        listDataHeader.add("Learning Languages");
        listDataHeader.add("Cooking");

        // Adding child data
        List<String> sports = new ArrayList<String>();
        sports.add("Soccer");
        sports.add("Baseball");
        sports.add("Basketball");
        sports.add("Ice Hockey");
        sports.add("American Football");

        List<String> languages = new ArrayList<String>();
        languages.add("French");
        languages.add("German");
        languages.add("Spanish");
        languages.add("Dutch");

        List<String> cooking = new ArrayList<String>();
        cooking.add("Breakfast");
        cooking.add("5 minutes food");
        cooking.add("Dessert");

        listDataChild.put(listDataHeader.get(0), sports); // Header, Child data
        listDataChild.put(listDataHeader.get(1), languages);
        listDataChild.put(listDataHeader.get(2), cooking);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
