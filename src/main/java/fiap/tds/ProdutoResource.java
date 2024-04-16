package fiap.tds;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("produtos")
public class ProdutoResource {

    private static List<Produto> produtos = new ArrayList<>(Arrays.asList(
            new Produto(1, "Iphone 15 pro max", 10000.0),
            new Produto(2, "Samsung Galaxy S21", 5000.0),
            new Produto(3, "Xiaomi Redmi Note 10", 2000.0)
    ));

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos(){
        return produtos;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProduto(@PathParam("id") int id){
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduto(Produto produto){
        produtos.add(produto);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        produtos.removeIf(p -> p.getId() == id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduto(@PathParam("id")int id, Produto produto){
        produtos.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setNome(produto.getNome());
            p.setPreco(produto.getPreco());
        });
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    
}
