package ws;

import com.google.gson.Gson;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Filme;

public class ClientFilme {
    
    private WebTarget webTarget;
    private Client client;
    
    private static final String BASE_URI = "http://localhost:8080/TicketMaster/api";
    
/*  
    
    EXEMPLOS DE CONSUMO DE API
    
    
//  ADICIONAR FILMES
    ClienteFilme cliente = new ClienteFilme();        
    Filme filme = new Filme(JOptionPane.showInputDialog("Codigo: "), JOptionPane.showInputDialog("Nome: "), JOptionPane.showInputDialog("Genero: "), JOptionPane.showInputDialog("Sinopse: "));       
    cliente.adicionar(filme);
    List<Filme> filmes = cliente.getFilmes(new GenericType<List<Filme>>(){});                        
    for(Filme f: filmes){System.out.println(f.toString());}
        
//  REMOCAO DE FILME POR CODIGO
    ClienteFilme cliente = new ClienteFilme();
    cliente.remover(JOptionPane.showInputDialog("Código: "));
             
//  BUSCAR FILME POR CODIGO
    ClienteFilme cliente = new ClienteFilme();
    Filme filme = cliente.getFilmesPorId(new GenericType<Filme>(){}, JOptionPane.showInputDialog("Codigo: "));                                
      
//  BUSCA POR CODIGO + ALTERAÇÃO DE REGISTRO        
    ClienteFilme cliente = new ClienteFilme();
    Filme filme = cliente.getPorId(new GenericType<Filme>(){}, JOptionPane.showInputDialog("Codigo: "));                                        
    filme.setNome(JOptionPane.showInputDialog("Informe o novo nome: "));
    cliente.alterar(filme);
*/
    
    public ClientFilme() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("movies");
    }
    
    public <T> T getFilmes(Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFilmes(GenericType<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public <T> T getPorId(GenericType<T> responseType, String codigo) throws ClientErrorException {
        return webTarget.path(codigo).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
                
    public void adicionar(Filme filme) throws ClientErrorException {
        String json = new Gson().toJson(filme);
        webTarget.path("add").request().post(Entity.entity(json, MediaType.APPLICATION_JSON));  
    }
    
    public void alterar(Filme filme) throws ClientErrorException {
        String json = new Gson().toJson(filme);
        webTarget.path("put").request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }
    
    public void remover(String codigo) throws ClientErrorException {
        webTarget.path("delete").path(codigo).request().get();         
    }
    
    public void close() {
        client.close();
    }
}
