package com.wordpress.mfandrade.cadastro.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.wordpress.mfandrade.cadastro.Aluno;
import com.wordpress.mfandrade.cadastro.R;
import com.wordpress.mfandrade.cadastro.dao.AlunoDAO;
import com.wordpress.mfandrade.cadastro.databinder.BinderFormAluno;
import java.io.File;

public class FormAlunoActivity extends Activity {
  private static final int TIRANDO_FOTO = 987;
  private BinderFormAluno _binder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_aluno);
    _binder = new BinderFormAluno(this);
    //
    Button btnSalvar = (Button) findViewById(R.id.form_aluno_btnSalvar);
    Intent intent = getIntent();
    Aluno dadosAluno = (Aluno) intent.getSerializableExtra("dadosAluno");
    if (dadosAluno != null) {
      btnSalvar.setText(R.string.txt_atualizar);
      _binder.populateForm(dadosAluno);
    }
    btnSalvar.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Aluno aluno = _binder.getAluno();
        AlunoDAO dao = new AlunoDAO(FormAlunoActivity.this);
        dao.insertOrUpdate(aluno);
        finish();
      }
    });
    ImageView foto = (ImageView) findViewById(R.id.form_aluno_imgFoto);
    foto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String arquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        _binder.getAluno().setArquivoFoto(arquivoFoto);
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(arquivoFoto)));
        startActivityForResult(camera, TIRANDO_FOTO);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    if (requestCode == TIRANDO_FOTO && resultCode == Activity.RESULT_OK) {
      _binder.carregarImagem(_binder.getAluno().getArquivoFoto());
    }
  }
}
