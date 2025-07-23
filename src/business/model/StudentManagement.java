package business.model;

import business.dao.Database;
import business.entity.Student;
import business.entity.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public void displayStudent() {
        List<Student> students = new ArrayList<>();
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{call get_all_students() }");
            ResultSet resultSet = callableStatement.executeQuery();
           while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                students.add(student);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());
        } finally {
            Database.closeConnection(connection);
        }
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            System.out.println("Danh sách sinh viên: ");
            students.forEach(System.out::println);
        }
    }

    public void addStudent(Scanner scanner) {
        Student student = new Student();
        student.setName(Validator.getString(scanner, "Mời nhập vào tên sinh viên:"));
        student.setAge(Validator.getInt(scanner, "Mời nhập vào tuổi sinh viên:"));
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{call add_student(?,?)}");
            callableStatement.setString(1, student.getName());
            callableStatement.setInt(2, student.getAge());
            Boolean result = callableStatement.executeUpdate() > 0;
            if (result) {
                System.out.println("Thêm sinh viên thàn công.");
            } else {
                System.out.println("Thêm sinh viên thất bại.");
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());
        } finally {
            Database.closeConnection(connection);
        }
    }

    public void updateStudent(Scanner scanner) {

        int id = Validator.getInt(scanner, "Nhập vào mã sinh viên muốn cập nhật:");
        Student oldStudent = findStudentById(id);
        if (oldStudent == null) {
            System.out.println("Không tìm thấy mã sinh viên.");
        } else {
            oldStudent.setId(id);
            oldStudent.setName(Validator.getString(scanner, "Nhập vào tên mới của sinh viên."));
            oldStudent.setAge(Validator.getInt(scanner, "Nhập vào tuổi mới của sinh viên."));
        }
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{call update_student(?,?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, oldStudent.getName());
            callableStatement.setInt(3, oldStudent.getAge());
            boolean result = callableStatement.executeUpdate() > 0;
            if (result) {
                System.out.println("Cập nhật thành công.");
            } else {
                System.out.println("Cập nhật không thành công.");
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());
        }finally {
            Database.closeConnection(connection);
        }
    }

    public Student findStudentById(int id) {
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{call find_student_by_id(?) }");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                return student;
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());
        } finally {
            Database.closeConnection(connection);
        }
        return null;
    }
    public void deleteStudentByAge(Scanner scanner) {
        int age = Validator.getInt(scanner, "Nhập vào tuổi để xóa những sinh viên có tuổi nhỏ hơn: ");
        Connection connection = Database.getConnection();
        try {
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{call delete_student(?)}");
            callableStatement.setInt(1, age);
            Boolean result = callableStatement.executeUpdate() > 0;
            if (result) {
                System.out.println("Xóa sinh viên thành công.");
            }else {
                System.out.println("Xóa sinh viên thành công.");
            }
            
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            Database.closeConnection(connection);
        }
    }
}
