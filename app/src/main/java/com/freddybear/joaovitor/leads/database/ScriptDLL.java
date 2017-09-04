package com.freddybear.joaovitor.leads.database;

/**
 * Created by João Vitor on 30/08/2017.
 */

public class ScriptDLL {

    public static String getCreateTableEntrevistado(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS ENTREVISTADO ( ");
        sql.append("    ID       INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    NOME     VARCHAR (80) NOT NULL DEFAULT (''), ");
        sql.append("    TELEFONE VARCHAR (11) NOT NULL DEFAULT ('') ) ");

        return sql.toString();
    }




}
