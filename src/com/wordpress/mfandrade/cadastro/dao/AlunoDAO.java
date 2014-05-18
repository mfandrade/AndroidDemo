package com.wordpress.mfandrade.cadastro.dao;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;
import com.wordpress.mfandrade.cadastro.*;

public class AlunoDAO extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "CadastroCaelum";
	private static final String TABLE_NAME = "alunos";
	private static final String _ID = "id";
	private static final String _NAME = "nome";
	private static final String _ADDRESS = "endereco";
	private static final String _PHONE = "telefone";
	private static final String _WEBSITE = "website";
	private static final String _FGRADE = "mediaFinal";
	private static final String _PHOTO = "arquivoFoto";

	public AlunoDAO(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		sb.append(TABLE_NAME);
		sb.append("(");
		sb.append(_ID + " INTEGER PRIMARY KEY, ");
		sb.append(_NAME + " TEXT UNIQUE NOT NULL, ");
		sb.append(_ADDRESS + " TEXT NOT NULL, ");
		sb.append(_PHONE + " TEXT, ");
		sb.append(_WEBSITE + " TEXT, ");
		sb.append(_FGRADE + " REAL, ");
		sb.append(_PHOTO + " STRING ");
		sb.append(")");
		String create = sb.toString();
		db.execSQL(create); //, new String[] {_ID, _NAME, _ADDRESS, _PHONE, _WEBSITE, _FGRADE, _PHOTO});
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public int delete(Aluno aluno)
	{
		if (aluno == null || aluno.getId() == null) { throw new IllegalArgumentException(); }
		Long id = aluno.getId();
		SQLiteDatabase db = getWritableDatabase();
		int nol = db.delete(TABLE_NAME, "id=?", new String[] { Long.toString(id) });
		db.close();
		return nol;
	}

	public long insertOrUpdate(Aluno aluno)
	{
		if (aluno == null) { throw new IllegalArgumentException(); }
		ContentValues data = new ContentValues();
		data.put(_NAME, aluno.getNome());
		data.put(_ADDRESS, aluno.getEndereco());
		data.put(_PHONE, aluno.getTelefone());
		data.put(_WEBSITE, aluno.getWebsite());
		data.put(_FGRADE, aluno.getMediaFinal());
		data.put(_PHOTO, aluno.getArquivoFoto());
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			Long id = aluno.getId();
			if (id == null)
			{
				id = db.insert(TABLE_NAME, null, data);
			} else
			{
				data.put(_ID, id);
				db.update(TABLE_NAME, data, "id=?", new String[] { Long.toString(id) });
			}
			return id;
		} finally
		{
			db.close();
		}
	}

	public Aluno getById(Long id)
	{
		return null;
	}

	public List<Aluno> getAll()
	{
		List<Aluno> ret = new LinkedList<>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		while (c.moveToNext())
		{
			Aluno aluno = new Aluno();
			aluno.setId(c.getLong(c.getColumnIndex(_ID)));
			aluno.setNome(c.getString(c.getColumnIndex(_NAME)));
			aluno.setEndereco(c.getString(c.getColumnIndex(_ADDRESS)));
			aluno.setTelefone(c.getString(c.getColumnIndex(_PHONE)));
			aluno.setWebsite(c.getString(c.getColumnIndex(_WEBSITE)));
			aluno.setMediaFinal(c.getFloat(c.getColumnIndex(_FGRADE)));
			aluno.setArquivoFoto(c.getString(c.getColumnIndex(_PHOTO)));
			ret.add(aluno);
		}
		db.close();
		c.close();
		return ret;
	}
}
