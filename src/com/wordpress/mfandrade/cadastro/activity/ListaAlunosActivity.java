/*
 *  Copyright (C) 2014  Marcelo F Andrade <mfandrade@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wordpress.mfandrade.cadastro.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.wordpress.mfandrade.cadastro.R;
import com.wordpress.mfandrade.cadastro.dao.AlunoDAO;
import com.wordpress.mfandrade.cadastro.entity.Aluno;
import java.util.List;
import com.wordpress.mfandrade.cadastro.adapter.AlunoAdapter;

public class ListaAlunosActivity extends Activity {
  private ListView _lstAlunos;
  private Aluno _selecionado;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_alunos);

    _lstAlunos = (ListView) findViewById(R.id.lista_alunos_lstAlunos);
    registerForContextMenu(_lstAlunos);
    _lstAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
        _selecionado = (Aluno) adapter.getItemAtPosition(pos);
        return false;
      }
    });
    _lstAlunos.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
        Intent formAluno = new Intent(ListaAlunosActivity.this, FormAlunoActivity.class);
        formAluno.putExtra("dadosAluno", (Aluno) adapter.getItemAtPosition(pos));
        startActivity(formAluno);
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    recarregarLista();
  }

  private void recarregarLista() {
    AlunoDAO dao = new AlunoDAO(this);
    List<Aluno> alunos = dao.getAll();
    AlunoAdapter adapter = new AlunoAdapter(this, alunos);
    _lstAlunos.setAdapter(adapter);
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

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    getMenuInflater().inflate(R.menu.lista_alunos_ctxmenu, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.lista_alunos_ctxmenu_ligar:
        Intent call = new Intent(Intent.ACTION_VIEW);
        call.setData(Uri.parse("tel:" + _selecionado.getTelefone()));
        startActivity(call);
        break;
      case R.id.lista_alunos_ctxmenu_enviar_sms:
        SmsManager sms = SmsManager.getDefault();
        PendingIntent sendSms = PendingIntent.getActivity(this, 0, null, Intent.FLAG_ACTIVITY_NEW_TASK);
        if (PhoneNumberUtils.isWellFormedSmsAddress(_selecionado.getTelefone())) {
          sms.sendTextMessage(_selecionado.getTelefone(), null, "Sua nota é " + _selecionado.getMediaFinal(), sendSms, null);
          Toast.makeText(this, "SMS enviado", Toast.LENGTH_LONG).show();
        }
        else {
          Toast.makeText(this, "Falha ao enviar SMS. Tente novamente.", Toast.LENGTH_LONG).show();
        }
        break;
      case R.id.lista_alunos_ctxmenu_acessar_website:
        Intent site = new Intent(this, WebViewActivity.class);
        site.putExtra("website", "http://" + _selecionado.getWebsite());
        startActivity(site);
        break;
      case R.id.lista_alunos_ctxmenu_mapa:
        Intent map = new Intent(Intent.ACTION_VIEW);
        String address = _selecionado.getEndereco();
        map.setData(Uri.parse("geo:0,0?q=" + address));
        startActivity(map);
        break;
      case R.id.lista_alunos_ctxmenu_enviar_email:
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { "cadastrocaelum@mailinator.com" });
        email.putExtra(Intent.EXTRA_SUBJECT, "Nota por e-mail");
        email.putExtra(Intent.EXTRA_TEXT, "Sua nota é " + _selecionado.getMediaFinal());
        if (email.resolveActivity(getPackageManager()) != null) {
          startActivity(Intent.createChooser(email, "Enviar usando"));
          Toast.makeText(this, "Nota enviada", Toast.LENGTH_LONG).show();
        }
        break;
      case R.id.lista_alunos_ctxmenu_deletar:
        new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.ctx_deletar)
        .setMessage(R.string.quer_mesmo_deletar)
        .setNegativeButton(R.string.nao, null)
        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
            dao.delete(_selecionado);
            recarregarLista();
          }
        }).show();
        return false;
    }
    return super.onContextItemSelected(item);
  }
}
