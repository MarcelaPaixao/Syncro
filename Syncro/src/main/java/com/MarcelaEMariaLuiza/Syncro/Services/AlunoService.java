package com.MarcelaEMariaLuiza.Syncro.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.DTO.LoginDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Errors.SenhaIncorretaException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;

@Service
public class AlunoService implements UserDetailsService{
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
        
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Aluno aluno = alunoRepository.findByEmail(username);
        if(aluno==null) throw new UsernameNotFoundException("Esse usuario não existe");
        return aluno;
    }
    public Aluno createAluno(Aluno aluno){
         
        if(aluno.getNome().isEmpty() || aluno.getEmail().isEmpty() || aluno.getSenha().isEmpty()
        || aluno.getNome()==null || aluno.getEmail() == null || aluno.getSenha()==null){
            throw new CampoNaoPreenchidoException("Existem campos não preenchidos") ;
        }
        String email = aluno.getEmail();
        aluno.setSenha(passwordEncoder().encode(aluno.getSenha()));
        if(alunoRepository.findByEmail(email)!=null){
            throw new EmailExistenteException("O email informado já está cadastrado");
        }
        alunoRepository.save(aluno);
        return aluno;
    }

    public Aluno login(LoginDTO loginDTO){
        
        if(loginDTO.getEmail().isEmpty() || loginDTO.getSenha().isEmpty()
         || loginDTO.getEmail() == null || loginDTO.getSenha()==null){
            throw new CampoNaoPreenchidoException("Existem campos não preenchidos") ;
        }

        Aluno aluno = alunoRepository.findByEmail(loginDTO.getEmail());
        if(aluno == null){
            throw new UsernameNotFoundException("Usuário inexistente");
        }

        if(passwordEncoder().matches(loginDTO.getSenha(), aluno.getSenha())){
            return aluno;
        }
        throw new SenhaIncorretaException("Senha incompativel");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
