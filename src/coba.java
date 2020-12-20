import java.sql.*;
import java.util.Scanner;
public class coba {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_pabrik";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static void create(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Masukan Nama :");
            String Nama= input.next();
            System.out.println("Masukan Alamat :");
            String Alamat= input.next();
            System.out.println("Masukan Kota :");
            String Kota= input.next();

            String sql = "INSERT INTO admin (Nama , Alamat , Kota) VALUE ('%s','%s','%s')";
            sql= String.format(sql,Nama,Alamat,Kota);

            stmt.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void read(){
        String sql = "SELECT * FROM admin";
        try{
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                int ID = rs.getInt("ID");
                String Nama = rs.getString("Nama");
                String Alamat = rs.getString("Alamat");
                String Kota = rs.getString("Kota");

                System.out.println(String.format("%d. Nama : %s, Alamat : %s,Kota : %s",ID,Nama,Alamat,Kota));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void update(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Masukan ID yang ingin di update :");
            int ID = input.nextInt();
            System.out.println("Masukan nama baru :");
            String Nama=input.next();
            System.out.println("Masukan alamat baru :");
            String Alamat=input.next();
            System.out.println("Masukan kota asal baru");
            String Kota=input.next();

            String sql="UPDATE admin SET Nama='%s', Alamat='%s', Kota='%s' WHERE ID=%d";
            sql = String.format(sql,Nama,Alamat,Kota,ID);

            stmt.execute(sql);
            System.out.println("data telah di update");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void delete(){
        try{
            Scanner input= new Scanner(System.in);

            System.out.println("Masukan ID yang ingin dihapus :");
            int ID=input.nextInt();

            String sql = String.format("DELETE FROM admin WHERE ID = %d",ID);
            stmt.execute(sql);

            System.out.println("data telah dihapus");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER,PASS);
            stmt=conn.createStatement();

            while(!conn.isClosed()){
                System.out.println("1. Create" +
                        "\n2. Read" +
                        "\n3. Update" +
                        "\n4. Delete" +
                        "\n5. exit");
                System.out.println("masukan pilihan :");
                int pilih = input.nextInt();

                if(pilih==1){
                    create();
                }else if(pilih==2){
                    read();
                }else if(pilih==3){
                    update();
                }else if(pilih==4){
                    delete();
                }else{
                    System.exit(0);
                }
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
