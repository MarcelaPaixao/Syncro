package com.MarcelaEMariaLuiza.Syncro.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.DTO.AlunosResponseDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.CreateAlunoDTO;
import com.MarcelaEMariaLuiza.Syncro.DTO.LoginDTO;
import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Entities.Grupo;
import com.MarcelaEMariaLuiza.Syncro.Errors.CampoNaoPreenchidoException;
import com.MarcelaEMariaLuiza.Syncro.Errors.EmailExistenteException;
import com.MarcelaEMariaLuiza.Syncro.Errors.GrupoInexistenteException;
import com.MarcelaEMariaLuiza.Syncro.Errors.SenhaIncorretaException;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;
import com.MarcelaEMariaLuiza.Syncro.Repositories.GrupoRepository;

/**
 * Serviço responsável pela lógica de negócio de Alunos e pela integração com o Spring Security.
 * <p>
 * Esta classe implementa a interface {@link UserDetailsService} para carregar os dados do usuário
 * para o processo de autenticação do Spring Security. Além disso, gerencia operações de
 * negócio como a criação de novos alunos e a lógica de login.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Service
public class AlunoService implements UserDetailsService{
 
    private final AlunoRepository alunoRepository;
   
    private final GrupoRepository grupoRepository;
    
    @Autowired
    public AlunoService(AlunoRepository alunoRepository, GrupoRepository grupoRepository){
        this.alunoRepository = alunoRepository;
        this.grupoRepository = grupoRepository;
    }

    /**
     * Carrega os detalhes de um usuário (aluno) pelo seu e-mail para autenticação no Spring Security.
     * <p>
     * Este método é a implementação do contrato de {@link UserDetailsService}, onde o "username"
     * é tratado como o e-mail do aluno.
     * </p>
     *
     * @param username O e-mail do aluno que está tentando se autenticar.
     * @return um objeto {@link UserDetails} contendo os dados do aluno.
     * @throws UsernameNotFoundException se nenhum aluno for encontrado com o e-mail fornecido.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Aluno aluno = alunoRepository.findByEmail(username);
        if(aluno==null) throw new UsernameNotFoundException("Esse usuario não existe");
        return aluno;
    }
    /**
     * Cria e persiste um novo aluno no sistema.
     * <p>
     * Realiza a validação dos campos, codifica a senha do aluno e verifica
     * se o e-mail fornecido já existe antes de salvar o novo registro.
     * </p>
     *
     * @param aluno A entidade {@link Aluno} com os dados do novo usuário.
     * @return A entidade {@link Aluno} salva, com a senha já codificada.
     * @throws CampoNaoPreenchidoException se campos obrigatórios como nome, e-mail ou senha estiverem vazios.
     * @throws EmailExistenteException se o e-mail fornecido já estiver em uso por outro aluno.
     */
    public Aluno createAluno(CreateAlunoDTO aluno){
         
        if(aluno.getNome().isEmpty() || aluno.getEmail().isEmpty() || aluno.getSenha().isEmpty()
        || aluno.getNome()==null || aluno.getEmail() == null || aluno.getSenha()==null){
            throw new CampoNaoPreenchidoException("Existem campos não preenchidos") ;
        }
        Aluno novoAluno = new Aluno();
        String email = aluno.getEmail();
        novoAluno.setSenha(passwordEncoder().encode(aluno.getSenha()));
        novoAluno.setEmail(aluno.getEmail());
        novoAluno.setNome(aluno.getNome());
        if(alunoRepository.findByEmail(email)!=null){
            throw new EmailExistenteException("O email informado já está cadastrado");
        }
        alunoRepository.save(novoAluno);
        return novoAluno;
    }

    /**
     * Define o bean do codificador de senhas para a aplicação.
     * <p>
     * Este método, anotado com {@code @Bean}, cria uma instância de {@link BCryptPasswordEncoder}
     * para ser gerenciada pelo contêiner do Spring. Essa instância é usada em toda a aplicação
     * para codificar e verificar senhas de forma segura.
     * </p>
     *
     * @return Uma instância de {@link PasswordEncoder}.
     */
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

    public List<AlunosResponseDTO> getAlunosGrupo(Long GrupoId){
        
        Optional<Grupo> g  = grupoRepository.findById(GrupoId);
        if(!g.isPresent()) throw new GrupoInexistenteException("Grupo inválido. Não é possível retornar a lista");
        Grupo grupo = g.get();
        List <Aluno> alunos = alunoRepository.FindByGrupos(GrupoId); 

        List <AlunosResponseDTO> alunosFormatados = new ArrayList<>();
        for(Aluno a: alunos){
            AlunosResponseDTO alunoResponse = new AlunosResponseDTO();
            alunoResponse.setNome(a.getNome());
            alunoResponse.setEmail(a.getEmail());
            alunoResponse.setId(a.getId());
            alunosFormatados.add(alunoResponse);
        }

        return alunosFormatados;

    }
    public AlunosResponseDTO getAluno(Aluno a){ 
    
        AlunosResponseDTO alunoResponse = new AlunosResponseDTO();
        alunoResponse.setNome(a.getNome());
        alunoResponse.setEmail(a.getEmail());
        alunoResponse.setId(a.getId());
        return alunoResponse;

    }
}
