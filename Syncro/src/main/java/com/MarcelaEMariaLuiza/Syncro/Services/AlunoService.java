package com.MarcelaEMariaLuiza.Syncro.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public Aluno createAluno(Aluno aluno){
         
        if(aluno.getNome().isEmpty() || aluno.getEmail().isEmpty() || aluno.getSenha().isEmpty()
        || aluno.getNome()==null || aluno.getEmail() == null || aluno.getSenha()==null){
            throw new CampoNaoPreenchidoException("Existem campos não preenchidos") ;
        }
        String email = aluno.getEmail();
        if(alunoRepository.findByEmail(email)!=null){
            throw new EmailExistenteException("O email informado já está cadastrado");
        }
        alunoRepository.save(aluno);
        return aluno;
    }

}
