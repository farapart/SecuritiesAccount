package Model;

import Model.CorporateAccount;
import Model.PersonalAccount;

import java.sql.*;
import java.util.ArrayList;

public class SecuritiesAccountDBManager {
    // 创建个人账户
    public static int newAccount(PersonalAccount account) {
        Connection conn = getConn("root", "");
        int i = 0;  // 数据库受影响的行数
        final String SQL = "insert into personal_account(securities_id, register_date, name, gender, id_no, " +
                "family_add, career, education, organization, phone_no, agent_id_no) values(?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?)";

        try {
            // 修改SQL语句
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, account.getSecurities_id());
            pStmt.setDate(2, account.getRegister_date());
            pStmt.setString(3, account.getName());
            pStmt.setBoolean(4, account.getGender());
            pStmt.setString(5, account.getId_no());
            pStmt.setString(6, account.getFamily_add());
            pStmt.setString(7, account.getCareer());
            pStmt.setString(8, account.getEducation());
            pStmt.setString(9, account.getOrganization());
            pStmt.setString(10, account.getPhone_no());
            pStmt.setString(11, account.getAgent_id_no());

            // 插入新的账户
            i = pStmt.executeUpdate();
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    // 创建法人账户
    public static int newAccount(CorporateAccount account) {
        Connection conn = getConn("root", "");
        int i = 0;  // 数据库受影响的行数
        final String SQL = "insert into corporate_account(securities_id, register_no, business_license_no, " +
                "legal_representative_id, legal_representative_name, legal_representative_phone_no, " +
                "legal_representative_add, authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // 修改SQL语句
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, account.getSecurities_id());
            pStmt.setString(2, account.getRegister_no());
            pStmt.setString(3, account.getBusiness_license_no());
            pStmt.setString(4, account.getLegal_representative_id());
            pStmt.setString(5, account.getLegal_representative_name());
            pStmt.setString(6, account.getLegal_representative_phone_no());
            pStmt.setString(7, account.getLegal_representative_add());
            pStmt.setString(8, account.getAuthorizer_name());
            pStmt.setString(9, account.getAuthorizer_id());
            pStmt.setString(10, account.getAuthorizer_phone_no());
            pStmt.setString(11, account.getAuthorizer_add());

            // 插入新的账户
            i = pStmt.executeUpdate();
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    // 删除个人账户，id_no是个人身份证号
    public static int deleteAccount(PersonalAccount account) {
        Connection conn = getConn("root", "");
        int i = 0;  // 数据库受影响的行数
        final String SQL = "delete from personal_account where securities_id = '" + account.getId_no() + "'";
        try {
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            i = pStmt.executeUpdate();
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    // 删除法人账户，register_no是法人注册登记号
    public static int deleteAccount(CorporateAccount account) {
        Connection conn = getConn("root", "");
        int i = 0;  // 数据库受影响的行数
        final String SQL = "delete from corporate_account where register_no = '" + account.getRegister_no() + "'";
        try {
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            i = pStmt.executeUpdate();
            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    // 得到个人账户的信息和相关联的资金账户号
    public static boolean getInformation(String id_no, PersonalAccount account, ArrayList<String> fundAccount) {
        Connection conn = getConn("root", "");
        boolean find = false;   // 标志是否找到对应的账户
        final String SQL = "select * from ? where id_no = '" + id_no + "'";
        try {
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            pStmt.setString(1, "personal_account");
            ResultSet rs = pStmt.executeQuery();

            // 如果找到了对应的账户，返回账户信息account和关联的资金账户fundAccount
            if (rs.next()) {
                account.setSecurities_id(rs.getString(1));
                account.setRegister_date(rs.getDate(2));
                account.setName(rs.getString(3));
                account.setGender(rs.getBoolean(4));
                account.setId_no(rs.getString(5));
                account.setFamily_add(rs.getString(6));
                account.setCareer(rs.getString(7));
                account.setEducation(rs.getString(8));
                account.setOrganization(rs.getString(9));
                account.setPhone_no(rs.getString(10));
                account.setAgent_id_no(rs.getString(11));
                find = true;

                pStmt.setString(1, "securities_fund");
                rs = pStmt.executeQuery();

                while (rs.next()) {
                    fundAccount.add(rs.getString(2));
                }
            }

            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    // 得到法人账户的信息和相关联的资金账户号
    public static boolean getCorporateInformation(String register_no, CorporateAccount account,
                                              ArrayList<String> fundAccount) {
        Connection conn = getConn("root", "");
        boolean find = false;   // 标志是否找到对应的账户
        final String SQL = "select * from corporate_account where register_no = '" + register_no + "'";
        final String SQL1 = "select * from securities_fund where securities_id = ?";
        try {
            PreparedStatement pStmt = conn.prepareStatement(SQL);
            ResultSet rs = pStmt.executeQuery();

            // 如果找到了对应的账户，返回账户信息account和关联的资金账户fundAccount
            if (rs.next()) {
                account.setSecurities_id(rs.getString(1));
                account.setRegister_no(rs.getString(2));
                account.setBusiness_license_no(rs.getString(3));
                account.setLegal_representative_id(rs.getString(4));
                account.setLegal_representative_name(rs.getString(5));
                account.setLegal_representative_phone_no(rs.getString(6));
                account.setLegal_representative_add(rs.getString(7));
                account.setAuthorizer_name(rs.getString(8));
                account.setAuthorizer_id(rs.getString(9));
                account.setAuthorizer_phone_no(rs.getString(10));
                account.setAuthorizer_add(rs.getString(11));
                find = true;

                pStmt = conn.prepareStatement(SQL1);
                pStmt.setString(1, account.getSecurities_id());
                rs = pStmt.executeQuery();

                while (rs.next()) {
                    fundAccount.add(rs.getString(2));
                }
            }

            pStmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    public static void setInformation() {}


    // 连接数据库
    private static Connection getConn(String user, String password) {
        final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";  // mysql驱动
        final String URL = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";    // 数据库URL
        Connection conn = null;

        try {
            Class.forName(DRIVER_NAME); // 加载驱动
            conn = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}