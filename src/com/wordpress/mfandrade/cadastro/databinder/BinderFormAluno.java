package com.wordpress.mfandrade.cadastro.databinder;

import android.widget.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.activity.*;

public class BinderFormAluno
{
    private Aluno     aluno;
    private EditText  _edtNome;
    private EditText  _edtEndereco;
    private EditText  _edtTelefone;
    private EditText  _edtWebsite;
    private RatingBar _ratMediaFinal;

    public BinderFormAluno(FormAlunoActivity form)
    {
        aluno = new Aluno();
        _edtNome = (EditText) form.findViewById(R.id.form_aluno_edtNome);
        _edtEndereco = (EditText) form.findViewById(R.id.form_aluno_edtEndereco);
        _edtTelefone = (EditText) form.findViewById(R.id.form_aluno_edtTelefone);
        _edtWebsite = (EditText) form.findViewById(R.id.form_aluno_edtWebsite);
        _ratMediaFinal = (RatingBar) form.findViewById(R.id.form_aluno_ratMediaFinal);
    }

	public void populateForm(Aluno aluno)
	{
		Toast.makeText(_edtNome.getContext(), "test " + aluno.getNome(), Toast.LENGTH_LONG).show();
		_edtNome.setText(aluno.getNome());
		_edtEndereco.setText(aluno.getEndereco());
		_edtTelefone.setText(aluno.getTelefone());
		_edtWebsite.setText(aluno.getWebsite());
		_ratMediaFinal.setProgress(aluno.getMediaFinal().intValue());
	}

    public Aluno getAluno()
    {
        aluno.setNome(_edtNome.getText().toString());
        aluno.setEndereco(_edtEndereco.getText().toString());
        aluno.setTelefone(_edtTelefone.getText().toString());
        aluno.setWebsite(_edtWebsite.getText().toString());
        aluno.setMediaFinal(Float.valueOf(_ratMediaFinal.getProgress()));
        return aluno;
    }
}
