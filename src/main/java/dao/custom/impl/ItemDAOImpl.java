package dao.custom.impl;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.utill.SQLUtil;
import dao.custom.ItemDAO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> list = new ArrayList<>();
        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("qtyOnHand"),
                    rst.getInt("unitPrice"));

            list.add(itemDTO);
        }
        return list;
    }

    @Override
    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());

    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }

    @Override
    public void delete(String code) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from Item where Code = ?",id);
        rst.next();
        return new ItemDTO(id +"",rst.getString("description"),rst.getBigDecimal("unitPrice"),rst.getInt("qtyOnHand"));
    }
}

