package com.example.insertcoin.core;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.example.insertcoin.R;

        import java.util.ArrayList;

public class AdaptadorComentarios extends BaseAdapter {

    protected Activity activity;
    //ARRAYLIST CON TODOS LOS ITEMS
    protected ArrayList<comenta> items;

    //CONSTRUCTOR
    public AdaptadorComentarios(Activity activity, ArrayList<comenta> items) {
        this.activity = activity;
        this.items = items;
    }
    //CUENTA LOS ELEMENTOS
    @Override
    public int getCount() {
        return items.size();
    }
    //DEVUELVE UN OBJETO DE UNA DETERMINADA POSICION
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    //DEVUELVE EL ID DE UN ELEMENTO
    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }
    //METODO PRINCIPAL, AQUI SE LLENAN LOS DATOS
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // SE GENERA UN CONVERTVIEW POR MOTIVOS DE EFICIENCIA DE MEMORIA
        //ES UN NIVEL MAS BAJO DE VISTA, PARA QUE OCUPEN MENOS MEMORIA LAS

        //IMAGENES
        View v = convertView;
        //ASOCIAMOS LA VISTA AL LAYOUT DEL RECURSO XML DONDE ESTA LA BASE DE

        //CADA ITEM
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_comentario, null);
        }

        comenta c = items.get(position);
        //RELLENAMOS LA IMAGEN Y EL TEXTO
        TextView login = (TextView) v.findViewById(R.id.usuario);
        login.setText(c.getLogin());
        TextView comentario = (TextView) v.findViewById(R.id.comentario);
        comentario.setText(c.getComentario());
        // DEVOLVEMOS VISTA
        return v;
    }

}
