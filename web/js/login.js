function changeCode() {
    let codeImg=document.getElementById("VCode");
    codeImg.src="/CreateVerifyCodeImage?t="+Math.random();
}
///地址没变，但是后面跟的参数r的值变了，每次都有一个随机的数字作为t的值。
///这样做是为了避免浏览器缓存，因为每次的URL都不一致，所以浏览器会从新发请求。
///如果设置这个随机数参数，验证码将不会被点击然后修改，永远是第一次那个验证码图片
///因为地址不变，验证码图片已经被浏览器缓存了

