package dao.custom.impl;

import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.utill.SQLUtil;

import java.sql.SQLException;

public class OrderDetailDAO implements dao.custom.OrderDetailDAO {
    @Override
    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOid(),dto.getItemCode(),dto.getUnitPrice(),dto.getQty());
    }
}
