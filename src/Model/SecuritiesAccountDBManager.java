package Model;

import java.sql.*;
import java.util.*;

public class SecuritiesAccountDBManager {
    private String driverName = "com.mysql.cj.jdbc.Driver";
    private String dbURL = "jdbc:mysql://localhost:3306/project?serverTimezone=UTC";
    private String userName = "root";
    private String userPwd = "hzx8865198";

    /**
     * 注册个人账户
     * @param account 个人账户注册信息
     * @param flag 标志是否存在代理人，0表示存在，1表示不存在
     * @return 操作是否成功
     */
    public boolean newPersonalAccount(PersonalAccount account, int flag) {
        if(flag == 0){
            String sql = "INSERT INTO personal_account(register_date, name, gender, id_no, " +
                    "family_add, career, education, organization, phone_no, agent_id_no) VALUES(?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?)";
            Object []args = {account.getRegister_date(), account.getName(),
                    account.getGender(), account.getId_no(), account.getFamily_add(), account.getCareer(), account.getEducation(),
                    account.getOrganization(), account.getPhone_no(), account.getAgent_id_no()};
            return executeUpdate(sql, args);
        }else{
            String sql = "INSERT INTO personal_account(register_date, name, gender, id_no, " +
                    "family_add, career, education, organization, phone_no) VALUES(?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?)";
            Object []args = {account.getRegister_date(), account.getName(),
                    account.getGender(), account.getId_no(), account.getFamily_add(), account.getCareer(), account.getEducation(),
                    account.getOrganization(), account.getPhone_no()};
            return executeUpdate(sql, args);
        }
    }

    /**
     * 注册法人账户
     * @param account 法人账户注册信息
     * @return 操作是否成功
     */
    public boolean newCorporateAccount(CorporateAccount account) {
        String sql = "INSERT INTO corporate_account(register_no, business_license_no, " +
                "legal_representative_id, legal_representative_name, legal_representative_phone_no, " +
                "legal_representative_add, authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object []args = {account.getRegister_no(), account.getBusiness_license_no(),
        account.getLegal_representative_id(), account.getLegal_representative_name(), account.getBusiness_license_no(),
        account.getLegal_representative_add(), account.getAuthorizer_name(), account.getAuthorizer_id(),
        account.getAuthorizer_phone_no(), account.getAuthorizer_add()};
        return executeUpdate(sql, args);
    }

    /**
     * 把被删除的个人账户放入personal_deleted表中
     * @param account 被删除的账户信息
     * @return 操作是否成功
     */
    public boolean newPersonalDeleted(PersonalAccount account) {
        String sql = "INSERT INTO personal_deleted(securities_id, register_date, name, gender, id_no, " +
                "family_add, career, education, organization, phone_no, agent_id_no) VALUES(?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?)";
        Object []args = {account.getSecurities_id(), account.getRegister_date(), account.getName(), account.getGender(),
        account.getId_no(), account.getFamily_add(), account.getCareer(), account.getEducation(), account.getOrganization(),
        account.getPhone_no(), account.getAgent_id_no()};
        return executeUpdate(sql, args);
    }

    /**
     * 把被删除的法人账户放入corporate_deleted表中
     * @param account 被删除的账户信息
     * @return 操作是否成功
     */
    public boolean newCorporateDeleted(CorporateAccount account) {
        String sql = "INSERT INTO corporate_deleted(securities_id, register_no, business_license_no, " +
                "legal_representative_id, legal_representative_name, legal_representative_phone_no, " +
                "legal_representative_add, authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object []args = {account.getSecurities_id(), account.getRegister_no(), account.getBusiness_license_no(),
                account.getLegal_representative_id(), account.getLegal_representative_name(), account.getBusiness_license_no(),
                account.getLegal_representative_add(), account.getAuthorizer_name(), account.getAuthorizer_id(),
                account.getAuthorizer_phone_no(), account.getAuthorizer_add()};
        return executeUpdate(sql, args);
    }

    /**
     * 用身份证号来获取个人账户信息
     * @param id_no 身份证号
     * @param account 返回的个人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getPersonalAccount(String id_no, PersonalAccount account) {
        String sql = "SELECT * FROM personal_account WHERE id_no=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, id_no);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
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
                account.setState(rs.getInt(12));
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 用账户ID来获取个人账户信息
     * @param securities_id 账户ID
     * @param account 返回的个人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getPersonalAccountID(String securities_id, PersonalAccount account) {
        String sql = "SELECT * FROM personal_account WHERE securities_id=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, securities_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
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
                account.setState(rs.getInt(12));
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 用注册号码来获取法人账户信息
     * @param register_no 注册号码
     * @param account 返回的法人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getCorporateAccount(String register_no, CorporateAccount account) {
        String sql = "SELECT * FROM corporate_account WHERE register_no=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, register_no);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
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
                account.setState(rs.getInt(12));
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 用账户ID来获取法人账户信息
     * @param securities_id 账户ID
     * @param account 返回的法人账户
     * @return 操作是否成功，即是否存在该账户
     */
    public boolean getCorporateAccountID(String securities_id, CorporateAccount account) {
        String sql = "SELECT * FROM corporate_account WHERE securities_id=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, securities_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                account.setSecurities_id(rs.getInt(1));
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
                account.setState(rs.getInt(12));
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取关联的资金账户ID
     * @param securities_id 证券账户ID
     * @return 返回关联的资金账号ID
     */
    public List<String> getSecuritiesFund(int securities_id) {
        String sql = "SELECT * from securities_fund WHERE securities_id=?";
        boolean result = false;
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, securities_id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                result = true;
                list.add(rs.getString(2));
                while (rs.next()) {
                    list.add(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
// 注释原因：需要修改的内容只有state，可以使用modifyPersonalState()和modifyCorporateState()进行修改，不需要下面两个函数。
//    /**
//     * 修改个人账户中除了securities_id、register_date和state之外的信息
//     * @param account
//     * @return 操作是否成功
//     */
//    public boolean modifyPersonalAccount(PersonalAccount account) {
//        String sql = "UPDATE personal_account SET name=?, gender=?, id_no=?, family_add=?, career=?, education=?, " +
//                "organization=?, phone_no=?, agent_id_no=? WHERE securities_id=?";
//        Object []args = {account.getName(), account.getGender(), account.getId_no(), account.getFamily_add(),
//                account.getCareer(), account.getEducation(), account.getOrganization(), account.getPhone_no(),
//                account.getAgent_id_no(), account.getSecurities_id()};
//        return executeUpdate(sql, args);
//    }
//
//    /**
//     * 修改法人账户中除了securities_id和state之外的信息
//     * @param account
//     * @return 操作是否成功
//     */
//    public boolean modifyCorporateAccount(CorporateAccount account) {
//        String sql = "UPDATE corporate_account SET register_no=?, business_license_no=?, legal_representative_id=?, " +
//                "legal_representative_name=?, legal_representative_phone_no=?, legal_representative_add=?, " +
//                "authorizer_name=?, authorizer_id=?, authorizer_phone_no=?, authorizer_add=? WHERE securities_id=?";
//        Object []args = {account.getRegister_no(), account.getBusiness_license_no(), account.getLegal_representative_id(),
//                account.getLegal_representative_name(), account.getLegal_representative_phone_no(),
//                account.getLegal_representative_add(), account.getAuthorizer_name(), account.getAuthorizer_id(),
//                account.getAuthorizer_phone_no(), account.getAuthorizer_add(), account.getSecurities_id()};
//        return executeUpdate(sql, args);
//    }

    /**
     * 修改个人账户状态
     * @param id_no 身份证号
     * @param state
     * @return 操作是否成功
     */
    public boolean modifyPersonalState(String id_no, int state) {
        String sql = "UPDATE personal_account SET state=? WHERE id_no=?";
        Object []args = {state, id_no};
        return executeUpdate(sql, args);
    }

    /**
     * 修改法人账户状态
     * @param register_no 注册号码
     * @param state
     * @return 操作是否成功
     */
    public boolean modifyCorporateState(String register_no, int state) {
        String sql = "UPDATE corporate_account SET state=? WHERE register_no=?";
        Object []args = {state, register_no};
        return executeUpdate(sql, args);
    }

    /**
     * 更新证券账户与资金账户的关联信息，使用newID代替所有的oldID
     * @param oldID
     * @param newID
     * @return 操作是否成功
     */
    public boolean modifySecuritiesFunds(int oldID, int newID) {
        String sql = "UPDATE securities_fund SET securities_id=? WHERE securities_id=?";
        Object []args = {newID, oldID};
        return executeUpdate(sql, args);
    }

    /**
     * 删除个人账户
     * @param id_no 身份证号
     * @return 操作是否成功
     */
    public boolean deletePersonalAccount(String id_no) {
        String sql = "DELETE FROM personal_account WHERE id_no=?";
        Object []args = {id_no};
        return executeUpdate(sql, args);
    }

    public boolean getStock(String id_no) {
        int securities_id = Integer.valueOf(id_no);
        String sql = "SELECT * FROM securities_stock WHERE securities_id=?";
        boolean result = false;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(sql);
            pStmt.setObject(1, securities_id);
            rs = pStmt.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    /**
     * 删除法人账户
     * @param register_no 注册号码
     * @return 操作是否成功
     */
    public boolean deleteCorporateAccount(String register_no) {
        String sql = "DELETE FROM corporate_account WHERE register_no=?";
        Object []args = {register_no};
        return executeUpdate(sql, args);
    }

    /**
     * 获取数据库连接
     * @return
     */
    private Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行查询操作
     * @param SQL
     * @param args
     * @return 查询结果
     */
    public List<Map<String, Object>> executeQuery(String SQL, Object []args) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(SQL);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pStmt.setObject(i + 1, args[i]);
                }
            }
            rs = pStmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colName = metaData.getColumnName(i + 1);
                    Object colValue = rs.getObject(colName);
                    if (colValue == null) {
                        colValue = "";
                    }
                    map.put(colName, colValue);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 执行增删改操作
     * @param SQL
     * @param args
     * @return 操作是否成功
     */
    public boolean executeUpdate(String SQL, Object []args) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        try {
            conn = getConn();
            pStmt = conn.prepareStatement(SQL);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pStmt.setObject(i + 1, args[i]);
                }
            }
            int result = pStmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStmt != null)
                    pStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}