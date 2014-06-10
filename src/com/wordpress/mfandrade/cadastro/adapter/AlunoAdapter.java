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

package com.wordpress.mfandrade.cadastro.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.wordpress.mfandrade.cadastro.R;
import com.wordpress.mfandrade.cadastro.entity.Aluno;
import java.util.List;
import android.widget.ImageView;
import android.widget.TextView;
import com.wordpress.mfandrade.cadastro.dao.databinder.BinderFormAluno;
import android.graphics.BitmapFactory;

public class AlunoAdapter extends BaseAdapter {

  private List<Aluno> _list;
  private Activity _activity;

  public AlunoAdapter(Activity activity, List<Aluno> list) {
    _activity = activity;
    _list = list;
  }

  @Override
  public int getCount() {
    if (_list == null)
      return 0;
    return _list.size();
  }

  @Override
  public Aluno getItem(int pos) {
    if (_list == null)
      return null;
    return _list.get(pos);
  }

  @Override
  public long getItemId(int pos) {
    Aluno aluno = getItem(pos);
    if (aluno != null) {
      return aluno.getId();
    }
    return -1L;
  }

  @Override
  public View getView(int pos, View v, ViewGroup parent) {
    View view = _activity.getLayoutInflater().inflate(R.layout.item_aluno, null);
    ImageView photo = (ImageView) view.findViewById(R.id.photo);
    TextView name = (TextView) view.findViewById(R.id.name);
    Aluno aluno = getItem(pos);
    String arquivoFoto = aluno.getArquivoFoto();
    if (arquivoFoto != null) {
      photo.setImageBitmap(BitmapFactory.decodeFile(arquivoFoto));
    }
    name.setText(aluno.getNome());
    return view;
  }
}
