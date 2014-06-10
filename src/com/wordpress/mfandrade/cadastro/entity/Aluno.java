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

package com.wordpress.mfandrade.cadastro.entity;

import java.io.Serializable;

public class Aluno implements Serializable {
  private static final long serialVersionUID = 2581691150319034797L;
  private Long _id;
  private String _nome;
  private String _endereco;
  private String _telefone;
  private String _website;
  private Float _mediaFinal;
  private String _arquivoFoto;
  
  public Aluno() {}

  public Aluno(String nome) {
	setNome(nome);
  }

  public String getEndereco() {
	return _endereco;
  }

  public Long getId() {
	return _id;
  }

  public Float getMediaFinal() {
	return _mediaFinal;
  }

  public String getNome() {
	return _nome;
  }

  public String getTelefone() {
	return _telefone;
  }

  public String getWebsite() {
	return _website;
  }

  public void setEndereco(String endereco) {
	_endereco = endereco;
  }

  public void setId(Long id) {
	_id = id;
  }

  public void setMediaFinal(Float mediaFinal) {
	_mediaFinal = mediaFinal;
  }

  public void setNome(String nome) {
	_nome = nome;
  }

  public void setTelefone(String telefone) {
	_telefone = telefone;
  }

  public void setWebsite(String website) {
	_website = website;
  }

  @Override
  public String toString() {
	return _id + ". " + _nome;
  }

  public void setArquivoFoto(String arquivoFoto) {
	_arquivoFoto = arquivoFoto;
  }

  public String getArquivoFoto() {
	return _arquivoFoto;
  }
}
