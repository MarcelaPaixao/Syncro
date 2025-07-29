package com.MarcelaEMariaLuiza.Syncro.Security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.MarcelaEMariaLuiza.Syncro.Repositories.AlunoRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de segurança para validar tokens JWT a cada requisição.
 * <p>
 * Este filtro intercepta todas as requisições à API, extrai o token JWT
 * do cabeçalho de autorização, valida-o e, se for válido, define o usuário
 * autenticado no contexto de segurança do Spring ({@link SecurityContextHolder}).
 * Ele herda de {@link OncePerRequestFilter} para garantir que seja executado
 * apenas uma vez por requisição.
 * </p>
 *
 * @author Marcela & Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoRepository alunoRepository;

    /**
     * Executa a lógica de validação do token para cada requisição.
     * <p>
     * Este método recupera o token, valida-o e, se for autêntico, carrega os
     * dados do usuário e o define no {@link SecurityContextHolder}, permitindo
     * o acesso a endpoints protegidos. Após o processo, a requisição é passada
     * adiante na cadeia de filtros.
     * </p>
     *
     * @param request     O objeto da requisição HTTP.
     * @param response    O objeto da resposta HTTP.
     * @param filterChain O objeto que representa a cadeia de filtros, para passar a requisição adiante.
     * @throws ServletException Se ocorrer um erro de servlet.
     * @throws IOException      Se ocorrer um erro de I/O.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if(login!=null){
            Aluno aluno = alunoRepository.findByEmail(login);
            if(aluno == null){
                throw new RuntimeException("Email inexistente");
            }
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(aluno, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
 
        }
        filterChain.doFilter(request, response);
        
    }
    /**
     * Extrai o token JWT do cabeçalho 'Authorization' da requisição.
     *
     * @param request O objeto da requisição HTTP.
     * @return O token JWT como uma {@link String}, ou {@code null} se o cabeçalho não for encontrado ou estiver mal formatado.
     */
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}
