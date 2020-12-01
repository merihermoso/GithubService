import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;

public class Main{
       public static void main(String[] args){
        System.out.println("Hello World");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //cada vez que recibimos peticion interceptamos lo que viene en el body y lo que va (ayuda a debugar)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //ademas de las urls le pedimos que nos muestre el body

        OkHttpClient client = new OkHttpClient.Builder()        //construimos el cliente "a mano"
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()          //generamos objeto retrofit
                .baseUrl("https://api.github.com/")         //api que queremos
                .addConverterFactory(GsonConverterFactory.create())     //Gson converter para que acepte el JSON
                .client(client)     //nos da linea a linea get/push/post... a que servidor voy, url,
                .build();

        GitHubService service = retrofit.create(GitHubService.class);   //creamos GitHubService

        Call<List<Repo>> repos = service.listRepos("merihermoso");
        //llamamos a la funcion repos con el parametro para que me de MIS repositorios (PREPARA)

        try{
            List<Repo> result = repos.execute().body();
            //EJECUTA funcion repos i consulta el body
            //pasa a ser una lista de Repo

            for(Repo r: result){        //iteramos la lista
                System.out.println(r);
            }
        } catch (Exception e){
            System.out.println("EXCEPTION: ");
            System.out.println(e.toString());
        }
    }
}
