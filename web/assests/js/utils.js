$(document).ready(function () {
    $('.addProductBtn').click(function () {
        addProduct($(this));
    });
    $('.reduceProductBtn').click(function () {
        reduceProduct($(this));
    });
    $('.createOrderBtn').click(function () {
        createOrder($(this));
    });
    $('.savePartnerBtn').click(function () {
        savePartner($(this));
    });
    $('.addPartnerBtn').click(function () {
        addPartner($(this));
    });
    $('.editOrderBtn').click(function () {
        editOrder($(this));
    });
    $('.cancelBtn').click(function () {
        cancelOrder($(this));
    });
});

function cancelOrder() {
    // document.getElementById("editOrder").reset();
    $(this)
        .find("input,textarea,select")
        .val('')
        .end()
        .find("input[type=checkbox], input[type=radio]")
        .prop("checked", "")
        .end();
}

function addProduct(element) {
    var productId = $(element).attr('id');
    var json = JSON.stringify(productId);
    console.log(json);
    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=addProduct&productId=' + productId
    }).done(function (data) {
        $('#count' + productId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function createOrder() {

    $.ajax({
        type: 'get',
        url: contextUrl + '/frontController?command=createOrder'
    }).done(function (data) {
        //$('#count'+productId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function reduceProduct(element) {
    var productId = $(element).attr('id');
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController',
        data: {command: 'reduceProduct', id: productId}
    }).done(function (data) {
        $('#count' + productId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function savePartner(element) {

    var partnerJson = {
        tin: $('#mtin').val(),
        name: $('#mname').val(),
        fullname: $('#mfullname').val(),
        typePartner: $('select#selectTypesPartners option:selected').val()
    };
    // var partnerJSON = JSON.stringify({ partner: partnerJson });

    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=savePartner',
        contentType: "application/json",
        data: JSON.stringify(partnerJson)

    }).done(function (data) {
        console.log("true");
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function addPartner(element) {

    var partnerJson = {
        tin: $('#ntin').val(),
        name: $('#nname').val(),
        fullname: $('#nfullname').val(),
        typePartner: $('#ntypePartner').val()
    };

    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController?command=addPartner',
        contentType: "application/json",
        data: JSON.stringify(partnerJson)

    }).done(function (data) {
        console.log("true");
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

function editOrder(element) {

    var orderId = $(element).attr('editOrderId');
    $.ajax({
        type: 'post',
        url: contextUrl + '/frontController',
        data: {command: 'editOrder', orderId: orderId}
    }).done(function (data) {
        var trModal = '';
        var lastTd = '';
        lastTd = ' <td>\n' +
            '<a class="addrow" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>\n' +
            '<a class="editrow" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>\n' +
            '<a class="deleterow" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>\n' +
            '</td>';
        $.each(JSON.parse(data), function (i, item) {
            console.log(item);
            console.log(i);
                trModal += '<tr><td>' + item.id + '</td><td>' + item.productId + '</td><td>' + item.quantity +
                '</td><td>' + item.discountPercent + '</td>' + lastTd + '</tr>';
        });
        $('#modal_table tbody').append(trModal);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}







