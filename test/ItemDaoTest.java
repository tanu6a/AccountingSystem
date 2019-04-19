//import dao.ItemOrderCustomerDao;
//import dao.impl.ItemOrderCustomerDaoImpl;
//import db.ConnectionManager;
//import entities.ItemOrderCustomer;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//
//public class ItemDaoTest {
//    private ItemOrderCustomerDao itemOrderCustomerDao = ItemOrderCustomerDaoImpl.getInstance();
//
//    public void initData() {}
//
//    @Test
//    public void fullTest() throws SQLException {
//        Connection connection = ConnectionManager.getConnection();
//        connection.setAutoCommit(true);
//        int beforeSave = itemOrderCustomerDao.getAll().size();
//        ItemOrderCustomer newItem = ItemOrderCustomerDao.save(new ItemOrderCustomer("Makita", "HOF 12-23", 388.50));
//        int afterSave = ItemOrderCustomerDao.getAll().size();
//        Assert.assertNotSame(beforeSave, afterSave);
//
////        connection.rollback();
//
//        newItem.setModel("HF 12-23");
//        ItemOrderCustomerDao.update(newItem);
//
//        ItemOrderCustomer updatedItem = ItemOrderCustomerDao.get(newItem.getId());
//        Assert.assertEquals("Метод update не корректен","HF 12-23", updatedItem.getModel());
//
//        ItemOrderCustomer item2 = ItemOrderCustomerDao.getItemByModelAndSupplier("HF 12-23", "Makita");
//        Assert.assertNotNull(item2);
//
//        itemOrderCustomerDao.delete(newItem.getId());
//
//        afterSave = itemOrderCustomerDao.getAll().size();
//        Assert.assertEquals(beforeSave, afterSave);
//
//    }
//
//
//}
