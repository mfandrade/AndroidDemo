package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.dao.*;
import com.wordpress.mfandrade.cadastro.databinder.*;
import java.io.*;

public class FormAlunoActivity extends Activity
{
    private BinderFormAluno _binder;
	private String _localFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        _binder = new BinderFormAluno(this);
        //
        Button btnSalvar = (Button) findViewById(R.id.form_aluno_btnSalvar);
        Intent intent = getIntent();
        Aluno dadosAluno = (Aluno) intent.getSerializableExtra("dadosAluno");
        if (dadosAluno != null)
        {
            btnSalvar.setText(R.string.txt_atualizar);
            _binder.populateForm(dadosAluno);
        }
        btnSalvar.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Aluno aluno = _binder.getAluno();
                AlunoDAO dao = new AlunoDAO(FormAlunoActivity.this);
                dao.insertOrUpdate(aluno);
                finish();
            }
        });
		
		ImageView foto = _binder.getFoto();
		foto.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_localFoto = getExternalFilesDirs(null) + "/" + System.currentTimeMillis();
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(_localFoto)));
				startActivityForResult(camera, 987);
			}
		});
    }
}
