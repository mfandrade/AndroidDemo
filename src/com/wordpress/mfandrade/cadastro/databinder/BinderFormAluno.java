package com.wordpress.mfandrade.cadastro.databinder;

import android.graphics.*;
import android.widget.*;
import com.wordpress.mfandrade.cadastro.*;
import com.wordpress.mfandrade.cadastro.activity.*;

public class BinderFormAluno
{
	private Aluno _aluno;
	private ImageView _imgFoto;
	private EditText _edtNome;
	private EditText _edtEndereco;
	private EditText _edtTelefone;
	private EditText _edtWebsite;
	private RatingBar _ratMediaFinal;

	public BinderFormAluno(FormAlunoActivity form)
	{
		_aluno = new Aluno();
		_imgFoto = (ImageView) form.findViewById(R.id.form_aluno_imgFoto);
		_edtNome = (EditText) form.findViewById(R.id.form_aluno_edtNome);
		_edtEndereco = (EditText) form.findViewById(R.id.form_aluno_edtEndereco);
		_edtTelefone = (EditText) form.findViewById(R.id.form_aluno_edtTelefone);
		_edtWebsite = (EditText) form.findViewById(R.id.form_aluno_edtWebsite);
		_ratMediaFinal = (RatingBar) form.findViewById(R.id.form_aluno_ratMediaFinal);
	}

	public void populateForm(Aluno aluno)
	{
		carregarImagem(aluno.getArquivoFoto());
		_edtNome.setText(aluno.getNome());
		_edtEndereco.setText(aluno.getEndereco());
		_edtTelefone.setText(aluno.getTelefone());
		_edtWebsite.setText(aluno.getWebsite());
		_ratMediaFinal.setProgress(aluno.getMediaFinal().intValue());
		_aluno = aluno;
	}

	public Aluno getAluno()
	{
		//_aluno.setArquivoFoto(arquivoFoto);
		_aluno.setNome(_edtNome.getText().toString());
		_aluno.setEndereco(_edtEndereco.getText().toString());
		_aluno.setTelefone(_edtTelefone.getText().toString());
		_aluno.setWebsite(_edtWebsite.getText().toString());
		_aluno.setMediaFinal(Float.valueOf(_ratMediaFinal.getProgress()));
		return _aluno;
	}

	public void carregarImagem(String fotoACarregar)
	{
		Bitmap foto = BitmapFactory.decodeFile(fotoACarregar);
		Bitmap fotoMin = Bitmap.createScaledBitmap(foto, _imgFoto.getWidth(), _imgFoto.getHeight(), true);
		_imgFoto.setImageBitmap(fotoMin);
	}
}
