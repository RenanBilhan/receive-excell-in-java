package org.example;

import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GerenciadorCertificado {
    public List<Certificado> criar() throws IOException {
//        Criar uma lista que será retornada
        List<Certificado> certificados = new ArrayList<>();
//        Receber o arquivo excell que será utilizado
        @Cleanup FileInputStream arquivo = new FileInputStream("src\\main\\resources\\Teste.xlsx");
//        Colocar o arquivo excell na classe workbook para acessar os metodos
        Workbook workbook = new XSSFWorkbook(arquivo);
//        Escolher qual das tabelas do arquivo excell será utilizada através do índice
        Sheet sheet = workbook.getSheetAt(0);
//        Criando uma lista de linhas
        List<Row> rows = (List<Row>) toList(sheet.iterator());
//        Removendo cabeçalhos, removendo a linha no indice 0
        rows.remove(0);

//        Percorrendo as celulas da linha
        rows.forEach(row->{
//        Criando uma lista de celulas, recebendo as informações
            List<Cell> cells = (List<Cell>) toList(row.cellIterator());
//        Preenchendo os atributos da classe Certificado com as informações da tabela e criando os objetos certificado
            Certificado certificado = Certificado.builder()
                    .nome(cells.get(0).getStringCellValue())
                    .cargaHoraria(cells.get(1).getNumericCellValue())
                    .curso(cells.get(2).getStringCellValue())
                    .build();
//            Adicionando os certificados à lista certificados
            certificados.add(certificado);
        });


        return certificados;
    }

    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }

    public void imprimir(List<Certificado> certificados){certificados.forEach(System.out::println);}
}


