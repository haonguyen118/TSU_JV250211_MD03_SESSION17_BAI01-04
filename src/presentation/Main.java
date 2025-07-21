package presentation;

import business.entity.Validator;
import business.model.StudentManagement;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        do {
            System.out.println("******QUẢN LÝ SINH VIÊN*****\n" +
                    "1. Hiển thị danh sách sinh viên.\n" +
                    "2. Thêm sinh viên.\n" +
                    "3. Xóa sinh viên.\n" +
                    "4. Cập nhật sinh viên.\n" +
                    "5. Thoát");
            int choice = Validator.getInt(scanner, "Nhập vào lựa chọn của bạn:");
            switch (choice) {
                case 1:
                    studentManagement.displayStudent();
                    break;
                case 2:
                    studentManagement.addStudent(scanner);
                    break;
                case 3:
                    studentManagement.deleteStudentByAge(scanner);
                    break;
                case 4:
                    studentManagement.updateStudent(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-5.");
            }
        } while (true);
    }
}