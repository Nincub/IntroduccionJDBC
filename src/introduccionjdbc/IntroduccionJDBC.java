/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introduccionjdbc;

import students.jdbc.DaoJDBC;
import students.jdbc.IDAO;

/**
 *
 * @author Nincub
 */
public class IntroduccionJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ----------Patrones de Diseño----------
//        DAO: Data Access Object (Capa de persistencia)
//        DTO: Data Transfer Object


//        DaoJDBC       -Realiza todos los Query CRUD a la DB
//        Student       -Modelo del objecto que puede persistirse en la base de datos (DTO)
//        DBConnection  -Conexión a la DB
//        IDao          -Interfaz que implementa Dao JDBC

//        Utilizamos el tipo de interface como referencia
//        a una clase concreta
        IDAO daoJDBC = new DaoJDBC();
        
        //------------NUEVO ESTUDIANTE---------------------
        //Hacemos uso de la clase Student la cual se usa
        //para transferir la información entre las capas,
        //no es necesario especificar la llava primaria
        //ya que en este caso es de tipo autonumerico y la BD se encarga
        //de asignarle un nuevo valor
        
        /*Student student = new Student();
        student.setName("Benito Camelo");
        student.setAge(23);
        student.setCohort(10);*/
        
        
        
    }
    
}
