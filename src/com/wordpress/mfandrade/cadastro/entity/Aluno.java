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