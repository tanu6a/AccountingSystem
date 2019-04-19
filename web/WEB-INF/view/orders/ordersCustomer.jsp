<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assests/css/style.css" type="text/css">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assests/js/utils.js" type="text/javascript">
        <jsp:text/>
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
            var actions = $("#modal_table tbody td:last-child").html();
            // Append table with add row form on add new button click
            $(".add-new").click(function(){
                $(this).attr("disabled", "disabled");
                var index = $("#modal_table tbody tr:last-child").index();
                var row = '<tr>' +
                    '<td>' + '</td>' +
                    '<td><input type="text" class="form-control" name="productName" id="productName"></td>' +
                    '<td><input type="text" class="form-control" name="quantity" id="quantity"></td>' +
                    '<td><input type="text" class="form-control" name="discount" id="discount"></td>' +
                    '<td>' + actions + '</td>' +
                    '</tr>';
                $("#modal_table").append(row);
                $("#modal_table tbody tr").eq(index + 1).find(".addrow, .editrow").toggle();
                $('[data-toggle="tooltip"]').tooltip();
            });
            // Add row on add button click
            $(document).on("click", ".addrow", function(){
                var empty = false;
                var input = $(this).parents("tr").find('input[type="text"]');
                input.each(function(){
                    if(!$(this).val()){
                        $(this).addClass("error");
                        empty = true;
                    } else{
                        $(this).removeClass("error");
                    }
                });
                $(this).parents("tr").find(".error").first().focus();
                if(!empty){
                    input.each(function(){
                        $(this).parent("td").html($(this).val());
                    });
                    $(this).parents("tr").find(".addrow, .editrow").toggle();
                    $(".add-new").removeAttr("disabled");
                }
            });
            // Edit row on edit button click
            $(document).on("click", ".editrow", function(){
                $(this).parents("tr").find("td:not(:last-child)").each(function(){
                    $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
                });
                $(this).parents("tr").find(".addrow, .editrow").toggle();
                $(".add-new").attr("disabled", "disabled");
            });
            // Delete row on delete button click
            $(document).on("click", ".deleterow", function(){
                $(this).parents("tr").remove();
                $(".add-new").removeAttr("disabled");
            });

            // Activate tooltip
            $('[data-toggle="tooltip"]').tooltip();

            // Select/Deselect checkboxes
            var checkbox = $('table tbody input[type="checkbox"]');
            $("#selectAll").click(function () {
                if (this.checked) {
                    checkbox.each(function () {
                        this.checked = true;
                    });
                } else {
                    checkbox.each(function () {
                        this.checked = false;
                    });
                }
            });
            checkbox.click(function () {
                if (!this.checked) {
                    $("#selectAll").prop("checked", false);
                }
            });

            $('#theModal').on('show.bs.modal', function (e) {
                var button = $(e.relatedTarget);
                var modal = $(this);
                modal.find('iframe').attr('src', button.data("remote"));
            });

            document.getElementById('dateOrder').value = new Date().toISOString().substring(0, 10);


            $('#modal_close').on('click', function () {
                $('#editOrder').trigger("reset");
            })
        });
    </script>
</head>

<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>Заказы покупателей</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addOrderModal" class="btn btn-success" data-toggle="modal"><i
                            class="material-icons">&#xE147;</i>
                        <span>Add New Order</span></a>
                    <a href="#deleteOrderModal" class="btn btn-danger" data-toggle="modal"><i
                            class="material-icons">&#xE15C;</i>
                        <span>Delete</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>
							<span class="custom-checkbox">
								<input type="checkbox" id="selectAll">
								<label for="selectAll"></label>
							</span>
                </th>
                <th>№</th>
                <th>Order Customer Id</th>
                <th>User id</th>
                <th>Sum</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderCustomer" items="${ordersCustomer}" varStatus="status">
                <tr class="info">
                    <td>
							<span class="custom-checkbox">
								<input type="checkbox" id="checkbox1" name="options[]" value="1">
								<label for="checkbox1"></label>
							</span>
                    </td>
                    <td>${status.index + 1}</td>
                    <td class="orderCustomerId">${orderCustomer.id}</td>
                    <td>${orderCustomer.userId}</td>
                    <td>${orderCustomer.total}</td>
                    <td>
                        <a href="#editOrderModal" editOrderId="${orderCustomer.id}" class="edit editOrderBtn" data-toggle="modal"><i class="material-icons"
                                                                                                                             data-toggle="tooltip"
                                                                                                                             title="Edit">&#xE254;</i></a>
                        <a href="#deleteOrderModal" class="delete" data-toggle="modal"><i class="material-icons"
                                                                                          data-toggle="tooltip"
                                                                                          title="Delete">&#xE872;</i></a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- ADD Modal HTML -->
    <div id="addOrderModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Add Order</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Date</label>
                            <input type="date" class="form-control" id="date" required>
                        </div>
                        <div class="form-group">
                            <label>status</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label>Address</label>--%>
                        <%--<textarea class="form-control" required></textarea>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label>Phone</label>--%>
                        <%--<input type="text" class="form-control" required>--%>
                        <%--</div>--%>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->
    <div id="editOrderModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="editOrder">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Order</h4>
                        <button type="button" class="close" id="modal_close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Date</label>
                            <input type="date" class="form-control" id="dateOrder" required>
                        </div>
                        <div class="form-group">
                            <label>NUMBER</label>
                            <input type="text" name="id" class="form-control" id="mid" required>
                        </div>
                        <div class="form-group">
                            <label>Partner</label>
                            <select name="partners" required>
                                <option value="" hidden>Select partner</option>
                                <c:forEach var="partner" items="${partners}" varStatus="status">
                                    <option value="${partner.name}">${partner.name}</option>
                                </c:forEach>
                            </select>
                            <%--<input type="text" class="form-control" required>--%>
                        </div>
                        <div class="form-group">
                            <label>Status</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label>Partner</label>--%>
                        <%--<input type="text" class="form-control" required>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label>Address</label>--%>
                        <%--<textarea class="form-control" required></textarea>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                        <%--<label>Phone</label>--%>
                        <%--<input type="text" class="form-control" required>--%>
                        <%--</div>--%>

                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2><b>Товары</b></h2>
                                </div>
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Add New</button>
                                </div>
                                <%--<div class="col-sm-6">--%>
                                <%--<a href="#addItemModal" class="btn btn-success" data-toggle="modal"><i--%>
                                <%--class="material-icons">&#xE147;</i>--%>
                                <%--<span>Add Item</span></a>--%>
                                <%--<a href="#deleteItemModal" class="btn btn-danger" data-toggle="modal"><i--%>
                                <%--class="material-icons">&#xE15C;</i>--%>
                                <%--<span>Delete</span></a>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <table class="table table-striped table-hover" id="modal_table">
                            <thead>
                            <tr>
                                <%--<th>--%>
                                <%--<span class="custom-checkbox">--%>
                                <%--<input type="checkbox" id="selectAllItem">--%>
                                <%--<label for="selectAllItem"></label>--%>
                                <%--</span>--%>
                                <%--</th>--%>
                                <th>№</th>
                                <th>Product name</th>
                                <th>Quantity</th>
                                <th>Discount</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--<c:forEach var="item" items="${items}" varStatus="status">--%>
                            <%--<tr class="info">--%>
                            <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<span class="custom-checkbox">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<input type="checkbox" id="checkboxForItem" name="options[]" value="1">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label for="checkboxForItem"></label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                            <%--<td>${item.id}</td>--%>
                            <%--<td>${item.productId}</td>--%>
                            <%--<td>${item.quantity}</td>--%>
                            <%--<td>${item.discountPercent}</td>--%>
                            <%--<td>--%>
                            <%--<a class="addrow" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>--%>
                            <%--<a class="editrow" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>--%>
                            <%--<a class="deleterow" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>--%>
                            <%--</td>--%>
                            <%--</tr>--%>
                            <%--</c:forEach>--%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default cancelBtn" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deleteOrderModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Delete Order</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete these Records?</p>
                        <p class="text-warning">
                            <small>This action cannot be undone.</small>
                        </p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>