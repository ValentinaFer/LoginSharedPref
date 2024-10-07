package com.softulp.loginsharedpref.main.ui.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.softulp.loginsharedpref.main.ui.model.Usuario;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if (sp == null){
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){

        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nombre", usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putString("email", usuario.getEmail());
        editor.putString("password", usuario.getPassword());
        editor.putLong("dni", usuario.getDni());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String nombre = sp.getString("nombre", "-1");
        String apellido = sp.getString("apellido", "-1");
        String email = sp.getString("email", "-1");
        String password = sp.getString("password", "-1");
        Usuario user = new Usuario(nombre, password, dni, email, apellido);
        return user;
    }

    public static Usuario login(Context context, String email, String password){
        Usuario usuario = null;
        SharedPreferences sp = conectar(context);
        Long dni = sp.getLong("dni", -1);
        String nombre = sp.getString("nombre", "-1");
        String apellido = sp.getString("apellido", "-1");
        String emailGuardado = sp.getString("email", "-1");
        String passwordGuardada = sp.getString("password", "-1");

        if (email.equals(emailGuardado) && password.equals(passwordGuardada)){
            usuario = new Usuario(nombre, passwordGuardada, dni, emailGuardado, apellido);
        }
        return usuario;
    }

}
