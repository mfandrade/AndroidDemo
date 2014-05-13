package com.wordpress.mfandrade.cadastro.dao;

import android.content.*;
import android.database.sqlite.*;

import java.util.*;

import com.wordpress.mfandrade.cadastro.*;

public class AlunoDAO extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "CadastroCaelum";
    private static final String TABLE_NAME = "alunos";

    public AlunoDAO(Context context) {

	super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

	StringBuilder sb = new StringBuilder();
	sb.append("CREATE TABLE ");
	sb.append(TABLE_NAME);
	sb.append(" (");
	sb.append("  id INTEGER PRIMARY KEY");
	sb.append(", nome TEXT UNIQUE NOT NULL");
	sb.append(", endereco TEXT NOT NULL");
	sb.append(", telefone TEXT");
	sb.append(", website TEXT");
	sb.append(", mediaFinal REAL");
	sb.append(")");
	String create = sb.toString();
	db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	onCreate(db);
    }

    public long insert(Aluno aluno) {

	ContentValues data = new ContentValues();
	//
	data.put("nome", aluno.getNome());
	data.put("endereco", aluno.getEndereco());
	data.put("telefone", aluno.getTelefone());
	data.put("website", aluno.getWebsite());
	data.put("mediaFinal", aluno.getMediaFinal());
	//
	SQLiteDatabase db = getWritableDatabase();
	long ret = db.insert(TABLE_NAME, null, data);
	db.close();
	return ret;
    }

    public void delete(long id) {

    }

    public void update(long id, String chave, Object valor) {

    }

    public Aluno getById(long id) {

	return null;
    }

    public Aluno getByField(String chave, Object valor) {

	return null;
    }

    public List<Aluno> getAll() {

	return null;
    }
}
