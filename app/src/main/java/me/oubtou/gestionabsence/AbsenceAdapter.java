package me.oubtou.gestionabsence;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class AbsenceAdapter extends ArrayAdapter<Etudiant> {

    private LayoutInflater layoutinflater;
    private Context context;
    private ArrayList<Etudiant> etudiants;

    public AbsenceAdapter(Context context, ArrayList<Etudiant> etudiants) {
        super(context, R.layout.absence_item, etudiants);
        this.context = context;
        this.layoutinflater = this.layoutinflater.from(context);
        this.etudiants = etudiants;
    }

    @Override
    public Etudiant getItem(int position){
        return etudiants.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        if (convertView == null)
            convertView = layoutinflater.inflate(R.layout.absence_item, container, false);

        final Etudiant etud = getItem(position);
        final CheckBox chbx = (CheckBox) convertView.findViewById(R.id.etud_abs);
        chbx.setText( etud.toString() );

        chbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                etudiants.get(position).setIs_abs(isChecked);
                chbx.setBackgroundColor( (isChecked == true) ? Color.LTGRAY : Color.WHITE);
            }
        });

        chbx.setChecked(etud.isIs_abs());
        return convertView;
    }

}
