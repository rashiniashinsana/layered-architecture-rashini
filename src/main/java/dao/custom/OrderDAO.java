package dao.custom;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<OrderDTO> {
    boolean checkOrderId(String orderId) throws SQLException, ClassNotFoundException;
    }

