<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Partners</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assests/css/style.css" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--<div style="font-size: large">--%>
<%--<c:if test="${not empty message}">INFO : ${message}</c:if> <br/>--%>
<%--</div>--%>
<head>
    <script type="text/javascript">
        $(document).ready(function () {
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

            // $("#edit_number_link").each(function() {
            //     $(this).click(function(){
            //         var id = $(this).closest('tr').attr('data-id');
            //         $("#edit_number").data('num_id', id).dialog('open');
            //         return false;
            //     });
            // });

            $(".edit").click(function(){
                $("#mtin").val($(this).closest('tr').children('.tin').text());
                $("#mname").val($(this).closest('tr').children('.name').text());
                $("#mfullname").val($(this).closest('tr').children('.fullname').text());

                // get the OPTION we want selected
                var selectType = $(this).closest('tr').children('.typePartner').text();
                //$('#selectTypesPartners option[value=selectType]').attr('selected','selected');
                //var optionValue = $('select#selectTypesPartners').children('option[value='+ selectType +']');
                // // and now set the option we want selected
                //optionValue.attr('selected', true);​​
                $('#selectTypesPartners').val(selectType).find("option[value=" + selectType+"]").attr('selected', true);

            });

        });
    </script>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>Партнеры</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addPartnerModal" class="btn btn-success" data-toggle="modal"><i
                            class="material-icons">&#xE147;</i>
                        <span>Add New Partner</span></a>
                    <a href="#deletePartnerModal" class="btn btn-danger" data-toggle="modal"><i
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
                <th>Number</th>
                <th>Name</th>
                <th>Fullname</th>
                <th>TypePartner</th>
                <th>Actions</th>
            </tr>
            </thead>
            <%--<script>--%>
            <%--function callAlert(partnerTin) {--%>
            <%--alert(partnerTin);--%>
            <%--}--%>
            <%--</script>--%>

            <tbody>
            <c:forEach var="partner" items="${partners}" varStatus="status">
                <tr class="info">
                    <td>
							<span class="custom-checkbox">
								<input type="checkbox" id="checkbox1" name="options[]" value="1">
								<label for="checkbox1"></label>
							</span>
                    </td>
                    <td class = "tin">${partner.tin}</td>
                    <td class = "name">${partner.name}</td>
                    <td class = "fullname">${partner.fullname}</td>
                    <td class = "typePartner">${partner.typePartner}</td>
                    <td>
                        <a href="#editPartnerModal" class="edit" data-toggle="modal"><i class="material-icons"
                                                                                        data-toggle="tooltip"
                                                                                        title="Edit">&#xE254;</i></a>
                        <a href="#deletePartnerModal" class="delete" data-toggle="modal"><i class="material-icons"
                                                                                            data-toggle="tooltip"
                                                                                            title="Delete">&#xE872;</i></a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- ADD Modal HTML -->
    <div id="addPartnerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Add Partner</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>TIN</label>
                            <input type="text" class="form-control" id="ntin" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" id="nname" required>
                        </div>
                        <div class="form-group">
                            <label>Fullname</label>
                            <input type="text" class="form-control" id="nfullname" required>
                        </div>
                        <div class="form-group">
                            <label>Type partner</label>
                            <select name="typesPartners" required>
                                <option value="" hidden>Select typepartner</option>
                                <c:forEach var="typePartner" items="${typesPartners}" varStatus="status">
                                    <option value="${typePartner}" id="ntypePartner">${typePartner}</option>
                                </c:forEach>
                            </select>
                            <%--<input type="text" class="form-control" required>--%>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success addPartnerBtn" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->
    <div id="editPartnerModal" class="modal fade">
        <div class="modal-dialog" style="width: 600px; height: 450px ">
            <div class="modal-content">
                <form action="frontController?command=SAVE_PARTNER" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Partner</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>TIN</label>
                            <input type="text" name="tin" class="form-control" id="mtin" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control" id="mname" required>
                        </div>
                        <div class="form-group">
                            <label>Fullname</label>
                            <input type="text" name="fullName" class="form-control" id="mfullname" required>
                        </div>
                        <div class="form-group">
                            <label>Type partner</label>
                            <select name="typesPartners" id="selectTypesPartners" required>
                                <%--<option value=""></option>--%>
                                <c:forEach var="typePartner" items="${typesPartners}" varStatus="status">
                                    <option value="${typePartner}">${typePartner}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-info savePartnerBtn" value="Save">
                        </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deletePartnerModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Delete Partner</h4>
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



