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
            .requestMatchers(new AntPathRequestMatcher("/code"), 
            				new AntPathRequestMatcher("/critical"),
            				new AntPathRequestMatcher("/codeRegister"),
            				new AntPathRequestMatcher("/user"),
            				new AntPathRequestMatcher("/use"),
            				new AntPathRequestMatcher("/product"),
            				new AntPathRequestMatcher("/order/payments"),
            				new AntPathRequestMatcher("/order/paymentsCancel"),
            				new AntPathRequestMatcher("/cart"),
            				new AntPathRequestMatcher("/order"),
            				new AntPathRequestMatcher("/orderComplete"),
            				new AntPathRequestMatcher("/userRegister"),
            				new AntPathRequestMatcher("/memberSignIn"),
            				new AntPathRequestMatcher("/memberSignUp"),
            				new AntPathRequestMatcher("/css/**"),
            				new AntPathRequestMatcher("/js/**"),
            				new AntPathRequestMatcher("/img/**"),
            				new AntPathRequestMatcher("/error")).permitAll()
            .anyRequest().hasRole("2")
            )
        .csrf((csrf) -> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
            .ignoringRequestMatchers("/order/payments")
        	.ignoringRequestMatchers("/order/paymentsCancel"))
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
