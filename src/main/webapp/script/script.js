/**
 * Created by asus on 2017/5/5.
 */
// var imgUrl = $('.detail_left')[0].getElementsByTagName('img')[0].src;
// var img1 = $('.contain')[0].getElementsByTagName('img')[0];
// var img2 = $('.contain')[0].getElementsByTagName('img')[1];
let price = $('.price')[0];
let intergral = $('.integral')[0];
let detail_left = $(".detail_left")[0];
let contain = $('.contain')[0];
let spec = $('.spec')[0];
// $(function () {
//         let commodityId = 59;
//         let url = 'http://duolaimon.cn/';
//         $.ajax({
//             url: url + 'shop/commodities/' + commodityId,
//             type: "GET",
//             // contentType: "application/json",
//             // data: "",
//             // crossDomain: true,
//             success: function (result) {
//                 console.log('success!');
//                 price.getElementsByTagName("b")[0].innerHTML = '￥' + result.commodityPrice;
//                 price.getElementsByTagName('i')[0].innerHTML = result.commodityLeavenum;
//                 intergral.getElementsByTagName('span')[0].innerHTML = result.commodityPrice / 10;
//                 detail_left.getElementsByTagName("img")[0].src = url + result.commodityPicture;
//                 detail_left.getElementsByTagName('a')[0].href = url + result.commodityPicture;
//                 spec.getElementsByTagName('a')[0].innerHTML = result.commodityStandard + 'kg';
//                 /*
//                  * sessionStorage 最近浏览
//                  **/
//
//                 let imgUrl = detail_left.getElementsByTagName('img')[0].src;
//                 let img1 = contain.getElementsByTagName('img')[0];
//                 let img2 = contain.getElementsByTagName('img')[1];
//                 if (sessionStorage.length) {
//                     sessionStorage[1] = sessionStorage[0];
//                 }
//                 sessionStorage[0] = imgUrl;
//                 img1.src = sessionStorage[0];
//                 img2.src = sessionStorage[1];
//
//
//             },
//             error: function () {
//                 console.log('ajax error');
//             },
//
//         });
//     }
// );

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




let commodityid = 100;
$.ajax({
    type: "POST",
    url: '/kys24/userController/login?userPhone=152010&userPassword=123456789' ,
    data: commodityid,
    // dataType: "jsonp",
    // crossDomain: true,
    success: function (result) {
        if(result.info === 1){
            console.log('success')
        }
        else{
            alert('please sign in before!');
        }
    },
    error: function () {
    }
});
$.ajax({
    type: 'get',
    url: '/kys24/cart/show',
    data: '',
    // dataType: 'jsonp',
    // crossDomain: true,
    success: function (msg) {
        console.log(msg);
    },
    error: function () {
    }
});
function addCar() {
    $.ajax({
        type: 'POST',
        url: '/kys24/cart/commodities/'+commodityid,
        data: "",
        // dataType: 'jsonp',
        //crossDomain: true,
        success: function (msg) {
            console.log(msg);
        },
        error: function () {
        }
    });
}