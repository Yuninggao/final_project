$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("$"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

function addQuantity(obj){
    let index = $(".car_btn_2").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    let stock = parseInt($(".productStock").eq(index).val());
    if(quantity == stock){
        alert("Out of Stock！");
        return false;
    }
    quantity++;
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price;
    let id = parseInt($(".id").eq(index).val());
    $.ajax({
       url:"/cart/update/"+id+"/"+quantity+"/"+cost,
       type:"POST",
       success:function (data) {
            if(data == "success"){
                $(".qprice").eq(index).text('$'+cost);
                $(".car_ipt").eq(index).val(quantity);

                let array = $(".qprice");
                let totalCost = 0;
                for(let i = 0;i < array.length;i++){
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("$"+totalCost);
            }
       }
    });
}

function subQuantity(obj){
    let index = $(".car_btn_1").index(obj);
    let quantity = parseInt($(".car_ipt").eq(index).val());
    if(quantity == 1){
        alert("Select at least one item！");
        return false;
    }
    quantity--;
    let price = parseFloat($(".productPrice").eq(index).val());
    let cost = quantity * price
    let id = parseInt($(".id").eq(index).val());
    $.ajax({
        url:"/cart/update/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        success:function (data) {
            if(data == "success"){
                $(".qprice").eq(index).text('$'+cost);
                $(".car_ipt").eq(index).val(quantity);

                let array = $(".qprice");
                let totalCost = 0;
                for(let i = 0;i < array.length;i++){
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("$"+totalCost);
            }
        }
    });
}

function removeCart(obj){
    let index = $(".delete").index(obj);
    let id = parseInt($(".id").eq(index).val());
    if(confirm("Confirmation of deletion?")){
        window.location.href = "/cart/delete/"+id;
    }
}

function settlement2() {
    var totalCost = $("#totalprice").text();
    if(totalCost == "$0"){
        alert("Shopping cart is empty and cannot be checked out!");
        return false;
    }
    window.location.href="/cart/confirm";
}