/**
 * Created by asus on 2017/5/23.
 */
let number = $('#num')[0];
function sub() {
    let goodsValue = parseInt(number.value);
    if (goodsValue > 0) {
        goodsValue -= 1;
    }
    else {
        goodsValue = 0;
    }
    number.value = goodsValue;
}
function add() {
    let goodsValue = parseInt(number.value);
    if (goodsValue || goodsValue === 0) {
        goodsValue += 1;
    }
    else {
        goodsValue = 0;
    }
    number.value = goodsValue;
}

// let commodityid = 100;
// $.ajax({
//     type: "POST",
//     url: 'http://demoncirno.cn/kys24/userController/login?userPhone=152010&userPassword=123456789' ,
//     data: commodityid,
//     // dataType: "jsonp",
//     // crossDomain: true,
//     success: function (result) {
//         if(result.info === 1){
//             $.ajax({
//                 url: 'http://demoncirno.cn/kys24/cart/show',
//                 type: 'get',
//                 data: '',
//                 crossDomain: true,
//                 success: function (msg) {
//                     console.log(msg);
//                 }
//             });
//         }
//         else{
//             alert('please sign in before!');
//         }
//     },
//     error: function () {
//         "use strict";
//
//     }
// });