package me.oubtou.gestionabsence;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClasseAdapter extends ArrayAdapter<Classe> {

    private LayoutInflater layoutinflater;
    private Context context;
    private ArrayList<Classe> classes;

    public ClasseAdapter(Context context, ArrayList<Classe> classes) {
        super(context, R.layout.classe_item, classes);
        this.context = context;
        this.layoutinflater = this.layoutinflater.from(context);
        this.classes = classes;
    }

    @Override
    public Classe getItem(int position){
        return classes.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        if (convertView == null)
            convertView = layoutinflater.inflate(R.layout.classe_item, container, false);

        final Classe clss = getItem(position);
        LinearLayout container_classe = (LinearLayout) convertView.findViewById(R.id.container_classe);
        TextView btn_classe = (TextView) convertView.findViewById(R.id.btn_classe);
        btn_classe.setText( clss.toString() );

        TextView txt_nbr = (TextView) convertView.findViewById(R.id.nbr_etudiant);
        txt_nbr.setText( "Nbr. étudiants : "+ clss.getNbr_etudiant() );

        container_classe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent marquer_abs;

                if( ClassesActivity.archive )
                    marquer_abs = new Intent( context, SeanceActivity.class);
                else
                    marquer_abs = new Intent( context, AbsenceActivity.class);

                marquer_abs.putExtra("classe", clss.toJson() );
                marquer_abs.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(marquer_abs);
            }
        });
        return convertView;
    }

}