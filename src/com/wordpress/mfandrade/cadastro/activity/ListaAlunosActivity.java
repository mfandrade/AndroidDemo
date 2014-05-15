package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;
import android.widget.AdapterView.OnItemLongClickListener;
import java.util.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.dao.*;

public class ListaAlunosActivity extends Activity
{
    private ListView _lstAlunos;
    private Aluno    _selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        //
        _lstAlunos = (ListView) findViewById(R.id.lista_alunos_lstAlunos);
        _lstAlunos.setOnItemLongClickListener(new OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id)
            {
                _selecionado = (Aluno) adapter.getItemAtPosition(pos);
                return false;
            }
        });
        registerForContextMenu(_lstAlunos);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        recarregarLista();
    }

    private void recarregarLista()
    {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getAll();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        _lstAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.lista_alunos_menu_novo:
                Intent formAluno = new Intent(this, FormAlunoActivity.class);
                startActivity(formAluno);
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.lista_alunos_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.lista_alunos_ctxmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.lista_alunos_ctxmenu_deletar:
                //@formatter:off
                new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.ctx_deletar)
                .setMessage(R.string.quer_mesmo_deletar)
                .setNegativeButton(R.string.nao, null)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                        dao.delete(_selecionado);
                        recarregarLista();
                    }
                }).show();
                return false;
                //@formatter:on
        }
        return super.onContextItemSelected(item);
    }
}
