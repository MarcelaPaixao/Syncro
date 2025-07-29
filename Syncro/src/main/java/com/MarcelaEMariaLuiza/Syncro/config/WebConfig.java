package com.MarcelaEMariaLuiza.Syncro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Classe de configuração global da web para a aplicação.
 * <p>
 * Implementa {@link WebMvcConfigurer} para permitir a personalização da
 * configuração do Spring MVC, especificamente para definir as políticas de
 * Cross-Origin Resource Sharing (CORS).
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura as permissões de Cross-Origin Resource Sharing (CORS) para a API.
     * <p>
     * Este método define que os endpoints sob o caminho {@code /api/**} podem ser
     * acessados a partir da origem {@code http://localhost:8081} (o frontend em desenvolvimento).
     * Ele especifica os métodos HTTP permitidos (GET, POST, PUT, DELETE, OPTIONS),
     * permite todos os cabeçalhos e autoriza o envio de credenciais.
     * </p>
     *
     * @param registry o registro de CORS fornecido pelo Spring para adicionar os mapeamentos.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8081") // A URL do seu frontend em desenvolvimento
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}