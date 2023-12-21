package dao.custom.impl;

import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.utill.SQLUtil;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOid(),dto.getItemCode(),dto.getUnitPrice(),dto.getQty());
    }
}
