<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-12">
                        <h2><b>Товары</b></h2>
                    </div>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th class="col-md-4">Supplier</th>
                    <div class="col-md-8">
                        <th class="col-md-4">Model</th>
                        <th class="col-md-1">Quantity</th>
                        <th class="col-md-1">Price</th>
                        <th class="col-md-1"></th>
                        <th class="col-md-1"></th>
                    </div>
                </tr>
                </thead>
                <script>
                    function callAlert(productId) {
                        alert(productId);
                    }
                </script>
                <tbody>
                <c:forEach var="product" items="${products}" varStatus="status">
                    <tr class="info">
                        <td class="col-md-4">${product.supplier}</td>
                        <div class="col-md-8">
                            <td class="col-md-4">${product.model}</td>
                            <td id="count${product.id}" class="col-md-1">0</td>
                            <td class="col-md-1">${product.price}</td>
                            <td class="col-md-1"><input id="${product.id}" class="btn btn-success addProductBtn"
                                                        type="button" title="Добавить в корзину" value="+"/></td>
                            <td class="col-md-1"><input id="${product.id}" class="btn btn-danger reduceProductBtn"
                                                        type="button" title="Удалить 1 из корзину" value="-"/></td>
                        </div>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



