package com.chat.androidchat;

/**
 * Created by TSE on 07/06/2016.
 */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**En esta clase se centrara todo el trabado de con firebase
 */
public class FirebaseHelper {
    private DatabaseReference myDataBase;
    private final static String USERS = "users";
    private final static String CHATS = "chats";
    private final static String CONTACTS = "contacts";
    private final static String SEPARATOR = "___";

    // es un singleton para definir una instancia en toda mi app
    private static class SingletonHolder{
        private static final FirebaseHelper INTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INTANCE;
    }

    public FirebaseHelper() {
        this.myDataBase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getMyDataBase() {
        return myDataBase;
    }

    // Obtener el email del usuario autenticado
    public String getAutUserEmail(){
        String email = null;
        //AuthData
        return email;
    }
}
