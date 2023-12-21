package dao.custom.impl;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.utill.SQLUtil;
import dao.custom.CustomerDAO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO>  CustomerDTOS  = new ArrayList<>();

        while(rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));

            CustomerDTOS.add(customerDTO);
        }
        return CustomerDTOS;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name,address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(),dto.getAddress(),dto.getId());

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?" , id);
        if (rst.next()){
            return new CustomerDTO(rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
        }
        return null;
    }

}
