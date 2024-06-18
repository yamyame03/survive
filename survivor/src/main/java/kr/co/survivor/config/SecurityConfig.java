package kr.co.survivor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.survivor.repo.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        
        http
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/code")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/codeRegister")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/user")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/use")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/product")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/order/payments")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/cart")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/order")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/userRegister")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/memberSignIn")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/memberSignUp")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/img/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
            .anyRequest().hasRole("2")
            )
        .csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
            .ignoringRequestMatchers("/order/payments"))
        .headers((headers) -> headers
            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        .formLogin((formLogin) -> formLogin
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage("/memberSignIn")
            .defaultSuccessUrl("/code"))            
            .logout((logout) -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/memberLogOut"))
            .logoutSuccessUrl("/code")
            .invalidateHttpSession(true));    
        
        return http.build();
    }
}
