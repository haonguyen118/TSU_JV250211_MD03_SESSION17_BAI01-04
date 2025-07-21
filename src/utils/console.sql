create database session17;
use session17;
create table students (
    id int primary key auto_increment,
    name varchar(50),
    age int not null
);
insert into students (name, age) VALUES
                                     ('Nguyễn Văn A',20),
                                     ('Nguyễn Thị B',21);
select * from students;
insert into students (name, age) VALUES
                                     ('hào',17);
# Tạo bảng tài khoản ngân hàng
create table accounts (
     id int primary key auto_increment,
    balance decimal not null);
# tạo procedure chuyển tiền giữa hai tài khoản
DELIMITER //
create procedure transfer_funds(id_from int , id_to int , amount decimal ,out result varchar(255))
begin
    declare count_id_from int ;
    declare count_id_to int ;
    declare balance_from decimal ;
    declare balance_to decimal ;
    select count(id) into count_id_from from accounts where id = id_from ;

    select balance into balance_from from accounts where id = id_from ;


    if count_id_from = 0 then set result = 'không tìm thấy người gửi !' ;
    elseif count_id_to = 0 then set result = 'không tìm thấy người nhận !' ;
    elseif balance_from - amount < 0 then set result = 'Người gửi không đủ tiền !' ;
    else
        update accounts set balance = balance - amount where id = id_from ;
        update accounts set balance = balance + amount where id = id_to ;
        set result = 'Chuyển tiền thành công !' ;
    end if ;

end //



delimiter //
create procedure get_all_students ()
begin
    select * from students;
end //

# tạo thủ tục thêm sinh viên
delimiter //
create procedure add_student (
    IN in_name varchar(50),
    IN in_age int
)
begin
    insert into students (name, age)
       values ( in_name,
             in_age);

end //
drop procedure add_student;
#Cập nhật sinh viên
delimiter //
create procedure update_student (
    In in_id int,
    in in_name varchar(50),
    in in_age int
)
begin
    update students
        set
            name = in_name,
            age = in_age
    where id = in_id;
end //

# Xóa sinh viên
delimiter //
create procedure delete_student (
    In in_age int
)
begin
    delete from students where age < in_age;
end //
drop procedure delete_student;
#tìm sinh vien theo id

delimiter //
create procedure find_student_by_id(
    in in_id int
)
begin
    select * from students
        where id = in_id;
end //