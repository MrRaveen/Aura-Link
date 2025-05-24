package com.example.SocialAuthLogIn.Config;

import com.example.SocialAuthLogIn.Controller.AccessPoint;
import com.example.SocialAuthLogIn.Entity.users;
import com.example.SocialAuthLogIn.Repo.usersRepo;
import com.example.SocialAuthLogIn.Services.checkUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
@EnableJpaRepositories
//@EnableJpaRepositories(basePackages = "com.example.SocialAuthLogIn.Repo")
public class userConfig {
//    @Primary
//    @Bean
//    public JpaRepositoryFactoryBean<usersRepo, com.example.SocialAuthLogIn.Entity.users, Integer> usersRepoFactoryBean() {
//        return new JpaRepositoryFactoryBean<>(usersRepo.class);
//    }
    @Bean
    public usersRepo usersRepo() {return new usersRepo() {
        @Override
        public List<users> findAll(Sort sort) {
            return List.of();
        }

        @Override
        public Page<users> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends users> S save(S entity) {
            return null;
        }

        @Override
        public <S extends users> List<S> saveAll(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public Optional<users> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public List<users> findAll() {
            return List.of();
        }

        @Override
        public List<users> findAllById(Iterable<Integer> integers) {
            return List.of();
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(users entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends users> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends users> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends users> List<S> saveAllAndFlush(Iterable<S> entities) {
            return List.of();
        }

        @Override
        public void deleteAllInBatch(Iterable<users> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public users getOne(Integer integer) {
            return null;
        }

        @Override
        public users getById(Integer integer) {
            return null;
        }

        @Override
        public users getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends users> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends users> List<S> findAll(Example<S> example) {
            return List.of();
        }

        @Override
        public <S extends users> List<S> findAll(Example<S> example, Sort sort) {
            return List.of();
        }

        @Override
        public <S extends users> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends users> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends users> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends users, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public List<users> testQuery() {
            return List.of();
        }
    };
    }
    @Bean
    public AccessPoint accessPoint() {return new AccessPoint();}
    @Bean
    public checkUser createAccount(){
        return new checkUser();
    }
    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }
}
