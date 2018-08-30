/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.jdbc;

import com.dto.Student;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nincub
 */
public class DaoJDBC implements IDAO{
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO student(name, age, cohort) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE student SET name=?, age=?, cohort=? WHERE id_student=?";
    private final String SQL_DELETE = "DELETE FROM student WHERE id_student = ?";
    private final String SQL_SELECT = "SELECT id_student, name, age, cohort FROM student";

    public DaoJDBC() {
    }

    public DaoJDBC(Connection userConn) {
        this.userConn = userConn;
    }    
    
    @Override
    public int insert(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            //Verificar si ya existe una conexion
            conn = (this.userConn != null ) ? this.userConn : DBConnection.getConnection();
            //Precompilacion del SQlL statement, hacer cache del QUERY
            stmt = (PreparedStatement) conn.prepareStatement(SQL_INSERT);
            int index = 1;//Número del parametro
            stmt.setString(index++, student.getName());
            stmt.setInt(index++, student.getAge());
            stmt.setInt(index++, student.getCohort());
            System.out.println("Executing QUERY: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records: " + rows);
        } finally {
            //Cerramos los objetos PreparedStatement y Connection
            DBConnection.close(stmt);
            if (this.userConn == null) {
                DBConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int update(Student student) throws SQLException {
        Connection conn =  null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = (PreparedStatement) conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, student.getName());
            stmt.setInt(index++, student.getAge());
            stmt.setInt(index++, student.getCohort());
            stmt.setInt(index, student.getId_student());
            System.out.println("Executing QUERY: " + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Affected rows: " + rows);
        } finally {
            //Cerramos los objetos PreparedStatement y Connection
            DBConnection.close(stmt);
            if (this.userConn == null) {
                DBConnection.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int delete(Student student) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int deleted = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = (PreparedStatement) conn.prepareStatement(SQL_DELETE);
            int index = 1;
            stmt.setInt(index++, student.getId_student());
            System.out.println("Executing QUERY: " +SQL_DELETE);
            deleted = stmt.executeUpdate();
            System.out.println("Affected rows: " + deleted);
        } finally {
            //Cerramos los objetos PreparedStatement y Connection
            DBConnection.close(stmt);
            if (this.userConn == null) {
                DBConnection.close(conn);
            }
        }
        return deleted;
    }

    @Override
    public List<Student> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        Student student;
        ResultSet rs = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : DBConnection.getConnection();
            stmt = (PreparedStatement) conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Recuperamos cada atributo del Student
                int idStudentTemp = rs.getInt(1);
                String nameTemp = rs.getString(2);
                int ageTemp = rs.getInt(3);
                int cohortTemp = rs.getInt(4);
                //Intanciamos un objeto por cada registro
                student = new Student();
                student.setId_student(idStudentTemp);
                student.setName(nameTemp);
                student.setAge(ageTemp);
                student.setCohort(cohortTemp);
                //El objeto creado es añadido a la lista
                students.add(student);
            }
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if (this.userConn == null) {
                DBConnection.close(conn);
            }
        }
        return students;
    }
    
}
