import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class NotPassException extends RuntimeException{
    NotPassException(String s){
        super(s);
    }
}
public class StuTest {
    Connection con = null;
    PreparedStatement ps = null;
    private int rollno;
    private String name;
    private int marks;

    StuTest() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etest1", "root", "");

    }

    public StuTest(int rollno, String name, int marks) {
        this.rollno = rollno;
        this.name = name;
        this.marks = marks;
    }

    public void insert() throws SQLException
    {
    String str = "insert into student values(?,?,?)";
    ps =con.prepareStatement(str);
    ps.setInt(1,rollno);
    ps.setString(2,name);
    ps.setInt(3,marks);
    ps.executeUpdate();
        System.out.println("Insert succesfull");
    }
    public void display() throws SQLException
    {

        Statement s = con.createStatement();
        ResultSet res = ps.executeQuery("select * from student");
        System.out.println("This is your database");
        while (res.next())
        {
            System.out.println(res.getInt(1)+" "+res.getInt(3));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException,InterruptedException
    {
        Class.forName("com.mysql.sj,jdbc.Driver");
        Scanner sc = new Scanner(System.in);
        ArrayList<StuTest> obj = new ArrayList<StuTest>(5);
        for(int i=0;i<1;i++)
        {
            System.out.println("<Enter The RollNumber>");
            int rno =  sc.nextInt();
            System.out.println("<Enter The Name of the Student>");
            String name = sc.next();
            System.out.println("<Enter The marks of the student>");
            int marks = sc.nextInt();

            if(marks>35)
            {
                StuTest obj1 = new StuTest();
                int Rollno = obj1.rollno;
                String Name = obj1.name;
                int Marks = obj1.marks;
                obj.add(obj1);
                obj.get(i).insert();
            }
            else {
                try{
                    throw new NotPassException("Not Paseed");
                }
                catch (NotPassException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
