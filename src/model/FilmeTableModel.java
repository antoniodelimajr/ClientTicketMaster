package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.ws.rs.core.GenericType;
import ws.ClientFilme;

public class FilmeTableModel extends AbstractTableModel {

    ClientFilme cliente = new ClientFilme();
    public List<Filme> filmes = cliente.getFilmes(new GenericType<List<Filme>>() {});

    @Override
    public int getRowCount() {
        return filmes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Codigo";
                break;
            case 1:
                name = "Nome";
                break;
            case 2:
                name = "Genêro";
                break;
            case 3:
                name = "Sinopse";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                type = Integer.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Filme filme = filmes.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = filme.getCodigo();
                break;
            case 1:
                value = filme.getNome();
                break;
            case 2:
                value = filme.getGenero();
                break;
            case 3:
                value = filme.getSinopse();
                break;
        }
        return value;
    }
}
