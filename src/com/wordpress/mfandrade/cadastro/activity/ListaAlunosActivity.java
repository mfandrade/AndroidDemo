package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.wordpress.mfandrade.cadastro.*;

public class ListaAlunosActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_alunos);
		//
		Aluno[] alunos = { new Aluno("Huguinho"), new Aluno("Zezinho"), new Aluno("Luizinho") };
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
		ListView lstAlunos = (ListView) findViewById(R.id.list_alunos_lstAlunos);
		lstAlunos.setAdapter(adapter);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		switch (item.getItemId()) {
		case R.id.lista_alunos_menu_novo:
			Intent formAluno = new Intent(this, FormAlunoActivity.class);
			startActivity(formAluno);
			return false;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.lista_alunos_menu, menu);
		return true;
	}
}
