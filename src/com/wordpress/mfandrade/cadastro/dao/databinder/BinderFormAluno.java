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

package com.wordpress.mfandrade.cadastro.dao.databinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.wordpress.mfandrade.cadastro.R;
import com.wordpress.mfandrade.cadastro.activity.FormAlunoActivity;
import com.wordpress.mfandrade.cadastro.entity.Aluno;

public class BinderFormAluno {
  private Aluno _aluno;
  private ImageView _imgFoto;
  private EditText _edtNome;
  private EditText _edtEndereco;
  private EditText _edtTelefone;
  private EditText _edtWebsite;
  private RatingBar _ratMediaFinal;

  public BinderFormAluno(FormAlunoActivity form) {
	_aluno = new Aluno();
	_imgFoto = (ImageView) form.findViewById(R.id.form_aluno_imgFoto);
	_edtNome = (EditText) form.findViewById(R.id.form_aluno_edtNome);
	_edtEndereco = (EditText) form.findViewById(R.id.form_aluno_edtEndereco);
	_edtTelefone = (EditText) form.findViewById(R.id.form_aluno_edtTelefone);
	_edtWebsite = (EditText) form.findViewById(R.id.form_aluno_edtWebsite);
	_ratMediaFinal = (RatingBar) form.findViewById(R.id.form_aluno_ratMediaFinal);
  }

  public void populateForm(Aluno aluno) {
	carregarImagem(aluno.getArquivoFoto());
	_edtNome.setText(aluno.getNome());
	_edtEndereco.setText(aluno.getEndereco());
	_edtTelefone.setText(aluno.getTelefone());
	_edtWebsite.setText(aluno.getWebsite());
	_ratMediaFinal.setProgress(aluno.getMediaFinal().intValue());
	_aluno = aluno;
  }

  public Aluno getAluno() {
	_aluno.setNome(_edtNome.getText().toString());
	_aluno.setEndereco(_edtEndereco.getText().toString());
	_aluno.setTelefone(_edtTelefone.getText().toString());
	_aluno.setWebsite(_edtWebsite.getText().toString());
	_aluno.setMediaFinal(Float.valueOf(_ratMediaFinal.getProgress()));
	return _aluno;
  }

  public void carregarImagem(String fotoACarregar) {
	if (fotoACarregar != null) {
	  Bitmap foto = BitmapFactory.decodeFile(fotoACarregar);
	  Bitmap fotoMin = Bitmap.createScaledBitmap(foto, 160, 120, true);
	  _imgFoto.setImageBitmap(fotoMin);
	}
  }
}
