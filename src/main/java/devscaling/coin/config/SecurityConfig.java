package devscaling.coin.config;

import devscaling.coin.jwt.JWTFilter;
import devscaling.coin.jwt.JWTUtil;
import devscaling.coin.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil){
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //csrf disable
        http
                .csrf(auth -> auth.disable());

        //Form 로그인 방식 disable
        http
                .formLogin(auth -> auth.disable());

        //http basic 인증 방식 disable
        //
        http
                .httpBasic(auth -> auth.disable());

        //H2 콘솔을 iframe에서 사용할 수 있도록 허용(기본적으로 보안을 위해 비활성화 되어 있음)
        http.headers(auth -> auth
                .frameOptions(frame -> frame.disable()));

//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/home", "signup").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .anyRequest().authenticated());
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
