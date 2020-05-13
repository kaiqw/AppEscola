package br.univali.cc.prog.escolaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirALunoActivity extends AppCompatActivity {

    EditText nome;
    EditText idade;
    EditText curso;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_a_luno2);

        nome = findViewById(R.id.etNome);
        idade = findViewById(R.id.etIdade);
        curso = findViewById(R.id.etCurso);

        criarBancoDados();

    }
    private void criarBancoDados(){
         db = openOrCreateDatabase("escola.db", Context.MODE_PRIVATE, null);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS aluno(");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("nome VARCHAR(35), ");
        sql.append("idade INTEGER(3), ");
        sql.append("curso VARCHAR(35)");
        sql.append(")");

        try {
            db.execSQL(sql.toString());
        }catch (SQLException ex){
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void gravarBanco (String nomeAluno, String idadeAluno, String cursoALuno){
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO aluno(nome, idade, curso) VALUES(");
        sql.append( nomeAluno +  ", ");
        sql.append( idadeAluno + ",");
        sql.append( cursoALuno );
        sql.append(")");

        try {
            Toast.makeText(getApplicationContext(), sql.toString(), Toast.LENGTH_LONG).show();
            db.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void salvarbtn(View v){
        String idadeSt  = idade.getText().toString().trim();
        String nomeSt = nome.getText().toString().trim();
        String cursoSt = curso.getText().toString().trim();

        gravarBanco(nomeSt, idadeSt, cursoSt);
    }

    public void homebtn(View v){
        Button btn_tela1 = (Button) findViewById(R.id.btn_home);
        btn_tela1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InserirALunoActivity.this, MainActivity.class);
                startActivity(in);
            }
        });

    }
}
