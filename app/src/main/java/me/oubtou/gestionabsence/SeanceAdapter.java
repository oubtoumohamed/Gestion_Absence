package me.oubtou.gestionabsence;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SeanceAdapter extends ArrayAdapter<Seance> {

    private LayoutInflater layoutinflater;
    private Context context;
    private ArrayList<Seance> seances;

    public SeanceAdapter(Context context, ArrayList<Seance> seances) {
        super(context, R.layout.absence_item, seances);
        this.context = context;
        this.layoutinflater = this.layoutinflater.from(context);
        this.seances = seances;
    }

    @Override
    public Seance getItem(int position){
        return seances.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        if (convertView == null)
            convertView = layoutinflater.inflate(R.layout.seance_item, container, false);

        final Seance seance = getItem(position);
        TextView txt = (TextView) convertView.findViewById(R.id.seance_date);
        txt.setText( "- Séance "+seance.getId()+" : "+seance.getDate() );

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent marquer_abs = new Intent( context, AbsenceActivity.class);
                marquer_abs.putExtra("seance_id", seance.getId() );
                marquer_abs.putExtra("seance_date", seance.getDate() );
                marquer_abs.putExtra("classe", SeanceActivity.classe.toJson() );
                marquer_abs.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(marquer_abs);
            }
        });

        return convertView;
    }

}