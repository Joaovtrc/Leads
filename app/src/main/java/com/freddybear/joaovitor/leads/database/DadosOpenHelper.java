package com.freddybear.joaovitor.leads.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by João Vitor on 30/08/2017.
 */

public class DadosOpenHelper extends SQLiteOpenHelper{


    public DadosOpenHelper(Context context) {
        super(context, "DADOS", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDLL.getCreateTableEntrevistado());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
