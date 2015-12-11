package com.example.dsoutofernandez.examenmasterdetail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dsoutofernandez.examenmasterdetail.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    View rootView;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);

            //Instanciamos un objeto de tipo botón y lo asociamos al boton del detail fragment
            Button button = (Button) rootView.findViewById(R.id.boton);
            //Añadimos el listener para el OnClick y creamos un objeto ItemListFragment. Lo asociamos al activity_item_list a traves de su id.
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemListFragment fragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);
                    //Evaluamos si el fragment está en el layout
                    if (fragment == null || isInLayout()) {
                        /*Si no está el fragment1 en el layout; quiere decir que esta en modo Portrait; por lo que cerrará
                        el activity que contiene el Fragment2 para volver a ver el primero*/
                        getActivity().setResult(getActivity().RESULT_OK);//Este get activity nos devolverá si el resultado es satisfactorio o OK
                        getActivity().finish();
                    } else {
                        //Si está el primero, quiere decir que está en modo Land; ya que ambos estan en pantalla, por lo tanto, solo borrará el contenido
                        //Para esto es necesario configurar el modo Land previamente...
                        ((TextView) rootView.findViewById(R.id.item_detail)).setText("");
                    }
                }


            });

        }

        return rootView;
    }
}
