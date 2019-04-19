package web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import web.command.Controller;
import web.command.impl.*;

@Getter
@AllArgsConstructor
public enum ControllerType {
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("login.jsp", "Logout", "logout.title", new LogoutController()),
    EDIT_ORDER("", "editOrder", "", new EditOrderCustomerController()),
    //CREATE_ORDER_AJAX("", "", "", new CreateOrderController()),
    ADD_PRODUCTS_AJAX("", "addProduct", "", new AddToBasketController()),
    PRODUCTS("products/main.jsp", "Products", "products.title", new ProductController()),
    PARTNERS("partners/partner.jsp", "Partners", "partners.title", new PartnerController()),
    ORDERS_CUSTOMER("orders/ordersCustomer.jsp", "OrdersCustomer", "ordersCustomer.title", new OrderCustomerController()),
    ORDERS_SUPPLIER("orders/ordersSupplier.jsp", "OrdersSupplier", "ordersSupplier.title", new OrderSupplierController()),
    SAVE_PARTNER("partners/partner.jsp", "savePartner", "partners.title", new SavePartnerController()),
    ADD_PARTNER("partners/partner.jsp", "addPartner", "partners.title", new AddPartnerController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return PRODUCTS;
    }
}
