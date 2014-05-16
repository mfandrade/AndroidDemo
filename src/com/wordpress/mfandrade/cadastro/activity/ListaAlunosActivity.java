package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.telephony.*;
import android.view.*;
import android.view.ContextMenu.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.dao.*;
import java.util.*;

public class ListaAlunosActivity extends Activity
{
    private ListView _lstAlunos;
    private Aluno _selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        //
        _lstAlunos = (ListView) findViewById(R.id.lista_alunos_lstAlunos);
        registerForContextMenu(_lstAlunos);
        _lstAlunos.setOnItemLongClickListener(new OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id)
            {
                _selecionado = (Aluno) adapter.getItemAtPosition(pos);
                return false;
            }
        });
        _lstAlunos.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int pos, long id)
            {
                Intent formAluno = new Intent(ListaAlunosActivity.this, FormAlunoActivity.class);
                formAluno.putExtra("dadosAluno", (Aluno) adapter.getItemAtPosition(pos));
                startActivity(formAluno);
            }
        });
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
			case R.id.lista_alunos_ctxmenu_ligar:
                Intent call = new Intent(Intent.ACTION_VIEW);
                call.setData(Uri.parse("tel:" + _selecionado.getTelefone()));
                startActivity(call);
                break;
			case R.id.lista_alunos_ctxmenu_enviar_sms:
				SmsManager smsManager = SmsManager.getDefault();
                PendingIntent sendSms = PendingIntent.getActivity(this, 0, null, Intent.FLAG_ACTIVITY_NEW_TASK);
                if (PhoneNumberUtils.isWellFormedSmsAddress(_selecionado.getTelefone()))
                {
                	smsManager.sendTextMessage(_selecionado.getTelefone(), null, "Sua nota é " + _selecionado.getMediaFinal(), sendSms, null);
                    Toast.makeText(this, "SMS enviado", Toast.LENGTH_LONG).show();
                }
                else 
                {
                	Toast.makeText(this, "Falha ao enviar SMS. Tente novamente.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.lista_alunos_ctxmenu_acessar_website:
                Intent visitSite = new Intent(this, WebViewActivity.class);
                visitSite.putExtra("website", "http://" + _selecionado.getWebsite());
                startActivity(visitSite);
                break;
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
