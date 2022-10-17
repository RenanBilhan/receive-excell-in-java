package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        GerenciadorCertificado gerenciadorCertificado = new GerenciadorCertificado();
        List<Certificado> certificados = gerenciadorCertificado.criar();
        gerenciadorCertificado.imprimir(certificados);
    }
}