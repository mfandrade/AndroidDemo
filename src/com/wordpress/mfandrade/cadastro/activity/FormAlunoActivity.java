package com.wordpress.mfandrade.cadastro.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.dao.*;
import com.wordpress.mfandrade.cadastro.databinder.*;

public class FormAlunoActivity extends Activity
{
    private BinderFormAluno _binder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        _binder = new BinderFormAluno(this);
        //
        Button btnSalvar = (Button) findViewById(R.id.form_aluno_btnSalvar);
        btnSalvar.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Aluno aluno = _binder.getAluno();
                AlunoDAO dao = new AlunoDAO(FormAlunoActivity.this);
                dao.insert(aluno);
                finish();
            }
        });
		
		Intent intent = getIntent();
        Aluno dadosAluno = (Aluno) intent.getSerializableExtra("dadosAluno");
        if(dadosAluno != null)
		{
			_binder.populateForm(dadosAluno);
		}
    }
}
