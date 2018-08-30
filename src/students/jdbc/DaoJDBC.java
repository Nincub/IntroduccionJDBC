/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package students.jdbc;

import com.dto.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nincub
 */
public class DaoJDBC implements IDAO{
    private Connection userConn;
    private final String SQL_INSERT = "INSERT INTO student(name, age, cohort) VALUES(?,?,?)";
    private final String SQL_UPDATE = "UPDATE student SET name=?, age=? WHERE id_student=?";
    private final String SQL_DELETE = "DELETE FROM student WHERE id_student = ?";
    private final String SQL_SELECT = "SELECT id_student, name, age, cohort FROM student";

    public DaoJDBC() {
    }

    public DaoJDBC(Connection userConn) {
        this.userConn = userConn;
    }    
    
    @Override
    public int insert(Student student) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Student student) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Student student) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
