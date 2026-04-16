package com.example.iniflex.model;

import java.time.LocalDate;

public class Pessoa {
    private String name;
    private LocalDate dataNascimento;

    public Pessoa (String name, LocalDate dataNascimento) {
        this.name = name;
        this.dataNascimento = dataNascimento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
